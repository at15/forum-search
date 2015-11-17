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

    // use word count for test
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public void run(String inputPath, String outputPath) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf, "thread indexer");
            job.setJarByClass(ThreadIndexer.class);
            job.setMapperClass(TokenizerMapper.class);
            job.setCombinerClass(IntSumReducer.class);
            job.setReducerClass(IntSumReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
            job.submit();
        } catch (IOException ex) {
            LOGGER.error("got io exception for job", ex);
        } catch (ClassNotFoundException ex){
            LOGGER.error("got class not found ", ex);
        } catch (InterruptedException ex){
            LOGGER.error("got interrupted ", ex);
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadIndexer indexer = new ThreadIndexer();
        indexer.run("/user/at15/input","/user/at15/output/1");
    }
}
