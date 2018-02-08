package com.pllug.course.ivankiv.courseproject.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;
import com.pllug.course.ivankiv.courseproject.R;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public class TodoAdapter extends BaseAdapter {
    private Context context;
    private List<TodoModel> todos;
    private LayoutInflater inflater;

    public TodoAdapter(Context context, List<TodoModel> todos) {
        this.context = context;
        this.todos = todos;
        inflater = LayoutInflater.from(this.context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.todo_item, parent, false);
            myViewHolder = new  MyViewHolder (convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = ( MyViewHolder ) convertView.getTag();
        }

        TodoModel todo = todos.get(position);

        if (myViewHolder.title != null) {
            myViewHolder.title.setText(todo.getTitle());
        }

        if(myViewHolder.status != null) {
            myViewHolder.status.setChecked(todo.getCompleted());
            myViewHolder.status.setClickable(false);
        }
        return convertView;
    }

    private class MyViewHolder {

        TextView  title;
        CheckBox status;

        public MyViewHolder(View item) {
            title = item.findViewById(R.id.todo_item_title);
            status = item.findViewById(R.id.todo_item_checkbox);
        }
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public TodoModel getItem(int position) {
        return todos.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }
}
