package com.example.tarea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.tarea.untils.AppConstants;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mUser, mPass, mEmail, mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.bt_send);
        mUser = findViewById(R.id.et_user);
        mPass = findViewById(R.id.et_password);
        mEmail = findViewById(R.id.et_email);
        mGender = findViewById(R.id.et_gender);

        mButton.setOnClickListener(v -> {
            Intent mIntent = new Intent(MainActivity.this, InformationView.class);
            mIntent.putExtra(AppConstants.USER, mUser.getText().toString());
            mIntent.putExtra(AppConstants.PASS, mPass.getText().toString());
            mIntent.putExtra(AppConstants.EMAIL, mEmail.getText().toString());
            mIntent.putExtra(AppConstants.GENDER, mGender.getText().toString());

            if (!mUser.getText().toString().isEmpty() && !mPass.getText().toString().isEmpty()
            && !mEmail.getText().toString().isEmpty() && !mGender.getText().toString().isEmpty()){
                if (validatePassword(mEmail.getText().toString())){
                    if(validateGender(mGender.getText().toString())){
                        startActivity(mIntent);
                    }
                }
            } else{
                Toast.makeText(MainActivity.this,"Debe llenar todos sus datos!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validatePassword(String email){
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            }
        }
        Toast.makeText(MainActivity.this,"El correo no es valido!!!", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean validateGender(String gender){
        if(gender.toLowerCase().equals("male")){
            return true;
        } else if(gender.toLowerCase().equals("female")){
            return true;
        }
        Toast.makeText(MainActivity.this,"El genero no es valido!!!", Toast.LENGTH_SHORT).show();
        return false;
    }
}
