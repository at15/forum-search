package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import cn.edu.sjtu.at15.forum.common.StringUtils;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;

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
        try {
            ForumThread thread = resultItems.get("thread");
            if (thread != null) {
                write(thread);
            }
        } catch (IOException ex) {
            LOGGER.warn("unable to save thread to file due to ", ex);
        }

        try {
            ForumMainThread mainThread = resultItems.get("main-thread");
            if (mainThread != null) {
                write(mainThread);
            }
        } catch (IOException ex) {
            LOGGER.warn("unable to save thread to file due to ", ex);
        }
    }

    public void write(ForumThread thread) throws IOException {
        if (thread.getUrl() == null) {
            throw new InvalidObjectException("thread is not valid, lack some attrs like url");
        }
        write(thread.getUrl(), thread);
    }

    public void write(String url, ForumThread thread) throws IOException {
        LOGGER.debug("current directory " + System.getProperty("user.dir"));
        String fileName;
        if (thread instanceof ForumMainThread) {
            fileName = baseFolder + "/main-" + StringUtils.encodeBase64(url) + ".json";
        } else {
            fileName = baseFolder + "/sub-" + StringUtils.encodeBase64(url) + ".json";
        }
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        mapper.writeValue(file, thread);
    }
}
