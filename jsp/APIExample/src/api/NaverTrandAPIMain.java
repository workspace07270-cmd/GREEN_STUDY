package api;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import config.APIKey;

public class NaverTrandAPIMain {
	 public static void main(String[] args) {
	        String clientId = APIKey.NAVER_CLIENT_ID;
	        String clientSecret = APIKey.NAVER_CLIENT_SECRET;

	        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        requestHeaders.put("Content-Type", "application/json");

	        String requestBody = "{\"startDate\":\"2017-01-01\"," +
	                "\"endDate\":\"2017-04-30\"," +
	                "\"timeUnit\":\"month\"," +
	                "\"keywordGroups\":[{\"groupName\":\"한글\"," + "\"keywords\":[\"한글\",\"korean\"]}," +
	                "{\"groupName\":\"영어\"," + "\"keywords\":[\"영어\",\"english\"]}]," +
	                "\"device\":\"pc\"," +
	                "\"ages\":[\"1\",\"2\"]," +
	                "\"gender\":\"f\"}";

	        String responseBody = post(apiUrl, requestHeaders, requestBody);
	        System.out.println(responseBody);
	        
	        parseJSON(responseBody);
	    }

	    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
	        HttpURLConnection con = connect(apiUrl);

	        try {
	            con.setRequestMethod("POST");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            con.setDoOutput(true);
	            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
	                wr.write(requestBody.getBytes());
	                wr.flush();
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
	                return readBody(con.getInputStream());
	            } else {  // 에러 응답
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl) {
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection) url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private static String readBody(InputStream body) {
	        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	        
	        
	    private static void parseJSON(String string) {
	    	JSONObject json = new JSONObject(string);
	    	JSONArray arr = json.getJSONArray("results");
	    	for(int i=0;i<arr.length();i++) {
	    		System.out.println(arr.getJSONObject(i).getString("title")); 
	    		System.out.println(arr.getJSONObject(i).getJSONArray("keywords"));
	    		JSONArray temp = arr.getJSONObject(i).getJSONArray("data");
	    		for(int j=0;j<temp.length();j++) {
	    			System.out.println("\t"+ temp.getJSONObject(i).getString("period"));
	    			System.out.println("\t"+ temp.getJSONObject(i).getBigDecimal("ratio"));
	    		}
	    		System.out.println("--------------------------------");
	    	}
	    }
}