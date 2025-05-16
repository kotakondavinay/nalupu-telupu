from flask import Flask, render_template, request, redirect, url_for, flash
from datetime import datetime
import sqlite3
import os

app = Flask(__name__)
app.secret_key = os.urandom(24)

def init_db():
    conn = sqlite3.connect('diary.db')
    c = conn.cursor()
    
    # First, check if the entries table exists
    c.execute('''SELECT name FROM sqlite_master WHERE type='table' AND name='entries' ''')
    table_exists = c.fetchone() is not None
    
    if table_exists:
        # Check if entry_date column exists
        c.execute('PRAGMA table_info(entries)')
        columns = [column[1] for column in c.fetchall()]
        
        if 'entry_date' not in columns:
            # Add entry_date column and set it to created_at for existing entries
            c.execute('ALTER TABLE entries ADD COLUMN entry_date DATE')
            c.execute('UPDATE entries SET entry_date = date(created_at)')
    else:
        # Create new table with all columns
        c.execute('''CREATE TABLE entries
                     (id INTEGER PRIMARY KEY AUTOINCREMENT,
                      title TEXT NOT NULL,
                      content TEXT NOT NULL,
                      mood TEXT,
                      entry_date DATE NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)''')
    
    conn.commit()
    conn.close()

@app.route('/')
def index():
    conn = sqlite3.connect('diary.db')
    c = conn.cursor()
    c.execute('SELECT * FROM entries ORDER BY entry_date DESC, created_at DESC')
    entries = c.fetchall()
    conn.close()
    return render_template('index.html', entries=entries)

@app.route('/add', methods=['POST'])
def add_entry():
    title = request.form.get('title')
    content = request.form.get('content')
    mood = request.form.get('mood')
    entry_date = request.form.get('entry_date')
    
    if not title or not content or not entry_date:
        flash('Title, content, and date are required!', 'error')
        return redirect(url_for('index'))
    
    try:
        # Validate date format
        datetime.strptime(entry_date, '%Y-%m-%d')
    except ValueError:
        flash('Invalid date format! Please use YYYY-MM-DD', 'error')
        return redirect(url_for('index'))
    
    conn = sqlite3.connect('diary.db')
    c = conn.cursor()
    c.execute('INSERT INTO entries (title, content, mood, entry_date) VALUES (?, ?, ?, ?)',
              (title, content, mood, entry_date))
    conn.commit()
    conn.close()
    
    flash('Entry added successfully!', 'success')
    return redirect(url_for('index'))

@app.route('/delete/<int:id>')
def delete_entry(id):
    conn = sqlite3.connect('diary.db')
    c = conn.cursor()
    c.execute('DELETE FROM entries WHERE id = ?', (id,))
    conn.commit()
    conn.close()
    
    flash('Entry deleted successfully!', 'success')
    return redirect(url_for('index'))

@app.route('/edit/<int:id>', methods=['GET', 'POST'])
def edit_entry(id):
    conn = sqlite3.connect('diary.db')
    c = conn.cursor()
    
    if request.method == 'POST':
        title = request.form.get('title')
        content = request.form.get('content')
        mood = request.form.get('mood')
        entry_date = request.form.get('entry_date')
        
        if not title or not content or not entry_date:
            flash('Title, content, and date are required!', 'error')
            return redirect(url_for('edit_entry', id=id))
        
        try:
            # Validate date format
            datetime.strptime(entry_date, '%Y-%m-%d')
        except ValueError:
            flash('Invalid date format! Please use YYYY-MM-DD', 'error')
            return redirect(url_for('edit_entry', id=id))
        
        c.execute('''UPDATE entries 
                    SET title = ?, content = ?, mood = ?, entry_date = ?
                    WHERE id = ?''',
                 (title, content, mood, entry_date, id))
        conn.commit()
        flash('Entry updated successfully!', 'success')
        return redirect(url_for('index'))
    
    # GET request - fetch entry for editing
    c.execute('SELECT * FROM entries WHERE id = ?', (id,))
    entry = c.fetchone()
    conn.close()
    
    if entry is None:
        flash('Entry not found!', 'error')
        return redirect(url_for('index'))
    
    return render_template('edit.html', entry=entry)

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5001)  # Using port 5001 to avoid conflict with existing app.py 