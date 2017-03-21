package com.qf.a6_1food;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qf.a6_1food.adapter.FgAdapter;
import com.qf.a6_1food.bean.Classify;
import com.qf.a6_1food.fragment.BaseFragment;
import com.qf.a6_1food.util.Constants;
import com.qf.a6_1food.util.DownloadData;
import com.qf.a6_1food.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.DOWNLOADOK:
                    List<Fragment> fragments = new ArrayList<>();
                    for (int i = 0; i < classifies.size(); i++) {
                        BaseFragment fragment = new BaseFragment();
                        Bundle args = new Bundle();
                        args.putInt("id",classifies.get(i).getId());
                        args.putInt("index",i);
                        fragment.setArguments(args);
                        fragments.add(fragment);
                        FgAdapter adapter = new FgAdapter(getSupportFragmentManager(), classifies, fragments);
                        viewPager.setAdapter(adapter);
                        tabLayout.setupWithViewPager(viewPager);
                    }
                    break;
            }
        }
    };
    private List<Classify> classifies;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = DownloadData.getData(Constants.CLASSIFYURL);
                classifies = JsonParse.parseJson2Classify(data);
                if (classifies != null) {
                    mHandler.sendEmptyMessage(Constants.DOWNLOADOK);
                } else {
                    mHandler.sendEmptyMessage(Constants.DOWNLOADERROR);
                }
            }
        }).start();
    }
}
