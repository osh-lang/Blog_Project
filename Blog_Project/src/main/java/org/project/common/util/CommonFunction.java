package org.project.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @Class Name : CommonFunction.java
* @Description : 시스템 공통으로 사용하는 함수 설정를 위한 클래스를 정의한다.
* @since 2020.12.21
* @version 1.0
* @see
*/

public class CommonFunction {
	private static final Logger logger = LoggerFactory.getLogger(CommonFunction.class);

	/**
	 * <Map>에 있는 null값을 ""값으로 치환
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> nullToBlankInMap(Map<String, Object> map) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Set set = map.entrySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			resultMap.put(e.getKey().toString(),  e.getValue()==null?"":e.getValue());
		}

		return resultMap;
	}

	/**
	 * List<Map>에 있는 null값을 ""값으로 치환
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> nullToBlankInList(List<Map<String, Object>> list) throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		for(int i=0; i<list.size(); i++) {
			Map tempMap = new HashMap();

			Set set = list.get(i).entrySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				tempMap.put(e.getKey(),  e.getValue()==null?"":e.getValue());
			}

			resultList.add(tempMap);
		}

		return resultList;
	}

	/**
	 * 1. List<Map>에 있는 null값을 ""값으로 치환
	 * 2. 키값을 소문자로 치환
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> nullToBlankInListWithLowerKey(List<Map<String, Object>> list) throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		for(int i=0; i<list.size(); i++) {
			Map tempMap = new HashMap();

			Set set = list.get(i).entrySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				tempMap.put(e.getKey().toString().toLowerCase(),  e.getValue()==null?"":e.getValue());
			}

			resultList.add(tempMap);
		}

		return resultList;
	}

	/**
	 * 1. null값을 ""값으로 치환
	 * @param list
	 * @return
	 */
	public static String isNullToString(Object object) {
		String string = "";

		if (object != null) {
			string = object.toString().trim();
		}

		return string;
	}

	/**
	 * Exception 메시지를 String으로 변경
	 * @param e
	 * @return
	 */
	public static String printStackTraceToString(Throwable e) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(e.toString());
            sb.append("\n");
            StackTraceElement element[] = e.getStackTrace();
            for (int idx = 0; idx < element.length; idx++) {
                sb.append("\tat ");
                sb.append(element[idx].toString());
                sb.append("\n");
            }
        } catch (NullPointerException ex) {
            return e.toString();
        } catch (Exception ex) {
            return e.toString();
        }
        return sb.toString();
    }

	/**
	 * Object 를 Map으로 변환
	 * @param obj
	 * @return
	 */
	public static HashMap<String, Object> convertObjectToMap(Object obj) throws Exception {

		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			HashMap<String, Object> resultMap = new HashMap<String, Object>();

			for(int i = 0 ; i < fields.length ; i++) {
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}

			return resultMap;
		}catch (IllegalArgumentException e) {
			logger.error("에러 발생!!", e);
		}catch (IllegalAccessException e) {
			logger.error("에러 발생!!", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> requestToMap(HttpServletRequest request) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        Map<String, Object> resultMap = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        String str = stringBuilder.toString();

        if(!"".equals(str)) {
        	resultMap = (Map<String, Object>)new ObjectMapper().readValue(str, Map.class);
        }else {
        	resultMap = new HashMap<String, Object>();
        }

        return resultMap;
	}

	public static Map<String, Object> requestParameterToMap(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Enumeration<?> names = request.getParameterNames();
        while (names.hasMoreElements()) {
        	String key = (String)names.nextElement();
        	if(request.getParameterValues(key).length > 1) {
        		resultMap.put(key, request.getParameterValues(key));
        	}else {
        		resultMap.put(key, request.getParameter(key));
        	}
        }

        return resultMap;
	}

	public static String getIpFromRequest(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	public static String convert2CamelCase(String underScore) {
        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }

	// 날짜 폴더명 추출
	public static String getDatePath(String uploadPath) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String yearPath = File.separator + calendar.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

		makeDateDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}

	// 날짜별 폴더 생성
	public static void makeDateDir(String uploadPath, String... paths) throws Exception {
		// 날짜별 폴더가 이미 존재하면 메서드 종료
		if(new File(uploadPath + paths[paths.length - 1]).exists())
			return;

		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists())
				dirPath.mkdir();
		}
	}

	/**
	 * 입력된 Map<String, Object> 에 필수값이 입력되어 있는지 체크하여
	 * 필수 값이 없으면 true, 필수 값이 모두 있으면 false
	 * @return
	 * @throws Exception
	 */
	public static boolean checkRequiredValue(Map<String, Object> requestMap, String... keys) throws Exception {
		boolean bResult = false;

		if(requestMap != null) {
			for(String key : keys) {
				String value = requestMap.get(key)==null?"":requestMap.get(key).toString();
				if("".equals(value)) {
					bResult = true;
				}
			}
		}else {
			bResult = true;
		}

		return bResult;
	}

	/**
     * Object type 변수가 비어있는지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean empty(Object obj) {
		if (obj instanceof String)
			return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof List)
			return obj == null || ((List<?>) obj).isEmpty();
		else if (obj instanceof Map)
			return obj == null || ((Map<?, ?>) obj).isEmpty();
		else if (obj instanceof Object[])
			return obj == null || Array.getLength(obj) == 0;
		else
			return obj == null;
    }

    /**
     * Object type 변수가 비어있지 않은지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean notEmpty(Object obj) {
        return !empty(obj);
    }


	// 아이디 검증 정규식
    public static final Pattern REGEX_ID = Pattern.compile("^([A-Z0-9._%+-]){1,64}+@([A-Z0-9.-]){1,248}+\\.([A-Z]){1,6}$", Pattern.CASE_INSENSITIVE);
	/**
	 * 아이디 검증
	 * @param sId
	 * @return
	 */
    public static boolean validateId(String sId) {
		Matcher matcher = REGEX_ID .matcher(sId);
		return matcher.find();
	}


	// 비밀번호 검증 정규식
	public static final Pattern REGEX_PW = Pattern.compile("^(?=.*\\\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).{8,16}$", Pattern.CASE_INSENSITIVE);
	/**
	 * 비밀번호 검증
	 * @param sPw
	 * @return
	 */
	public static boolean validatePw(String sPw) {
		Matcher matcher = REGEX_PW .matcher(sPw);
		return matcher.find();
	}

	// 휴대전화번호 검증 정규식
	public static final Pattern REGEX_PHONE = Pattern.compile("^[0-9]{3}[-]+[0-9]{3,4}[-]+[0-9]{4}$", Pattern.CASE_INSENSITIVE);
	/**
	 * 휴대전화번호 검증
	 * @param sPhone
	 * @return
	 */
	public static boolean validatePhone(String sPhone) {
		Matcher matcher = REGEX_PHONE .matcher(sPhone);
		return matcher.find();
	}

	// 이메일 검증 정규식
