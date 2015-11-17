package cn.edu.sjtu.at15.forum.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadTokenizeMapper extends
        Mapper<Object, Text, Text, Text> {
    private Text token = new Text();

    public void map(Object key, Text value, Context context
    ) throws IOException, InterruptedException {
        String json = value.toString();

    }
}
