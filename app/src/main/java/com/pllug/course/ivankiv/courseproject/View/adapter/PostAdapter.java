package com.pllug.course.ivankiv.courseproject.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iw97d on 31.01.2018.
 */

public class PostAdapter extends BaseAdapter {

    public interface PostAdapterEventListener {
        void openComments(int position);
    }

    private Context context;
    private List<PostModel> posts;
    private LayoutInflater inflater;
    private PostAdapterEventListener listener;
    private String name;
    private String imageUrl;

    public PostAdapter(Context context, List<PostModel> posts, String name, String imageUrl) {
        this.context = context;
        this.posts = posts;
        this.name = name;
        this.imageUrl = imageUrl;
        inflater = LayoutInflater.from(this.context);
    }

    public void addListener(PostAdapterEventListener listener) {
        this.listener = listener;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.post_item, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        PostModel post = posts.get(position);

        if (myViewHolder.avatar != null) {
            Picasso.with(context)
                    .load(imageUrl)
                    .transform(new CircularTransformation(300))
                    .into(myViewHolder.avatar);
        }

        if (myViewHolder.name != null) {
            myViewHolder.name.setText(name);
        }

        if (myViewHolder.postTitle != null) {
            myViewHolder.postTitle.setText(post.getTitle());
        }
        if (myViewHolder.postBody != null) {
            myViewHolder.postBody.setText(post.getBody());
        }

        if (myViewHolder.commentImage != null) {
            myViewHolder.commentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.openComments(position);
                    }
                }
            });
        }

        return convertView;


    }
    private class MyViewHolder {

        TextView postTitle, postBody, name;
        ImageView commentImage, avatar;
        public MyViewHolder(View item) {
            commentImage = item.findViewById(R.id.post_item_go_to_comments);
            postTitle = item.findViewById(R.id.post_item_post_title);
            postBody = item.findViewById(R.id.post_item_post_body);
            name = item.findViewById(R.id.post_item_user_name);
            avatar = item.findViewById(R.id.post_item_user_logo);
        }

    }
    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public PostModel getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return posts.get(position).getId();
    }


}
