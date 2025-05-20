import argparse
import json
from columnstore.engine import ColumnStoreEngine

def main():
    parser = argparse.ArgumentParser(description="Column Store CLI")
    subparsers = parser.add_subparsers(dest="command")

    # Create table
    create_parser = subparsers.add_parser("create-table")
    create_parser.add_argument("table_name")
    create_parser.add_argument("schema", help="JSON string, e.g. '{\"id\": \"int\", \"name\": \"str\"}'")

    # Insert
    insert_parser = subparsers.add_parser("insert")
    insert_parser.add_argument("table_name")
    insert_parser.add_argument("row", help="JSON string, e.g. '{\"id\": 1, \"name\": \"Alice\"}'")

    # Select
    select_parser = subparsers.add_parser("select")
    select_parser.add_argument("table_name")
    select_parser.add_argument("--columns", help="Comma-separated column names", default=None)
    select_parser.add_argument("--where", help="JSON string for filters, e.g. '{\"id\": 1}'", default=None)

    args = parser.parse_args()
    engine = ColumnStoreEngine()

    if args.command == "create-table":
        schema = json.loads(args.schema)
        engine.create_table(args.table_name, schema)
        print(f"Table {args.table_name} created.")
    elif args.command == "insert":
        row = json.loads(args.row)
        engine.insert(args.table_name, row)
        print("Row inserted.")
    elif args.command == "select":
        columns = args.columns.split(",") if args.columns else None
        where = json.loads(args.where) if args.where else None
        rows = engine.select(args.table_name, columns, where)
        print(json.dumps(rows, indent=2))
    else:
        parser.print_help()

if __name__ == "__main__":
    main() 