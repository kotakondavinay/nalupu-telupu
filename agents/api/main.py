from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import Dict, Any, List, Optional
import sys
sys.path.append('..')
from columnstore.engine import ColumnStoreEngine

app = FastAPI()
engine = ColumnStoreEngine()

class TableSchema(BaseModel):
    schema: Dict[str, str]

class InsertRow(BaseModel):
    row: Dict[str, Any]

class SelectQuery(BaseModel):
    columns: Optional[List[str]] = None
    where: Optional[Dict[str, Any]] = None

@app.post("/tables/{table_name}")
def create_table(table_name: str, table_schema: TableSchema):
    try:
        engine.create_table(table_name, table_schema.schema)
        return {"message": f"Table {table_name} created."}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

@app.post("/tables/{table_name}/rows")
def insert_row(table_name: str, insert_row: InsertRow):
    try:
        engine.insert(table_name, insert_row.row)
        return {"message": "Row inserted."}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

@app.post("/tables/{table_name}/select")
def select(table_name: str, query: SelectQuery):
    try:
        result = engine.select(table_name, query.columns, query.where)
        return {"rows": result}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e)) 