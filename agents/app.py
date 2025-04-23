from flask import Flask, render_template, request, jsonify, flash, redirect, url_for
from web_insight_agent_categories import WebInsightAgentCategories

app = Flask(__name__)
app.secret_key = 'your-secret-key-here'  # Required for flash messages
agent = WebInsightAgentCategories()

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/analyze', methods=['POST'])
def analyze():
    try:
        url = request.form.get('url')
        if not url:
            flash('URL is required', 'error')
            return redirect(url_for('index'))
        
        # Validate URL format
        if not url.startswith(('http://', 'https://')):
            url = 'https://' + url
        
        insights = agent.get_insights(url)
        if not insights:
            flash('Failed to analyze URL. Please check if the URL is valid and accessible.', 'error')
            return redirect(url_for('index'))
        
        if 'error' in insights:
            flash(insights['error'], 'error')
            return redirect(url_for('index'))
        
        return render_template('results.html', insights=insights)
    except Exception as e:
        flash(f'An error occurred: {str(e)}', 'error')
        return redirect(url_for('index'))

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=3000) 