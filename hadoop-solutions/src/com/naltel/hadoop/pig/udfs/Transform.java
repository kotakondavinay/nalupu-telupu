package com.naltel.hadoop.pig.udfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.UDFContext;

public class Transform extends EvalFunc<String> {
	public String exec(Tuple input) throws IOException {
		if (input == null || input.size() == 0) {
			return null;
		}
		Configuration conf = UDFContext.getUDFContext().getJobConf();
		String from = conf.get("replace.string");
		if (from == null) {
			throw new IOException("replace.string should not be null");
		}
		String to = conf.get("replace.by.string");
		if (to == null) {
			throw new IOException("replace.by.string should not be null");
		}
		try {
			String str = (String) input.get(0);
			if(str == null)
				return null;	
			return str.replace(from, to);
		} catch (Exception e) {
			throw new IOException("Caught exception processing input row", e);
		}
	}
}
