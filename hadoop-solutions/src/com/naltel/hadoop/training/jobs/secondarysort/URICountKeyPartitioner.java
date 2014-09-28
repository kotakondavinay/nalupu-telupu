package com.naltel.hadoop.training.jobs.secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class URICountKeyPartitioner extends Partitioner<URICountKey, IntWritable> {

	@Override
	public int getPartition(URICountKey key, IntWritable lw, int noOfPartitions) {
		return key.getUri().hashCode() % noOfPartitions;
	}

}
