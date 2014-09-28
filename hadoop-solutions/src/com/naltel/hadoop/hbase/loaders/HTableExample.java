package com.naltel.hadoop.hbase.loaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.hbase.client.Get;

import org.apache.hadoop.hbase.client.HTable;

import org.apache.hadoop.hbase.client.Put;

import org.apache.hadoop.hbase.client.Result;

import org.apache.hadoop.hbase.client.ResultScanner;

import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class HTableExample
{
 public static void main(String[] args) throws IOException
 {
  
  
  org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
  config.set("hbase.zookeeper.quorum", "localhost:2181");

  HTable table = new HTable(config, "User1");
  

  Put p = new Put(Bytes.toBytes("row3"));


  p.add(Bytes.toBytes("credentials"), Bytes.toBytes("Id"),Bytes.toBytes("Emp1"));

  p.add(Bytes.toBytes("credentials"),Bytes.toBytes("name"),Bytes.toBytes("NalTel1"));

  
  Put p1 = new Put(Bytes.toBytes("row4"));


  p1.add(Bytes.toBytes("credentials"), Bytes.toBytes("Id"),Bytes.toBytes("Emp2"));

  p1.add(Bytes.toBytes("credentials"),Bytes.toBytes("name"),Bytes.toBytes("NalTel2"));


  List<Put> puts = new ArrayList<Put>();
  puts.add(p);
  puts.add(p1);
  
  table.put(puts);


  Get g = new Get(Bytes.toBytes("row1"));

  Result r = table.get(g);

  byte [] value = r.getValue(Bytes.toBytes("credentials"),Bytes.toBytes("Id"));

  byte [] value1 = r.getValue(Bytes.toBytes("credentials"),Bytes.toBytes("name"));

  String valueStr = Bytes.toString(value);

  String valueStr1 = Bytes.toString(value1);

  System.out.println("GET: " +"Id: "+ valueStr+"Name: "+valueStr1);

  Scan s = new Scan();

  s.addColumn(Bytes.toBytes("credentials"), Bytes.toBytes("Id"));

  s.addColumn(Bytes.toBytes("credentials"), Bytes.toBytes("name"));

  ResultScanner scanner = table.getScanner(s);

  try
  {
   for (Result rr = scanner.next(); rr != null; rr = scanner.next())
   {
    System.out.println("Found row : " + rr);
   }
  } finally
  {
   scanner.close();
  }
 }
}
