package com.example.realmdbexam;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by junsuk on 2017. 9. 6..
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration.Builder config = new RealmConfiguration.Builder();
        // 스키마가 변경되면 다 날리고 다시 가자
        config.deleteRealmIfMigrationNeeded();

        Realm.setDefaultConfiguration(config.build());
    }
}
