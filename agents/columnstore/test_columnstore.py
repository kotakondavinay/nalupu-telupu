import os
import shutil
import tempfile
import pytest
from columnstore.engine import ColumnStoreEngine
from columnstore.storage.csv_storage import CSVStorage

def make_engine():
    temp_dir = tempfile.mkdtemp()
    engine = ColumnStoreEngine(storage_backend=CSVStorage(storage_dir=temp_dir))
    return engine, temp_dir

def cleanup(temp_dir):
    shutil.rmtree(temp_dir)

def test_create_table():
    engine, temp_dir = make_engine()
    engine.create_table('users', {'id': 'int', 'name': 'str'})
    # Check if files exist
    assert os.path.exists(os.path.join(temp_dir, 'users_id.csv'))
    assert os.path.exists(os.path.join(temp_dir, 'users_name.csv'))
    cleanup(temp_dir)

def test_insert_and_select_all():
    engine, temp_dir = make_engine()
    engine.create_table('users', {'id': 'int', 'name': 'str'})
    engine.insert('users', {'id': 1, 'name': 'Alice'})
    rows = engine.select('users')
    assert rows == [{'id': '1', 'name': 'Alice'}]
    cleanup(temp_dir)

def test_select_with_columns():
    engine, temp_dir = make_engine()
    engine.create_table('users', {'id': 'int', 'name': 'str'})
    engine.insert('users', {'id': 1, 'name': 'Alice'})
    engine.insert('users', {'id': 2, 'name': 'Bob'})
    rows = engine.select('users', columns=['name'])
    assert rows == [{'name': 'Alice'}, {'name': 'Bob'}]
    cleanup(temp_dir)

def test_select_with_where():
    engine, temp_dir = make_engine()
    engine.create_table('users', {'id': 'int', 'name': 'str'})
    engine.insert('users', {'id': 1, 'name': 'Alice'})
    engine.insert('users', {'id': 2, 'name': 'Bob'})
    rows = engine.select('users', where={'id': 2})
    assert rows == [{'id': '2', 'name': 'Bob'}]
    cleanup(temp_dir)

def test_insert_and_select_multiple_rows():
    engine, temp_dir = make_engine()
    engine.create_table('users', {'id': 'int', 'name': 'str'})
    engine.insert('users', {'id': 1, 'name': 'Alice'})
    engine.insert('users', {'id': 2, 'name': 'Bob'})
    engine.insert('users', {'id': 3, 'name': 'Carol'})
    rows = engine.select('users')
    assert rows == [
        {'id': '1', 'name': 'Alice'},
        {'id': '2', 'name': 'Bob'},
        {'id': '3', 'name': 'Carol'}
    ]
    cleanup(temp_dir)

def test_error_on_missing_table():
    engine, temp_dir = make_engine()
    with pytest.raises(Exception):
        engine.insert('missing', {'id': 1})
    with pytest.raises(Exception):
        engine.select('missing')
    cleanup(temp_dir) 