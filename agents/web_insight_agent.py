import requests
from bs4 import BeautifulSoup
from typing import List, Dict
import re
from collections import Counter

class WebInsightAgent:
    def __init__(self):
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
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

    def get_insights(self, url: str) -> Dict:
        """Get insights from a given URL."""
        html_content = self.fetch_content(url)
        if not html_content:
            return {"error": "Failed to fetch content"}
            
        text = self.extract_text(html_content)
        insights = self.analyze_content(text)
        
        return insights

def main():
    # Example usage
    agent = WebInsightAgent()
    url = input("Enter the URL to analyze: ")
    insights = agent.get_insights(url)
    
    print("\nContent Analysis Insights:")
    print(f"Total words: {insights['total_words']}")
    print(f"Unique words: {insights['unique_words']}")
    print(f"Word diversity: {insights['word_diversity']:.2%}")
    print("\nMost common words:")
    for word, count in insights['most_common_words'].items():
        print(f"{word}: {count}")

if __name__ == "__main__":
    main() 