package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.indexer.index.DocumentIndex;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadMapper extends
        Mapper<Object, Text, Text, Text> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Text keyVal = new Text();
    private Text valueVal = new Text();

    public void map(Object ignore, Text value, Context context
    ) throws IOException, InterruptedException {
        String json = value.toString();
        // see if this is a main thread or sub thread
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String fileName = fileSplit.getPath().getName();
        // TODO: this cant not be tested using mrunit now I guess, it does not support fileSplit?
        Map<String, DocumentIndex> indexMap = new HashMap<String, DocumentIndex>();
        if (fileName.startsWith("main")) {
            ForumMainThread forumMainThread = mapper.readValue(json, ForumMainThread.class);
            indexMap = ThreadIndexer.processDocument(forumMainThread);
        }
        if (fileName.startsWith("sub")) {
            ForumThread forumThread = mapper.readValue(json, ForumThread.class);
            indexMap = ThreadIndexer.processDocument(forumThread);
        }
        // loop through the map and output
        for (Map.Entry<String, DocumentIndex> entry : indexMap.entrySet()) {
            keyVal.set(entry.getKey());
            valueVal.set(mapper.writeValueAsString(entry.getValue()));
            context.write(keyVal, valueVal);
        }
    }
}
