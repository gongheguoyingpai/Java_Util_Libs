package com.zhang.util;

import java.io.File;

public class PathUtil {

	public static final String PATG_SEG_REGEX = "\\\\|/";
	
	public static String join(String dirPath, String fileName) {
		File file = new File(dirPath, fileName);
		return file.getPath();
 	}
	
	public static String basePath(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			return file.getPath();
		} else {
		    String fileName = file.getName();
		    return file.getPath().replaceFirst(fileName, "");
		}
	}
	
	public static String baseName(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			return null;
		} else {
			return file.getName();
		}
	}
	
	public static String pathPart(String path, int index) {
		if (path == null) {
			return null;
		}
		String[] parts = path.split(PATG_SEG_REGEX);
		if (Math.abs(index) > parts.length) {
			return null;
		} else {
			index = (index + parts.length) % parts.length;
			return parts[index];
		}
	}
	
	public static void main(String[] args) {
		System.err.println(join("D:/classify/sources/Reduced/economic", "10.txt"));
		System.err.println(basePath(join("D:/classify/sources/Reduced/economic", "")));
		
		System.err.println(pathPart("D:\\classify\\sources\\Reduced\\economic", -1));
	}
}
