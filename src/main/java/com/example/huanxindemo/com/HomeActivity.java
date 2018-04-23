package com.example.huanxindemo.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huanxindemo.R;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * Created by Administrator on 2016/10/20.
 */
public class HomeActivity extends FragmentActivity implements TextView.OnEditorActionListener {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        et = (EditText) findViewById(R.id.et);
        //跳转到 聊天界面
        et.setOnEditorActionListener(this);
        EaseConversationListFragment fragment = new EaseConversationListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flg, fragment);
        ft.commit();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        startActivity(new Intent(this, ChatActivity.class).putExtra("userid", et.getText().toString()));
        return false;
    }
}
