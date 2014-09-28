package com.naltel.hadoop.training.jobs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class GrepJob implements Tool {

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
		Job grepJob = new Job(getConf());
		grepJob.setJobName("NalTel Grep Count");
		grepJob.setJarByClass(this.getClass());
		grepJob.setMapperClass(GrepMapper.class);
		grepJob.setNumReduceTasks(0);
		grepJob.setOutputValueClass(Text.class);

		grepJob.setInputFormatClass(TextInputFormat.class);
		grepJob.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.setInputPaths(grepJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(grepJob, new Path(args[1]));
		return grepJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		Configuration conf1 = new Configuration();
		conf1.set("grep_arg", "terrorist");
		System.out.println(" FileSystem:  "+conf1.get("fs.default.name"));
		ToolRunner.run(conf1, new GrepJob(), args);
	}

}
