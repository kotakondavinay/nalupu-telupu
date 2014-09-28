package com.naltel.hadoop.hive.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HadoopHiveUseCaseTest {

	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	private static String tableName = "TradeUsecase_2";

	private static String partitionedTableName = "tradePart";

	private String dataFilePath = "/input/raptor/usecase/testdata1_copy_1.log";

	private boolean standAloneServer = true;

	public static String hiveURI = "jdbc:hive://localhost:10000/";

	public HadoopHiveUseCaseTest(String name) {
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

		// drop table. ignore error.
		stmt.executeQuery("drop table " + tableName);

		// create table

		ResultSet res = stmt.executeQuery("create table " + tableName
				+ " (key int, value string)ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE");

		/*
		 * ResultSet res = stmt .executeQuery("create table " + tableName +
		 * " (trade string, acctNo string, broker string, side string, symbol string, shareQty int, price double, currency string, capacity string, timeStamp_t string)ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE"
		 * );
		 */

		// load data
		/*
		 * ResultSet res = stmt.executeQuery("load data local inpath '" +
		 * dataFilePath.toString() + "' into table " + tableName);
		 */

		res = stmt.executeQuery("load data  inpath '" + dataFilePath.toString() + "' into table " + tableName);
		return stmt;
	}

	protected Statement setUpPartitionTable(Connection con) throws Exception {
		Statement stmt = null;

		stmt = con.createStatement();

		ResultSet res = stmt.executeQuery("drop table " + partitionedTableName);
		/*
		 * res = stmt.executeQuery("create table " + partitionedTableName +
		 * " (key int, value string) partitioned by (dt STRING)");
		 */

		res = stmt
				.executeQuery("create table "
						+ partitionedTableName
						+ " (trade string, acctNo string, broker string, side string, symbol string, shareQty int, price double, currency string, capacity string, timeStamp string) partitioned by (timeStamp STRING)");

		res = stmt.executeQuery("load data local inpath '" + dataFilePath.toString() + "' into table "
				+ partitionedTableName + " PARTITION (timeStamp='20090619')");
		return stmt;
	}

	protected ResultSet executeQuery(Statement stmt, String query) throws Exception {
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	protected static void printResultSet(ResultSet res) throws Exception {
		if (res == null && res.getFetchSize() <= 0)
			return;

		while (res.next()) {
			System.out.println(res.getString(1)); // + " " + res.getString(2));
			// System.out.println(res.getString("Symbol"));
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Starting Test...");
			HadoopHiveUseCaseTest test = new HadoopHiveUseCaseTest("Hive");
			Thread.sleep(1000);
			System.out.println("Connecting to Hive....");
			Connection con = test.getConnection();
			/*
			 * System.out.println("Setting up sample table...."); Statement stmt
			 * = test.setUpSimpleTable(con);
			 */
			Statement stmt = con.createStatement();
			System.out.println("Executing sammple query....");

			ResultSet set = test.executeQuery(stmt, "select trade, symbol from TradeUsecase_2");
			System.out.println("Printing results...");
			// stmt.close();
			Thread.sleep(5000);
			printResultSet(set);
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
