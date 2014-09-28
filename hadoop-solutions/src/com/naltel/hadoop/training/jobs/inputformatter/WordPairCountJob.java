package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordPairCountJob implements Tool {

	enum badRecords {
		BADCOUNT
	}

	private Configuration conf;

	@Override
	public int run(String[] args) throws Exception {
		Job wordPairCountJob = new Job(getConf());
		wordPairCountJob.setJobName("NalTel WordPair Count");
		wordPairCountJob.setJarByClass(this.getClass());

		wordPairCountJob.setMapperClass(WordPairCountMapper.class);
		wordPairCountJob.setMapOutputKeyClass(WordPairCountKey.class);
		wordPairCountJob.setMapOutputValueClass(LongWritable.class);

		wordPairCountJob.setReducerClass(WordPairCountReducer.class);
		wordPairCountJob.setOutputKeyClass(WordPairCountKey.class);
		wordPairCountJob.setOutputValueClass(LongWritable.class);

		wordPairCountJob.setInputFormatClass(CombineFileInputFormat.class);
		

		FileInputFormat.setInputPaths(wordPairCountJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(wordPairCountJob, new Path(args[1]));

		return wordPairCountJob.waitForCompletion(true) == true ? 0 : -1;
	}

	@Override
	public Configuration getConf() {
		return conf;
	}

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new WordPairCountJob(), args);
	}

}
