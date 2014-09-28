package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class BiGramReducer extends Reducer<BiGram, LongWritable, BiGram, LongWritable>{

	protected void reduce(BiGram key, java.lang.Iterable<LongWritable> values, org.apache.hadoop.mapreduce.Reducer<BiGram,LongWritable,BiGram,LongWritable>.Context context) throws java.io.IOException ,InterruptedException {
		long sum = 0;
		while (values.iterator().hasNext()) {
			sum += values.iterator().next().get();
		}
		context.write(key, new LongWritable(sum));
	};
}
