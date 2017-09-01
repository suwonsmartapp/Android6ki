package com.example.memosqlite.models;

import io.realm.RealmObject;

/**
 * Created by junsuk on 2017. 8. 31..
 */

public class Memo extends RealmObject {

    private String title;
    private String memo;

    // 기본 생성자 필요

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
