package com.example.huanxindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.huanxindemo.com.HomeActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class MainActivity extends AppCompatActivity {

    EditText uname, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        uname = (EditText) findViewById(R.id.uname);
        pwd = (EditText) findViewById(R.id.pwd);
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {
                //直接跳转
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }

            @Override
            public void onDisconnected(int i) {

            }
        });
    }

    public void login(View v) {
        //检测非空
        final String password = pwd.getText().toString();
        final String username = uname.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
            return;
        }
        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                //子线程
                showToast("登录成功");
                //加载组和最近聊天
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            }

            @Override
            public void onError(int i, String s) {
                //子线程
                showToast(s);
            }

            @Override
            public void onProgress(int i, String s) {
                //子线程
            }
        });


    }

    public void register(View v) {
        //检测非空
        final String password = pwd.getText().toString();
        final String username = uname.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                //环信注册
                try {
                    EMClient.getInstance().createAccount(username, password);
                    //注册成功
                    showToast("注册成功");
                } catch (HyphenateException e) {
                    showToast("注册失败:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
