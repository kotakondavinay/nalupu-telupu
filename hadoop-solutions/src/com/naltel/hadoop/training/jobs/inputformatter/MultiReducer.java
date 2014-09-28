package com.naltel.hadoop.training.jobs.inputformatter;
/*

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MultiReducer extends Reducer<Text, Text, Text, Text> {
    MultipleOutputs mos;

    @override
    public void setup(Context context) {
        mos = new MultipleOutputs(context);
    }

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            String str = value.toString();
            String[] items = str.split("\t");

            mos.write(fruitOutputName, NullWritable.get(), new Text(items[1]));
            mos.write(colorOutputName, NullWritable.get(), new Text(items[2]));
        }
    }

    @override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}*/
