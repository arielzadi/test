package com.youtube_list.pojo;

import java.util.List;

/**
 * Created by arielzadok on 12/07/2016.
 */
public class PlayLists {
    private List<YouTubeListItems> Playlists;

    public PlayLists() {
    }

    public List<YouTubeListItems> getPlaylists() {
        return Playlists;
    }

    public void setPlaylists(List<YouTubeListItems> playlists) {
        Playlists = playlists;
    }
}
