package net.gdbmedia.jam.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.adapters.StatusListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.statusList) RecyclerView mRecyclerView;
    @Bind(R.id.newApp)
    Button mNewApp;
    private StatusListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new StatusListAdapter(MainActivity.this, Constants.APPLICATION_STATUSES);
        mRecyclerView.setAdapter(mAdapter);


        mNewApp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
       makeNewApp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                makeNewApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeNewApp() {
        Intent intent = new Intent(MainActivity.this, NewAppActivity.class);
        startActivity(intent);
    }
}
