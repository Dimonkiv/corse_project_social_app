package com.pllug.course.ivankiv.courseproject.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class AlbumAdapter extends BaseAdapter {
    private Context context;
    private List<String> titles;
    private List<String> photoUrl;
    private List<Integer> photoCount;
    private final LayoutInflater inflater;

    public AlbumAdapter (Context context, List<String> titles, List<String> photoUrl, List<Integer> photoCount) {
        this.context = context;
        this.titles = titles;
        this.photoUrl = photoUrl;
        this.photoCount = photoCount;
        inflater = LayoutInflater.from(this.context);
    }
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public String getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.album_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        if (mViewHolder.albumName != null) {
            mViewHolder.albumName.setText(titles.get(position));
        }

        if (mViewHolder.photoSize != null) {
            mViewHolder.photoSize.setText(Integer.toString(photoCount.get(position)));
        }

        if (mViewHolder.photo != null) {
            Picasso.with(context)
                    .load(photoUrl.get(position))
                    .into(mViewHolder.photo);
        }
        return convertView;
    }

    private class MyViewHolder {
        TextView albumName, photoSize;
        ImageView photo;

        public MyViewHolder(View item) {
            albumName =  item.findViewById(R.id.album_item_name_ET);
            photoSize = item.findViewById(R.id.album_item_count_of_photo);
            photo = item.findViewById(R.id.album_item_photo);
        }
    }
}


