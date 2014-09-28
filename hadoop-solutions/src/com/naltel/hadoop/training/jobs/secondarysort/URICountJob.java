package com.naltel.hadoop.training.jobs.secondarysort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class URICountJob implements Tool {

	private Configuration conf;

	@Override
	public int run(String[] args) throws Exception {
		Job uriCountJob = new Job(getConf());
		uriCountJob.setJobName("NalTel URI Count");
		uriCountJob.setJarByClass(this.getClass());

		uriCountJob.setMapperClass(URICountMapper.class);
		uriCountJob.setMapOutputKeyClass(URICountKey.class);
		uriCountJob.setMapOutputValueClass(IntWritable.class);

		uriCountJob.setReducerClass(URICountReducer.class);
		uriCountJob.setOutputKeyClass(Text.class);
		uriCountJob.setOutputValueClass(DoubleWritable.class);

		uriCountJob.setInputFormatClass(KeyValueTextInputFormat.class);

//		uriCountJob.setPartitionerClass(URICountKeyPartitioner.class);
//		uriCountJob.setGroupingComparatorClass(URICountKeyGroupingComparator.class);

		FileInputFormat.setInputPaths(uriCountJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(uriCountJob, new Path(args[1]));

		return uriCountJob.waitForCompletion(true) == true ? 0 : -1;
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
		ToolRunner.run(new Configuration(), new URICountJob(), args);
	}

}
