package com.naltel.hadoop.hbase.loaders;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;


public class Loader1 {
	  private static Configuration conf;
	  
	  static{
		  conf = HBaseConfiguration.create();
		  conf.set("hbase.zookeeper.quorum", "localhost:2181");
	  }
	  
	  public static void addRecord(String tableName, String rowKey, String family, String qual, String value) throws Exception {
	    try {
	      HTable table = new HTable(conf, tableName);
	      Put put = new Put(Bytes.toBytes(rowKey));
	      put.add(Bytes.toBytes(family), Bytes.toBytes(qual), Bytes.toBytes(value));
	      table.put(put);
	      System.out.println("insert record "+rowKey+" to table "+tableName+" ok.");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public static void main(String[] args) throws Exception{
	    String tablename = args[0];
	    Loader1.addRecord(tablename, "row-1", "data", "name", "Joe");
	    Loader1.addRecord(tablename, "row-1", "data", "age", "28");
	  }

	}
