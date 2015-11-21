package cn.edu.sjtu.at15.forum.indexer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadReducerTest {
    private ReduceDriver<Text, Text, Text, Text> reduceDriver;

    @Before
    public void setUp() {
        ThreadReducer reducer = new ThreadReducer();
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
    }

//    @Test
//    public void testReducer() throws IOException {
//        List<Text> values = new ArrayList<Text>();
//        values.add(new Text("http://www.baidu.com;250;0"));
//        values.add(new Text("http://www.baidu.com;250;1"));
//        values.add(new Text("http://www.google.com;250;1"));
//        reduceDriver.withInput(new Text("a"), values);
//        reduceDriver.withOutput(new Text("a"), new Text("http://www.baidu.com;250;0,1;;http://www.google.com;250;1"));
//        reduceDriver.runTest();
//    }
}
