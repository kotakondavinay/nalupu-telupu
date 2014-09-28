package temp;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.TextInputFormat;

public class TestTextInputFormat extends TextInputFormat {

	@Override
	protected boolean isSplitable(FileSystem fs, Path file) {
		return false;
	}

}
