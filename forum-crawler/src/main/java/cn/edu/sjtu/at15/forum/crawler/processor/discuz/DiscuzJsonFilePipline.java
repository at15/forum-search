package cn.edu.sjtu.at15.forum.crawler.processor.discuz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.IOException;

/**
 * Created by gpl on 15/11/16.
 */
public class DiscuzJsonFilePipline implements Pipeline {
    private final static Logger LOGGER = LoggerFactory.getLogger(DiscuzJsonFilePipline.class);
    private final String baseFolder;
    private final static ObjectMapper mapper = new ObjectMapper();

    public DiscuzJsonFilePipline(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public void process(ResultItems resultItems, Task task) {
        DiscuzThread thread = resultItems.get("thread");
        // TODO: thread logic here

    }

    public void write(String url, DiscuzThread thread) throws IOException {
        LOGGER.debug("current directory " + System.getProperty("user.dir"));
        File file = new File(baseFolder + "/" + DiscuzStringUtils.encodeBase64(url));
        if(!file.exists()){
            file.createNewFile();
        }
        mapper.writeValue(file, thread);
    }
}
