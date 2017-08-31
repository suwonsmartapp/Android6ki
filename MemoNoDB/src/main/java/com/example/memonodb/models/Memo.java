package com.example.memonodb.models;

/**
 * Created by junsuk on 2017. 8. 31..
 */

public class Memo {
    private String title;
    private String memo;

    public Memo(String title, String memo) {
        this.title = title;
        this.memo = memo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Memo{");
        sb.append("title='").append(title).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
