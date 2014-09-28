package com.naltel.hadoop.training.jobs.inputformatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class NLineWordPairJob implements Tool {

	private Configuration conf;

	@Override
	public int run(String[] args) throws Exception {
		Job nLineJob = new Job(getConf());
		nLineJob.setJobName("NalTel NLine WordPair Count");
		nLineJob.setJarByClass(this.getClass());

		nLineJob.setMapperClass(NLineWordPairMapper.class);
		nLineJob.setMapOutputKeyClass(LongWritable.class);
		nLineJob.setMapOutputValueClass(Text.class);
		nLineJob.setInputFormatClass(NLineInputFormat.class);
		NLineInputFormat.setNumLinesPerSplit(nLineJob, 2);
		nLineJob.setNumReduceTasks(0);
		FileInputFormat.setInputPaths(nLineJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(nLineJob, new Path(args[1]));

		return nLineJob.waitForCompletion(true) == true ? 0 : -1;
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
		Configuration conf1 = new Configuration();
		// conf1.setLong("mapred.line.input.format.linespermap", 3);
		ToolRunner.run(conf1, new NLineWordPairJob(), args);
	}

}
