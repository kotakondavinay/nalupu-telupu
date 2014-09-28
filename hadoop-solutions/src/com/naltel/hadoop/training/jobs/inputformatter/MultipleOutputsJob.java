package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MultipleOutputsJob implements Tool {

	private Configuration  conf;
	
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
		Path inputDir = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(MultipleOutputsJob.class);
		job.setJobName("NalTel MultipleOutputs ");
		job.setMapperClass(MultipleOutputsMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, inputDir);
		FileOutputFormat.setOutputPath(job, outputDir);
		org.apache.hadoop.mapreduce.lib.output.MultipleOutputs.addNamedOutput(
				job, "errors", TextOutputFormat.class, Text.class,
				NullWritable.class);
		org.apache.hadoop.mapreduce.lib.output.MultipleOutputs.addNamedOutput(
				job, "actuals", SequenceFileOutputFormat.class, Text.class,
				NullWritable.class);
		MultipleOutputs.getCountersEnabled(job);
		return job.waitForCompletion(true) == true ? 0 : -1;
	}
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new MultipleOutputsJob(), args);
	}

}
