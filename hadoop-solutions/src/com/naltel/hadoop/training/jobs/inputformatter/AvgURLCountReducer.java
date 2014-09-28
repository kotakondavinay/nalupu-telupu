package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgURLCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text key, Iterable<LongWritable> value, Context context) throws IOException,
			InterruptedException {
		long sum = 0;
		long count = 0;
		while (value.iterator().hasNext()) {
			sum += value.iterator().next().get();
			count++;
		}
		context.write(key, new LongWritable(sum / count));
	};
}
