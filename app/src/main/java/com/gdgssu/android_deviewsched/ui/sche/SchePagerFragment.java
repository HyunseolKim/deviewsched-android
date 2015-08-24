package com.gdgssu.android_deviewsched.ui.sche;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.ui.detailsession.DetailSessionActivity;

public class SchePagerFragment extends Fragment {

    private static final String TAG = "SchePagerFragment";

    private Track mTrackData;
    private boolean sessionPickMode = false;

    public static SchePagerFragment newInstance(Track track) {
        SchePagerFragment fragment = new SchePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, track);
        fragment.setArguments(bundle);

        return fragment;
    }

    public SchePagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null){
            mTrackData = (Track)getArguments().getSerializable(TAG);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sche_pager, container, false);

        initScheListView(rootView);

        return rootView;
    }

    private void initScheListView(View rootView) {
        final ListView listview = (ListView) rootView.findViewById(R.id.fragment_sche_pager_list);
        SchePagerAdapter adapter = new SchePagerAdapter(mTrackData, DeviewSchedApplication.GLOBAL_CONTEXT);

        //임시로 아이템을 누르면 테스트중인 액티비티가 뜨게 만들어놓음
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * Item을 클릭했을때 Day부분(position 0, 8)을 누르면 아무일도 일어나지 않도록 해놓음
                 * 이 Position은 Deview2015 스케줄이 나오고 꼭 다시한번 확인해보아야할 부분이다.
                 */
                if (sessionPickMode){
                    if ((position>0&&position<8)||(position>8)){
                        listview.getChildAt(position).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    }
                }else{
                    if ((position == 0) || (position == 8)) {
                    }else{
                        getActivity().startActivity(new Intent(getActivity(), DetailSessionActivity.class));
                    }
                }
            }
        });
        listview.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_all_sche, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_all_sche_favorite:

                if (sessionPickMode){
                    sessionPickMode=false;
                }else{
                    sessionPickMode=true;
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
