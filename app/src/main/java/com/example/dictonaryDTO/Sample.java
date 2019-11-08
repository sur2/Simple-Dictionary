package com.example.dictonaryDTO;

public class Sample {

    private long sample_key;
    private String name;
    private String pullName;
    private String contents;
    private boolean bookmark;

    public Sample() {
        super();
    }

    public Sample(long sample_key, String name, String pullName, String contents, boolean bookmark) {
        this.sample_key = sample_key;
        this.name = name;
        this.pullName = pullName;
        this.contents = contents;
        this.bookmark = bookmark;
    }

    public Sample(String name, String pullName, String contents) {
        this.name = name;
        this.pullName = pullName;
        this.contents = contents;
    }

    public long getSample_key() {
        return sample_key;
    }

    public void setSample_key(long sample_key) {
        this.sample_key = sample_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPullName() {
        return pullName;
    }

    public void setPullName(String pullName) {
        this.pullName = pullName;
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

    @Override
    public String toString() {
        return "Sample{" +
                "sample_key=" + sample_key +
                ", name='" + name + '\'' +
                ", pullName='" + pullName + '\'' +
                ", contents='" + contents + '\'' +
                ", bookmark=" + bookmark +
                '}';
    }
}
