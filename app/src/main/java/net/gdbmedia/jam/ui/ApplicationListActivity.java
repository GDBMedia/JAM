package net.gdbmedia.jam.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.adapters.ApplicationAdapter;
import net.gdbmedia.jam.models.Application;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationListActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.applicationsList) RecyclerView mRecyclerView;
    private int mStatus;
    private ChildEventListener mChildEventListener;
    private SharedPreferences mSharedPreferences;
    private String mUserID;
    private ArrayList<Application> mApplications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserID = mSharedPreferences.getString(Constants.USER_ID_REF, null);

        mStatus = intent.getIntExtra(Constants.STATUS_REF, 0);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(Constants.STATUS_MAP_REVERSE.get(mStatus));

        LinearLayoutManager llm = new LinearLayoutManager(ApplicationListActivity.this);

        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        setStatusListener();
    }

    private void setStatusListener() {
        Query query = null;
        if(mStatus > 0){
            query = FirebaseDatabase.getInstance().getReference(Constants.USERS_REF + mUserID + Constants.APPLICATIONS_REF)
                    .orderByChild(Constants.STATUS_DB_REF).equalTo(mStatus);
        }else{
            query = FirebaseDatabase.getInstance().getReference(Constants.USERS_REF + mUserID + Constants.APPLICATIONS_REF);
        }

        mChildEventListener = query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildAdded: " ,dataSnapshot.getValue().toString());
                mApplications.add(dataSnapshot.getValue(Application.class));
                setAdapter();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildChanged: ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved: ");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildMoved: ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    private void setAdapter() {
        ApplicationAdapter adapter = new ApplicationAdapter(ApplicationListActivity.this, mApplications);
        mRecyclerView.setAdapter(adapter);
    }
}
