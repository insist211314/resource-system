package com.jiuzhi.common.resource.entity;

/**
 * @author yujw
 *  
 */
public class ImgUrlInfo {
	//自媒体文章中的图片url
	private String imgUrl;
	//自媒体文章中处理过的图片url如解密等
	private String processImgUrl;
	//页面地址（header-refer中的属性）
	private String referUrl;
	
	public String getReferUrl() {
		return referUrl;
	}
	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProcessImgUrl() {
		return processImgUrl;
	}
	public void setProcessImgUrl(String processImgUrl) {
		this.processImgUrl = processImgUrl;
	}


}
