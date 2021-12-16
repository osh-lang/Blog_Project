package org.project.common.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
* @Class Name : CommonFileUtil.java
* @Description : 시스템 공통으로 사용하는 file 관련 기능을 위한 클래스를 정의한다.
* @since 2020.12.01
* @version 1.0
* @see
*/

@Component("CommonFileUtil")
public class CommonFileUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonFileUtil.class);

	public static String filePath;

    @Value("${blog.file.path}")
    public void setFilePath(String value) {
    	filePath = value;
    }

	public static HashMap<String, Object> uploadFile(MultipartFile file, String subDir) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			String saveName = "";
			String originalName = file.getOriginalFilename();

			int index = originalName.lastIndexOf(".");
			String fileExt = originalName.substring(index + 1);
			long size = file.getSize();

			//파일 저장 이름 생성.
			saveName = UUIDGenerateUtils.parseTo16UUID(UUID.randomUUID().toString()) + "." + fileExt;

			//폴더 없을때 생성
			File Folder = new File(filePath + "/" + subDir);

			if (!Folder.exists()) {
				Folder.mkdirs();
			}

			file.transferTo(new File(filePath + "/" + subDir + "/" + saveName));

			map.put("original_name", originalName);
			map.put("save_name", saveName);
			map.put("save_path", filePath + "/" + saveName);
			map.put("extension", fileExt);
			map.put("file_size", String.valueOf(size));

		} catch (IOException ie) {
			logger.debug(ie.getMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return map;
	}

	private static MediaType contentType(String keyName) {
	    String[] arr = keyName.split("\\.");
	    String type = arr[arr.length-1];
	    switch(type) {
	      	case "txt": return MediaType.TEXT_PLAIN;
	      	case "png": return MediaType.IMAGE_PNG;
	      	case "jpg": return MediaType.IMAGE_JPEG;
	      	default: return MediaType.APPLICATION_OCTET_STREAM;
	    }
	}

	public static String getFileNm(String browser, String fileNm){
		logger.info("browser : " + browser);
		String reFileNm = null;
		try {
			if (browser.equals("MSIE") ||
				browser.equals("Trident") ||
				browser.equals("Edge")) {
				reFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
			} else {
				if(browser.equals("Chrome")){
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < fileNm.length(); i++) {
						char c = fileNm.charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode(Character.toString(c), "UTF-8"));
						} else {
							sb.append(c);
						}
					} reFileNm = sb.toString();
				} else{
					reFileNm = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
				}
				if(browser.equals("Safari") || browser.equals("Firefox")) {
					reFileNm = URLDecoder.decode(reFileNm);
					logger.info("safari reFileNm : " + reFileNm);
				}
			}
		} catch(Exception e){}
		return reFileNm;
	}

}
