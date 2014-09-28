package com.naltel.test.hadoop.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopKReducer extends Reducer<Text, LongWritable, Text, Text>{
	private int topk = 0;
	
	protected void setup(org.apache.hadoop.mapreduce.Reducer<Text,LongWritable,Text,Text>.Context context) throws IOException ,InterruptedException {
		topk = Integer.parseInt(context.getConfiguration().get("k"));
	};
	
		@Override
		protected void reduce(Text key, Iterable<LongWritable> value, Context context) throws IOException,
				InterruptedException {
			
			List<Long> list = new ArrayList<Long>();
			while (value.iterator().hasNext()) {
				list.add(Long.valueOf(value.iterator().next().get()));
			}			
			Collections.sort(list);
			
			String[] keyTopK = key.toString().split(",");
			String keyString = keyTopK[0];
			int topK = Integer.parseInt(keyTopK[1]);
			list = list.subList(0, topK);
			String valueString = "";
			
			Iterator<Long> listIterator = list.iterator();
			while(listIterator.hasNext()){
				valueString = valueString + "," + listIterator.next();
			}

			context.write(new Text(keyString), new Text(valueString));
			
		};
}
