package com.naltel.hadoop.hive.udf.examples;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;

@Description(name = "movingavg", value = "_FUNC_(expr, ticks) - Returns the moving average for the set of numbers")
public class UDFMovingAverage extends UDF {

	private DoubleWritable result = new DoubleWritable();
	private static ValueStack stack = new ValueStack();

	public static class ValueStack {
		ArrayList<Double> store = new ArrayList<Double>();
		int size = 0;
		int current = -1;
		int count = 0;

		public void reset() {
			size = 0;
			current = -1;
			count = 0;
		}

		public void setSize(int size) {
			if (size > 0)
				this.size = size;
			else
				size = 1;
		}

		public void push(double d) {
			current = (current + 1) % size;
			store.add(current, d);
			count++;
		}

		public double pop() {
			Double sum = 0.0;
			for (int i = 0; i < size; i++) {
				if (i + 1 > count) {
					sum += store.get(current);
					continue;
				}
				sum += store.get(i);
			}
			return sum / size;
		}

		public int getCount() {
			return count;
		}
	}

	public UDFMovingAverage() {
		stack.reset();
	}

	public DoubleWritable evaluate(DoubleWritable a, DoubleWritable b) {
		if (a == null || b == null) {
			return null;
		} else {
			stack.setSize((int) b.get());
			stack.push(a.get());
			if (stack.getCount() <= b.get()) {
				result.set(a.get());
			} else {
				result.set(stack.pop());
			}
			return result;
		}
	}
}
