package com.naltel.hadoop.training.jobs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(value.toString().contains(context.getConfiguration().get("grep_arg")))
		{
			context.getCounter("Grep", "Invalid").increment(1);
		}
		if (value.toString().contains(context.getConfiguration().get("grep-arg"))) {
			context.write(NullWritable.get(), value);
		}
	};

}
