package com.gdgssu.android_deviewsched.ui.detailsession;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.DetailSessionInfo;
import com.gdgssu.android_deviewsched.model.Speaker;
import com.gdgssu.android_deviewsched.model.Speakers;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

import static com.navercorp.volleyextensions.volleyer.Volleyer.volleyer;

public class DetailSessionActivity extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<String>();

    private ListView listView;

    private DetailSessionInfo sessionInfo;
    private Speakers speakers;

    private TextView sessionTitle;
    private TextView sessionDesc;
    private LinearLayout speakerBasket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_session);

        Intent intent = getIntent();
        sessionInfo = (DetailSessionInfo)intent.getSerializableExtra("DetailSessionInfo");

        arrayList.add("댓글1");
        arrayList.add("댓글2");
        arrayList.add("댓글3");

        initView();

        volleyer(DeviewSchedApplication.deviewRequestQueue)
                .get(DeviewSchedApplication.HOST_URL + "2015/"+sessionInfo.id+"/speakers")
                .withTargetClass(Speakers.class)
                .withListener(new Response.Listener<Speakers>() {
                    @Override
                    public void onResponse(Speakers item) {
                        speakers = item;

                        setData();
                    }
                })
                .withErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
                .execute();


    }

    public void setData() {
        sessionTitle.setText(sessionInfo.title);
        sessionDesc.setText(sessionInfo.description);

        for (int i=0;i<speakers.speakers.size();i++){
            setSpeakerInfo(i);
        }
    }

    private void setSpeakerInfo(int index) {
        LinearLayout speakerInfoLayout = (LinearLayout)LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_speaker_info, null, false);
        ImageView speakerPicture = (ImageView)speakerInfoLayout.findViewById(R.id.item_detail_session_header_speaker_img);
        TextView speakerName = (TextView)speakerInfoLayout.findViewById(R.id.item_detail_session_header_name);
        TextView speakerOrg = (TextView)speakerInfoLayout.findViewById(R.id.item_detail_session_header_company);
        TextView speakerIntro = (TextView)speakerInfoLayout.findViewById(R.id.item_detail_session_header_speakerinfo);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(speakers.speakers.get(index).picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(speakerPicture);

        speakerName.setText(speakers.speakers.get(index).name);
        speakerOrg.setText(speakers.speakers.get(index).organization);
        speakerIntro.setText(speakers.speakers.get(index).introduction);

        speakerBasket.addView(speakerInfoLayout);
    }


    private void initView() {

        initToolbar();
        loadBackdropImage();
        initListView();

    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle("세션 안내");

    }

    private void loadBackdropImage() {
        //임시 더미이미지
        ImageView backdropImage = (ImageView) findViewById(R.id.backdrop);
        Glide.with(getApplicationContext()).load("http://insanehong.kr/post/deview2013/@img/keynote.jpg")
                .centerCrop().into(backdropImage);
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.activity_detail_session_list);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            listView.setNestedScrollingEnabled(true);
        }

        initHeaderView();
        initFooterView();

        listView.setAdapter(new DetailSessionAdapter(getApplicationContext(), arrayList));
    }

    private void initHeaderView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_detail_session_header, null, false);

        ImageView backButton = (ImageView)headerView.findViewById(R.id.item_detail_session_header_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sessionTitle = (TextView)headerView.findViewById(R.id.item_detail_session_header_title);
        sessionDesc = (TextView)headerView.findViewById(R.id.item_detail_session_header_sessioninfo);
        speakerBasket = (LinearLayout)headerView.findViewById(R.id.item_detail_session_header_speaker_basket);

        listView.addHeaderView(headerView);
    }

    private void initFooterView() {
        View footerView = getLayoutInflater().inflate(R.layout.item_detail_session_footer, null, false);

        final EditText replyEditText = (EditText)footerView.findViewById(R.id.item_detail_session_footer_edittext);

        CircleButton replySendButton = (CircleButton)footerView.findViewById(R.id.item_detail_session_footer_sendreply);
        replySendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 이곳에 댓글을 달았을때의 로직을 작성해주면 됨.
                 */
                Toast.makeText(getApplicationContext(), replyEditText.getText(), Toast.LENGTH_SHORT).show();

            }
        });

        listView.addFooterView(footerView);
    }
}
