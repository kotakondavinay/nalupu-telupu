from flask import Flask, render_template, request, jsonify
import re

app = Flask(__name__)
app.secret_key = 'indic-language-app-secret-key'

class IndicLanguageProcessor:
    def __init__(self):
        # Define Unicode ranges for different Indic languages
        self.language_ranges = {
            'telugu': (0x0C00, 0x0C7F),      # Telugu Unicode range
            'kannada': (0x0C80, 0x0CFF),     # Kannada Unicode range  
            'sanskrit': (0x0900, 0x097F),    # Devanagari Unicode range (includes Sanskrit)
        }
        
        # Language names in their respective scripts
        self.language_names = {
            'telugu': 'తెలుగు',
            'kannada': 'ಕನ್ನಡ', 
            'sanskrit': 'संस्कृतम्'
        }
    
    def detect_language(self, text):
        """Detect which Indic language the text belongs to"""
        # Remove whitespace and punctuation for analysis
        clean_text = re.sub(r'[\s\W]', '', text)
        
        if not clean_text:
            return 'unknown'
        
        # Count characters in each language range
        language_counts = {}
        for lang, (start, end) in self.language_ranges.items():
            count = sum(1 for char in clean_text if start <= ord(char) <= end)
            language_counts[lang] = count
        
        # Return the language with the highest character count
        if max(language_counts.values()) > 0:
            return max(language_counts, key=language_counts.get)
        return 'unknown'
    
    def process_text(self, text):
        """Process the input text and return analysis results"""
        if not text.strip():
            return {
                'error': 'Please enter some text to process'
            }
        
        detected_language = self.detect_language(text)
        
        # Basic text analysis
        word_count = len(text.split())
        char_count = len(text)
        char_count_no_spaces = len(text.replace(' ', ''))
        
        # Language-specific processing
        language_info = {
            'detected': detected_language,
            'name_in_script': self.language_names.get(detected_language, 'Unknown'),
            'is_indic': detected_language in self.language_ranges
        }
        
        # Generate some basic insights
        insights = []
        if language_info['is_indic']:
            insights.append(f"Detected language: {language_info['name_in_script']} ({detected_language})")
            insights.append(f"Text contains {char_count} characters")
            insights.append(f"Text contains {word_count} words")
            
            # Add language-specific insights
            if detected_language == 'telugu':
                insights.append("Telugu text detected - one of the classical languages of India")
            elif detected_language == 'kannada':
                insights.append("Kannada text detected - official language of Karnataka")
            elif detected_language == 'sanskrit':
                insights.append("Sanskrit text detected - ancient language of India")
        else:
            insights.append("No Indic language detected in the text")
            insights.append("Supported languages: Telugu, Kannada, Sanskrit")
        
        return {
            'original_text': text,
            'language_info': language_info,
            'statistics': {
                'word_count': word_count,
                'char_count': char_count,
                'char_count_no_spaces': char_count_no_spaces
            },
            'insights': insights,
            'processed_text': text  # For now, just return the original text
        }

# Initialize the processor
processor = IndicLanguageProcessor()

@app.route('/')
def index():
    return render_template('indic_language.html')

@app.route('/process', methods=['POST'])
def process():
    try:
        text = request.form.get('text', '').strip()
        
        if not text:
            return jsonify({'error': 'Please enter some text to process'})
        
        result = processor.process_text(text)
        return jsonify(result)
        
    except Exception as e:
        return jsonify({'error': f'An error occurred: {str(e)}'})

@app.route('/api/detect-language', methods=['POST'])
def detect_language():
    """API endpoint to detect language of input text"""
    try:
        data = request.get_json()
        text = data.get('text', '').strip()
        
        if not text:
            return jsonify({'error': 'Please provide text to analyze'})
        
        detected_language = processor.detect_language(text)
        language_name = processor.language_names.get(detected_language, 'Unknown')
        
        return jsonify({
            'detected_language': detected_language,
            'language_name': language_name,
            'is_indic': detected_language in processor.language_ranges
        })
        
    except Exception as e:
        return jsonify({'error': f'An error occurred: {str(e)}'})

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=5000)
