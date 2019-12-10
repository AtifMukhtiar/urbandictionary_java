
package com.jadgroup.urbandictionary.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("thumbs_up")
    @Expose
    private Integer thumbsUp;
    @SerializedName("sound_urls")
    @Expose
    private java.util.List<String> soundUrls = null;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("defid")
    @Expose
    private Integer defid;
    @SerializedName("current_vote")
    @Expose
    private String currentVote;
    @SerializedName("written_on")
    @Expose
    private String writtenOn;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("thumbs_down")
    @Expose
    private Integer thumbsDown;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public java.util.List<String> getSoundUrls() {
        return soundUrls;
    }

    public void setSoundUrls(java.util.List<String> soundUrls) {
        this.soundUrls = soundUrls;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getDefid() {
        return defid;
    }

    public void setDefid(Integer defid) {
        this.defid = defid;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public void setCurrentVote(String currentVote) {
        this.currentVote = currentVote;
    }

    public String getWrittenOn() {
        return writtenOn;
    }

    public void setWrittenOn(String writtenOn) {
        this.writtenOn = writtenOn;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Integer thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

}
