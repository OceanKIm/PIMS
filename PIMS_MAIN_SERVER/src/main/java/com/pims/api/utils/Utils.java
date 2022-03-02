package com.pims.api.utils;


import com.pims.api.custom.CustomMap;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Utils
 * : 범용 유틸 클래스
 *
 * @author hskim
 * @version 1.0.0
 * 작성일 2022-02-26
**/
@Log4j2
public class Utils {


    /**
     * 맵 NULL 데이터 유무 체크
     *
     * @param map 데이터
     * @return boolean
     */
    public static boolean isNotEmptyMap(HashMap<String, Object> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * ArrayList null 데이터유무 체크
     *
     * @param array 데이터
     * @return boolean
     */
    public static boolean isNotEmptyArray(ArrayList array) {
        return array != null && !array.isEmpty();
    }


    /**
     * Map 특정 키 데이터 존재 확인
     *
     * @param map 데이터 맵
     * @param key 맵 키
     * @return boolean
     */
    public static boolean isKey(HashMap<String, Object> map, String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        return map.containsKey(key);
    }

    /**
     * Map 특정 키 데이터 존재 확인
     *
     * @param map 데이터 맵
     * @param key 맵 키
     * @return boolean
     */
    public static boolean isKey(CustomMap map, String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        return map.containsKey(key);
    }

    /**
     * 문자열에서 특정 문자열 포함 여부 (배열 체크)
     *
     * @param str 대상 문자열
     * @param keys 문자열 배열
     * @return boolean
     */
    public static boolean isContainStrArray(final String str, final String[] keys) {
        return Arrays.stream(keys).anyMatch(i -> str.contains(i));
    }

    /**
     * 문자열에서 특정 문자열 갯수 찾기
     *
     * @param text 원본 문자열
     * @param word 대상 문자열
     * @return boolean
     */
    public static Integer countWord(String text, String word) {
        Integer str_count = 0;
        ArrayList<Integer> str_list = new ArrayList();
        if(text.contains(text)) {
            for(int i = 0; i < text.length(); i++) {
                if(text.charAt(i) == word.charAt(0)) {
                    str_list.add(i);
                }
            }
            String pointer = "";
            for(int i = 0; i < str_list.size(); i++) {
                Integer start_idx = str_list.get(i);
                pointer = text.substring(start_idx, start_idx + word.length());
                if(pointer.equals(word)) {
                    str_count ++;
                }
            }
        }
        return str_count;
    }

    /**
     * 문자열에서 특정 문자열 갯수 찾기 (배열)
     *
     * @param text 원본 문자열
     * @param words 대상 문자열 배열
     * @return boolean
     */
    public static Integer countWord(String text, String[] words) {
        Integer total_count = 0;
        for (int i = 0; i < words.length; i++) {
            Integer str_count = 0;
            ArrayList<Integer> str_list = new ArrayList();
            if(text.contains(text)) {
                for(int j = 0; j < text.length(); j++) {
                    if(text.charAt(j) == words[i].charAt(0)) {
                        str_list.add(j);
                    }
                }
                String pointer = "";
                for(int j = 0; j < str_list.size(); j++) {
                    Integer start_idx = str_list.get(j);
                    pointer = text.substring(start_idx, start_idx + words[i].length());
                    if(pointer.equals(words[i])) {
                        str_count ++;
                    }
                }
            }
            total_count += str_count;
        }
        return total_count;
    }

    /**
     * 프로퍼티 리스트 가져오기
     *
     * @param properties 프로터티 텍스트
     * @return ArrayList 응답값
     */
    public static ArrayList<String> getPropertiesList(String properties) {
        try {
            String[] propertiesArray = properties.replace("[", "").replace("]", "").replace(" ", "").trim().split(",");
            ArrayList<String> propertiesList = new ArrayList<>(Arrays.asList(propertiesArray));

            return propertiesList;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /**
     * request 헤더 정보 가져오기
     *
     * @param header request Header
     * @return ArrayList 헤더 내용
     */
    public static ArrayList<String> getRequestHeaders(String header) {
        ArrayList<String> headers = null;
        try {
            headers = Utils.getPropertiesList(header);
        } catch (Exception e) {
            log.error("=================================================================");
            log.error("BaseWebConfig header to array not work");
            log.error(e.getMessage());
            log.error("=================================================================");
        }
        if (null == headers) {
            headers = new ArrayList<>();
        }
        return headers;
    }

    /**
     * 유니코드를 UTF-8로 변환하는 메소드 입니다.
     *
     * @param uni 유니코드 값
     * @return String UTF-8 변환값
     */
    public static String decode(String uni) {
        StringBuffer str = new StringBuffer();
        for (int i = uni.indexOf("\\u"); i > -1; i = uni.indexOf("\\u")) {// euc-kr(%u), utf-8(//u)
            str.append(uni, 0, i);
            str.append((char) Integer.parseInt(uni.substring(i + 2, i + 6), 16));
            uni = uni.substring(i + 6);
        }
        str.append(uni);
        return str.toString();
    }

    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     *
     * @param request HttpServletRequest
     * @return JSONObject 응답값
     */
    public static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }

    public static void addRequestDate(HashMap<String, Object> requestMap) {
        if (requestMap.containsKey("reqDt")) {
            requestMap.put("regDt", DateUtils.getInsertDate());
        }
        if (requestMap.containsKey("chgDt")) {
            requestMap.put("chgDt", DateUtils.getInsertDate());
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 문자열 숫자로만 이루어 졌는지 검사
     *
     * @param str 문자열
     * @return boolean 숫자면 true 아니면 false
     */
    public static boolean isNumber(String str) {
        return str.trim().matches("[+-]?\\d*(\\.\\d+)?");
    }

    public static String searchFormat(String keyword) {
        return String.format("%%%s%%", keyword.trim());
    }


}
