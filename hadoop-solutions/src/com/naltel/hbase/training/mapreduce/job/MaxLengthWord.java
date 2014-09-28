package com.naltel.hbase.training.mapreduce.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.naltel.hadoop.training.jobs.WordCountJob;
import com.naltel.hadoop.training.jobs.WordCountMapper;
import com.naltel.hadoop.training.jobs.WordCountPartitioner;
import com.naltel.hadoop.training.jobs.WordCountReducer;

public class MaxLengthWord implements Tool{

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
		Job maxLegnthJob = new Job(getConf());
		maxLegnthJob.setJobName("NalTel MAx Legnth Job");
		maxLegnthJob.setJarByClass(this.getClass());
		maxLegnthJob.setMapperClass(MaxLenthWordMapper.class);
		maxLegnthJob.setReducerClass(MaxLenthWordReducer.class);
		maxLegnthJob.setMapOutputKeyClass(Text.class);
		maxLegnthJob.setMapOutputValueClass(LongWritable.class);
		maxLegnthJob.setOutputKeyClass(Text.class);
		maxLegnthJob.setOutputValueClass(LongWritable.class);
		maxLegnthJob.setInputFormatClass(TextInputFormat.class);
		FileInputFormat.setInputPaths(maxLegnthJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(maxLegnthJob, new Path(args[1]));
		return maxLegnthJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new MaxLengthWord(), args);
	}

}
