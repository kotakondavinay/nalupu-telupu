import os
import csv
from typing import List, Dict, Any
from .base import BaseStorage

class CSVStorage(BaseStorage):
    def __init__(self, storage_dir: str = 'storage'):
        self.storage_dir = storage_dir
        os.makedirs(self.storage_dir, exist_ok=True)

    def _get_column_path(self, table_name: str, column: str) -> str:
        return os.path.join(self.storage_dir, f"{table_name}_{column}.csv")

    def create_table(self, table_name: str, schema: Dict[str, str]) -> None:
        for column in schema.keys():
            path = self._get_column_path(table_name, column)
            if not os.path.exists(path):
                with open(path, 'w', newline='') as f:
                    writer = csv.writer(f)
                    writer.writerow([column])

    def insert(self, table_name: str, row: Dict[str, Any]) -> None:
        for column, value in row.items():
            path = self._get_column_path(table_name, column)
            with open(path, 'a', newline='') as f:
                writer = csv.writer(f)
                writer.writerow([value])

    def select(self, table_name: str, columns: List[str] = None, where: Dict[str, Any] = None) -> List[Dict[str, Any]]:
        # Read all columns
        if columns is None:
            # Infer columns from files
            files = [f for f in os.listdir(self.storage_dir) if f.startswith(f"{table_name}_") and f.endswith('.csv')]
            columns = [f[len(table_name)+1:-4] for f in files]
        data = {}
        for column in columns:
            path = self._get_column_path(table_name, column)
            with open(path, 'r', newline='') as f:
                reader = csv.reader(f)
                next(reader)  # skip header
                data[column] = [row[0] for row in reader]
        # Transpose columns to rows
        num_rows = len(next(iter(data.values()), []))
        result = []
        for i in range(num_rows):
            row = {col: data[col][i] for col in columns}
            if where:
                match = True
                for k, v in where.items():
                    if row.get(k) != str(v):
                        match = False
                        break
                if not match:
                    continue
            result.append(row)
        return result 