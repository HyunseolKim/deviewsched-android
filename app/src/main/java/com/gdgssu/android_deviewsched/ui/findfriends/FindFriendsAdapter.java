package com.gdgssu.android_deviewsched.ui.findfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;

import java.util.LinkedList;

public class FindFriendsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private LinkedList<String> m_List;

    public FindFriendsAdapter(){
        m_List = new LinkedList<String>();
    }
    public FindFriendsAdapter(LinkedList<String> list){
        this.mContext = DeviewSchedApplication.GLOBAL_CONTEXT;
        m_List = list;
        this.mInflater = (LayoutInflater.from(this.mContext));
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView avatarView = null;
        TextView userNameView = null;

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_findfriend,parent,false);

            // TextView item_text = (TextView)convertView.findViewById(R.id.item_text);
            // item_text.setText(m_List.get(position));

            Button item_button = (Button)convertView.findViewById(R.id.item_button);
            item_button.setText("Click");
            item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "button click contents" + m_List.get(pos) + " pos:" + pos, Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"item click contents:"+m_List.get(pos)+" pos"+pos,Toast.LENGTH_LONG).show();
                }
            });

        }
        ImageView item_image = (ImageView)convertView.findViewById(R.id.item_image);

        TextView item_text = (TextView)convertView.findViewById(R.id.item_text);
        item_text.setText(m_List.get(position));

        return convertView;
    }
}
