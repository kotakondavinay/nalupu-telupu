package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountPerFileJob implements Tool {

	private Configuration conf;

	@Override
	public int run(String[] args) throws Exception {
		Job wordCountPerFileJob = new Job(getConf());
		wordCountPerFileJob.setJobName("NalTel Word Count Per File");
		wordCountPerFileJob.setJarByClass(this.getClass());

		wordCountPerFileJob.setMapperClass(WordCountPerFileMapper.class);
		wordCountPerFileJob.setMapOutputKeyClass(WordCountPerFileKey.class);
		wordCountPerFileJob.setMapOutputValueClass(IntWritable.class);

		wordCountPerFileJob.setReducerClass(WordCountPerFileReducer.class);
		wordCountPerFileJob.setOutputKeyClass(WordCountPerFileKey.class);
		wordCountPerFileJob.setOutputValueClass(IntWritable.class);

		wordCountPerFileJob.setInputFormatClass(SequenceFileInputFormat.class);

		FileInputFormat.setInputPaths(wordCountPerFileJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(wordCountPerFileJob, new Path(args[1]));

		return wordCountPerFileJob.waitForCompletion(true) == true ? 0 : -1;
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
		ToolRunner.run(new Configuration(), new WordCountPerFileJob(), args);
	}

}
