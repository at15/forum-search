package cn.edu.sjtu.at15.forum.indexer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadTokenizeMapperTest {
    MapDriver<Object, Text, Text, Text> mapDriver;

    @Before
    public void setUp() {
        ThreadTokenizeMapper mapper = new ThreadTokenizeMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() throws IOException {
        String json = "{\"url\":\"http://www.baidu.com\",\"title\":\"我是好人\",\"viewCount\":883,\"replyCount\":25,\"author\":\"qreety\",\"authorPost\":\"我是作者\"}";
        mapDriver.withInput(new Text("no use"),new Text(json));
        mapDriver.withOutput(new Text("我"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("是"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("好"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("人"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("我"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("是"),new Text("http://www.baidu.com"));
        mapDriver.withOutput(new Text("作者"),new Text("http://www.baidu.com"));
        mapDriver.runTest();
    }
}
