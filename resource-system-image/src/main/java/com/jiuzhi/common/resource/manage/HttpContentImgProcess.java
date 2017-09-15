package com.jiuzhi.common.resource.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.appleframework.config.core.PropertyConfigurer;
import com.jiuzhi.common.resource.entity.ImgRefer;
import com.jiuzhi.common.resource.entity.ImgUrlInfo;
import com.jiuzhi.common.resource.utils.ImgeUtils;
import com.jiuzhi.common.tools.upload.QiniuImageInfo;
import com.jiuzhi.common.tools.upload.QiniuMediaCommon;
import com.jiuzhi.common.tools.util.GifUtil;

@SuppressWarnings("deprecation")
@Service("httpContentImgProcess")
public class HttpContentImgProcess {

	private static Logger logger = Logger.getLogger(HttpContentImgProcess.class);

	/**
	 * 直接下载连接
	 * 
	 * @param url
	 * @return string
	 */
	public String getQnUrl(String url, List<ImgRefer> imgRefers) throws Exception {
		String qnUrl = "";
		url = url.replace("&amp;", "&");
		if (isReplace(url)) {
			String referUrl = "";
			for (ImgRefer imgRefer : imgRefers) {
				if (url.indexOf(imgRefer.getImgUrl()) > -1) {
					referUrl = imgRefer.getReferUrl();
				}
			}
			if ("".equals(referUrl)) {
				referUrl = url;
			}
			url = URLDecoder.decode(url);
			qnUrl = uploadImage7Niu(url, referUrl, 0);
		} else {
			if (url.toLowerCase().indexOf("gif") > -1 && url.indexOf("?gifUrl=") < 0) {// gif需获取第一帧保存
				QiniuMediaCommon qm = new QiniuMediaCommon();
				String filename = UUID.randomUUID().toString();
				String store = PropertyConfigurer.getString("local.upload.storePath", "/work/upload/tmp/");
				String filePath = downloadimg(url, filename, store);
				File file = new File(filePath);
				InputStream input = null;
				String url2 = "";
				try {
					input = new FileInputStream(file);
					byte[] bytes = GifUtil.getReadOneImage(input);

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					String dir = dateFormat.format(new Date());
					String uuid = UUID.randomUUID().toString();
					String imagePath2 = dir + "/" + uuid + "." + "png";
					url2 = qm.getUpTokenImage(imagePath2, bytes);
				} finally {
					if (file.exists()) {
						file.delete();
					}
					input.close();
				}
				if (!"".equals(url2)) {
					url = url2 + "?gifUrl=" + url;
				}
				return url;
			}
		}

		return qnUrl;

	}

	/**
	 * 获取文本中的图片url--批量处理url
	 * 
	 * @param htmlStr
	 * @return list
	 */
	@SuppressWarnings("deprecation")
	public List<ImgUrlInfo> getImgStr(String htmlStr, List<ImgRefer> imgRefers) {

		String img = "";
		Pattern patternImg;
		Matcher matcherImg;
		List<ImgUrlInfo> imgUrls = new ArrayList<ImgUrlInfo>();
		String regExImg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		patternImg = Pattern.compile(regExImg, Pattern.CASE_INSENSITIVE);
		matcherImg = patternImg.matcher(htmlStr);
		while (matcherImg.find()) {
			img = matcherImg.group();
			logger.info("截取的img" + img);
			Matcher m = Pattern.compile(" src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				String url = m.group(1);
				if (isReplace(url) && url.indexOf("http") > -1) {
					ImgUrlInfo mediaInfoImgUrl = new ImgUrlInfo();
					String referUrl = "";
					for (ImgRefer imgRefer : imgRefers) {
						if (url.indexOf(imgRefer.getImgUrl()) > -1) {
							referUrl = imgRefer.getReferUrl();
						}
					}
					if ("".equals(referUrl)) {
						mediaInfoImgUrl.setReferUrl(url);
					} else {
						mediaInfoImgUrl.setReferUrl(referUrl);
					}
					mediaInfoImgUrl.setProcessImgUrl(URLDecoder.decode(url).replace("&amp;", "&"));
					mediaInfoImgUrl.setImgUrl(url);
					imgUrls.add(mediaInfoImgUrl);
				}
			}
		}
		return imgUrls;
	}

