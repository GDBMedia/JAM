package net.gdbmedia.jam.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import net.gdbmedia.jam.R;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.statusList) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}