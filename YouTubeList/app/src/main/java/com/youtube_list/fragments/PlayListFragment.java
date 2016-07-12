package com.youtube_list.fragments;

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;
import com.youtube_list.R;
import com.youtube_list.YouTubeAc;
import com.youtube_list.adapt.YouTubeItemsAdapter;
import com.youtube_list.pojo.YouTubeItem;
import com.youtube_list.pojo.YouTubeListItems;

import java.util.List;
import java.util.regex.PatternSyntaxException;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mysc on 13.5.2016.
 */
public class PlayListFragment extends Fragment {
    private final String TAG = "PlayListFragment";
    private YouTubeItemsAdapter youTubeItemsAdapter;

    @Bind(R.id.you_tube_item_list) ListView youTubeItemList;
    @Bind(R.id.btn_back)
    ImageView btnBack;
    @Bind(R.id.tv_header)
    TextView header;
    private List<YouTubeItem> youTubeItems;
    YouTubeListItems youTubeListItems;

    public void setFragment(YouTubeListItems youTubeListItems) {
        this.youTubeListItems = youTubeListItems;
        this.youTubeItems = youTubeListItems.getListItems();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_you_tube_item_list, container, false);
        ButterKnife.bind(this, view);

        header.setText(youTubeListItems.getListTitle());

        youTubeItemsAdapter = new YouTubeItemsAdapter(getActivity(), youTubeItems);
        youTubeItemList.setAdapter(youTubeItemsAdapter);

        youTubeItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YouTubeItem youTubeItem = youTubeItems.get(position);
                String vidId = parseUrl(youTubeItem.getLink());
                if (vidId.length()>0){
                    Intent intent = new Intent(getActivity(),YouTubeAc.class);
                    intent.putExtra("data",vidId);
                    startActivity(intent);
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    public String parseUrl(String url){
        String[] splitArray = null;
        try {
            splitArray = url.split("=");
        } catch (PatternSyntaxException ex) {
            return "";
        }

        if (splitArray.length>1){
            return splitArray[1];
        }
        return "";
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
