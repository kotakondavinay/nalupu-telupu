package com.naltel.hadoop.training.jobs.inputformatter;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import com.naltel.hadoop.training.jobs.WordCountMapper;
import com.naltel.hadoop.training.jobs.WordCountReducer;

public class ReduceSideJoinJob implements Tool{

	@Override
	public Configuration getConf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConf(Configuration arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int run(String[] args) throws Exception {
		Job reduceSideJoinJob = new Job(getConf());
		reduceSideJoinJob.setJobName("NalTel ReduceSide Join");
		reduceSideJoinJob.setJarByClass(this.getClass());
		reduceSideJoinJob.setMapperClass(WordCountMapper.class);
		reduceSideJoinJob.setReducerClass(WordCountReducer.class);
		reduceSideJoinJob.setMapOutputKeyClass(Text.class);
		reduceSideJoinJob.setMapOutputValueClass(LongWritable.class);
		reduceSideJoinJob.setOutputKeyClass(Text.class);
		reduceSideJoinJob.setOutputValueClass(LongWritable.class);
		
		Path firstPath = new Path(args[0]);
		Path sencondPath = new Path(args[1]);
		Path outputPath = new Path(args[2]);
		
		MultipleInputs.addInputPath(reduceSideJoinJob, firstPath, TextInputFormat.class, WordCountMapper.class);
		MultipleInputs.addInputPath(reduceSideJoinJob, sencondPath, SequenceFileInputFormat.class, WordCountMapper.class);
		
		FileInputFormat.setInputPaths(reduceSideJoinJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(reduceSideJoinJob, new Path(args[1]));
		return reduceSideJoinJob.waitForCompletion(true) == true ? 0 : -1;
	}

}
