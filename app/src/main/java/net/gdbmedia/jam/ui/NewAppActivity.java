package net.gdbmedia.jam.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.models.Application;
import net.gdbmedia.jam.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class NewAppActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{
    private final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.status) MaterialBetterSpinner mStatusSpinner;
    @Bind(R.id.folowUpTimeFrame) MaterialBetterSpinner mFollowUpSpinner;
    @Bind(R.id.application_format) MaterialBetterSpinner mApplicationFormat;
    @Bind(R.id.state) MaterialBetterSpinner mState;
    @Bind(R.id.companyNameEditText) EditText mCompanyNameEditText;
    @Bind(R.id.jobTitleEditText) EditText mJobTitleEditText;
    @Bind(R.id.dateEditText) EditText mDateEditText;
    @Bind(R.id.urlEditText) EditText mUrlEditText;
    @Bind(R.id.cityEditText) EditText mCityEditText;
    @Bind(R.id.hiringManagerEditText) EditText mHiringManagerEditText;
//    @Bind(R.id.hourlyEditText) EditText mHourlyEditText;
    @Bind(R.id.interviewDateEditText) EditText mInterviewDateEditText;
    @Bind(R.id.saveAppButton) Button mSaveButton;
    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog mApplicationDatePickerDialog;
    private DatePickerDialog mInterviewDatePickerDialog;
    private DatabaseReference mDatabase;
    private SharedPreferences mSharedPreferences;
    private String mUserID;
    private String mPushKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_app);
        ButterKnife.bind(this);
        setDatePickers();
        setSpinners();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserID = mSharedPreferences.getString(Constants.USER_ID_REF, null);
        mDatabase = FirebaseDatabase.getInstance().getReference("users/" + mUserID);

        mSaveButton.setOnClickListener(this);
        mDateEditText.setOnFocusChangeListener(this);
        mDateEditText.setKeyListener(null);
        mDateEditText.setOnClickListener(this);
        mInterviewDateEditText.setOnFocusChangeListener(this);
        mInterviewDateEditText.setKeyListener(null);
        mInterviewDateEditText.setOnClickListener(this);
    }

    private void setSpinners() {
        ArrayList<String> statusList = new ArrayList<String>(){
            {add(Constants.ACCEPTED);
                add(Constants.REJECTED);
                add(Constants.AWAITING_RESPONSE);
                add(Constants.HOPEFUL);}
        };
        ArrayAdapter<String> statusArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, statusList);
        ArrayAdapter<String> followUpArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Constants.FOLLOWUP_TIMEFRAME_LIST);
        ArrayAdapter<String> formatArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Constants.APPLICATION_FORMAT_ARRAY);
        ArrayAdapter<String> stateArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Constants.STATES_ARRAYLIST);

        mStatusSpinner.setAdapter(statusArrayAdapter);
        mFollowUpSpinner.setAdapter(followUpArrayAdapter);
        mApplicationFormat.setAdapter(formatArrayAdapter);
        mState.setAdapter(stateArrayAdapter);
    }

    private void setDatePickers() {
        myCalendar.setTime(new Date());
        DatePickerDialog.OnDateSetListener mApplicationDateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                int month = arg2 + 1;
                mDateEditText.setText(DateUtils.formatDate(month + "/" + arg3 + "/" + arg1, Constants.SOURCE));
            }
        };
        DatePickerDialog.OnDateSetListener mInterviewDateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                int month = arg2 + 1;
                mInterviewDateEditText.setText(DateUtils.formatDate(month + "/" + arg3 + "/" + arg1, Constants.SOURCE));
            }
        };
        mApplicationDatePickerDialog = new DatePickerDialog(this, mApplicationDateListener, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        mInterviewDatePickerDialog = new DatePickerDialog(this, mInterviewDateListener, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if (view == mSaveButton) {
            getFormInfo();
        }
        if(view == mInterviewDateEditText){
            mInterviewDatePickerDialog.show();
        }
        if(view == mDateEditText){
            mApplicationDatePickerDialog.show();
        }
    }

    private void getFormInfo() {
        Map<String, Object> childUpdates = new HashMap<>();
        mPushKey = mDatabase.child("applications").push().getKey();
        boolean error = false;

        if(required(mCompanyNameEditText))error = true;
        if(required(mDateEditText))error = true;
        if(required(mStatusSpinner)) error = true;

        if(error) return;

        String companyName = mCompanyNameEditText.getText().toString();
        String jobTitle = mJobTitleEditText.getText().toString();
        String appDate = mDateEditText.getText().toString();
        String status = mStatusSpinner.getText().toString();
        String followUpTimeFrame = mFollowUpSpinner.getText().toString();
        String applicationFormat = mApplicationFormat.getText().toString();
        String url = mUrlEditText.getText().toString();
        String state = mState.getText().toString();
        String city = mCityEditText.getText().toString();
        String hiringMangerName = mHiringManagerEditText.getText().toString();
        String interviewDate = mInterviewDateEditText.getText().toString();
        Application application = new Application(companyName, jobTitle, DateUtils.setToUnix(appDate, Constants.DATE_FORMAT_OUTPUT_YEAR),
                                                    Constants.STATUS_MAP.get(status), Constants.FOLLOW_UP_TIMES_MAP.get(followUpTimeFrame),
                                                    applicationFormat, url, city, state, hiringMangerName, DateUtils.setToUnix(interviewDate,
                                                    Constants.DATE_FORMAT_OUTPUT_YEAR), mPushKey);
        Map<String, Object> applicationValues = application.toMap();
        childUpdates.put("/applications/" + mPushKey, applicationValues);
        mDatabase.updateChildren(childUpdates);
        Intent intent = new Intent(NewAppActivity.this, ApplicationListActivity.class);
        intent.putExtra(Constants.STATUS_REF, Constants.STATUS_MAP.get(status));
        startActivity(intent);
    }

    private boolean required(EditText editText) {
        if(editText.getText().toString().equals("")){
            editText.setError(getString(R.string.feild_required));
            return true;
        }
        return false;
    }

    private void logMessage(String message) {
        Log.d(TAG, "onClick: " + message);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(view == mDateEditText && b){
            mApplicationDatePickerDialog.show();
        }
        if(view == mInterviewDateEditText && b){
            mInterviewDatePickerDialog.show();
        }
    }
}
