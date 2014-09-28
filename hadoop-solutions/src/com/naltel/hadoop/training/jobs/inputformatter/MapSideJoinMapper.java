package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.TupleWritable;

public class MapSideJoinMapper extends MapReduceBase implements
		Mapper<Text, TupleWritable, Text, LongWritable> {

	private LongWritable value10 = new LongWritable();

	@Override
	public void map(Text key, TupleWritable value,
			OutputCollector<Text, LongWritable> context, Reporter reporter)
			throws IOException {
		if (value.toString().length() == 2) {
			Long value1 = Long.valueOf(value.get(0).toString());
			Long value2 = Long.valueOf(value.get(1).toString());
			value10.set(value1 + value2);
			context.collect(key, value10);
		}
	}

}