	/**
	 * 批量下载图片
	 * 
	 * @param imgUrls
	 * @return replaceUrls
	 */
	public List<Map<String, String>> UploadImgList(List<ImgUrlInfo> infoImgUrls) throws Exception {

		// 剪切url地址的标识
		String cutUrlFlag = PropertyConfigurer.getString("imgurl.cutflag");
		// 剪切url地址的标识,优先级高
		String cutUrlFlagFirst = PropertyConfigurer.getString("imgurl.cutflag.first");

		List<Map<String, String>> replaceUrls = new ArrayList<Map<String, String>>();
		String imgUrl = "";
		String proceImgUrl = "";
		String qnUrl = "";

		for (int i = 0; i < infoImgUrls.size(); i++) {
			Map<String, String> urlMap = new HashMap<String, String>();
			ImgUrlInfo infoImgUrl = infoImgUrls.get(i);
			imgUrl = infoImgUrl.getImgUrl();
			proceImgUrl = infoImgUrl.getProcessImgUrl();
			String refer = infoImgUrl.getReferUrl();
			logger.info("信息里匹配的url" + imgUrl);
			logger.info("转换后的url" + proceImgUrl);
			logger.info("header-refer---url" + refer);
			
			if(!isUpload(proceImgUrl)){
				urlMap.put(imgUrl, imgUrl);
				replaceUrls.add(urlMap);
				continue;
			}
			
			if (null != proceImgUrl && !"".equals(proceImgUrl)) {
				logger.info("图片url剪切标识" + cutUrlFlag);
				if (isCut(proceImgUrl)) {
					String tmpUrl = getCutStringFist(proceImgUrl, cutUrlFlagFirst);
					if (tmpUrl.equals("")) {
						tmpUrl = getCutStringFist(proceImgUrl, cutUrlFlag);
						if (!tmpUrl.equals("")) {
							proceImgUrl = tmpUrl;
						}
					} else {
						proceImgUrl = tmpUrl;
					}
				}
				
				qnUrl = uploadImage7Niu(proceImgUrl, refer, 1);
				logger.info("返回的七牛  url--" + qnUrl);
				urlMap.put(imgUrl, qnUrl);
				replaceUrls.add(urlMap);
			}
		}
		return replaceUrls;
	}

