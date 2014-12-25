package com.naltel.hadoop.wordcount;

//package org.mapred.jobs.wordcount.partitioner;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
//import org.mapred.jobs.bigram.BiGram;

public class WordCountJob implements Tool {

	private Configuration conf;

	@Override
	public Configuration getConf() {
		return conf;
	}

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public int run(String[] args) throws Exception {
		Job wordCountJob = new Job(getConf());
		wordCountJob.setJobName("Word Count");
		wordCountJob.setJarByClass(this.getClass());
		
		wordCountJob.setMapperClass(WordCountMapper.class);
		wordCountJob.setReducerClass(WordCountReducer.class);
		
		//wordCountJob.setPartitionerClass(WordCountPartitioner.class);
//		wordCountJob.setCombinerClass(WordCountCombiner.class);
		//wordCountJob.setNumReduceTasks(26);
		wordCountJob.setNumReduceTasks(1);
		
		wordCountJob.setMapOutputKeyClass(Text.class);
		wordCountJob.setMapOutputValueClass(LongWritable.class);
		
		wordCountJob.setOutputKeyClass(Text.class);
		wordCountJob.setOutputValueClass(LongWritable.class);
		wordCountJob.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		FileInputFormat.setInputPaths(wordCountJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(wordCountJob, new Path(args[1]));
		return wordCountJob.waitForCompletion(true) == true ? 0 : -1;
	}
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new WordCountJob(), args);
	}

}
