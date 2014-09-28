package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueLineRecordReader;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;
import org.apache.hadoop.mapred.lib.CombineFileRecordReader;
import org.apache.hadoop.mapred.lib.CombineFileSplit;

public class MyCombineFileInputFormat extends CombineFileInputFormat
{

	public static class MyKeyValueLineRecordReader implements RecordReader
	{
		private final KeyValueLineRecordReader delegate;

		public MyKeyValueLineRecordReader(CombineFileSplit split, Configuration conf, Reporter reporter, Integer idx) throws IOException
		{
			FileSplit fileSplit = new FileSplit(split.getPath(idx), split.getOffset(idx), split.getLength(idx), split.getLocations());
			delegate = new KeyValueLineRecordReader(conf, fileSplit);
		}

		public boolean next(Text key, Text value) throws IOException
		{
			return delegate.next(key, value);
		}

		@Override
		public Text createKey()
		{
			return delegate.createKey();
		}

		@Override
		public Text createValue()
		{
			return delegate.createValue();
		}

		@Override
		public long getPos() throws IOException
		{
			return delegate.getPos();
		}

		@Override
		public void close() throws IOException
		{
			delegate.close();
		}

		@Override
		public float getProgress() throws IOException
		{
			return delegate.getProgress();
		}

		@Override
		public boolean next(Object key, Object value) throws IOException
		{
			// TODO Auto-generated method stub
			return false;
		}
	}

	@Override
	public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException
	{
		return new CombineFileRecordReader(job, (CombineFileSplit) split, reporter, (Class) MyKeyValueLineRecordReader.class);
	}
}