package com.naltel.hbase.training.mapreduce.job;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxLenthWordMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	String maxWord;
	int maxLength;

	protected void setup(Context context) throws IOException ,InterruptedException {
		maxWord = "";
		maxLength = -1;
	};
	protected void map(LongWritable key, Text value, Context context) throws IOException ,InterruptedException {
		String string = value.toString();
		StringTokenizer st = new StringTokenizer(string, " ");
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if(tmp.length() >= maxLength)
			{
				maxWord = tmp;
				maxLength = tmp.length();
			}
		}
	};
	
	protected void cleanup(Context context) throws IOException ,InterruptedException {
		context.write(new Text(maxWord), new LongWritable(maxLength));
	};
}
