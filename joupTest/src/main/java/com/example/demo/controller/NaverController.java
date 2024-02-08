package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class NaverController {
	
	@GetMapping("/naver")
	public String naver() throws IOException {
		String clientId = ""; // 네이버 개발자 센터에서 발급받은 클라이언트 ID
        String clientSecret = ""; // 네이버 개발자 센터에서 발급받은 클라이언트 시크릿
        String query = "강아지"; // 검색어 입력

        // 검색어를 URL 인코딩
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        // 네이버 이미지 검색 API 요청 URL 생성
        String apiUrl = "https://openapi.naver.com/v1/search/image?query=" + encodedQuery + "&display=10&start=1";

        // API 호출 및 응답 JSON 파싱
        String responseJson = sendGetRequest(apiUrl, clientId, clientSecret);
        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();

        // 이미지 다운로드
        downloadImages(jsonObject);
		return "OK";
	}
	
	private static String sendGetRequest(String apiUrl, String clientId, String clientSecret) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

        int responseCode = con.getResponseCode();
        BufferedReader in;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }
	
	
	private static void downloadImages(JsonObject jsonObject) throws IOException {
        JsonArray items = jsonObject.getAsJsonArray("items");

        for (int i = 0; i < items.size(); i++) {
            JsonObject item = items.get(i).getAsJsonObject();
            String imageUrl = item.get("link").getAsString();

            // 이미지 다운로드 경로 및 파일명 설정
            String fileName = "c:/naver/image_" + i + ".jpg";
            File imageFile = new File(fileName);

            // 이미지 다운로드
            FileUtils.copyURLToFile(new URL(imageUrl), imageFile);
            System.out.println("이미지 다운로드 완료: " + imageFile.getAbsolutePath());
        }
    }
}