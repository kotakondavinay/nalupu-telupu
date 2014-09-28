package com.naltel.hadoop.training.jobs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MaxLengthWordJob implements Tool {

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
		Job maxLengthWordJob = new Job(getConf());
		maxLengthWordJob.setJobName("NalTel Max Length Word ");
		maxLengthWordJob.setJarByClass(this.getClass());
		maxLengthWordJob.setMapperClass(MaxLengthWordMapper.class);
		maxLengthWordJob.setReducerClass(MaxLengthWordReducer.class);
		// maxLengthWordJob.setCombinerClass(WordCoumtCombiner.class);
		// maxLengthWordJob.setNumReduceTasks(1);
		maxLengthWordJob.setMapOutputKeyClass(Text.class);
		maxLengthWordJob.setMapOutputValueClass(LongWritable.class);
		maxLengthWordJob.setOutputKeyClass(Text.class);
		maxLengthWordJob.setOutputValueClass(NullWritable.class);
		// maxLengthWordJob.setPartitionerClass(WordCountPartitioner.class);

		maxLengthWordJob.setInputFormatClass(TextInputFormat.class);
		maxLengthWordJob.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.setInputPaths(maxLengthWordJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(maxLengthWordJob, new Path(args[1]));
		return maxLengthWordJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new MaxLengthWordJob(), args);
	}

}
