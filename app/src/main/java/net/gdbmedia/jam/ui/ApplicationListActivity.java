package net.gdbmedia.jam.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationListActivity extends AppCompatActivity {
    @Bind(R.id.test)
    TextView mTest;
    private int mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        mStatus = intent.getIntExtra(Constants.STATUS_REF, 0);

        mTest.setText(Integer.toString(mStatus));
    }
}
