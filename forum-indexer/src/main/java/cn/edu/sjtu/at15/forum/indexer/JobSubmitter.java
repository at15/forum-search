package cn.edu.sjtu.at15.forum.indexer;

import cn.edu.sjtu.at15.forum.indexer.mapreduce.ThreadMapper;
import cn.edu.sjtu.at15.forum.indexer.mapreduce.ThreadReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by gpl on 15/11/17.
 */
public class JobSubmitter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSubmitter.class);

    public void runWordCount(String inputPath, String outputPath) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf, "thread indexer word count");
            job.setJarByClass(JobSubmitter.class);
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

    public void runIndexThread(String inputPath, String outputPath) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf, "thread indexer");
            job.setJarByClass(JobSubmitter.class);
            job.setMapperClass(ThreadMapper.class);
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
        JobSubmitter submitter = new JobSubmitter();
        submitter.runIndexThread(args[0], args[1]);
    }
}
