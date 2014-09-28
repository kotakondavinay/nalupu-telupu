package com.naltel.hadoop.training.jobs.dc;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class PhoneRetrievalJob implements Tool {

	private Configuration conf;

	@Override
	public int run(String[] args) throws Exception {
		Job phoneJob = new Job(getConf());
		phoneJob.setJobName("NalTel Phone JOb");
		phoneJob.setJarByClass(this.getClass());

		phoneJob.setNumReduceTasks(0);

		phoneJob.setMapperClass(PhoneRetrievalMapper.class);
		phoneJob.setOutputKeyClass(Text.class);
		phoneJob.setOutputValueClass(Text.class);

		phoneJob.setInputFormatClass(KeyValueTextInputFormat.class);

		/*Path lookupPath = new Path(args[2]);
		URI[] uris = new URI[1];
		uris[0] = lookupPath.toUri();
 
		DistributedCache.setCacheFiles(uris, phoneJob.getConfiguration());*/
		String inputPaths = args[0];
		String[] inputPathsArray = StringUtils.split(inputPaths, ',');
		
		Path[] inputPath = new Path[inputPathsArray.length];
		int i = 0;
		for(String inputPathStr : inputPathsArray) 
		{
			inputPath[i++] = new Path(inputPathStr);
		}
		
		FileInputFormat.setInputPaths(phoneJob, inputPath);
		FileOutputFormat.setOutputPath(phoneJob, new Path(args[1]));

		return phoneJob.waitForCompletion(true) == true ? 0 : -1;
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
		
		Configuration configuration = new Configuration();
		configuration.set("hadoop.tmp.dir", "/home/vinaykk/workh/tmp");
		ToolRunner.run(configuration, new PhoneRetrievalJob(), args);
	}

}
