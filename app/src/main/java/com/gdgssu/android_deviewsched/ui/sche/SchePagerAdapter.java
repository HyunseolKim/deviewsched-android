package com.gdgssu.android_deviewsched.ui.sche;

import android.content.Context;
import android.util.Log;
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
import com.gdgssu.android_deviewsched.helper.DPtoPixelConvertHelper;
import com.gdgssu.android_deviewsched.model.Session;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

public class SchePagerAdapter extends BaseAdapter {

    private static final String[] SESSION_TIME =
            {
                    "09:20~09:40", "10:00~10:50", "11:00~11:50", "12:00~12:50", "12:50 ~ 14:10", "14:10 ~ 15:00", "15:10 ~ 16:00", "16:10 ~ 17:00", "17:15 ~ 18:30"
            };

    private LayoutInflater mInflater;
    private ArrayList<Session> sessionItems;
    private ArrayList<Integer> mSelectedItemPosition = new ArrayList<>();
    private Context mContext;

    public SchePagerAdapter(Track track, Context context) {

        this.sessionItems = track.sessions;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    public void addSelectedItemPosition(int position){
        mSelectedItemPosition.add(position);
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
        SessionViewHolder sessionHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_session_sche_sessioninfo, parent, false);

            sessionHolder = new SessionViewHolder();

            sessionHolder.speakerImg = (ImageView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_img);
            sessionHolder.speakerImgSecond = (ImageView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_img_second);
            sessionHolder.speakerName = (TextView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_name);
            sessionHolder.sessionName = (TextView) convertView.findViewById(R.id.item_session_sche_sessioninfo_session_title);

            convertView.setTag(sessionHolder);

        } else {
            sessionHolder = (SessionViewHolder) convertView.getTag();
        }

        Session sessionItem = sessionItems.get(position);

        if (sessionItem.speakers.size() > 1) {
            setTwoSpeakerInfo(sessionHolder, sessionItem);
        } else {
            setOneSpeakerInfo(sessionHolder, sessionItem);
        }

        sessionHolder.sessionName.setText(sessionItem.title);

        for (int i=0;i<mSelectedItemPosition.size();i++){
            if (position==mSelectedItemPosition.get(i)){
                convertView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_blue_light));
            }
        }

        return convertView;
    }

    public void setOneSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.GONE);

        Glide.with(mContext)
                .load(sessionItem.speakers.get(0).picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(sessionHolder.speakerImg);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name);
    }

    private void setTwoSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
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

    public static class SessionViewHolder {

        public TextView sessionTime;
        public ImageView speakerImg;
        public ImageView speakerImgSecond;
        public TextView speakerName;
        public TextView sessionName;
    }
}
