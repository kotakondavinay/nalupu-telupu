# Indic Language Processor

A web application for processing and analyzing text in Indic languages: Telugu, Kannada, and Sanskrit.

## Features

- **Language Detection**: Automatically detects Telugu, Kannada, or Sanskrit text
- **Text Analysis**: Provides word count, character count, and other statistics
- **Modern UI**: Beautiful, responsive web interface
- **Sample Texts**: Built-in sample texts for each supported language
- **Real-time Processing**: Instant analysis of input text

## Supported Languages

1. **Telugu (తెలుగు)** - Classical language of Andhra Pradesh and Telangana
2. **Kannada (ಕನ್ನಡ)** - Official language of Karnataka
3. **Sanskrit (संस्कृतम्)** - Ancient language of India

## Installation

1. Install required dependencies:
```bash
pip install -r indic_requirements.txt
```

2. Run the application:
```bash
python indic_language_app.py
```

3. Open your browser and navigate to `http://localhost:5000`

## Usage

1. Enter text in any of the supported Indic languages
2. Click "Process Text" to analyze the input
3. View the results including:
   - Detected language
   - Text statistics
   - Analysis insights
   - Processed output

## API Endpoints

- `POST /process` - Process text and return analysis results
- `POST /api/detect-language` - Detect language of input text

## Sample Texts

The application includes sample texts in all three languages:
- Telugu: Sample paragraph about Telugu language
- Kannada: Sample paragraph about Kannada language  
- Sanskrit: Sample paragraph about Sanskrit language

## Technical Details

- Built with Flask (Python web framework)
- Uses Unicode ranges for language detection
- Responsive design with modern CSS
- JavaScript for interactive features
- RESTful API for text processing
