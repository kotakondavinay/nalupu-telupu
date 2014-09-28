package com.naltel.hadoop.training.jobs.outputformatter;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends
		Mapper<LongWritable, Text, Text, LongWritable> {

	private Text temp = new Text();
	private final static LongWritable one = new LongWritable(1);
	Pattern wordPattern = Pattern.compile("[(a-zA-Z)+]");

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String string = value.toString();

		StringTokenizer strTock = new StringTokenizer(string, " ");
		while (strTock.hasMoreTokens()) {
			String word = strTock.nextToken();
			if (wordPattern.matcher(word).matches()) {
				context.getCounter("wordcount", "alpha").increment(1);
			} else {
				context.getCounter("wordcount", "non-alpha").increment(1);
			}
			temp.set(word);
			context.write(temp, one);
		}
	};
}
