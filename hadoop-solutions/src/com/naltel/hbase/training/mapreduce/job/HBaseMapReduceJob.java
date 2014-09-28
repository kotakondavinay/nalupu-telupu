package com.naltel.hbase.training.mapreduce.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HBaseMapReduceJob implements Tool {

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
		Configuration hbaseConf = HBaseConfiguration.create(getConf());
		hbaseConf.set("hbase.zookeeper.quorum", "localhost:2181");
		Job hbaseLoadJob = new Job(hbaseConf);
		hbaseLoadJob.setJobName("Naltel HBase Word Count");
		hbaseLoadJob.setJarByClass(this.getClass());
		hbaseLoadJob.setMapperClass(HBaseMapper.class);
		hbaseLoadJob.setNumReduceTasks(0);
		hbaseLoadJob.setOutputKeyClass(ImmutableBytesWritable.class);
		hbaseLoadJob.setOutputValueClass(Put.class);
		hbaseLoadJob.setInputFormatClass(KeyValueTextInputFormat.class);
		hbaseLoadJob.setOutputFormatClass(TableOutputFormat.class);
		hbaseLoadJob.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, "test131");
		FileInputFormat.setInputPaths(hbaseLoadJob, new Path(args[0]));
		return hbaseLoadJob.waitForCompletion(true) == true ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new HBaseMapReduceJob(), args);
	}

}
