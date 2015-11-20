package cn.edu.sjtu.at15.forum.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/20.
 */
public class FileUtilsTest {
    @Test
    public void testReadFileAsString() {
        Assert.assertEquals("I dont think you can keep up", FileUtils.readFileAsString("fixture/test.txt"));
    }
}
