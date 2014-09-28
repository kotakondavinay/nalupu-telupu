package com.naltel.hadoop.training.jobs.dc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PhoneRetrievalMapper extends Mapper<Text, Text, Text, Text> {

	private BufferedReader reader;
	private Text phone = new Text();
	private Path lookUp;

	@Override
	protected void setup(Context context) throws java.io.IOException,
			InterruptedException {
		Path[] cacheFiles = DistributedCache.getLocalCacheFiles(context
				.getConfiguration());
		lookUp = cacheFiles[0];
	};

	@Override
	protected void map(Text key, Text value, Context context)
			throws java.io.IOException, InterruptedException {
		if(key.toString().trim().isEmpty()) {
			return;
		}
		File file = new File(lookUp.toString());
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(
				file)));

		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains(key.toString())) {
				String phoneNum = line.split("\t")[1];
				phone.set(phoneNum);
				context.write(key, phone);
				break;
			}
		}
		reader.close();
	};

}
