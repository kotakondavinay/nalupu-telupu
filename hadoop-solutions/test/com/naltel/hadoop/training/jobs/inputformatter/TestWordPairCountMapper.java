package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.naltel.hadoop.training.jobs.inputformatter.WordPairCountKey;
import com.naltel.hadoop.training.jobs.inputformatter.WordPairCountMapper;
import com.naltel.hadoop.training.jobs.inputformatter.WordPairCountReducer;

public class TestWordPairCountMapper {

	private MapDriver<Text, Text, WordPairCountKey, LongWritable> mapDriver;
	private ReduceDriver<WordPairCountKey, LongWritable, WordPairCountKey, LongWritable> reduceDriver;
	private MapReduceDriver<Text, Text, WordPairCountKey, LongWritable, WordPairCountKey, LongWritable> mrDriver;

	@Before
	public void setup() {
		WordPairCountMapper wpcm = new WordPairCountMapper();
		mapDriver = new MapDriver<Text, Text, WordPairCountKey, LongWritable>();
		mapDriver.setMapper(wpcm);
		WordPairCountReducer wpcr = new WordPairCountReducer();
		reduceDriver = new ReduceDriver<WordPairCountKey, LongWritable, WordPairCountKey, LongWritable>();
		reduceDriver.setReducer(wpcr);
		mrDriver = new MapReduceDriver<Text, Text, WordPairCountKey, LongWritable, WordPairCountKey, LongWritable>();
		mrDriver.setMapper(wpcm);
		mrDriver.setReducer(wpcr);
	}

	@Test
	public void testMapper() throws IOException {
		Text key = new Text("key");
		Text val = new Text("val");
		mapDriver.withInput(key, val);
		List<Pair<WordPairCountKey, LongWritable>> output = mapDriver.run();
		Pair<WordPairCountKey, LongWritable> pair = output.get(0);
		WordPairCountKey actualKey = new WordPairCountKey();
		actualKey.setWord1("key");
		actualKey.setWord2("val");
		Assert.assertEquals(pair.getFirst().toString(), actualKey.toString());
		Assert.assertEquals(pair.getSecond().get(), 1l);
	}

	@Test
	public void testReducer() throws IOException {
		WordPairCountKey key = new WordPairCountKey();
		key.setWord1("key");
		key.setWord2("val");
		List<LongWritable> list = new ArrayList<LongWritable>();
		list.add(new LongWritable(1));
		list.add(new LongWritable(1));
		list.add(new LongWritable(1));
		list.add(new LongWritable(1));
		list.add(new LongWritable(1));
		reduceDriver.withInputKey(key);
		reduceDriver.withInputValues(list);
		List<Pair<WordPairCountKey, LongWritable>> output = reduceDriver.run();
		Pair<WordPairCountKey, LongWritable> pair = output.get(0);
		WordPairCountKey actualKey = new WordPairCountKey();
		actualKey.setWord1("key");
		actualKey.setWord2("val");
		Assert.assertEquals(pair.getFirst().toString(), actualKey.toString());
		Assert.assertEquals(pair.getSecond().get(), 5l);
	}

	@Test
	public void testMR() throws IOException {
		Text key = new Text("key");
		Text val = new Text("val");
		mrDriver.addInput(key, val);
		mrDriver.addInput(key, val);
		mrDriver.addInput(key, val);
		mrDriver.addInput(key, val);
		mrDriver.addInput(key, val);
		List<Pair<WordPairCountKey, LongWritable>> output = mrDriver.run();
		Pair<WordPairCountKey, LongWritable> pair = output.get(0);
		WordPairCountKey actualKey = new WordPairCountKey();
		actualKey.setWord1("key");
		actualKey.setWord2("val");
		Assert.assertEquals(pair.getFirst().toString(), actualKey.toString());
		Assert.assertEquals(pair.getSecond().get(), 5l);
	}

}
