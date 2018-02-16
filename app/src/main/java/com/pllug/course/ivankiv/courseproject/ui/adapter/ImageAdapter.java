package com.pllug.course.ivankiv.courseproject.ui.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pllug.course.ivankiv.courseproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> urls;

    public ImageAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public String getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if (convertView == null) {
            img = new ImageView(context);
            convertView = img;
            img.setPadding(5, 5, 5, 5);
        } else {
            img = (ImageView) convertView;
        }

        Picasso.with(context)
                .load(urls.get(position))
                .placeholder(R.drawable.ic_launcher_background)
                .resize(350, 350)
                .into(img);

        return convertView;
    }



}


