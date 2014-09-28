package com.naltel.hadoop.hive.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HadoopHiveTest {

	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	private static String tableName = "naltel32";

	private static String partitionedTableName = "naltel1";

	private static String demoTableName = "naltelDemoTable";

	private static String demoPartitionedTableName = "naltelPartitionDemo1";

	private String hiveDataFilePath = "/home/vinaykk/workh/hive/input/hiveTable.log";

	private String hivePartitionDataFilePath = "/home/vinaykk/hive/input/demoHivePartition.log";

	private String dataFilePath = "/home/vinaykk/hive/input/input/k1.log";

	private boolean standAloneServer = true;

	// public static String hiveURI = "jdbc:hive://107.20.47.115:10000/";

	public static String hiveURI = "jdbc:hive://localhost:10000/myderby1";

	public HadoopHiveTest(String name) {
		standAloneServer = true;
	}

	protected Connection getConnection() throws Exception {
		Connection con = null;

		Class.forName(driverName);
		if (standAloneServer) {
			con = DriverManager.getConnection(hiveURI, "", "");
		} else {
			con = DriverManager.getConnection("jdbc:hive://", "", "");
		}

		return con;
	}

	protected Statement setUpSimpleTable(Connection con) throws Exception {
		Statement stmt = null;

		stmt = con.createStatement();

		stmt.executeQuery("use naltel200");
		// drop table. ignore error.
//		stmt.executeQuery("drop table " + tableName);

		// create table
		ResultSet res = stmt.executeQuery("create table " + tableName
				+ " (key int, value string)ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE");

		// load data
		res = stmt.executeQuery("load data local inpath '" + dataFilePath.toString() + "' into table " + tableName);
		return stmt;
	}

	protected Statement setUpPartitionTable(Connection con) throws Exception {
		Statement stmt = null;

		stmt = con.createStatement();

		stmt.executeQuery("use naltel200");
		ResultSet res = stmt.executeQuery("drop table " + demoPartitionedTableName);
		
		/*res = stmt
				.executeQuery("create table "
						+ demoPartitionedTableName
						+ " (key int, value string) partitioned by (dt STRING) CLUSTERED BY(value) SORTED BY(key) INTO 32 BUCKETS ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE");*/

		res = stmt
				.executeQuery("create table "
						+ demoPartitionedTableName
						+ " (key int, value string) partitioned by (dt STRING)ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE");

		/*
		 * res = stmt.executeQuery("load data local inpath '" +
		 * hiveDataFilePath.toString() + "' into table " +
		 * demoPartitionedTableName);
		 */

		res = stmt.executeQuery("load data local inpath '" + hivePartitionDataFilePath.toString() + "' into table "
				+ demoPartitionedTableName + " PARTITION (dt='67889')");
		return stmt;
	}

	protected ResultSet executeQuery(Statement stmt, String query) throws Exception {
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	protected static void printResultSet(ResultSet res) throws Exception {
		if (res == null)
			return;

		while (res.next()) {
			System.out.println(res.getInt(1)); // + " " + res.getString(2));
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Starting Test...");
			HadoopHiveTest test = new HadoopHiveTest("Hive");
			// Thread.sleep(1000);
			System.out.println("Connecting to Hive....");
			Connection con = test.getConnection();
			System.out.println("Setting up sample table....");
			Statement stmt = test.setUpSimpleTable(con);
			System.out.println("Executing sammple query....");
			// Statement stmt = con.createStatement();
			ResultSet set = test.executeQuery(stmt, "select key from " + tableName);
			System.out.println("Printing results...");
			printResultSet(set);
			set = test.executeQuery(stmt, "select min(key) from " + tableName);
			System.out.println("Printing results...");
			printResultSet(set);
			set = test.executeQuery(stmt, "select max(key) from " + tableName);
			System.out.println("Printing results...");
			printResultSet(set);

			// for partition based queries
			System.out.println("Setting up sample table....");
			stmt = test.setUpPartitionTable(con);
			System.out.println("Executing sammple partitioned query....");
			set = test.executeQuery(stmt, "select max(key) from " + demoPartitionedTableName);
			System.out.println("Printing results...");
			printResultSet(set); 
			set = test.executeQuery(stmt, "select * from " + demoPartitionedTableName +" where  dt='67889'");
			System.out.println("Printing Partitioned query results...");
			printResultSet(set); 
			
			// bucketization based query
			// System.out.println(" Printing from bucket....");
			// set = test.executeQuery(stmt,
			// "SELECT * FROM "+demoPartitionedTableName+" TABLESAMPLE(2 OUT OF n BUCKETS)WHERE dt=’20120929’ ");
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
