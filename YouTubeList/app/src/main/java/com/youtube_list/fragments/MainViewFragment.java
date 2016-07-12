package com.youtube_list.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youtube_list.R;
import com.youtube_list.Utilities;
import com.youtube_list.adapt.PlayListAdapter;
import com.youtube_list.adapt.YouTubeItemsAdapter;
import com.youtube_list.pojo.YouTubeItem;
import com.youtube_list.pojo.YouTubeListItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mysc on 13.5.2016.
 */
public class MainViewFragment extends Fragment {
    private final String TAG = "MainViewFragment";

    private List<YouTubeListItems> playLists;
    private PlayListAdapter playListAdapter;

    private PlayListFragment playListFragment;

    @Bind(R.id.lv_playList) ListView playListListView;

    public void setFragment(List<YouTubeListItems> playLists) {

        this.playLists = playLists;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        playListAdapter = new PlayListAdapter(getActivity(),playLists);

        playListListView.setAdapter(playListAdapter);

        playListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                YouTubeListItems youTubeListItems = playLists.get(position);
                playListFragment = new PlayListFragment();
                playListFragment.setFragment(youTubeListItems);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                        .replace(R.id.fragment_frame_container, playListFragment).addToBackStack(Utilities.PLAY_LIST_FRAGMENT).commit();

            }
        });

        return view;
    }

    public void notifyDataUpdate(){
        playListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

}
