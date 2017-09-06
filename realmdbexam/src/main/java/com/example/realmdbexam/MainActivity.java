package com.example.realmdbexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.email_edit)
    EditText mEmailEdit;
    @butterknife.BindView(R.id.password_edit)
    EditText mPasswordEdit;
    @butterknife.BindView(R.id.new_password_edit)
    EditText mNewPasswordEdit;
    @butterknife.BindView(R.id.result_text)
    TextView mResultText;

    private Realm mRealm;
    private RealmAsyncTask mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);

        mRealm = Realm.getDefaultInstance();

        showResult();
    }

    public void SignUp(View view) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 추가
                User user = realm.createObject(User.class);
                user.setEmail(mEmailEdit.getText().toString());
                user.setPassword(mPasswordEdit.getText().toString());

                showResult();
            }
        });
    }

    public void SignIn(View view) {
        User user = mRealm.where(User.class)
                .equalTo("email", mEmailEdit.getText().toString())
                .equalTo("password", mPasswordEdit.getText().toString())
                .findFirst();

        if (user != null) {
            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePassword(View view) {

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 쿼리
                User user = mRealm.where(User.class)
                        .equalTo("email", mEmailEdit.getText().toString())
                        .equalTo("password", mPasswordEdit.getText().toString())
                        .findFirst();
                if (user != null) {
                    // 수정
                    user.setPassword(mNewPasswordEdit.getText().toString());
                }

                showResult();
            }
        });

    }

    public void deleteAccount(View view) {
        // 삭제
        mTransaction = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = mRealm.where(User.class)
                        .equalTo("email", mEmailEdit.getText().toString())
                        .equalTo("password", mPasswordEdit.getText().toString())
                        .findFirst();
                if (user != null) {
                    // 삭제
                    user.deleteFromRealm();
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                showResult();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResult() {
        RealmResults<User> users = mRealm.where(User.class).findAll();
        mResultText.setText(users.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mTransaction != null & !mTransaction.isCancelled()) {
            mTransaction.cancel();
        }
    }
}
