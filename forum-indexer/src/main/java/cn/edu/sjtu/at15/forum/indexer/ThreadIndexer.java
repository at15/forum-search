package cn.edu.sjtu.at15.forum.indexer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadIndexer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadIndexer.class);

    public void runWordCount(String inputPath, String outputPath) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf, "thread indexer word count");
            job.setJarByClass(ThreadIndexer.class);
            job.setMapperClass(WordCountTokenizerMapper.class);
            job.setCombinerClass(WordCountIntSumReducer.class);
            job.setReducerClass(WordCountIntSumReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
            job.submit();
        } catch (IOException ex) {
            LOGGER.error("got io exception for job", ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("got class not found ", ex);
        } catch (InterruptedException ex) {
            LOGGER.error("got interrupted ", ex);
        }
    }

    public void runThreadIndex(String inputPath, String outputPath) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf, "thread indexer");
            job.setJarByClass(ThreadIndexer.class);
//            job.setMapperClass(ThreadTokenizeMapper.class);
            job.setReducerClass(ThreadReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
            job.waitForCompletion(true);
        } catch (IOException ex) {
            LOGGER.error("got io exception for job", ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("got class not found ", ex);
        } catch (InterruptedException ex) {
            LOGGER.error("got interrupted ", ex);
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadIndexer indexer = new ThreadIndexer();
        indexer.runThreadIndex(args[0], args[1]);
    }
}
