import os
import string
import random
import sqlite3
from flask import Flask, request, redirect, render_template, jsonify, url_for, g

app = Flask(__name__)
app.config['DATABASE'] = os.path.join(os.path.dirname(__file__), 'shorturl.db')
app.secret_key = os.urandom(24)

# Database helpers
def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(app.config['DATABASE'])
    return db

def init_db():
    with app.app_context():
        db = get_db()
        db.execute('''CREATE TABLE IF NOT EXISTS urls (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            shortcode TEXT UNIQUE NOT NULL,
            long_url TEXT NOT NULL
        )''')
        db.commit()

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

def generate_shortcode(length=6):
    chars = string.ascii_letters + string.digits
    return ''.join(random.choices(chars, k=length))

def get_unique_shortcode():
    db = get_db()
    while True:
        code = generate_shortcode()
        cur = db.execute('SELECT 1 FROM urls WHERE shortcode = ?', (code,))
        if not cur.fetchone():
            return code

@app.route('/', methods=['GET', 'POST'])
def index():
    short_url = None
    if request.method == 'POST':
        long_url = request.form.get('long_url')
        if long_url:
            db = get_db()
            code = get_unique_shortcode()
            db.execute('INSERT INTO urls (shortcode, long_url) VALUES (?, ?)', (code, long_url))
            db.commit()
            short_url = url_for('redirect_shorturl', shortcode=code, _external=True)
    return render_template('shorturl_index.html', short_url=short_url)

@app.route('/api/shorten', methods=['POST'])
def api_shorten():
    data = request.get_json()
    long_url = data.get('long_url')
    if not long_url:
        return jsonify({'error': 'Missing long_url'}), 400
    db = get_db()
    code = get_unique_shortcode()
    db.execute('INSERT INTO urls (shortcode, long_url) VALUES (?, ?)', (code, long_url))
    db.commit()
    short_url = url_for('redirect_shorturl', shortcode=code, _external=True)
    return jsonify({'short_url': short_url})

@app.route('/<shortcode>')
def redirect_shorturl(shortcode):
    db = get_db()
    cur = db.execute('SELECT long_url FROM urls WHERE shortcode = ?', (shortcode,))
    row = cur.fetchone()
    if row:
        return redirect(row[0])
    return 'URL not found', 404

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5002) 