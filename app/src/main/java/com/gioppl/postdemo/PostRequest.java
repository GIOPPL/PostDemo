package com.gioppl.postdemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by GIOPPL on 2017/12/2.
 */

public class PostRequest {
    private final int SUCCESS=0xffffffff;
    private final int ERROR=0xfffffffe;
    public static final String POST="POST";
    public static final String GET="GET";

    private RequestCallback callback;
    PostRequest(final HashMap<String, Object> argMap,final String url,final String requestType){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDate(argMap,url,requestType);
            }
        }).start();

    }
    PostRequest(final HashMap<String, Object> argMap,final String url,final String requestType,final RequestCallback callback){
        this.callback=callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDate(argMap,url,requestType);
            }
        }).start();

    }

    public void getDate(HashMap<String, Object> argMap,String address,String requestType){

        Message msg=new Message();
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod(requestType);
            String data=JSON.toJSONString(argMap);
            Log.i("发送的数据",data);
            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes("utf-8"));
            int responseCode = connection.getResponseCode();
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
                msg.arg1=SUCCESS;
                msg.obj=sb.toString();
                handler.sendMessage(msg);
            }else {
                msg.arg1=ERROR;
                msg.obj="请求失败";
                handler.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            msg.arg1=ERROR;
            msg.obj=e.getMessage();
            handler.sendMessage(msg);
        } catch (ProtocolException e) {
            e.printStackTrace();
            msg.arg1=ERROR;
            msg.obj=e.getMessage();
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
            msg.arg1=ERROR;
            msg.obj=e.getMessage();
            handler.sendMessage(msg);
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1==SUCCESS){
                callback.success((String) msg.obj);
            }else if (msg.arg1==ERROR){
                callback.error((String) msg.obj);
            }
        }
    };

    public interface RequestCallback{
        void success(String back);
        void error(String back);
        void getBeanList(ArrayList<Object> bean);
    }

    private ArrayList<Object> formatBeanList(String json,String className) {
        ArrayList<Object> list;
        Type listType = new TypeToken<List<Class>>() {}.getType();
        Gson gson=new Gson();
        list=gson.fromJson(json, listType);
        return list;
    }

}
