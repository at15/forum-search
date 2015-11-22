package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.common.StringUtils;
import cn.edu.sjtu.at15.forum.indexer.index.DocumentIndex;
import cn.edu.sjtu.at15.forum.indexer.index.InvertedIndex;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadReducer extends
        Reducer<Text, Text, Text, Text> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Text keyVal = new Text();
    private Text valueVal = new Text();
    private MultipleOutputs<Text, Text> mos;

    public void setup(Context context) {
        mos = new MultipleOutputs<Text, Text>(context);
    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }

    // FIXME: can't test it because it's quite hard to generate the data used for reduce
    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // merge all the documentIndex to get the inverted index
        InvertedIndex invertedIndex = new InvertedIndex();
        String term = key.toString();
        String termIndexFileName = StringUtils.encodeBase64(term) + ".term.idx";
        Integer count = 0;
        DocumentIndex documentIndex;
        for (Text val : values) {
            count++;
            documentIndex = mapper.readValue(val.toString(), DocumentIndex.class);
//            invertedIndex.addDocument(mapper.readValue(val.toString(), DocumentIndex.class));
            keyVal.set(documentIndex.getUrl());
            mos.write("term", keyVal, val, termIndexFileName);
        }
        invertedIndex.setTerm(term);
        invertedIndex.setCount(count);
//        valueVal.set(mapper.writeValueAsString(invertedIndex));
        // TODO: count is not used
        valueVal.set(termIndexFileName);
        context.write(key, valueVal);
    }
}
