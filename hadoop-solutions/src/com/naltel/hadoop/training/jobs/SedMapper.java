package com.naltel.hadoop.training.jobs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SedMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (value.toString().contains(context.getConfiguration().get("sed-arg1"))) {
			context.write(NullWritable.get(), new Text(value.toString().replaceAll(
					context.getConfiguration().get("sed-arg1"), context.getConfiguration().get("sed-arg2"))));
		} else {
			context.write(NullWritable.get(), value);
		}

	}
}
