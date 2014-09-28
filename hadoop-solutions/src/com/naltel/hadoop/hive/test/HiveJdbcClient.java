package com.naltel.hadoop.hive.test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection("jdbc:hive://127.0.0.1:10000/default", "", "");
		Statement stmt = con.createStatement();
		String tableName = "dummy_hive_table";

		// show tables
		String distinctSymbols = "show tables";
		System.out.println("Running: " + distinctSymbols);
		ResultSet res = stmt.executeQuery(distinctSymbols);
		if (res.next()) {
			System.out.println(res.getString(1));
		}

		String[] symbols = { "ATVI", "ADBE", "AKAM", "ALXN", "ALTR", "AMZN", "AMGN", "APOL", "AAPL", "AMAT", "ADSK",
				"ADP", "BIDU", "BBBY", "BIIB", "BMC", "BRCM", "CHRW", "CA", "CELG", "CEPH", "CERN", "CHKP", "CSCO",
				"CTXS", "CTSH", "CMCSA", "COST", "CTRP", "DELL", "XRAY", "DTV", "DLTR", "EBAY", "ERTS", "EXPE", "EXPD",
				"ESRX", "FFIV", "FAST", "FSLR", "FISV", "FLEX", "FLIR", "GRMN", "GILD", "GOOG", "GMCR", "HSIC", "ILMN",
				"INFY", "INTC", "INTU", "ISRG", "JOYG", "KLAC", "LRCX", "LINTA", "LIFE", "LLTC", "MRVL", "MAT", "MXIM",
				"MCHP", "MU", "MSFT", "MYL", "NTAP", "NFLX", "NWSA", "NIHD", "NVDA", "ORCL", "ORLY", "PCAR", "PAYX",
				"PCLN", "QGEN", "QCOM", "RIMM", "ROST", "SNDK", "STX", "SHLD", "SIAL", "SPLS", "SBUX", "SRCL", "SYMC",
				"TEVA", "URBN", "VRSN", "VRTX", "VMED", "VOD", "WCRX", "WFM", "WYNN", "XLNX", "YHOO" };

		// Moving average for 1 month on close
		System.out.println("----------------------------------Running for 1 months-------------------------------");

		long delta = 2678400000L;
		long lowerBound = 0L;
		long upperBound = 1009823400000L;
		while (true) {
			double count = 0;
			double closeSum = 0;
			lowerBound = upperBound;
			upperBound = upperBound + delta;
			String countQuery = "select count(close),sum(close) from simulated_ticks where symbol=\"AAPL\" AND  trade_time >= "
					+ lowerBound + " AND trade_time <=" + upperBound + "";
			System.out.println(" Running Query : " + countQuery);
			long initial_time = System.currentTimeMillis();
			res = stmt.executeQuery(countQuery);
			long final_time = System.currentTimeMillis();
			System.out.println("Total time taken = " + (final_time - initial_time) / 1000 + " seconds.");
			while (res.next()) {
				count = Long.valueOf(res.getLong(1));
				System.out.println(" count = " + count);
				closeSum = Long.valueOf(res.getLong(2));
				System.out.println(" close sum = " + closeSum);
			}
			if ((closeSum > 0) && count != 0) {
				System.out.println(" moving average of AAPL is for One month window =" + (closeSum) / count);
			} else {
				System.out.println("Data not found, count = " + count);
			}
		}

	}
}
