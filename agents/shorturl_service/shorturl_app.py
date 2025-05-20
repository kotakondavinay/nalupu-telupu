import os
import string
import random
import sqlite3
from flask import Flask, request, redirect, render_template, jsonify, url_for

app = Flask(__name__)
app.secret_key = os.urandom(24)

# Distributed DB setup
DB_NAMES = [
    os.path.join(os.path.dirname(__file__), f'shorturl{i}.db') for i in range(1, 4)
]

def init_db():
    for db_path in DB_NAMES:
        conn = sqlite3.connect(db_path)
        conn.execute('''CREATE TABLE IF NOT EXISTS urls (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            shortcode TEXT UNIQUE NOT NULL,
            long_url TEXT NOT NULL
        )''')
        conn.commit()
        conn.close()

def db_for_shortcode(shortcode):
    idx = hash(shortcode) % 3
    return DB_NAMES[idx]

def generate_shortcode(length=6):
    chars = string.ascii_letters + string.digits
    return ''.join(random.choices(chars, k=length))

def get_unique_shortcode():
    # Try to generate a unique shortcode across all DBs
    while True:
        code = generate_shortcode()
        db_path = db_for_shortcode(code)
        conn = sqlite3.connect(db_path)
        cur = conn.execute('SELECT 1 FROM urls WHERE shortcode = ?', (code,))
        exists = cur.fetchone()
        conn.close()
        if not exists:
            return code

@app.route('/', methods=['GET', 'POST'])
def index():
    short_url = None
    if request.method == 'POST':
        long_url = request.form.get('long_url')
        if long_url:
            code = get_unique_shortcode()
            db_path = db_for_shortcode(code)
            conn = sqlite3.connect(db_path)
            conn.execute('INSERT INTO urls (shortcode, long_url) VALUES (?, ?)', (code, long_url))
            conn.commit()
            conn.close()
            short_url = url_for('redirect_shorturl', shortcode=code, _external=True)
    return render_template('shorturl_index.html', short_url=short_url)

@app.route('/api/shorten', methods=['POST'])
def api_shorten():
    data = request.get_json()
    long_url = data.get('long_url')
    if not long_url:
        return jsonify({'error': 'Missing long_url'}), 400
    code = get_unique_shortcode()
    db_path = db_for_shortcode(code)
    conn = sqlite3.connect(db_path)
    conn.execute('INSERT INTO urls (shortcode, long_url) VALUES (?, ?)', (code, long_url))
    conn.commit()
    conn.close()
    short_url = url_for('redirect_shorturl', shortcode=code, _external=True)
    return jsonify({'short_url': short_url})

@app.route('/<shortcode>')
def redirect_shorturl(shortcode):
    db_path = db_for_shortcode(shortcode)
    conn = sqlite3.connect(db_path)
    cur = conn.execute('SELECT long_url FROM urls WHERE shortcode = ?', (shortcode,))
    row = cur.fetchone()
    conn.close()
    if row:
        return redirect(row[0])
    return 'URL not found', 404

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5002) 