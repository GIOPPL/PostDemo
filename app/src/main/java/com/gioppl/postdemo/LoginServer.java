package com.gioppl.postdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by GIOPPL on 2017/11/27.
 */

public class LoginServer {
    /** * post的方式请求
     *@return 返回null 登录异常
     */
    public static String loginByPost(String iphone,String title,String content,String email,String address){
        String path = "http://116.196.91.8:8080/webtest/ServletDxlSend";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
//            connection.setRequestMethod("GET");
            //数据准备
            String data = "{"
                    + "iphone:"+iphone+","
                    + "title:"+title+","
//                    + "content:"+content+","
//                    + "email:"+email+","
                    +"content:"+content
                    + "}";
//            String data="";
            Log.i("json",data);
            //至少要设置的两个请求头
            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            Log.i("aa",data.length()+"");
            //post的方式提交实际上是留的方式提交给服务器
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes("utf-8"));

            //获得结果码
            int responseCode = connection.getResponseCode();
            Log.i("code",responseCode+"");
            if(responseCode ==200){
                //请求成功
                InputStream is = connection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuffer sb=new StringBuffer();
                while ((line=br.readLine())!=null){
                    sb.append(line);
                }
                is.close();
                br.close();
                return sb.toString();
            }else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
