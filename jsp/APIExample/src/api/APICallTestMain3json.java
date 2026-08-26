package api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import config.APIKey;

public class APICallTestMain3json {

	public static void main(String[] args) {
		try {
			String baseTime = "1100";
			// 1. URL 셋팅
			String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=";
			// 2. 인증키 및 파라미터 셋팅
			apiURL += APIKey.PUBLIC_DATA_KEY;
			apiURL += "&pageNo=1";
			apiURL += "&numOfRows=100";
			apiURL += "&base_date=20260521";
			apiURL += "&base_time="+baseTime;
			apiURL += "&dataType=JSON";
			apiURL += "&nx=59";
			apiURL += "&ny=126";
			
			// 3. HTTP 연결 -> 차후 HTTP 헤더에 인증 정보, timeout 설정 등을 할 수 있음
			URI url = new URI(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.toURL().openConnection();
			conn.setRequestMethod("GET");
//			conn.setConnectTimeout(5000);
//			conn.setReadTimeout(10000);
			// 3-1. POST일 경우 데이터를 전송
			// 4. 응답코드 확인 - 정상적인 요청과 응답을 받으면 200
			int responseCode = conn.getResponseCode();
			System.out.println("Http Status Code : " + responseCode);
			// 5. 응답 데이터를 읽어오기
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null)
				sb.append(line);
			
			// 6. 응답 데이터를 출력
			System.out.println(sb);
			
			//parseXML(sb.toString());
			parseJSON(sb.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private static void parseJSON(String string) {
		JSONObject json = new JSONObject(string);
		JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
//		System.out.println(arr);
		String fcstTime = "";
		for(int i=0;i<arr.length();i++) {
			String category = arr.getJSONObject(i).getString("category");
			String tempTime = arr.getJSONObject(i).getString("fcstTime");
			if(!fcstTime.equals(tempTime) && !fcstTime.isEmpty()) break;
			switch(category) {
			case "TMP": 
			case "TMN": 
			case "TMX": 
			case "PCP": 
			case "SKY": 
				if(fcstTime.isEmpty()) fcstTime = tempTime;
				System.out.println(category + " : " 
						+ arr.getJSONObject(i).getString("fcstValue"));
			}
		}
	}

	
	
}