package cn.edu.sjtu.at15.forum.crawler.discuz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import cn.edu.sjtu.at15.forum.common.StringUtils;
import cn.edu.sjtu.at15.forum.crawler.entity.ForumThread;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;

/**
 * Created by gpl on 15/11/16.
 */
public class JsonFilePipline implements Pipeline {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonFilePipline.class);
    private final String baseFolder;
    private final static ObjectMapper mapper = new ObjectMapper();

    public JsonFilePipline(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public void process(ResultItems resultItems, Task task) {
        ForumThread thread = resultItems.get("thread");
        try {
            write(thread);
        }catch (IOException ex){
            LOGGER.warn("unable to save thread to file due to ",ex);
        }
    }

    public void write(ForumThread thread) throws IOException {
        if (!thread.isValid()) {
            throw new InvalidObjectException("thread is not valid, lack some attrs like url");
        }
        write(thread.getUrl(), thread);
    }

    public void write(String url, ForumThread thread) throws IOException {
        LOGGER.debug("current directory " + System.getProperty("user.dir"));
        File file = new File(baseFolder + "/" + StringUtils.encodeBase64(url) + ".json");
        if (!file.exists()) {
            file.createNewFile();
        }
        mapper.writeValue(file, thread);
    }
}
