package com.gioppl.postdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                //String uname,String upwd,String iphone,String email,String address
        //                LoginServer.loginByPost("15620520750","title","content","2","2");
        ////                LoginServer.loginByPost("GIOPPL","123","15620520750","gioppl@vip.qq.com","中国天津");
        //            }
        //        }).start();
        //        HashMap<String, Object> map = new HashMap<String, Object>();
        //        map.put("iphone", "15620520750");
        //        map.put("title", "titlePPL");
        //        map.put("content", "contentPPL");
        //        new PostRequest(map, "http://116.196.91.8:8080/webtest/ServletDxlSend", PostRequest.POST, new PostRequest.RequestCallback() {
        //            @Override
        //            public void success(String back) {
        //                Log.i("成功",back);
        //            }
        //
        //            @Override
        //            public void error(String back) {
        //                Log.i("失败",back);
        //            }
        //
        //            @Override
        //            public void getBeanList(ArrayList<Object> bean) {
        //
        //            }
        //        });

        val map = HashMap<String, Any>()
        //注册
//        map.put("uname", "123")
//        map.put("upwd", "123")
//        map.put("iphone", "12345678910")
//        map.put("email", "PPL@qq.com")
//        map.put("address", "天津")

        map.put("from","1")
        map.put("to","10");

        //登陆
//        map.put("iphone","afdsgs")
//        map.put("upwd","fdsgagfds")

//        map.put("uname", "杏子")
//        map.put("uid", 2)
//        map.put("upwd", "1234")
//        map.put("iphone", "1234")
//        map.put("email", "PPL@qq.com")
//        map.put("address", "TJ")
//        map.put("uimage", "http://ac-rxsnxjjw.clouddn.com/LBT7wbbfWF0bIvzwbH7kmF0J62OpeQlaNTx7bSp3.jpg")

//        map.put("iphone","1234")
//        map.put("title","少女杏")
//        map.put("content","少女杏是个傻子")
//        map.put("goods","http://ac-rxsnxjjw.clouddn.com/LBT7wbbfWF0bIvzwbH7kmF0J62OpeQlaNTx7bSp3.jpg")
//        map.put("uname","GIOPPL")
//        map.put("address","天津南开区")
//        map.put("uimage","http://ac-rxsnxjjw.clouddn.com/LBT7wbbfWF0bIvzwbH7kmF0J62OpeQlaNTx7bSp3.jpg")
//        map.put("uid",2)
//        map.put("hide",false)

//        map.put("title","猪")

        PostRequest(map, "http://116.196.91.8:8080/webtest/ServletPPLLimit", PostRequest.POST, object : PostRequest.RequestCallback {
            override fun success(back: String) {
                Log.i("成功", back)
            }

            override fun error(back: String) {
                Log.i("失败", back)
            }

            override fun getBeanList(bean: ArrayList<Any>) {

            }
        })
    }
}
