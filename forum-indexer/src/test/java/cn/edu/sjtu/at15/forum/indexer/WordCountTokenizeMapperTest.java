package cn.edu.sjtu.at15.forum.indexer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gpl on 15/11/17.
 */
public class WordCountTokenizeMapperTest {
    private MapDriver<Object, Text, Text, IntWritable> mapDriver;

    @Before
    public void setUp() {
        WordCountTokenizerMapper mapper = new WordCountTokenizerMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() throws IOException {
        mapDriver.withInput(new Text("a"), new Text("a b c"));
        mapDriver.withOutput(new Text("a"), new IntWritable(1));
        mapDriver.withOutput(new Text("b"), new IntWritable(1));
        mapDriver.withOutput(new Text("c"), new IntWritable(1));
        mapDriver.runTest();
    }
}
