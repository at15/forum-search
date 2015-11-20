package cn.edu.sjtu.at15.forum.indexer;

import cn.edu.sjtu.at15.forum.common.entity.Separator;
import cn.edu.sjtu.at15.forum.tokenizer.Token;
import cn.edu.sjtu.at15.forum.tokenizer.TokenInThread;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by gpl on 15/11/17.
 */
public class ThreadReducer extends
        Reducer<Text, Text, Text, Text> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadReducer.class);

    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        Map<String, TokenInThread> urlTokenInThreadMap = new HashMap<String, TokenInThread>();
        String term = key.toString();
        Token t;
        // merge terms in one thread first, this is in fact reducer
        for (Text val : values) {
            // TODO: this will cause error if we have combiner
            t = Token.fromInfo(val.toString(), term);
            if (!urlTokenInThreadMap.containsKey(t.getUrl())) {
                urlTokenInThreadMap.put(t.getUrl(), new TokenInThread());
            }
            urlTokenInThreadMap.get(t.getUrl()).addToken(t);
        }
        // merge all threads into multiple info
        Collection<TokenInThread> threads = urlTokenInThreadMap.values();
        result.set(StringUtils.join(threads, Separator.THREAD_SEP));
        context.write(key, result);
    }
}
