package com.naltel.hadoop.training.jobs.inputformatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.hadoop.util.ReflectionUtils;

public class TotalOrderPrep {
   private static final String ARG_SAMPLESIZE = "top.sample.size";
   private static final String ARG_INPUTFORMAT = "top.inputformat.class";
   private static final String ARG_MAP_SAMPLES = "top.map.samples";
   private static final int MAP_SAMPLES = 5000;

   public static void apply(Job job) throws IOException, ClassNotFoundException{

      Job sample_job = new Job(new Configuration(job.getConfiguration()));

      // If there's only one reduce task, don't bother with total order
      // partitioning.
      if( job.getNumReduceTasks() == 1 ) {
         job.setPartitionerClass( HashPartitioner.class );
         return;
      }

      // Remember the real input format so the sampling input format can use
      // it under the hood
      sample_job.getConfiguration().setClass( ARG_INPUTFORMAT, job.getInputFormatClass(), InputFormat.class);
      sample_job.setInputFormatClass(SamplingInputFormat.class);

      // Base the sample size on the number of reduce tasks that will be used
      // by the real job, but only use 1 reducer for this job (maps output very
      // little)
      sample_job.getConfiguration().setInt(ARG_SAMPLESIZE, job.getNumReduceTasks());
      sample_job.setNumReduceTasks(1);

      // Make this job's output a temporary filethe input file for the real job's
      // TotalOrderPartitioner
      Path partition = new Path("/tmp/topPartition/" + UUID.randomUUID());
      partition.getFileSystem(job.getConfiguration()).deleteOnExit(partition);

      FileOutputFormat.setOutputPath(sample_job, partition);
      TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path(partition, "part-r-00000"));
      job.setPartitionerClass(TotalOrderPartitioner.class);

      // If there's a combiner, turn it into an identity reducer to prevent
      // destruction of keys.
      if( null != sample_job.getCombinerClass() )
         sample_job.setCombinerClass(Reducer.class);


      // Setup output classes to prevent cast exceptions while using
      // the main job's mapper.
      sample_job.setOutputKeyClass( job.getMapOutputKeyClass() );
      sample_job.setOutputValueClass( NullWritable.class );
      sample_job.setMapOutputValueClass( job.getMapOutputValueClass() );


      // Use the default partitioner, our sampling reducer and produce
      // a sequence file.
      sample_job.setPartitionerClass( HashPartitioner.class );
      sample_job.setReducerClass( PartitioningReducer.class );
      sample_job.setOutputFormatClass( SequenceFileOutputFormat.class );

      sample_job.setJobName( job.getJobName() + " (Sampler)" );

      // Run the job.  If it fails, then it's probably because of the main job.
      try {
         sample_job.setJarByClass(TotalOrderPrep.class);
         sample_job.waitForCompletion(false);

         if( !sample_job.isSuccessful() )
            throw new RuntimeException("Partition sampler job failed.");

      } catch (Exception e) {
         throw new RuntimeException("Failed to start Partition sampler.", e);
      }
   }


   /**
    * An input format that makes a record reader which passes few KV pairs to the mapper
    */
   public static class SamplingInputFormat extends FileInputFormat {
      @Override
      public RecordReader createRecordReader(
            InputSplit split, TaskAttemptContext context) throws IOException,
            InterruptedException {

         Configuration conf = context.getConfiguration();

         @SuppressWarnings("unchecked")
         InputFormat realif = (InputFormat)
            ReflectionUtils.newInstance(conf.getClass(ARG_INPUTFORMAT, TextInputFormat.class), conf);

         return new SamplingRecordReader(realif.createRecordReader(split, context), context);
      }
   }

   /**
    * A record reader which only passes through a reduced set of KV pairs.
 * @param <K>
 * @param <V>
    */
   public static class SamplingRecordReader<K, V> extends RecordReader {

      private ArrayList key_buffer = new ArrayList();
      private ArrayList val_buffer = new ArrayList();
      int current = -1;
      RecordReader real;

      SamplingRecordReader(RecordReader real, TaskAttemptContext context) throws InterruptedException, IOException {
        this.real = real;
      }

      @Override
      public void close() throws IOException {
      }

      @Override
      public K getCurrentKey() throws IOException, InterruptedException {
         if( current < key_buffer.size() )
            return (K) key_buffer.get(current);
         return null;
      }

      @Override
      public V getCurrentValue() throws IOException, InterruptedException {
         if( current < val_buffer.size() )
            return (V) val_buffer.get(current);
         return null;
      }

      @Override
      public boolean nextKeyValue() throws IOException, InterruptedException {
      	// TODO Auto-generated method stub
      	return false;
      }
      
      @Override
      public float getProgress() throws IOException, InterruptedException {
         return real.getProgress();
      }

      @Override
      public void initialize(InputSplit split,
                             TaskAttemptContext context
                             ) throws IOException, InterruptedException {

         // Read in all of the data using a reservoir sampling collection.
         real.initialize(split, context);
         Configuration conf = context.getConfiguration();
         double seen = 0.0;
/*
         int samples = context.getConfiguration().getInt(ARG_MAP_SAMPLES, MAP_SAMPLES);
         while( real.nextKeyValue() ) {
            if( key_buffer.size() < samples ){
               key_buffer.add(WritableUtils.clone(real.getCurrentKey(), conf));
               val_buffer.add(WritableUtils.clone(real.getCurrentValue(), conf));
            }

            else if( Math.random() = key_buffer.size() )
            return false;

         ++current;
         return true;
         */
      }
   }

   /**
    * This reducer only emits enough keys to fill the partition file.
    */
   public static class PartitioningReducer extends Reducer {

      public void run(Context context) throws IOException, InterruptedException {
         // I wish there was a better way to get this, but alas Task.Counter
         // is protected...
         long N = context.getCounter("org.apache.hadoop.mapred.Task$Counter",
                                    "MAP_OUTPUT_RECORDS").getValue();
         long K = context.getConfiguration().getInt(ARG_SAMPLESIZE, 1);

         // Set the collection rate so that it will collects more frequently
         // than ideal.  It makes the last partition very slightly larger,
         // but avoids corner cases caused by round off errors, and keeps
         // the reducer from having to scan keys in the last partition.
         double collect_rate = Math.max(1.0, (N-1) / (double)K);
         double emit = 0.0;
         int collected = 0;

         while( collected < K-1 && context.nextKey() ) {
            emit += 1.0;
            if( emit > collect_rate ) {
               context.write(context.getCurrentKey(), NullWritable.get());
               emit -= collect_rate;
               ++collected;
            }
         }
      }

   }

}