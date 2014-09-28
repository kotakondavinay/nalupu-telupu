package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.join.CompositeInputFormat;

public class MapSideJoin {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		JobConf conf = new JobConf("Map Side Join");
		conf.setJarByClass(MapSideJoin.class);
		conf.setMapperClass(MapSideJoinMapper.class);
		conf.setInputFormat(CompositeInputFormat.class);
		String strJoinStmt = CompositeInputFormat.compose("inner",
				KeyValueTextInputFormat.class, new Path(args[0]), new Path(
						args[1]));
		conf.set("mapred.join.expr", strJoinStmt);
		conf.setNumReduceTasks(0);
		conf.setOutputFormat(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(conf, new Path(args[2]));
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
		RunningJob job = JobClient.runJob(conf);
		while (!job.isComplete()) {
			Thread.sleep(1000);
		}
		System.exit(job.isSuccessful() ? 0 : 2);
	}

}
