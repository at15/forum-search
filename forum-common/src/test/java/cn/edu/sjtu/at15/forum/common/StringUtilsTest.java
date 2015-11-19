package cn.edu.sjtu.at15.forum.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gpl on 15/11/19.
 */
public class StringUtilsTest {
    @Test
    public void testBase64() {
        String p = "I am a new man looking for a new run la la la la";
        Assert.assertEquals(p, StringUtils.decodeBase64(StringUtils.encodeBase64(p)));
    }
}
