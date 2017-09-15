package com.jiuzhi.common.resource.utils;


import com.jiuzhi.common.resource.exception.ExtensionNotExistsException;
import com.jiuzhi.common.tools.util.DateFormatUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.naming.ServiceUnavailableException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
public class FileDownload {

    private static final Logger logger = LoggerFactory.getLogger(FileDownload.class);

    private static Map<String, String> header = new HashMap<>();

    private static String tmpPath = "/work/tmp/file/";

    static{
//        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        header.put("Accept-Encoding", "gzip, deflate, sdch");
//        header.put("Accept-Language", "zh-CN,zh;q=0.8");
//        header.put("Cache-Control", "no-cache");
//        header.put("Connection", "keep-alive");
//        header.put("Host", "m.weibo.cn");
//        header.put("Pragma", "no-cache");
//        header.put("Upgrade-Insecure-Requests", "1");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
    }

    public static String download(String url){
        return download(url, Md5Util.md5DigestByString(url), null);
    }

    public static String download2(String url, String fileExtension){
        return download(url, Md5Util.md5DigestByString(url), fileExtension);
    }

    /**
     * 包含download(String url, String fileName)功能,可给或不给文件后缀名
     * 文件后缀名优先级：fileExtension > fileName > url
     * @param url  下载url
     * @param fileName 下载后的文件名称,可以带文件后缀名
     * @param fileExtension 文件后缀名
     * @return
     */
    public static String download(String url, String fileName, String fileExtension) {

        if(FileUtil.getFileExtension(fileName)!=null)
            fileName = fileName.substring(0, fileName.lastIndexOf("."));

        if(!StringUtils.hasText(fileExtension))
            fileExtension = FileUtil.getFileExtension(fileName);

        if(!StringUtils.hasText(fileExtension))
            fileExtension = getFileExtension(url);

        if (!StringUtils.hasText(fileExtension))
            throw new ExtensionNotExistsException("文件后缀不存在!");

        fileName = fileName + "." + fileExtension;
        return download(url, fileName);
    }

    /**
     * 文件已存在则直接返回文件路径，不存在则 1.下载中生成临时文件 2.下载完毕则重命名为真实文件名
     * @param url 下载url
     * @param fileName 文件名称,需要自带文件后缀名
     */
    public static String download(String url, String fileName){
        String filePath = tmpPath + DateFormatUtil.toString(new Date(), DateFormatUtil.pattern8) + "/" + fileName;
        if(new File(filePath).exists())
            return filePath;

        File tmpFile = getTmpFile(fileName);
        download(url,tmpFile);
        return rename(tmpFile, fileName);
    }

