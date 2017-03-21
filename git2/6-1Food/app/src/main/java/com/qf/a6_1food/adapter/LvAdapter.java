package com.qf.a6_1food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.a6_1food.R;
import com.qf.a6_1food.bean.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王松 on 2017/2/27.
 */

public class LvAdapter extends BaseAdapter {
    private Context context;
    private List<Food> foods;
    private LayoutInflater inflater;

    public LvAdapter(List<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv_item_layout, parent, false);
            holder = new ViewHolder();
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.keywords = (TextView) convertView.findViewById(R.id.keywords);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Food food = foods.get(position);
        holder.description.setText(food.getDescription());
        holder.keywords.setText(food.getKeywords());
        Picasso.with(context).load(food.getImg()).into(holder.iv);
        return convertView;
    }

    class ViewHolder {
        TextView description;
        TextView keywords;
        ImageView iv;
    }
}
