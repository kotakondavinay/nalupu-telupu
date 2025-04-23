import requests
from bs4 import BeautifulSoup
from typing import List, Dict
import re
from collections import Counter
from urllib.parse import urlparse
import math

class WebInsightAgent:
    def __init__(self):
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
        }
        self.category_keywords = {
            'news': ['news', 'article', 'breaking', 'headline', 'reporter', 'journalist'],
            'blog': ['blog', 'post', 'author', 'comment', 'read more'],
            'ecommerce': ['shop', 'store', 'product', 'price', 'cart', 'buy', 'add to cart'],
            'documentation': ['docs', 'documentation', 'api', 'reference', 'guide', 'tutorial'],
            'forum': ['forum', 'discussion', 'thread', 'topic', 'reply', 'post'],
            'portfolio': ['portfolio', 'work', 'projects', 'case study', 'client'],
            'educational': ['course', 'learn', 'education', 'tutorial', 'lesson', 'study']
        }

    def fetch_content(self, url: str) -> str:
        """Fetch content from a given URL."""
        try:
            response = requests.get(url, headers=self.headers)
            response.raise_for_status()
            return response.text
        except requests.RequestException as e:
            print(f"Error fetching content: {e}")
            return ""

    def extract_text(self, html_content: str) -> str:
        """Extract clean text from HTML content."""
        soup = BeautifulSoup(html_content, 'html.parser')
        
        # Remove script and style elements
        for script in soup(["script", "style"]):
            script.decompose()
            
        # Get text
        text = soup.get_text()
        
        # Break into lines and remove leading and trailing space
        lines = (line.strip() for line in text.splitlines())
        # Break multi-headlines into a line each
        chunks = (phrase.strip() for line in lines for phrase in line.split("  "))
        # Drop blank lines
        text = ' '.join(chunk for chunk in chunks if chunk)
        
        return text

    def analyze_content(self, text: str) -> Dict:
        """Analyze the text content and provide insights."""
        # Split text into words
        words = re.findall(r'\w+', text.lower())
        
        # Count word frequency
        word_freq = Counter(words)
        
        # Get most common words (excluding common stop words)
        stop_words = {'the', 'and', 'a', 'an', 'in', 'on', 'at', 'to', 'for', 'of', 'with', 'by', 'is', 'are', 'was', 'were', 'be', 'been', 'being'}
        common_words = {word: count for word, count in word_freq.most_common(20) if word not in stop_words}
        
        # Calculate basic statistics
        total_words = len(words)
        unique_words = len(set(words))
        
        return {
            'total_words': total_words,
            'unique_words': unique_words,
            'most_common_words': common_words,
            'word_diversity': unique_words / total_words if total_words > 0 else 0
        }

    def detect_page_category(self, url: str, text: str) -> Dict:
        """Detect the category of the webpage based on content and URL."""
        # Analyze URL structure
        parsed_url = urlparse(url)
        path_segments = parsed_url.path.lower().split('/')
        
        # Initialize category scores
        category_scores = {category: 0 for category in self.category_keywords.keys()}
        
        # Check URL path segments
        for segment in path_segments:
            for category, keywords in self.category_keywords.items():
                if any(keyword in segment for keyword in keywords):
                    category_scores[category] += 2
        
        # Check content for category keywords
        text_lower = text.lower()
        for category, keywords in self.category_keywords.items():
            for keyword in keywords:
                if keyword in text_lower:
                    category_scores[category] += 1
        
        # Get top categories
        top_categories = sorted(category_scores.items(), key=lambda x: x[1], reverse=True)
        top_category = top_categories[0][0] if top_categories[0][1] > 0 else 'unknown'
        
        return {
            'primary_category': top_category,
            'category_scores': category_scores,
            'confidence': top_categories[0][1] / (sum(score for _, score in top_categories) or 1)
        }

    def get_insights(self, url: str) -> Dict:
        """Get insights from a given URL."""
        html_content = self.fetch_content(url)
        if not html_content:
            return {"error": "Failed to fetch content"}
            
        text = self.extract_text(html_content)
        content_insights = self.analyze_content(text)
        category_insights = self.detect_page_category(url, text)
        
        return {
            **content_insights,
            **category_insights
        }

def main():
    # Example usage
    agent = WebInsightAgent()
    url = input("Enter the URL to analyze: ")
    insights = agent.get_insights(url)
    
    print("\nContent Analysis Insights:")
    print(f"Total words: {insights['total_words']}")
    print(f"Unique words: {insights['unique_words']}")
    print(f"Word diversity: {insights['word_diversity']:.2%}")
    print("\nPage Category:")
    print(f"Primary Category: {insights['primary_category'].capitalize()}")
    print(f"Confidence: {insights['confidence']:.2%}")
    print("\nCategory Scores:")
    for category, score in insights['category_scores'].items():
        if score > 0:
            print(f"{category.capitalize()}: {score}")
    print("\nMost common words:")
    for word, count in insights['most_common_words'].items():
        print(f"{word}: {count}")

if __name__ == "__main__":
    main() 