    /**
     * 不管文件是否已存在，直接下载覆盖该文件
     * @param url 下载url
     * @param storeFile 存储的文件
     */
    public static void download(String url, File storeFile){
        HttpClient httpclient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        HttpGet httpget = createHttpGet(url);
        httpget.setConfig(requestConfig);

        InputStream instream = null;
        FileOutputStream output = null;
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                instream = entity.getContent();
                output = new FileOutputStream(storeFile);

                byte b[] = new byte[1024];
                int j = 0;
                while( (j = instream.read(b))!=-1){
                    output.write(b,0,j);
                }
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            httpget.abort();
            throw new RuntimeException("文件下载失败 url=" + url + "  error=" + e.getMessage(), e);
        } finally {
            close(output);
            close(instream);
            close((CloseableHttpClient) httpclient);
        }
    }


    //---------------------------------------------------------------------------------------


    public static String downloadProxy(String url){
        return downloadProxy(url, Md5Util.md5DigestByString(url), null);
    }

    public static String downloadProxy2(String url, String fileExtension){
        return downloadProxy(url, Md5Util.md5DigestByString(url), fileExtension);
    }

    /**
     * 包含download(String url, String fileName)功能,可给或不给文件后缀名
     * 文件后缀名优先级：fileExtension > fileName > url
     * @param url  下载url
     * @param fileName 下载后的文件名称,可以带文件后缀名
     * @param fileExtension 文件后缀名
     * @return
     */
    public static String downloadProxy(String url, String fileName, String fileExtension) {

        if(FileUtil.getFileExtension(fileName)!=null)
            fileName = fileName.substring(0, fileName.lastIndexOf("."));

        if(!StringUtils.hasText(fileExtension))
            fileExtension = FileUtil.getFileExtension(fileName);

        if(!StringUtils.hasText(fileExtension))
            fileExtension = getFileExtensionByProxy(url);

        if (!StringUtils.hasText(fileExtension))
            throw new ExtensionNotExistsException("文件后缀不存在!");

        fileName = fileName + "." + fileExtension;
        return downloadProxy(url, fileName);
    }

    /**
     * 文件已存在则直接返回文件路径，不存在则 1.下载中生成临时文件 2.下载完毕则重命名为真实文件名
     * @param url 下载url
     * @param fileName 文件名称,需要自带文件后缀名
     */
    public static String downloadProxy(String url, String fileName){
        String filePath = tmpPath + DateFormatUtil.toString(new Date(), DateFormatUtil.pattern8) + "/" + fileName;
        if(new File(filePath).exists())
            return filePath;

        File tmpFile = getTmpFile(fileName);
        downloadProxy(url, tmpFile);
        return rename(tmpFile, fileName);
    }

    /**
     * 不管文件是否已存在，直接下载覆盖该文件
     * @param url 下载url
     * @param storeFile 存储的文件
     */
    public static void downloadProxy(String url, File storeFile){
        CloseableHttpClient httpclient = getProxyClient();

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        HttpGet httpget = createHttpGet(url);
        httpget.setConfig(requestConfig);

        InputStream instream = null;
        FileOutputStream output = null;
        try {
            HttpResponse response = httpclient.execute(httpget, proxy("127.0.0.1", 1080));
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                instream = entity.getContent();
                output = new FileOutputStream(storeFile);

                byte b[] = new byte[1024];
                int j = 0;
                while( (j = instream.read(b))!=-1){
                    output.write(b,0,j);
                }
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            httpget.abort();
            throw new RuntimeException("文件下载失败 url=" + url + "  error=" + e.getMessage(), e);
        } finally {
            close(output);
            close(instream);
            close((CloseableHttpClient) httpclient);
        }
    }

    private static CloseableHttpClient getProxyClient(){
        SSLContext sslcontext = null;
        try {
            sslcontext = createIgnoreVerifySSL();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new MyConnectionSocketFactory())
                .register("https", new MySSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, new FakeDnsResolver());
        //创建自定义的httpclient对象
        return HttpClients.custom().setConnectionManager(connManager).build();
    }

    /**
     * 获取文件后缀名
     * @param url 下载的url
     * @return 文件后缀名
     */
    public static String getFileExtensionByProxy(String url){

        CloseableHttpClient httpclient = getProxyClient();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        HttpGet httpget = createHttpGet(url);
        httpget.setConfig(requestConfig);

        //1.获取文件后缀名
        String fileExtension =  null;
        try {
            HttpResponse response = httpclient.execute(httpget, proxy("127.0.0.1", 1080));
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                if(!StringUtils.hasText(fileExtension)) { //3.如果文件已存在，则直接返回文件路径
                    fileExtension = getType(entity);
                    if(!StringUtils.hasText(fileExtension)){
                        fileExtension = FileUtil.getFileExtension(url);
                    }
                    if(!StringUtils.hasText(fileExtension))
                        throw new ExtensionNotExistsException("文件后缀不存在!");
                }
            }
        } catch (Exception e) {
            httpget.abort();
            throw new RuntimeException("获取文件后缀名失败! url=" + url + "  error=" + e.getMessage(), e);
        } finally {
            close((CloseableHttpClient) httpclient);
        }
        return fileExtension;
    }

    /**
     * 获取文件后缀名
     * @param url 下载的url
     * @return 文件后缀名
     */
    public static String getFileExtension(String url){
        HttpClient httpclient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        HttpGet httpget = createHttpGet(url);
        httpget.setConfig(requestConfig);

        //1.获取文件后缀名
        String fileExtension =  null;
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                if(!StringUtils.hasText(fileExtension)) { //3.如果文件已存在，则直接返回文件路径
                    fileExtension = getType(entity);
                    if(!StringUtils.hasText(fileExtension)){
                        fileExtension = FileUtil.getFileExtension(url);
                    }
                    if(!StringUtils.hasText(fileExtension))
                        throw new ExtensionNotExistsException("文件后缀不存在!");
                }
            }
        } catch (Exception e) {
            httpget.abort();
            throw new RuntimeException("获取文件后缀名失败! url=" + url + "  error=" + e.getMessage(), e);
        } finally {
            close((CloseableHttpClient) httpclient);
        }
        return fileExtension;
    }

    private static String getType(HttpEntity entity){
        if(entity==null || entity.getContentType()==null)
            return null;

        return FileUtil.contentType.get(entity.getContentType().getValue());
    }

    public static File getTmpFile(String fileName){
        File f = new File(tmpPath + DateFormatUtil.toString(new Date(), DateFormatUtil.pattern8) + "/");
        if(!f.exists()){
            f.mkdirs();
        }
        if(FileUtil.getFileExtension(fileName)!=null)
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        return new File(f,fileName+"tmp");
    }

    private static HttpGet createHttpGet(String uri){
        HttpGet httpget = new HttpGet(uri);
        if (header != null) {
            for (Map.Entry<String, String> param : header.entrySet()) {
                String value = param.getValue();
                if(value !=null && !"".equals(value))
                    httpget.addHeader(param.getKey(), param.getValue());
            }
        }
        return httpget;
    }

    private static void close(CloseableHttpClient httpClient){
        try{
            if(httpClient!=null)
                httpClient.close();
        } catch (IOException e) {
            logger.warn("", e);
        }
    }

    private static void close(InputStream instream){
        try{
            if(instream!=null)
                instream.close();
        }catch (Exception e){
            logger.warn("", e);
        }
    }

    private static void close(OutputStream outputStream){
        try{
            if(outputStream!=null)
                outputStream.close();
        }catch (Exception e){
            logger.warn("", e);
        }
    }

    public static String getDownloadFilePath(String url){
        return tmpPath + DateFormatUtil.toString(new Date(), DateFormatUtil.pattern8) + "/" + Md5Util.md5DigestByString(url);
    }


    public static String rename(File oldFile, String newName){
        if(!oldFile.exists()){
            throw new RuntimeException("修改失败,文件不存在! filepath=" + oldFile.getPath());
        }
        String rootPath = oldFile.getParent();
        File newFile = new File(rootPath + File.separator + newName);
        if (oldFile.renameTo(newFile)) {
            return newFile.getPath();
        }else {
            throw new RuntimeException("文件修改失败! filepath="+oldFile.getPath() + "  newName=" + newName);
        }
    }

    static class MyConnectionSocketFactory extends PlainConnectionSocketFactory {
        @Override
        public Socket createSocket(final HttpContext context) throws IOException {
            InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
            return new Socket(proxy);
        }

        @Override
        public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress,
                                    InetSocketAddress localAddress, HttpContext context) throws IOException {
            // Convert address to unresolved
            InetSocketAddress unresolvedRemote = InetSocketAddress
                    .createUnresolved(host.getHostName(), remoteAddress.getPort());
            return super.connectSocket(connectTimeout, socket, host, unresolvedRemote, localAddress, context);
        }
    }


    static class MySSLConnectionSocketFactory extends SSLConnectionSocketFactory {

        public MySSLConnectionSocketFactory(final SSLContext sslContext) {
            // You may need this verifier if target site's certificate is not secure
            super(sslContext);
        }

        @Override
        public Socket createSocket(final HttpContext context) throws IOException {
            InetSocketAddress socksaddr = (InetSocketAddress) context.getAttribute("socks.address");
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
            return new Socket(proxy);
        }

        @Override
        public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress,
                                    InetSocketAddress localAddress, HttpContext context) throws IOException {
            // Convert address to unresolved
            InetSocketAddress unresolvedRemote = InetSocketAddress
                    .createUnresolved(host.getHostName(), remoteAddress.getPort());
            return super.connectSocket(connectTimeout, socket, host, unresolvedRemote, localAddress, context);
        }
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    static class FakeDnsResolver implements DnsResolver {
        @Override
        public InetAddress[] resolve(String host) throws UnknownHostException {
            // Return some fake DNS record for every request, we won't be using it
            return new InetAddress[] { InetAddress.getByAddress(new byte[] { 1, 1, 1, 1 }) };
        }
    }

    public static HttpClientContext proxy(String hostOrIP, int port){
        InetSocketAddress socksaddr = new InetSocketAddress(hostOrIP,port);
        HttpClientContext context = HttpClientContext.create();
        context.setAttribute("socks.address", socksaddr);
        return context;
    }

    public static void main(String[] args) {

        FileDownload.downloadProxy("https://scontent-lax3-2.cdninstagram.com/t51.2885-15/e35/14359918_290715471311799_1125400882_n.jpg");
        //String resource = "/work/tmp/file/20161115/d0342984bdd2bf3ae3f8a321fc6f9e9etmp.mp4";
        //rename(new File(resource), "d0342984bdd2bf3ae3f8a321fc6f9e9e.mp4");
    }
}
