package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.SequenceFile.Writer;

public class SequenceFileGenerator {

	private static final int BUFFER_SIZE = 2 * 1024 * 1024;

	public static void main(String[] args) throws IOException {
		Writer writer = null;
		Configuration conf = new Configuration();
		Path inputDir = new Path(args[0]);
		Path seqFile = new Path(args[1]);

		try {
			writer = SequenceFile.createWriter(seqFile.getFileSystem(conf), conf, seqFile, Text.class,
					Text.class, CompressionType.BLOCK);
			FileSystem fs = inputDir.getFileSystem(conf);
			BytesWritable content = new BytesWritable();
//			Text content = new Text();
			Text fileName = new Text();
			byte[] data = new byte[BUFFER_SIZE];
			for (FileStatus file : fs.listStatus(inputDir)) {
				FSDataInputStream stream = fs.open(file.getPath());
				stream.read(data);
				content.set(data, 0, (int) file.getLen());
				fileName.set(file.getPath().getName());
				writer.append(fileName, new Text(new String(content.getBytes())));
				stream.close();
			}
			writer.close();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
