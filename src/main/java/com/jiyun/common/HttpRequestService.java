package com.jiyun.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * Created by Rayest on 2016/11/24 0024.
 */
public class HttpRequestService {

    public static JSONObject get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        String strResult = EntityUtils.toString(response.getEntity());
        return JSONObject.parseObject(strResult);
    }

    public static JSONObject post(String url, JSONObject jsonParam) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity stringEntity = new StringEntity(jsonParam.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String strResult = EntityUtils.toString(response.getEntity());
        return JSONObject.parseObject(strResult);
    }
    

    public static JSONObject put(String url, JSONObject jsonParam) throws Exception {
        HttpPut httpPut = new HttpPut(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity stringEntity = new StringEntity(jsonParam.toString(), ContentType.APPLICATION_JSON);
        httpPut.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPut);
        String strResult = EntityUtils.toString(response.getEntity());
        return JSONObject.parseObject(strResult);
    }

    public static JSONObject delete(String url) throws Exception {
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpDelete);
        String strResult = EntityUtils.toString(response.getEntity());
        return JSONObject.parseObject(strResult);
    }


}