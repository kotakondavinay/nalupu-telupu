package com.naltel.hadoop.training.jobs.inputformatter;
/*

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class MultipleOutputs implements Tool{

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
		 Path inputDir = new Path(args[0]);
		   Path outputDir = new Path(args[1]);

		   Configuration conf = new Configuration();

		   Job job = new Job(conf);
		   job.setJarByClass(MultipleOutputs.class);
		   job.setJobName("NalTel MultipleOutputs ");

		   job.setMapOutputKeyClass(Text.class);
		   job.setMapOutputValueClass(Text.class);

//		   job.setMapperClass(MultiMapper.class);
//		   job.setReducerClass(MultiReducer.class);

		   FileInputFormat.setInputPaths(job, inputDir);
		   FileOutputFormat.setOutputPath(job, outputDir);

		   MultipleOutputs.addNamedOutput(job, "test1", TextOutputFormat.class, NullWritable.class, Text.class);
		   MultipleOutputs.addNamedOutput(job, "test2", TextOutputFormat.class, NullWritable.class, Text.class);

		   return reduceSideJoinJob.waitForCompletion(true) == true ? 0 : -1;
	}

}
*/
