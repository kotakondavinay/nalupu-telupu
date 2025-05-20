import abc
from typing import List, Dict, Any

class BaseStorage(abc.ABC):
    @abc.abstractmethod
    def create_table(self, table_name: str, schema: Dict[str, str]) -> None:
        pass

    @abc.abstractmethod
    def insert(self, table_name: str, row: Dict[str, Any]) -> None:
        pass

    @abc.abstractmethod
    def select(self, table_name: str, columns: List[str] = None, where: Dict[str, Any] = None) -> List[Dict[str, Any]]:
        pass 