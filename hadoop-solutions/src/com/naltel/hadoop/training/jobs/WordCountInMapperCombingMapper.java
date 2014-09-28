package com.naltel.hadoop.training.jobs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountInMapperCombingMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	private Map<Text, LongWritable> wordMap;
	private LongWritable one = new LongWritable(1);

	@Override
	protected void setup(Context context) throws java.io.IOException, InterruptedException {
		wordMap = new HashMap<Text, LongWritable>();
	};

	@Override
	protected void map(LongWritable key, Text value, Context context) throws java.io.IOException, InterruptedException {
		if (wordMap.get(value) == null) {
			wordMap.put(value, one);
		} else {
			LongWritable previousVal = wordMap.get(value);
			long curVal = previousVal.get() + 1;
			wordMap.put(value, new LongWritable(curVal));
		}
	};

	@Override
	protected void cleanup(Context context) throws java.io.IOException, InterruptedException {
		Set<Entry<Text, LongWritable>> set = wordMap.entrySet();
		for (Entry<Text, LongWritable> entry : set) {
			context.write(entry.getKey(), entry.getValue());
		}
	};
}
