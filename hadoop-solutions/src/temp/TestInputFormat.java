package temp;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class TestInputFormat implements InputFormat<LongWritable, Text> {

	// MapRunner
	@Override
	public RecordReader<LongWritable, Text> getRecordReader(InputSplit arg0, JobConf arg1, Reporter arg2)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	// JobClient--->JobTracker
	@Override
	public InputSplit[] getSplits(JobConf arg0, int arg1) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
