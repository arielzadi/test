package com.youtube_list.pojo;

/**
 * Created by arielzadok on 12/07/2016.
 */
public class YouTubeItem {
    private String Title;
    private String link;
    private String thumb;

    public YouTubeItem() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
