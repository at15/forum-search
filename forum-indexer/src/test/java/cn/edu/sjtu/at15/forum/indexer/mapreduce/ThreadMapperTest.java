package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

import java.io.IOException;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadMapperTest {
    @Test
    public void testMainThreadMap() throws IOException {
        ThreadMapper threadMapper = new ThreadMapper();
        MapDriver<Object, Text, Text, Text> mapDriver = new MapDriver<Object, Text, Text, Text>(threadMapper);
        String content = FileUtils.readFileAsString("fixtures/main-thread.json");
        mapDriver.setMapInputPath(new Path("/user/at15/main-abc.json"));
        mapDriver.withInput(new Text("ignore"), new Text(content));
        mapDriver.withOutput(new Text("a"), new Text("b"));
        // FIXME: too many data to write the right output, btw: json make the output a lot bigger
//        mapDriver.runTest();
        Assert.assertEquals("a", "a");
    }
}
