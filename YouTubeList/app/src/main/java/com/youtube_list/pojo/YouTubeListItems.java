package com.youtube_list.pojo;

import java.util.List;

/**
 * Created by arielzadok on 12/07/2016.
 */
public class YouTubeListItems {
    private List<YouTubeItem> ListItems;
    private String ListTitle;

    public YouTubeListItems() {
    }

    public List<YouTubeItem> getListItems() {
        return ListItems;
    }

    public void setListItems(List<YouTubeItem> listItems) {
        ListItems = listItems;
    }

    public String getListTitle() {
        return ListTitle;
    }

    public void setListTitle(String listTitle) {
        ListTitle = listTitle;
    }
}
