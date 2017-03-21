package com.qf.a6_1food;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.a6_1food.bean.DetailFood;
import com.qf.a6_1food.util.Constants;
import com.qf.a6_1food.util.DownloadData;
import com.qf.a6_1food.util.JsonParse;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.DOWNLOADOK:
                    Picasso.with(DetailActivity.this).load(food.getImg()).into(iv);
                    tv.setText(Html.fromHtml(food.getMessage()));
                    break;
            }
        }
    };
    private DetailFood food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv = ((ImageView) findViewById(R.id.iv));
        tv = ((TextView) findViewById(R.id.tv));
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = DownloadData.getData(String.format(Constants.DETAILFOODURL, getIntent().getIntExtra("id", 0)));
                food = JsonParse.parseJson2DetailFood(data);
                if (food != null) {
                    handler.sendEmptyMessage(Constants.DOWNLOADOK);
                } else {
                    handler.sendEmptyMessage(Constants.DOWNLOADERROR);
                }
            }
        }).start();
    }
}
