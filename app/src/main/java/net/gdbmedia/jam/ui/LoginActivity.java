package net.gdbmedia.jam.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.models.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.submit)
    Button mSubmit;
    @Bind(R.id.username)
    EditText mEmail;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.registerTextView)
    TextView mRegisterTextView;
    @Bind(R.id.title) TextView mTitleTextView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    private DatabaseReference mUsersReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        Typeface adam = Typeface.createFromAsset(getAssets(), "fonts/adam.otf");
//        mTitleTextView.setTypeface(adam);
//        mSubmit.setTypeface(adam);
//        mEmail.setTypeface(adam);
//        mPassword.setTypeface(adam);
//        mRegisterTextView.setTypeface(adam);


        mSubmit.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthProgressDialog();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mAuthProgressDialog.show();

                    mEditor.putString(Constants.USER_ID_REF, user.getUid()).apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();




                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view == mSubmit) {
            loginWithPassword();
        }
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void loginWithPassword() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        boolean error = false;
        if (email.equals("")) {
            mEmail.setError("Please enter your email");
            error = true;
        }
        if (password.equals("")) {
            mPassword.setError("Password cannot be blank");
            error = true;
        }
        if (error){
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mAuthProgressDialog.dismiss();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mAuthProgressDialog.dismiss();
    }
}
