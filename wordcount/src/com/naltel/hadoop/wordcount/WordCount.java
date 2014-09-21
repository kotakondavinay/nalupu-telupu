package com.naltel.hadoop.wordcount;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount implements Tool {
	
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
	public int run(String[] arg0) throws Exception {
		//JobConf conf = new JobConf(WordCount.class);
		Job wcJob = new Job(getConf()); 
		wcJob.setJarByClass(this.getClass());
		wcJob.setJobName("wordcount");
		
		//wcJob.setMapperClass(WordCountMapper.class);
		wcJob.setMapperClass(WordCountMapper.class);
		
		
		wcJob.setOutputKeyClass(Text.class);
		wcJob.setOutputValueClass(IntWritable.class);

		
		
		
	   

		wcJob.setInputFormat(TextInputFormat.class);
		wcJob.setOutputFormat(TextOutputFormat.class);
		wcJob.setNumReduceTasks(26);
	     
	     wcJob.setPartitionerClass(CustomPartitioner.class);

	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	}

	public static void main(String[] args) throws Exception {
		   //  JobConf conf = new JobConf(WordCount.class);
		    /* conf.setJobName("wordcount");

		     conf.setOutputKeyClass(Text.class);
		     conf.setOutputValueClass(IntWritable.class);

		     conf.setMapperClass(WordCountMapper.class);
		     conf.setCombinerClass(WordCountReducer.class);
		     conf.setReducerClass(WordCountReducer.class);
		   

		     conf.setInputFormat(TextInputFormat.class);
		     conf.setOutputFormat(TextOutputFormat.class);
		     conf.setNumReduceTasks(26);
		     Job wcJob = new Job(conf);
		     wcJob.setPartitionerClass(CustomPartitioner.class);

		     FileInputFormat.setInputPaths(conf, new Path(args[0]));
		     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
*/
		     /*JobClient.runJob(conf);*/
		   }

}
