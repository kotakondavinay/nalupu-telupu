package com.naltel.test.hadoop.jobs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.naltel.hadoop.training.jobs.WordCountJob;
import com.naltel.hadoop.training.jobs.WordCountMapper;
import com.naltel.hadoop.training.jobs.WordCountPartitioner;
import com.naltel.hadoop.training.jobs.WordCountReducer;

public class TopKJob implements Tool{

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
		Job topKJob = new Job(getConf());
		topKJob.setJobName("NalTel test TopK Job");
		topKJob.setJarByClass(this.getClass());
		topKJob.setMapperClass(TopKMapper.class);
		topKJob.setReducerClass(TopKReducer.class);
		
		topKJob.setMapOutputKeyClass(Text.class);
		topKJob.setMapOutputValueClass(LongWritable.class);
		topKJob.setOutputKeyClass(Text.class);
		topKJob.setOutputValueClass(LongWritable.class);

		FileInputFormat.setInputPaths(topKJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(topKJob, new Path(args[1]));
		return topKJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(" Usage : <input_path>  <output_path> <k-value> ");
		Configuration configuration = new Configuration();
		configuration.set("k", args[2]);
		ToolRunner.run(configuration, new TopKJob(), args);
	}


}
