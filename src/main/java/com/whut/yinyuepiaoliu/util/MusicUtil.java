package com.whut.yinyuepiaoliu.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;

public class MusicUtil {

    private static final Logger logger = LoggerFactory.getLogger(MusicUtil.class);

    private static final int defaultPage = 0;
    private static final int defaultPageCount = 10;

    // 从酷我音乐API获取音乐信息
    private String searchAPIFromKUWO = "http://search.kuwo.cn/r.s?all={0}&ft=music&itemset=web_2013&client=kt&pn={1}&rn={2}&rformat=json&encoding=utf8";

    public ServerResponse searchMusicFromKUWO(String key, int page, int pageCount) {
        // 拼接搜索音乐的url
        String searchURL = MessageFormat.format(searchAPIFromKUWO, key, page, pageCount);
        ServerResponse response = this.get(searchURL);
        if (response.isSuccess()) {
            //                    String d =  EntityUtils.toString(entity);
//                    String dd = d.replaceAll("\'","\"");
//                    String ddd = dd.replaceAll("\'\'","\"\"");
//                    System.out.println(ddd);
            System.out.println(response.getData());
            return response;
        }
        return null;
    }

    // 从酷我音乐API获取音乐信息
    public ServerResponse searchMusicFromKUWO(String key) {
        return this.searchMusicFromKUWO(key, defaultPage, defaultPageCount);
    }

    /**
     * 返回值就是音乐的在线播放地址
     *
     * @param MusicId
     * @return
     */
    // 获取歌曲播放地址
    private String getPlayUrlFromKUWO = "http://antiserver.kuwo.cn/anti.s?type=convert_url&rid={0}&format=mp3&response=url";

    public String getPlayUrlFromKUWO(String MusicId) {
        // 拼接歌曲播放地址url
        String playURL = MessageFormat.format(getPlayUrlFromKUWO, MusicId);
        ServerResponse response = this.get(playURL);
        if (response.isSuccess()) {
            System.out.println(response.getData());
            return response.getData().toString();
        }
        return null;
    }

    // 这个留做备用
    private String downloadFromKUWO = "http://antiserver.kuwo.cn/anti.s?type=convert_url&rid={0}&format=aac&response=url";
    public Map downloadFromKUWO(String MusicId, String path) {
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        // 获取文件下载地址
        String downloadUrl = this.getPlayUrlFromKUWO(MusicId);
        // 设置文件扩展名
        String fileExtensionName = "mp3";
        // 生成上传文件名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传音乐文件至ftp服务器，网络下载地址为：{}，上传的路径为：{}，新文件名为：{}", downloadUrl, path, uploadFileName);
        try {
            // 下载文件
            downLoadFromUrl(downloadUrl, uploadFileName, path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 获取下载的文件
        File targetFile = new File(path, uploadFileName);

        System.out.println(targetFile.getName());
        System.out.println(targetFile.getPath());

        try {
            FTPUtil.uploadFile(Lists.newArrayList(targetFile), Const.File_save_to.SAVE_TO_MUSIC);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //已经上传到FTP服务器

        targetFile.delete();

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFile.getName());
        fileMap.put("url", PropertiesUtil.getProperty("ftp.server.http.prefix") + Const.File_save_to.SAVE_TO_MUSIC + "/" + targetFile.getName());

        return fileMap;
    }

    private String getPlayAndDownloadFromKUWO = "http://www.kuwo.cn/webmusic/st/getMuiseByRid?rid={0}";

    public ServerResponse<String> getPlayAndDownloadFromKUWO(String musicId) {
        // 拼接歌曲播放地址url
        String playURL = MessageFormat.format(getPlayAndDownloadFromKUWO, musicId);
        ServerResponse response = this.get(playURL);
        if (response.isSuccess()) {
            System.out.println(response.getData());
            return response;
        }
        return null;
    }

    /**
     * 发送 get请求
     */
    private ServerResponse get(String url) {
        logger.info("发起请求，请求地址为：{}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建http get
            HttpGet httpget = new HttpGet(url);
            // 执行get请求
            CloseableHttpResponse response = httpclient.execute(httpget);

            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("请求结果状态码：{}", statusCode);
            if (Const.StatusCode.OK != statusCode) {
                return ServerResponse.createByErrorMessage(Const.Message.REQUEST_FAIL);
            }
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 打印响应内容
                    //System.out.println(EntityUtils.toString(entity));
                    return ServerResponse.createBySuccess(Const.Message.REQUEST_SUCCESS, EntityUtils.toString(entity));
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.createByErrorMessage(Const.Message.REQUEST_FAIL);
    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        System.out.println("info:" + url + " download success");

    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    @Test
    public void URLTest() {
//        String searchURL = MessageFormat.format(searchAPI, "周杰伦", defaultPage, defaultPageCount);
//        System.out.println(searchURL);

        //   searchMusicFromKUWO("青花瓷");

         //getPlayUrlFromKUWO("MUSIC_3327328");

        //getPlayAndDownloadFromKUWO("MUSIC_3327328");

//        try {
//            downLoadFromUrl("http://other.web.nf01.sycdn.kuwo.cn/21a7a98582e00a7b78666c89e4e2affd/5a54762f/resource/n3/32/5/10657852.mp3", "11.mp3", "/root/Downloads");
//        } catch (IOException e) {
//
//        }

//        Map map = Maps.newHashMap();
//        map = downloadFromKUWO("MUSIC_324244","/usr/java/apache-tomcat-8.0.47/webapps/ROOT/upload");
//        System.out.println(map.get("uri"));
//        System.out.println(map.get("url"));
    }
}