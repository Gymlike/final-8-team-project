package com.team.final8teamproject.business.service;

import java.io.IOException;

import com.team.final8teamproject.share.exception.CustomException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final OkHttpClient client;
    private final String url = "http://api.odcloud.kr/api/nts-businessman/v1/validate";
    private final String serviceKey = "mL0rXajMYZKn1pEowrETpXHRG34RqLuQHJddce80r59gAAbMEocBNxz1SKMuepmO5ZBrhaloax3XgT%2FAj6KK1A%3D%3D";

    public BusinessServiceImpl() {
        this.client = new OkHttpClient();
    }

    @Override
    public String validateBusiness(String b_no, String start_dt, String p_nm, String b_nm) throws CustomException, IOException {
        String json = "{\n" +
                "  \"businesses\": [\n" +
                "    {\n" +
                "      \"b_no\": \"" + b_no + "\",\n" +
                "      \"start_dt\": \"" + start_dt + "\",\n" +
                "      \"p_nm\": \"" + p_nm + "\",\n" +
                "      \"p_nm2\": \"\",\n" +
                "      \"b_nm\": \"\",\n" +
                "      \"corp_no\": \"\",\n" +
                "      \"b_sector\": \"\",\n" +
                "      \"b_type\": \"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url + "?serviceKey=" + serviceKey + "&returnType=JSON")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                JSONObject dataObject = jsonArray.getJSONObject(0);
                String valid = dataObject.getString("valid");
                System.out.println("사업자등록번호: " + b_no + " 등록일: " + start_dt + " 대표자명: " + p_nm + " 상호명: " + b_nm);
                if (valid.equals("01")) {
                    System.out.println("사업자번호 검증 성공");
                } else {
                    System.out.println("사업자번호 검증 실패");
                }
                return valid;
            } else {
                throw new IOException("서버 응답 실패: " + response.code() + " - " + response.message());
            }
        }
    }
}