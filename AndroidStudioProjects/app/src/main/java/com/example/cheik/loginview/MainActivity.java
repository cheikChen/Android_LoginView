package com.example.cheik.loginview;

import android.content.Context;
import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_userName;
    private EditText et_passWord;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        et_userName = (EditText) findViewById(R.id.et_username);
        et_passWord = (EditText) findViewById(R.id.et_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_login);

        //设置button的点击事件
        bt_login.setOnClickListener(this);

        //判断有没有记录的用户名和密码,有就显示
        Map<String,String> map = UserInfoUtil.getUserInfo_android(mContext);
        if(map != null){
            String username = map.get("username");
            String password = map.get("password");
            et_passWord.setText(password);
            et_userName.setText(username);
            cb_rem.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println("你看就看看");
        boolean isRem = cb_rem.isChecked();

        //判断用户名或者密码是否为空
        String userName = et_userName.getText().toString();
        String passWord = et_passWord.getText().toString();
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
            Toast.makeText(mContext,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
            return;

        }
        //判断是否记住密码,如果记住将用户名保存在本地
        if(isRem){
            boolean result = UserInfoUtil.saveUserInfo_android(mContext,userName,passWord);
        }else{
            Toast.makeText(mContext,"密码不用保存",Toast.LENGTH_SHORT).show();
        }
    }
}
