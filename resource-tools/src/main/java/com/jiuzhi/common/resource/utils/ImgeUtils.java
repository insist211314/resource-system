package com.jiuzhi.common.resource.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgeUtils {

	/**
	 * 判断图片格式是否为图片
	 * @param imageName
	 * @return boolean
	 */
	public static boolean isImage(String imageName) {
		boolean flag = false;
		String regImgType = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png|webp)$";
		Matcher m = Pattern.compile(regImgType).matcher(imageName);
		while (m.find()) {
			String imgType = m.group(1);
			if (null != imgType && !"".equals(imgType)) {
				flag = true;
			}
		}
		return flag;
	}
}
