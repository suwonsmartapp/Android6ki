package com.example.memosqlite.models;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by junsuk on 2017. 8. 31..
 */

public class Memo extends RealmObject {
    private static AtomicInteger INTEGER_COUNTER;

    @PrimaryKey
    private int id;

    @Required
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNewId(Realm realm) {
        if (INTEGER_COUNTER == null) {
            INTEGER_COUNTER = new AtomicInteger(0);

            Number maxId = realm.where(Memo.class).max("id");
            if (maxId != null) {
                INTEGER_COUNTER.set(maxId.intValue() + 1);
            }
        }

        return INTEGER_COUNTER.getAndIncrement();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Memo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
