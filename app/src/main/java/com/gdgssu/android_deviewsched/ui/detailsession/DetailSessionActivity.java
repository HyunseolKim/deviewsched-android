package com.gdgssu.android_deviewsched.ui.detailsession;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.R;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class DetailSessionActivity extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<String>();

    private ListView listView;

    private String dummyText = "대통령의 임기연장 또는 중임변경을 위한 헌법개정은 그 헌법개정 제안 당시의 대통령에 대하여는 효력이 없다. 대통령이 궐위된 때 또는 대통령 당선자가 사망하거나 판결 기타의 사유로 그 자격을 상실한 때에는 60일 이내에 후임자를 선거한다. 체포·구속·압수 또는 수색을 할 때에는 적법한 절차에 따라 검사의 신청에 의하여 법관이 발부한 영장을 제시하여야 한다. 다만, 현행범인인 경우와 장기 3년 이상의 형에 해당하는 죄를 범하고 도피 또는 증거인멸의 염려가 있을 때에는 사후에 영장을 청구할 수 있다.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_session);

        /**
         * Todo 이전 세션리스트에서 Intent를 이용해 데이터를 가져와야한다.
         */

        //Intent intent = getIntent();
        //Session sessionInfo = (Session)intent.getSerializableExtra("KEY")

        // CoolapsingView를 테스트하기위한 리스트뷰 더미데이터
        arrayList.add("댓글1");
        arrayList.add("댓글2");
        arrayList.add("댓글3");

        initView();
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

        collapsingToolbar.setTitle("행복한 프로그래머");

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

        TextView sessionTarget = (TextView)headerView.findViewById(R.id.item_detail_session_header_sessiontarget);
        sessionTarget.setText("훌륭한 프로그래머가 되고싶은 모든 프로그래머");

        TextView sessionContent = (TextView)headerView.findViewById(R.id.item_detail_session_header_sessioninfo);
        sessionContent.setText(dummyText);

        /**
         * 이곳에 서버로부터 가져온 각 세션에대한 정보를 뷰에 적용하는 코드를 짜면 된다.
         */

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
