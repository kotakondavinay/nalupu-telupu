package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class WordPairCountReducer extends Reducer<WordPairCountKey, LongWritable, WordPairCountKey, LongWritable> {

	@Override
	protected void reduce(WordPairCountKey key, java.lang.Iterable<LongWritable> values, Context context)
			throws java.io.IOException, InterruptedException {
		long sum = 0;
		while (values.iterator().hasNext()) {
			sum += values.iterator().next().get();
		}
		context.write(key, new LongWritable(sum));
	};

}