//	public static final Pattern REGEX_EMAIL = Pattern.compile("^([A-Z0-9._%+-]){1,64}+@([A-Z0-9.-]){1,248}+\\.([A-Z]){1,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern REGEX_EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);


	/**
	 * 이메일 검증
	 * @param sEmail
	 * @return
	 */
	public static boolean validateEmail(String sEmail) {
		Matcher matcher = REGEX_EMAIL .matcher(sEmail);
		return matcher.find();
	}

	public static String executeGenerate(int length) {
	    char[] randomTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                               'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                               'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                               'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                               'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
                               '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	    Random random = new Random(System.currentTimeMillis());
        int randomlength = randomTable.length;
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < length; i++) {
            buf.append(randomTable[random.nextInt(randomlength)]);
        }

        return buf.toString();
	}

	/**
	 * PHP의 md5의 JAVA Ver.
	 * @param String
	 * @return String
	 */
	public static String md5(String inputValue) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch(Exception e) {
			e.printStackTrace();
		}
		String eip;
		byte[] bip;
		String temp = "";
		String tst = inputValue;

		bip = md5.digest(tst.getBytes());
		for(int i=0; i < bip.length; i++) {
			eip = "" + Integer.toHexString((int)bip[i] & 0x000000ff);
			if(eip.length() < 2) eip = "0" + eip;
				temp = temp + eip;
			}
		return temp;
	}

	/**
	 * userAgent로 Os 가져오기
	 * @param String
	 * @return String
	 */
	public static String getOs(String userAgent) {
		String str = userAgent.toLowerCase();
		String os = "nomobile";

		boolean mobile = false;
		mobile = str.contains("iphone");
		mobile = str.contains("ipad");
		mobile = str.contains("ipod");
		mobile = str.contains("android");

		if(mobile) {
			if(str.indexOf("android") != -1) {
				os = "android";
			}else if(str.indexOf("iphone") != -1 || str.indexOf("ipad") != -1|| str.indexOf("ipod") != -1) {
				os = "ios";
			}else {
				os = "else";
			}
		}else {
			os = "nomobile";
		}

		return os;
	}
}
