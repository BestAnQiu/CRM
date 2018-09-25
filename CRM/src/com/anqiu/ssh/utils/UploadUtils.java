package com.anqiu.ssh.utils;

import java.util.UUID;

public class UploadUtils {
	public static String getUuidfileName(String fileName){
		int indexOf = fileName.lastIndexOf("."); //aa.txt
		String extions=fileName.substring(indexOf);
		return UUID.randomUUID().toString().replaceAll("-","")+extions;
	}
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1= code1 & 0xf;//作为一级目录
		int code2 =code1 >>>4;
		int d2=code2 & 0xf; //作为二级目录
		return "/"+d1+"/"+d2;
	}
}
