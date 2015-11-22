package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.indexer.index.DocumentIndex;
import cn.edu.sjtu.at15.forum.indexer.index.InvertedIndex;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadReducer extends
        Reducer<Text, Text, Text, Text> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Text valueVal = new Text();

    // FIXME: can't test it because it's quite hard to generate the data used for reduce
    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // merge all the documentIndex to get the inverted index
        InvertedIndex invertedIndex = new InvertedIndex();
        String term = key.toString();
        Integer count = 0;
        invertedIndex.setTerm(term);
        for (Text val : values) {
            count++;
            invertedIndex.addDocument(mapper.readValue(val.toString(), DocumentIndex.class));
        }
        invertedIndex.setCount(count);
        valueVal.set(mapper.writeValueAsString(invertedIndex));
        context.write(key, valueVal);
    }
}
