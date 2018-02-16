package com.pllug.course.ivankiv.courseproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.data.model.Comment;

import java.util.List;

/**
 * Created by iw97d on 01.02.2018.
 */

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> comments;
    private LayoutInflater inflater;


    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommentAdapter.MyViewHolder myViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.comment_item, parent, false);
            myViewHolder = new  CommentAdapter.MyViewHolder (convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = ( CommentAdapter.MyViewHolder ) convertView.getTag();
        }

        Comment comment = comments.get(position);

        if (myViewHolder.name != null) {
            myViewHolder.name.setText(comment.getName());
        }
        if (myViewHolder.email != null) {
            myViewHolder.email.setText(comment.getEmail());
        }
        if (myViewHolder.comment != null) {
            myViewHolder.comment.setText(comment.getBody());
        }

        return convertView;


    }
    private class MyViewHolder {

        TextView email, comment, name;

        public MyViewHolder(View item) {
            name = item.findViewById(R.id.comment_item_name);
            email = item.findViewById(R.id.comment_item_email);
            comment = item.findViewById(R.id.comment_item_body);
        }

    }
    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return comments.get(position).getId();
    }
}
