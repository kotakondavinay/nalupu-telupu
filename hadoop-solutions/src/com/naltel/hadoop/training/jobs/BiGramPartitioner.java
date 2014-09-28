package com.naltel.hadoop.training.jobs;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.naltel.hadoop.training.jobs.inputformatter.BiGram;

public class BiGramPartitioner extends Partitioner<BiGram, LongWritable>{

	@Override
	public int getPartition(BiGram arg0, LongWritable arg1, int arg2) {
		return 0;
	}

}
