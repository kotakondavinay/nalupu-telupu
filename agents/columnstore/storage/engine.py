from typing import Dict, Any, List
from .storage.csv_storage import CSVStorage

class ColumnStoreEngine:
    def __init__(self, storage_backend=None):
        self.storage = storage_backend or CSVStorage()
        self.schemas = {}  # table_name -> schema dict

    def create_table(self, table_name: str, schema: Dict[str, str]) -> None:
        self.schemas[table_name] = schema
        self.storage.create_table(table_name, schema)

    def insert(self, table_name: str, row: Dict[str, Any]) -> None:
        if table_name not in self.schemas:
            raise Exception(f"Table {table_name} does not exist.")
        self.storage.insert(table_name, row)

    def select(self, table_name: str, columns: List[str] = None, where: Dict[str, Any] = None) -> List[Dict[str, Any]]:
        if table_name not in self.schemas:
            raise Exception(f"Table {table_name} does not exist.")
        return self.storage.select(table_name, columns, where) 