	/**
	 * 上传文件到七牛
	 * 
	 * isType:0为标题类型,图片类型为gif进行特殊处理
	 * 
	 * @param imageUrl
	 * @param imageName
	 * @return url
	 * @throws Exception
	 */
	public String uploadImage7Niu(String imageUrl, String refer, Integer isType) throws Exception {
		QiniuMediaCommon qm = new QiniuMediaCommon();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dir = dateFormat.format(new Date());
		String uuid = UUID.randomUUID().toString();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (imageUrl.indexOf("https") > -1) {
			// 创建TrustManager
			X509TrustManager xtm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[] {};
				}
			};
			SSLContext ctx = SSLContext.getInstance("SSL");

			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { xtm }, null);

			SSLSocketFactory sf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", 443, sf);
			httpClient.getConnectionManager().getSchemeRegistry().register(sch);

		}
		logger.info("处理完下载图片的url：" +imageUrl );
		HttpGet httpGet = new HttpGet(imageUrl);
		httpGet
         .setHeader(  
                 "User-Agent",  
                 "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");  
		httpGet  
         .setHeader("Accept",  
                 "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
		// 设置http头的引用，应对防盗链的图片不能下载的问题
		httpGet.setHeader("Referer", refer);
		httpGet.setHeader("Accept-Encoding", "identity");  
		// 发送请求，返回响应
		HttpResponse response = httpClient.execute(httpGet);
		
		logger.info("response.toString():" + response.toString());
		// 获取响应信息
		HttpEntity httpEntity = response.getEntity();
		String imgeType = StringUtils.substringAfter(httpEntity.getContentType().getValue(), "/");
		if(imageUrl.indexOf("sinaimg.cn") > 0){
			if (ImgeUtils.isImage(imageUrl)) {
				int index = imageUrl.lastIndexOf(".");
				imgeType = imageUrl.substring(index + 1, imageUrl.length());
			}
		}else if (imgeType.indexOf(";") > -1) {
			imgeType = StringUtils.substringBefore(imgeType, ";");
		}

		logger.info("从http头获取的图片类型信息" + imgeType);
		logger.info("httpEntity.getContentLength()" + httpEntity.getContentLength());
		byte[] bytes = null;
		InputStream in = httpEntity.getContent();
		if(httpEntity.getContentLength()<1)
		{
			HttpClient client = new HttpClient();
			GetMethod get = new GetMethod(imageUrl);			
			client.executeMethod(get);
			// 得到网络资源的字节数组,并写入文件
			bytes = get.getResponseBody();			
		}else{
			bytes = new byte[(int) httpEntity.getContentLength()];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) in.read();
			}
		}
		String imagePath = dir + "/" + uuid + "." + imgeType;
		String url = qm.getUpTokenImage(imagePath, bytes);
		if ("gif".equalsIgnoreCase(imgeType)) { // 标题gif需获取第一帧保存
			String filename = UUID.randomUUID().toString();
			String store = PropertyConfigurer.getString("local.upload.storePath", "/work/upload/tmp/");
			String filePath = downloadimg(imageUrl, filename, store);
			File file = new File(filePath);
			InputStream input = null;
			String url2 = "";
			try {
				input = new FileInputStream(file);
				bytes = GifUtil.getReadOneImage(input);
				if(null != bytes && bytes.length > 0){
					String imagePath2 = dir + "/" + uuid + "." + "png";
					url2 = qm.getUpTokenImage(imagePath2, bytes);
				}
			} finally {
				if (file.exists()) {
					file.delete();
				}
				if (input != null) {
					input.close();
				}
			}
			if (!"".equals(url2)) {
				url = url2 + "?gifUrl=" + url;
			}
		}

		if ("webp".equals(imgeType)) {
			url = url + "?imageMogr2/format/jpg";
		}
		in.close();
		return url;
	}

	/**
	 * 将文件写入本地服务器
	 * 
	 * @param url
	 * @param filename
	 * @param store
	 * @return
	 */
	public String downloadimg(String url, String filename, String store) {
		File storeFile = new File(store);
		if (!storeFile.exists() && !storeFile.isDirectory()) {
			storeFile.mkdirs();
		}
		File file = new File(store + filename);
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		try {
			client.executeMethod(get);
			FileOutputStream output = new FileOutputStream(file);
			// 得到网络资源的字节数组,并写入文件
			output.write(get.getResponseBody());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return store + filename;
	}

	/**
	 * 替换文章内容的url地址
	 * 
	 * @param content
	 * @param urlList
	 * @param repalceUrls
	 * @return newContent
	 */

	public String replaceImgUrl(String content, List<ImgUrlInfo> urlList, List<Map<String, String>> repalceUrls) {
		String newContent = content;
		ImgUrlInfo infoImgUrl = new ImgUrlInfo();
		for (int i = 0; i < repalceUrls.size(); i++) {
			infoImgUrl = urlList.get(i);
			String newUrl = repalceUrls.get(i).get(infoImgUrl.getImgUrl());
			if (ImgeUtils.isImage(newUrl)) {
				newContent = newContent.replace(infoImgUrl.getImgUrl(), newUrl);
			}
		}
		return newContent;
	}
	
	/**
	 * 替换文章内容的url地址
	 * 
	 * @param content
	 * @param urlList
	 * @param repalceUrls
	 * @return newContent
	 */

	public String replaceImgUrlV34(String content, List<ImgUrlInfo> urlList, List<Map<String, String>> repalceUrls) {
		String newContent = content;
		ImgUrlInfo infoImgUrl = new ImgUrlInfo();
		for (int i = 0; i < repalceUrls.size(); i++) {
			infoImgUrl = urlList.get(i);
			String newUrl = repalceUrls.get(i).get(infoImgUrl.getImgUrl());
			if (ImgeUtils.isImage(newUrl)) {
				newUrl = replaceImgSize(newUrl);
				newContent = newContent.replace(infoImgUrl.getImgUrl(), newUrl);
			}
		}
		return newContent;
	}

	/**
	 * 判断url是否需要处理
	 * 
	 * @param url
	 * @return isReplace
	 */

	public boolean isReplace(String url) {
		boolean isReplace = false;
		if (null != url && !"".equals(url)) {
			isReplace = true;
			if(url.toLowerCase().indexOf("?size=") < 0 ){
				return isReplace;
			}
			if (url.toLowerCase().indexOf("gif") > -1 && url.indexOf("?gifUrl=") < 0) {// gif需获取第一帧保存
				return isReplace;
			}
			// 不需要下载的url地址
			String patternUrls = PropertyConfigurer.getString("pattern.url", "");
			String[] patternUrlArry = patternUrls.split(",");
			int size = patternUrlArry.length;

			for (int i = 0; i < size; i++) {
				if (url.indexOf(patternUrlArry[i]) > -1) {
					isReplace = false;
					break;
				}
			}
		}

		return isReplace;
	}

	/**
	 * 判断url是否需要处理
	 * 
	 * @param url
	 * @return isReplace
	 */

	public boolean isCut(String url) {
		boolean isCut = true;

		if (null != url && !"".equals(url)) {
			// 不需要剪切的url地址
			String patternUrls = PropertyConfigurer.getString("imageurl.notcut", "");
			String[] patternUrlArry = patternUrls.split(",");
			int size = patternUrlArry.length;

			for (int i = 0; i < size; i++) {
				if (url.indexOf(patternUrlArry[i]) > -1) {
					isCut = false;
					break;
				}
			}
		}

		return isCut;
	}

	/**
	 * 获取剪切后的url
	 * 
	 * @param proceImgUrl
	 * @param cutUrlFlag
	 * @return
	 */
	public String getCutStringFist(String proceImgUrl, String cutUrlFlag) {
		String[] urlFlag = cutUrlFlag.split(",");
		String url = "";
		for (int f = 0; f < urlFlag.length; f++) {
			if (proceImgUrl.indexOf(urlFlag[f]) > -1) {
				url = StringUtils.substringAfterLast(proceImgUrl, urlFlag[f]);
				break;
			}
		}
		return url;

	}
	
	/**
	 * 生成图片size
	 * @param newUrl
	 * @return
	 */
	private String replaceImgSize(String imageUrl) {
		if(imageUrl.indexOf("?size=") < 0){ //实现没有这个字符串才进来
			int gifUrlIndex = imageUrl.indexOf("?gifUrl=");
			String newUrl = imageUrl;
			if(gifUrlIndex > 0){
				newUrl = imageUrl.substring(0, gifUrlIndex);
			}
			QiniuImageInfo qiniuImageInfo = QiniuMediaCommon.getImageInfo(newUrl);
			if(null != qiniuImageInfo){
				Integer width = qiniuImageInfo.getWidth();
				Integer higth = qiniuImageInfo.getHeight();
				if(null != width && null != higth){
					if(width > 0 && higth > 0){
						String sizeStr = "?size=" + width + "x" + higth; 
						if(gifUrlIndex > 0){
							//如果包含gif, 将字符串放到gif前面
							imageUrl = imageUrl.substring(0, gifUrlIndex) + sizeStr + imageUrl.substring(gifUrlIndex);
						}else{
							imageUrl = imageUrl + sizeStr;
						}
					}
				}
			}
		}
		
		return imageUrl;
	}
	
	private boolean isUpload(String proceImgUrl){
		String patternUrls = PropertyConfigurer.getString("pattern.url", "");
		String[] patternUrlArry = patternUrls.split(",");
		int size = patternUrlArry.length;
		
		boolean isUpload = true;
		if (proceImgUrl.toLowerCase().indexOf("gif") > -1 && proceImgUrl.indexOf("?gifUrl=") < 0) {// gif需获取第一帧保存
			return isUpload;
		}
		
		for (int z = 0; z < size; z++) {
			if (proceImgUrl.indexOf(patternUrlArry[z]) > -1) {
				isUpload = false;
				break;
			}
		}
		
		return isUpload;
	}
	public static void main(String[] args) {
		HttpContentImgProcess process = new HttpContentImgProcess();
		String newUrl = "http://7xlid5.com3.z0.glb.qiniucdn.com/b18b024e-a907-4455-848b-f9fdfb9f8999.png?size=332x171";
		System.out.println(process.replaceImgSize(newUrl));
	}
}
