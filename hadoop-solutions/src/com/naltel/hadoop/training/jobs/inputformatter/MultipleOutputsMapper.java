package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultipleOutputsMapper extends
		Mapper<LongWritable, Text, Text, Text> {
	@SuppressWarnings("rawtypes")
	private MultipleOutputs mouts;

	@SuppressWarnings("unchecked")
	protected void setup(Context context) throws java.io.IOException,
			InterruptedException {
		mouts = new MultipleOutputs(context);
	};

	@SuppressWarnings("unchecked")
	protected void map(
			LongWritable key,
			Text value,
			org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context)
			throws java.io.IOException, InterruptedException {
		if (value.toString().contains("error")) {
			mouts.write("errors", value, NullWritable.get());
			context.getCounter("CLASSIFICATION", "error-records").increment(1);
		} else {
			mouts.write("actuals", value, NullWritable.get());
			context.getCounter("CLASSIFICATION", "actual-records").increment(1);
		}
	};
	
	protected void cleanup(org.apache.hadoop.mapreduce.Mapper<LongWritable,Text,Text,Text>.Context context) throws java.io.IOException ,InterruptedException {
		mouts.close();
	};
}
