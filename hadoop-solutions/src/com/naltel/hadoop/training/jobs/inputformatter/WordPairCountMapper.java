package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.naltel.hadoop.training.jobs.inputformatter.WordPairCountJob.badRecords;

public class WordPairCountMapper extends Mapper<Text, Text, WordPairCountKey, LongWritable> {

	private final static LongWritable one = new LongWritable(1);

	@Override
	protected void map(Text key, Text value, Context context) throws java.io.IOException, InterruptedException {
		WordPairCountKey wpKey = new WordPairCountKey();
		wpKey.setWord1(key.toString());
		wpKey.setWord2(value.toString());
		context.getCounter(badRecords.BADCOUNT).increment(1);
		context.getCounter("BAD_RECORDS", "correct_record").increment(1);
		context.write(wpKey, new LongWritable(0));
	};

}
