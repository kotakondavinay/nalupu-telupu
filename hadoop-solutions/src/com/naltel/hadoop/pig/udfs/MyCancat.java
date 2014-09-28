package com.naltel.hadoop.pig.udfs;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class MyCancat extends EvalFunc<String> {

	@Override
	public String exec(Tuple input) throws IOException {
		if (input == null || input.size() == 0) {
			return null;
		}
		String str1 = (String) input.get(0);
		String str2 = (String) input.get(1);
		return str1 + str2;
	}

}
