package com.naltel.hbase.training.mapreduce.job;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HBaseMapper extends Mapper<Text, Text, ImmutableBytesWritable, Put> {

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, ImmutableBytesWritable, Put>.Context context)
			throws java.io.IOException, InterruptedException {
		Put put = new Put(key.getBytes());
		if (value != null) {
			put.add("cf1".getBytes(), "naltel".getBytes(), value.getBytes());
		} else {
			put.add("cf1".getBytes(), "naltel".getBytes(), "NalTel*".getBytes());
		}
		context.write(new ImmutableBytesWritable(key.getBytes()), put);
	}
}
