package com.naltel.test.hadoop.jobs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopKMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	String topKey;
	protected void setup(Context context) throws IOException, InterruptedException {
		topKey = new String("3");
	};

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] keyValues = value.toString().split(",");
		//StringTokenizer st. = new StringTokenizer(value.toString(), ",");
		String keyString = keyValues[0];
    	if ((keyString != null) && !(keyString.trim().equals(""))) {
				keyString = keyString + "," + topKey;				
				context.write(new Text(keyString), new LongWritable(Integer.parseInt(keyValues[1])));
		}
    	else
    	{
    		context.getCounter("TopK", "correpted_records").increment(1);
    	}
	};

	protected void cleanup(Context context) throws IOException, InterruptedException {
		topKey = null;
	};	
	
}
