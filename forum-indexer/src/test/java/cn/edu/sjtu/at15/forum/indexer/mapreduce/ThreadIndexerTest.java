package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.common.FileUtils;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;

import cn.edu.sjtu.at15.forum.indexer.index.DocumentIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadIndexerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testProcessDocument() throws IOException {
        ForumThread forumThread = mapper.readValue(FileUtils.readFileAsString("fixtures/sub-thread.json"), ForumThread.class);
        Map<String, DocumentIndex> indexMap = ThreadIndexer.processDocument(forumThread);
        Assert.assertEquals((long) 141, indexMap.size());
//        System.out.println(indexMap);
    }
}
