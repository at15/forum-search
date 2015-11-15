package cn.edu.sjtu.at15.forum.uploader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gpl on 15/11/15.
 */
public class HdfsClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HdfsClient.class);

    protected Configuration conf;
    protected FileSystem hdfs;

    public HdfsClient() throws IOException {
        conf = new Configuration();
        conf.addResource(new Path("core-site.xml"));
        conf.addResource(new Path("hdfs-site.xml"));
        hdfs = FileSystem.get(conf);

    }

    public void upload(String src, String dst) throws Exception {
        // handle the input
        // TODO: check file and catch
        File srcFile = new File(src);
        upload(srcFile, dst);
    }

    public void upload(File srcFile, String dst) throws IOException {
        LOGGER.info("start uploading " + srcFile.getName() + " to " + dst);
        FileInputStream inputStream = new FileInputStream(srcFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        Path dstPath = new Path(dst);
        FSDataOutputStream writer = hdfs.create(dstPath, (short) 1);
        byte[] buffer = new byte[1024];
        int index;


        while (-1 != (index = bufferedInputStream.read(buffer, 0, buffer.length))) {
            writer.write(buffer, 0, index);
        }
        writer.close();
        inputStream.close();
        bufferedInputStream.close();

        LOGGER.info("finish uploading " + srcFile.getName() + " to " + dst);
    }

    public static void main(String[] args) throws Exception {
        HdfsClient client = new HdfsClient();
        client.upload("data/a.txt", "/usr/at15/tmp3/a.txt");
    }
}
