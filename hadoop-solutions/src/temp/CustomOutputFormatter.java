package temp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class CustomOutputFormatter extends OutputFormat<Text, Text> {

	static Map<Text, Text> metrics = new HashMap<Text, Text>();

	@Override
	public void checkOutputSpecs(JobContext arg0) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public OutputCommitter getOutputCommitter(TaskAttemptContext arg0) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext arg0) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	static class CustomRecordWriter extends RecordWriter<Text, Text> {

		@Override
		public void close(TaskAttemptContext arg0) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

		}

		@Override
		public void write(Text arg0, Text arg1) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			metrics.put(arg0, arg1);
		}

	}

}
