package com.example.dictonaryDTO;

public class Sample {

    private String name;
    private String pullName;
    private String contents;
    private boolean bookmark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPullName() {
        return pullName;
    }

    public void setPullName(String engName) {
        this.pullName = engName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
