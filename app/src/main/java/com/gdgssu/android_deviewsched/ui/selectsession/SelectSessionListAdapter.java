package com.gdgssu.android_deviewsched.ui.selectsession;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Day;
import com.gdgssu.android_deviewsched.model.Session;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

public class SelectSessionListAdapter extends BaseAdapter {

    private ArrayList<Session> sessionItems = new ArrayList<>();

    private LayoutInflater mInflater;
    private Context mContext;

    public SelectSessionListAdapter(Day day, Context context) {
        for (int i = 0; i < day.tracks.size(); i++) {
            this.sessionItems.addAll(day.tracks.get(i).sessions);
        }
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sessionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sessionItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SelectSessionHolder selectHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_select_session, parent, false);

            selectHolder = new SelectSessionHolder();

            selectHolder.sessionTime = (TextView) convertView.findViewById(R.id.item_select_session_time);
            selectHolder.sessionTrack = (TextView) convertView.findViewById(R.id.item_select_session_track);
            selectHolder.speakerImg = (ImageView) convertView.findViewById(R.id.item_select_session_speaker_img);
            selectHolder.speakerImgSecond = (ImageView) convertView.findViewById(R.id.item_select_session_speaker_img_second);
            selectHolder.speakerName = (TextView) convertView.findViewById(R.id.item_select_session_speaker_name);
            selectHolder.sessionName = (TextView) convertView.findViewById(R.id.item_select_session_session_title);

            convertView.setTag(selectHolder);

        } else {
            selectHolder = (SelectSessionHolder) convertView.getTag();
        }

        Session sessionItem = sessionItems.get(position);

        if (sessionItem.speakers.size() > 1) {
            setTwoSpeakerInfo(selectHolder, sessionItem);
        } else {
            setOneSpeakerInfo(selectHolder, sessionItem);
        }

        selectHolder.sessionName.setText(sessionItem.title);

        return convertView;
    }

    public void setDayItem(Day day) {
        for (int i = 0; i < day.tracks.size(); i++) {
            this.sessionItems.addAll(day.tracks.get(i).sessions);
        }
    }

    public void setOneSpeakerInfo(SelectSessionHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.GONE);

        Glide.with(mContext)
                .load(sessionItem.speakers.get(0).picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sessionHolder.speakerImg);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name);
    }

    private void setTwoSpeakerInfo(SelectSessionHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.VISIBLE);

        Glide.with(mContext)
                .load(sessionItem.speakers.get(0).picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sessionHolder.speakerImg);

        Glide.with(mContext)
                .load(sessionItem.speakers.get(1).picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sessionHolder.speakerImgSecond);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name + "/" + sessionItem.speakers.get(1).name);
    }

    public static class SelectSessionHolder {

        public TextView sessionTime;
        public TextView sessionTrack;
        public ImageView speakerImg;
        public ImageView speakerImgSecond;
        public TextView speakerName;
        public TextView sessionName;

    }
}
