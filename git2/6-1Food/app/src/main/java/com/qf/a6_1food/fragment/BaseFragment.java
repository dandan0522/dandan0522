package com.qf.a6_1food.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qf.a6_1food.DetailActivity;
import com.qf.a6_1food.R;
import com.qf.a6_1food.adapter.LvAdapter;
import com.qf.a6_1food.adapter.VpAdapter;
import com.qf.a6_1food.bean.Food;
import com.qf.a6_1food.util.Constants;
import com.qf.a6_1food.util.DownloadData;
import com.qf.a6_1food.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2017/2/27.
 */

public class BaseFragment extends Fragment {
    private ListView lv;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.DOWNLOADOK:
                    LvAdapter adapter = new LvAdapter(foods, getActivity());
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (index == 0) {
                                position = position - 1;
                            }
                            Intent intent = new Intent(getActivity(), DetailActivity.class);
                            intent.putExtra("id", foods.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
    private List<Food> foods;
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_layout, container, false);
        lv = ((ListView) view.findViewById(R.id.lv));
        index = getArguments().getInt("index");
        if (index == 0) {
            View header = inflater.inflate(R.layout.lv_header_layout, null);
            ViewPager vp = (ViewPager) header.findViewById(R.id.vp);
            List<String> list = new ArrayList<>();
            list.add("http://tnfs.tngou.net/image/ext/161223/7083a1fde72448a62e477c5aab0721c8.jpg");
            list.add("http://tnfs.tngou.net/image/ext/161223/395b860c06ccaf5b35cde317ff082c18.jpg");
            list.add("http://tnfs.tngou.net/image/ext/161223/905b7784c0aeb91870fb40d34defae5d.jpg");
            list.add("http://tnfs.tngou.net/image/ext/161213/c5f1416b4feb857b8d711f83dc692885.jpg");
            list.add("http://tnfs.tngou.net/image/ext/161213/a94ead894d0d0e4e5b3b807626eeab4d.jpg");
            VpAdapter adapter = new VpAdapter(getActivity(), list);
            vp.setAdapter(adapter);
            lv.addHeaderView(header);
        }
        initData();
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = DownloadData.getData(String.format(Constants.LISTFOODURL, getArguments().getInt("id")));
                foods = JsonParse.parseJson2Food(data);
                if (foods != null) {
                    handler.sendEmptyMessage(Constants.DOWNLOADOK);
                } else {
                    handler.sendEmptyMessage(Constants.DOWNLOADERROR);
                }
            }
        }).start();
    }
}
