package com.naltel.hadoop.training.jobs;

/**
 * This example prepared for
 *
 * Brandeis University
 * cs147a
 * Spring 2008
 *
 * MultiFetch accepts one or more URL strings as input and outputs an
 * (url, title) pair for each one, where "title" is a string
 * containing the text between the html title tags.
 *
 * Outputs errors with System.err.println, which can be found in the
 * logs/userlogs/[map_id]/stderr directory under your root hadoop
 * directory.
 *
 * This class is a modification of the WordCount.java example included
 * with Hadoop in src/examples/org/apache/hadoop/examples/
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * This is an example Hadoop Map/Reduce application. It fetches the web page at
 * each input URL and extracts the title. The output is a single tuple for each
 * input URL: the URL and its associated title.
 * 
 * To run: bin/hadoop jar build/hadoop-examples.jar multifetch [-m <i>maps</i>]
 * [-r <i>reduces</i>] <i>urls</i> <i>out-dir</i>
 */
public class URLFetch implements Tool {
	/**
	 * Fetches web pages and extracts their titles.
	 */
	Configuration conf = new Configuration();

	public static class URLMap extends Mapper<LongWritable, Text, Text, Text> {

		private Text urlText = new Text();
		private Text titleText = new Text();

		public void map(LongWritable key, Text urls, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(urls.toString());
			while (itr.hasMoreTokens()) {
				String surl = itr.nextToken();
				try {
					URL url = new URL(surl);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					DataInputStream dis = new DataInputStream(is);
					String il;
					while ((il = dis.readLine()) != null) {
						if (il.trim().contains("<title>"))
							titleText.set(il);
					}
					urlText.set(surl);
					context.write(urlText, titleText);
				} catch (MalformedURLException e) {
					System.err.println("Malformed URL: " + surl);
				} catch (IllegalStateException e) {
					System.err.println("URL " + surl + " has no title");
				} catch (IOException e) {
					System.err.println("Cannot open " + surl + " (" + e.toString() + ")");
				}
			}
		}
	}

	/**
	 * A reducer class that just emits its input.
	 */
	public static class URLReduce extends Reducer<Text, Text, Text, Text> {

		public void reduce(Text key, Iterator<Text> values, Context context) throws IOException, InterruptedException {
			while (values.hasNext()) {
				context.write(key, values.next());
			}
		}
	}

	static int printUsage() {
		System.out.println("multifetch [-m nmaps] [-r nreduces] <inputs> <output>");
		ToolRunner.printGenericCommandUsage(System.out);
		return -1;
	}

	/**
	 * The main driver for word count map/reduce program. Invoke this method to
	 * submit the map/reduce job.
	 * 
	 * @throws IOException
	 *             When there is communication problems with the job tracker.
	 */

	public int run(String[] args) throws Exception {
		Job job = new Job(conf, "url data fetch");
		job.setMapperClass(URLMap.class);
		job.setCombinerClass(URLReduce.class);
		job.setReducerClass(URLReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new URLFetch(), args);
		System.exit(res);
	}

	@Override
	public Configuration getConf() {
		return conf;
	}

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

}
