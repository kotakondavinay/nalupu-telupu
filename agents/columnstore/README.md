# Minimal Column Store (ClickHouse-like)

A simple, extensible columnar storage engine with REST API and CLI, using CSV files for storage. Easily extendable to other formats (e.g., Parquet).

## Features
- Create tables with schema
- Insert rows
- Select/query data (with optional filters)
- REST API (FastAPI)
- CLI

## Usage

### REST API
1. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
2. Run the API:
   ```bash
   uvicorn api.main:app --reload
   ```
3. Example requests:
   - Create table:
     ```bash
     curl -X POST "http://localhost:8000/tables/users" -H "Content-Type: application/json" -d '{"schema": {"id": "int", "name": "str"}}'
     ```
   - Insert row:
     ```bash
     curl -X POST "http://localhost:8000/tables/users/rows" -H "Content-Type: application/json" -d '{"row": {"id": 1, "name": "Alice"}}'
     ```
   - Select:
     ```bash
     curl -X POST "http://localhost:8000/tables/users/select" -H "Content-Type: application/json" -d '{"columns": ["id", "name"], "where": {"id": 1}}'
     ```

### CLI
1. Create table:
   ```bash
   python cli.py create-table users '{"id": "int", "name": "str"}'
   ```
2. Insert row:
   ```bash
   python cli.py insert users '{"id": 1, "name": "Alice"}'
   ```
3. Select:
   ```bash
   python cli.py select users --columns id,name --where '{"id": 1}'
   ```

## Extending Storage
Implement a new storage backend by subclassing `BaseStorage` in `columnstore/storage/base.py` and updating the engine to use it. 