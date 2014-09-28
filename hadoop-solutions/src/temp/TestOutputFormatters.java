package temp;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCommitter;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.util.Progressable;

public class TestOutputFormatters implements OutputFormat<Text, Text> {

	@Override
	public void checkOutputSpecs(FileSystem arg0, JobConf arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public RecordWriter<Text, Text> getRecordWriter(FileSystem arg0, JobConf arg1, String arg2, Progressable arg3)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	// OutputCommitter copy the map output from attempt dir's to output dirs.

}
