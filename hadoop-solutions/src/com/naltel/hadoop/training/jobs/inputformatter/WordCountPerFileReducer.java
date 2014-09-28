package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountPerFileReducer extends
		Reducer<WordCountPerFileKey, IntWritable, WordCountPerFileKey, IntWritable> {

	@Override
	protected void reduce(WordCountPerFileKey key, java.lang.Iterable<IntWritable> values, Context context)
			throws java.io.IOException, InterruptedException {
		int count = 0;
		for (IntWritable intW : values) {
			count = count + intW.get();
		}
		context.write(key, new IntWritable(count));
	};
}
