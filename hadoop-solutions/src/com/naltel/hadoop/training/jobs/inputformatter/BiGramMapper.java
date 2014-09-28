package com.naltel.hadoop.training.jobs.inputformatter;

import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BiGramMapper extends
		Mapper<LongWritable, Text, BiGram, LongWritable> {
	private LongWritable one = new LongWritable(1l);

	protected void map(LongWritable key, Text value, Context context)
			throws java.io.IOException, InterruptedException {
		String temp = value.toString();
		StringTokenizer st = new StringTokenizer(temp, " ");
		if (!st.hasMoreTokens())
			return;
		String firstWord = st.nextToken();
		String secondWord = "";
		while (st.hasMoreTokens()) {
			secondWord = st.nextToken();
			BiGram bigram = new BiGram();
			bigram.setWord1(firstWord);
			bigram.setWord2(secondWord);
			context.write(bigram, one);
			firstWord = secondWord;
		}
	};
}
