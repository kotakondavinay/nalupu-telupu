package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgURLCountMapper extends Mapper<Text, Text, Text, LongWritable> {

	@Override
	protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		try {
			context.write(key, new LongWritable(Long.valueOf(value.toString())));
		} catch (NumberFormatException e) {
			System.out.println("Value " + value);
			e.printStackTrace();
		}
	};
}
