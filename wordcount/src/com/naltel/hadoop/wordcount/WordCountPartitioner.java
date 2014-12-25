package com.naltel.hadoop.wordcount;
//package org.mapred.jobs.wordcount.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordCountPartitioner extends Partitioner<Text, LongWritable>{

	@Override
	public int getPartition(Text key, LongWritable value, int noOfReducers) {
		String tempString = key.toString();
		return (tempString.toLowerCase().charAt(0)-'a')%noOfReducers;
	}

}