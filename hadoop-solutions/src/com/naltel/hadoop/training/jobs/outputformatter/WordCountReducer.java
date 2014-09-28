package com.naltel.hadoop.training.jobs.outputformatter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class WordCountReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	private MultipleOutputs<Text, LongWritable> mo;
	
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		mo = new MultipleOutputs<Text, LongWritable>(context);
	};

	@Override
	protected void reduce(Text key, Iterable<LongWritable> value,
			Context context) throws IOException, InterruptedException {
		long sum = 0;
		while (value.iterator().hasNext()) {
			sum += value.iterator().next().get();
		}
		LongWritable countLW = new LongWritable(sum);
		mo.write("wordcountText", key, countLW, "wcText");
		mo.write("wordcountSeq", key, countLW, "wcSeq");
		context.write(key, countLW);
	};
}
