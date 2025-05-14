import time
from file_storage import FileStorage

def test_file_storage():
    # Initialize storage
    storage = FileStorage()
    
    # Test Level 1 - Basic Functions
    print("\nTesting Level 1 - Basic Functions:")
    
    # Test FILE_UPLOAD
    try:
        storage.FILE_UPLOAD("test1.txt", 100)
        print("✓ FILE_UPLOAD successful")
    except RuntimeError as e:
        print(f"✗ FILE_UPLOAD failed: {e}")
    
    # Test duplicate upload
    try:
        storage.FILE_UPLOAD("test1.txt", 200)
        print("✗ Duplicate upload should have failed")
    except RuntimeError as e:
        print("✓ Duplicate upload correctly rejected")
    
    # Test FILE_GET
    size = storage.FILE_GET("test1.txt")
    print(f"✓ FILE_GET: {size}")
    
    # Test FILE_COPY
    try:
        storage.FILE_COPY("test1.txt", "test2.txt")
        print("✓ FILE_COPY successful")
    except RuntimeError as e:
        print(f"✗ FILE_COPY failed: {e}")
    
    # Test Level 2 - Search
    print("\nTesting Level 2 - Search:")
    storage.FILE_UPLOAD("test3.txt", 150)
    storage.FILE_UPLOAD("test4.txt", 300)
    results = storage.FILE_SEARCH("test")
    print("Search results:", results)
    
    # Test Level 3 - TTL
    print("\nTesting Level 3 - TTL:")
    current_time = time.time()
    storage.FILE_UPLOAD_AT(current_time, "temp1.txt", 100, ttl=5)
    storage.FILE_UPLOAD_AT(current_time, "temp2.txt", 200)  # No TTL
    
    print("Files before sleep:", storage.FILE_SEARCH("temp"))
    time.sleep(6)  # Wait for temp1.txt to expire
    print("Files after sleep:", storage.FILE_SEARCH("temp"))
    
    # Test Level 4 - Rollback
    print("\nTesting Level 4 - Rollback:")
    storage.FILE_UPLOAD("rollback1.txt", 100)
    storage.FILE_UPLOAD("rollback2.txt", 200)
    rollback_time = time.time()
    storage.FILE_UPLOAD("rollback3.txt", 300)
    
    print("Before rollback:", storage.FILE_SEARCH("rollback"))
    storage.ROLLBACK(rollback_time)
    print("After rollback:", storage.FILE_SEARCH("rollback"))
    
    # Test timestamp preservation
    print("\nTesting timestamp preservation:")
    t1 = time.time()
    storage.FILE_UPLOAD("time1.txt", 100)
    time.sleep(1)
    t2 = time.time()
    storage.FILE_UPLOAD("time2.txt", 200)
    time.sleep(1)
    t3 = time.time()
    
    # Rollback to t2
    storage.ROLLBACK(t2)
    
    # Verify time1.txt exists with original timestamp
    if "time1.txt" in storage.files:
        _, _, upload_time = storage.files["time1.txt"]
        print(f"time1.txt upload time: {upload_time} (should be close to {t1})")
    
    # Verify time2.txt doesn't exist
    print(f"time2.txt exists: {'time2.txt' in storage.files} (should be False)")

if __name__ == "__main__":
    test_file_storage() 