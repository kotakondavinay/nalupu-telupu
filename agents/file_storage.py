from datetime import datetime
from typing import List, Optional, Tuple
import time

class FileStorage:
    def __init__(self):
        self.files = {}  # {filename: (size, ttl, upload_time)}
        self.history = []  # List of operations for rollback

    def _is_file_alive(self, filename: str, current_time: Optional[float] = None) -> bool:
        """Check if file is still alive at the given timestamp."""
        if filename not in self.files:
            return False
        
        if current_time is None:
            current_time = time.time()
            
        size, ttl, upload_time = self.files[filename]
        if ttl is None:  # Infinite TTL
            return True
            
        return current_time < upload_time + ttl

    def _record_operation(self, operation: str, *args):
        """Record operation for rollback purposes."""
        self.history.append((time.time(), operation, args))

    def FILE_UPLOAD(self, file_name: str, size: int) -> None:
        """Upload file to storage."""
        if file_name in self.files:
            raise RuntimeError(f"File {file_name} already exists")
        
        self.files[file_name] = (size, None, time.time())
        self._record_operation("UPLOAD", file_name, size)

    def FILE_GET(self, file_name: str) -> Optional[int]:
        """Get file size if it exists."""
        if file_name not in self.files:
            return None
        return self.files[file_name][0]

    def FILE_COPY(self, source: str, dest: str) -> None:
        """Copy source file to destination."""
        if not self._is_file_alive(source):
            raise RuntimeError(f"Source file {source} does not exist")
            
        size, ttl, upload_time = self.files[source]
        self.files[dest] = (size, ttl, upload_time)
        self._record_operation("COPY", source, dest)

    def FILE_SEARCH(self, prefix: str) -> List[Tuple[str, int]]:
        """Search files by prefix, ordered by size and name."""
        results = []
        for filename, (size, ttl, upload_time) in self.files.items():
            if filename.startswith(prefix) and self._is_file_alive(filename):
                results.append((filename, size))
        
        # Sort by size (descending) and then by filename
        return sorted(results, key=lambda x: (-x[1], x[0]))[:10]

    def FILE_UPLOAD_AT(self, timestamp: float, file_name: str, file_size: int, ttl: Optional[int] = None) -> None:
        """Upload file at specific timestamp with optional TTL."""
        if file_name in self.files:
            raise RuntimeError(f"File {file_name} already exists")
            
        self.files[file_name] = (file_size, ttl, timestamp)
        self._record_operation("UPLOAD_AT", timestamp, file_name, file_size, ttl)

    def FILE_GET_AT(self, timestamp: float, file_name: str) -> Optional[int]:
        """Get file size at specific timestamp."""
        if file_name not in self.files:
            return None
            
        size, ttl, upload_time = self.files[file_name]
        if ttl is not None and timestamp > upload_time + ttl:
            return None
            
        return size

    def FILE_COPY_AT(self, timestamp: float, file_from: str, file_to: str) -> None:
        """Copy file at specific timestamp."""
        if file_from not in self.files:
            raise RuntimeError(f"Source file {file_from} does not exist")
            
        size, ttl, upload_time = self.files[file_from]
        if ttl is not None and timestamp > upload_time + ttl:
            raise RuntimeError(f"Source file {file_from} has expired")
            
        self.files[file_to] = (size, ttl, upload_time)
        self._record_operation("COPY_AT", timestamp, file_from, file_to)

    def FILE_SEARCH_AT(self, timestamp: float, prefix: str) -> List[Tuple[str, int]]:
        """Search files by prefix at specific timestamp."""
        results = []
        for filename, (size, ttl, upload_time) in self.files.items():
            if filename.startswith(prefix):
                if ttl is None or timestamp < upload_time + ttl:
                    results.append((filename, size))
        
        return sorted(results, key=lambda x: (-x[1], x[0]))[:10]

    def ROLLBACK(self, timestamp: float) -> None:
        """Rollback storage state to specified timestamp."""
        # Clear current state
        self.files.clear()
        
        # Replay operations up to timestamp
        for op_time, operation, args in self.history:
            if op_time <= timestamp:
                if operation == "UPLOAD":
                    # For UPLOAD, we need to preserve the original upload time
                    file_name, size = args
                    self.files[file_name] = (size, None, op_time)
                elif operation == "COPY":
                    source, dest = args
                    if source in self.files:
                        size, ttl, upload_time = self.files[source]
                        self.files[dest] = (size, ttl, upload_time)
                elif operation == "UPLOAD_AT":
                    # UPLOAD_AT already has timestamp in args
                    ts, file_name, file_size, ttl = args
                    self.files[file_name] = (file_size, ttl, ts)
                elif operation == "COPY_AT":
                    # COPY_AT already has timestamp in args
                    ts, file_from, file_to = args
                    if file_from in self.files:
                        size, ttl, upload_time = self.files[file_from]
                        self.files[file_to] = (size, ttl, upload_time) 