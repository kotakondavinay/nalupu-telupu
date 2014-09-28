package com.naltel.hadoop.hive.test;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class HadoopTest {

	public static String fsURI = "hdfs://master:9000";

	public static void copyFileToDFS(FileSystem fs, String srcFile, String dstFile) throws IOException {
		try {
			System.out.println("Initialize copy...");
			URI suri = new URI(srcFile);
			URI duri = new URI(fsURI + "/" + dstFile);
			Path dst = new Path(duri.toString());
			Path src = new Path(suri.toString());
			System.out.println("Start copy...");
			fs.copyFromLocalFile(src, dst);
			System.out.println("End copy...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFileFromDFS(FileSystem fs, String srcFile, String dstFile) throws IOException {
		try {
			System.out.println("Initialize copy...");
			URI suri = new URI(fsURI + "/" + srcFile);
			URI duri = new URI(dstFile);
			Path dst = new Path(duri.toString());
			Path src = new Path(suri.toString());
			System.out.println("Start copy...");
			fs.copyToLocalFile(src, dst);
			System.out.println("End copy...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Test Start.....");
			Configuration conf = new Configuration();
			DistributedFileSystem fs = new DistributedFileSystem();
			URI duri = new URI(fsURI);
			fs.initialize(duri, conf);
			long start = 0, end = 0;
			start = System.nanoTime();
			// writing data from local to HDFS
			copyFileToDFS(fs, "/home/vinaykk/workh/input/wordpair.txt", "/input/raptor/trade1.txt");
			// Writing data from HDFS to Local
			copyFileFromDFS(fs, "/input/raptor/trade1.txt", "/home/vinaykk/workh/input/wordpair1.txt");
			end = System.nanoTime();
			System.out.println("Total Execution times: " + (end - start));
			fs.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
