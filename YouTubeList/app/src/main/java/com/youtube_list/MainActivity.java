package com.youtube_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;


import com.google.gson.Gson;
import com.youtube_list.fragments.MainViewFragment;
import com.youtube_list.fragments.PlayListFragment;
import com.youtube_list.pojo.PlayLists;
import com.youtube_list.pojo.YouTubeListItems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PlayLists play;
    private List<YouTubeListItems> playLists;


    FrameLayout fragmentFrameContainer;

    private MainViewFragment mainViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playLists = new ArrayList<>();
        setContentView(R.layout.activity_main);
        fragmentFrameContainer = (FrameLayout) findViewById(R.id.fragment_frame_container);
        getJsonPlayList();

        mainViewFragment = new MainViewFragment();
        mainViewFragment.setFragment(playLists);

        getFragmentManager().beginTransaction().add(R.id.fragment_frame_container, mainViewFragment).commit();

    }

    private void getJsonPlayList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://www.razor-tech.co.il/hiring/youtube-api.json";

                String response = fetchUrl(url);

                if (response==null){
                    return;
                }
                Gson gson = new Gson();
                play = gson.fromJson(response, PlayLists.class);

                if (play!=null){
                    playLists.clear();
                    playLists.addAll(play.getPlaylists());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyServerResponseToView();
                    }
                });
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        int fragmentsCount = getFragmentManager().getBackStackEntryCount();
        if (fragmentsCount > 0) {
            String fragmentName = getFragmentManager().getBackStackEntryAt(fragmentsCount - 1).getName();
            switch (fragmentName) {
                case Utilities.MAIN_FRAGMENT:
                    new AlertDialog.Builder(this)
                            .setTitle("Quit")
                            .setMessage("Are you sure you want to quit?")
                            .setPositiveButton("Cancel", null)
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    System.exit(0);
                                    //turn off application
                                }
                            }).show();
                    break;
                case Utilities.PLAY_LIST_FRAGMENT:
                    getFragmentManager().popBackStack();

                break;
            }
        }
    }

    private void notifyServerResponseToView(){
        mainViewFragment.notifyDataUpdate();

    }

    public static String fetchUrl(String strUrl){
        String output = "";
        String line = null;
        try {
            URL url = new URL(strUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = reader.readLine()) != null) {
                output += line;
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return output;
    }
}
