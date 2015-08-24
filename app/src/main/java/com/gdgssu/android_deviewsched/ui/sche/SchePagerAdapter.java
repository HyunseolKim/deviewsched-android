package com.gdgssu.android_deviewsched.ui.sche;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Session;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

/**
 * Created by flashgugu on 15. 7. 29.
 */
public class SchePagerAdapter extends BaseAdapter {

    /**
     * Todo:Keynote시간까지 포함하여 9:30 ~ 9:50을 넣어야 함. 2014년 기준으로 하루에 한트랙에 8개의 세션이 존재함.
     */

    /**
     * 이 코드에서 Position과 관련한 부분은 Deview2015 스케줄이 나오고 꼭 다시한번 확인해보아야할 부분이다.
     */

    private static final int TYPE_DAY = 0;
    private static final int TYPE_SESSION = 1;
    private static final int TYPE_COUNT = 2;

    private LayoutInflater mInflater;
    private ArrayList<Session> sessionItems;
    private Context mContext;

    public SchePagerAdapter(Track track, Context context) {

        Session day1Item = new Session();
        Session day2Item = new Session();

        track.sessions.add(0, day1Item);
        track.sessions.add(8, day2Item);

        this.sessionItems = track.sessions;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sessionItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ((position==0)||(position==8)) {
            return TYPE_DAY;
        } else {
            return TYPE_SESSION;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return sessionItems.get(position);
    }

    //오류의 소지가 있음
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayViewHolder dayHolder;
        SessionViewHolder sessionHolder;

        switch (getItemViewType(position)){
            case TYPE_DAY:

                if (convertView==null){
                    convertView = mInflater.inflate(R.layout.item_session_sche_day, parent, false);

                    dayHolder = new DayViewHolder();

                    dayHolder.dayText = (TextView) convertView.findViewById(R.id.item_session_sche_day_day);
                    dayHolder.dateText = (TextView) convertView.findViewById(R.id.item_session_sche_day_date);

                    convertView.setTag(dayHolder);

                }else{
                    dayHolder = (DayViewHolder) convertView.getTag();
                }

                dayHolder.dayText.setText("Day 1");
                dayHolder.dateText.setText("9.14");

                break;

            case TYPE_SESSION:

                if (convertView==null){
                    convertView = mInflater.inflate(R.layout.item_session_sche_sessioninfo, parent, false);

                    sessionHolder = new SessionViewHolder();

                    sessionHolder.speakerImg = (ImageView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_img);
                    sessionHolder.speakerImgSecond = (ImageView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_img_second);
                    sessionHolder.speakerName = (TextView) convertView.findViewById(R.id.item_session_sche_sessioninfo_speaker_name);
                    sessionHolder.sessionName = (TextView) convertView.findViewById(R.id.item_session_sche_sessioninfo_session_title);

                    convertView.setTag(sessionHolder);

                }else{
                    sessionHolder = (SessionViewHolder) convertView.getTag();
                }

                Session sessionItem = sessionItems.get(position);
                Log.d("Position", position+"");

                if (sessionItem.speakers.size() > 1) {
                    setTwoSpeakerInfo(sessionHolder, sessionItem);
                } else {
                    setOneSpeakerInfo(sessionHolder, sessionItem);
                }

                sessionHolder.sessionName.setText(sessionItem.session_title);

                break;
        }

        return convertView;
    }

    public void setOneSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.GONE);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(sessionHolder.speakerImg);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name);
    }

    private void setTwoSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.VISIBLE);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(sessionHolder.speakerImg);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(1).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
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

    public static class DayViewHolder {

        public TextView dayText;
        public TextView dateText;

    }
}
