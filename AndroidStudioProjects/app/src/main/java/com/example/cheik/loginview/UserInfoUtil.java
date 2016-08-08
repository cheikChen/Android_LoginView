package com.example.cheik.loginview;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cheik on 16/8/8.
 */
public class UserInfoUtil {

    //保存用户名密码
    public static boolean saveUserInfo_android(Context context,String username,String password){
        try {
            String userinfo = username +"##" + password;//封装用户名和密码
            //得到一个私有目录下一个文件写入流
            FileOutputStream fileOutputStream = context.openFileOutput("userinfo.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(userinfo.getBytes());//将用户名密码写入文件
            fileOutputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //获取用户名
    public static Map<String,String>getUserInfo_android(Context context){
        try {
            //通过context对象获取一个私有目录的文件读取流
            FileInputStream fileInputStream = context.openFileInput("userinfo.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            //读取一行中包含用户名和密码,需要解析
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("##");
            HashMap<String ,String> hashMap = new HashMap<String, String>();
            hashMap.put("username",split[0]);
            hashMap.put("password",split[1]);
            bufferedReader.close();
            fileInputStream.close();
            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
