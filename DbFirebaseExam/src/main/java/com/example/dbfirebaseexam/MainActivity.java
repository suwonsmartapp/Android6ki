package com.example.dbfirebaseexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEmailEdit;
    private EditText mPasswordEdit;
    private EditText mNewPasswordEdit;
    private TextView mResultText;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("memo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailEdit = (EditText) findViewById(R.id.email_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);
        mNewPasswordEdit = (EditText) findViewById(R.id.new_password_edit);
        mResultText = (TextView) findViewById(R.id.result_text);

        // 실시간 모니터링
        setUpListener();
    }

    public void SignIn(View view) {

    }

    private void setUpListener() {
        // 실시간 모니터링
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Value is: " + dataSnapshot.toString());
                mResultText.setText(dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SignUp(View view) {
        User user = new User();
        user.setEmail(mEmailEdit.getText().toString());
        user.setPassword(mPasswordEdit.getText().toString());
        DatabaseReference push = myRef.push();
        user.setKey(push.getKey());
        push.setValue(user);
    }

    public void updatePassword(View view) {
        // 한번만
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User value = snapshot.getValue(User.class);

                    if (value.getEmail().equals(mEmailEdit.getText().toString())) {
                        key = snapshot.getKey();
                        break;
                    }
                }

                if (key != null) {
                    User user = new User();
                    user.setEmail(mEmailEdit.getText().toString());
                    user.setPassword(mNewPasswordEdit.getText().toString());
                    myRef.child(key).setValue(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deleteAccount(View view) {
        User user = new User();
        user.setEmail(mEmailEdit.getText().toString());
        user.setPassword(mPasswordEdit.getText().toString());

//        myRef.child(user.getKey()).removeValue();

        // 한번만
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User value = snapshot.getValue(User.class);

                    if (value.getEmail().equals(mEmailEdit.getText().toString())) {
                        key = snapshot.getKey();
                        break;
                    }
                }

                if (key != null) {
                    myRef.child(key).removeValue();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
