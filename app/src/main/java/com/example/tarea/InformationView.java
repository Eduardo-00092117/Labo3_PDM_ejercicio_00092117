package com.example.tarea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tarea.untils.AppConstants;

public class InformationView extends AppCompatActivity {

    private Button mButtonBack, mButtonShare;
    private TextView mUser, mPass, mEmail, mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_view);

        mUser = findViewById(R.id.tv_user_info);
        mPass = findViewById(R.id.tv_pass_info);
        mEmail = findViewById(R.id.tv_email_info);
        mGender = findViewById(R.id.tv_gender_info);
        mButtonBack = findViewById(R.id.bt_back);
        mButtonShare = findViewById(R.id.bt_share);

        Intent mIntent = getIntent();

        mButtonBack.setOnClickListener(v ->{
            Intent mIntent2 = new Intent(InformationView.this, MainActivity.class);
            startActivity(mIntent2);
        });

        mButtonShare.setOnClickListener(v -> {
            Intent mIntent3 = new Intent();
            mIntent3.setAction(Intent.ACTION_SEND);
            mIntent3.setType("text/plain");
            String datos = "Username: " + mUser.getText().toString() + "\nPassword: " + mPass.getText().toString()
                            + "\nEmail: " + mEmail.getText().toString() + "\nGender: " + mGender.getText().toString();
            mIntent3.putExtra(Intent.EXTRA_TEXT, datos);
            startActivity(mIntent3);
        });

        if (mIntent != null) {
            String passFormated = "";

            for(int i = 0; i <= mIntent.getStringExtra(AppConstants.PASS).length()-1; i++){
                passFormated += "*";
            }

            mUser.setText(mIntent.getStringExtra(AppConstants.USER));
            mPass.setText(passFormated);
            mEmail.setText(mIntent.getStringExtra(AppConstants.EMAIL));
            mGender.setText(mIntent.getStringExtra(AppConstants.GENDER));
        }
    }
}
