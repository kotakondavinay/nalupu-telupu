package com.naltel.hadoop.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<Text, LongWritable>{

	@Override
	public int getPartition(Text key, LongWritable value, int noOfReducers) {
		String tempString = key.toString().toLowerCase();
		if(tempString.charAt(0) >= 'a' && tempString.charAt(0) <= 'z') {
			return tempString.charAt(0) - 'a';
		}
		return 0;
	}

}
