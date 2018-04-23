package com.example.huanxindemo.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;

import com.example.huanxindemo.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * Created by Administrator on 2016/10/20.
 */

public class ChatActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String id = getIntent().getStringExtra("userid");
        EditText et = (EditText) findViewById(R.id.et);
        et.setVisibility(View.GONE);
        //聊天的fragment
        EaseChatFragment fragment = new EaseChatFragment();
        //和谁聊天
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, id);
        bundle.putString("new","123");
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flg, fragment);
        ft.commit();
    }
}
