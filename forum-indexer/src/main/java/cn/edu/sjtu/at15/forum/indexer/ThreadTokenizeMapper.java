package cn.edu.sjtu.at15.forum.indexer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadTokenizeMapper extends
        Mapper<Object, Text, Text, Text> {
    private Text term = new Text();
    private Text info = new Text();
//    private DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
//
//    public void map(Object key, Text value, Context context
//    ) throws IOException, InterruptedException {
//        String json = value.toString();
//        List<Token> tokens = tokenizer.tokenizeThread(json);
//        for (Token token : tokens) {
//            // TODO: change url to real info, including rank, position, url
//            term.set(token.getTerm());
//            info.set(token.toInfo());
//            context.write(term, info);
//        }
//    }
}
