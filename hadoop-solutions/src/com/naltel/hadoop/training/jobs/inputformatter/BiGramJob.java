package com.naltel.hadoop.training.jobs.inputformatter;

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
import com.naltel.hadoop.training.jobs.WordCountReducer;

public class BiGramJob implements Tool{

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
		Job wordCountJob = new Job(getConf());
		wordCountJob.setJobName("NalTel Word Count");
		wordCountJob.setJarByClass(this.getClass());
		wordCountJob.setMapperClass(BiGramMapper.class);
		wordCountJob.setReducerClass(BiGramReducer.class);
		wordCountJob.setMapOutputKeyClass(BiGram.class);
		wordCountJob.setMapOutputValueClass(LongWritable.class);
		wordCountJob.setOutputKeyClass(BiGram.class);
		wordCountJob.setOutputValueClass(LongWritable.class);
		FileInputFormat.setInputPaths(wordCountJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(wordCountJob, new Path(args[1]));
		return wordCountJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new BiGramJob(), args);
	}

}
