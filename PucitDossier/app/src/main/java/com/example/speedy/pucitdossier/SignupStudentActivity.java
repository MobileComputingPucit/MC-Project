package com.example.speedy.pucitdossier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Speedy on 06/01/2018.
 */

public class SignupStudentActivity extends Activity {
    EditText username;
    EditText password;
    EditText confirm_password;
    EditText phone_no;
    RadioGroup gender;
    EditText birthday;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

    }
    @Override
    public void onStart(){
        super.onStart();
        registerView();
    }
    private void registerView(){
        username=(EditText) findViewById(R.id.et_user_signup_student);
        password=(EditText) findViewById(R.id.et_password_signup_student);
        confirm_password=(EditText) findViewById(R.id.et_confirm_password_signup_student);
        phone_no=(EditText) findViewById(R.id.et_phone_signup_student);
        gender=(RadioGroup) findViewById(R.id.rg_gender_signup_student);
        birthday=(EditText) findViewById(R.id.et_birthday_signup_student);

        phone_no.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(phone_no, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        birthday.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isBirthDate(birthday, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        Button done = (Button)findViewById(R.id.btn_done_signup_student);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if( username.getText().toString().length() == 0 )
                    username.setError( "Name is required!" );
                if( password.getText().toString().length() == 0 )
                    password.setError( "Enter password!" );
                if( confirm_password.getText().toString().length() == 0 )
                    confirm_password.setError( "Confirm Password!" );
                if (gender.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_LONG).show();
                }

                if(checkValidation()){
                    String p1 = password.getText().toString();
                    String p2= confirm_password.getText().toString();
                    if(p1.equals(p2)) {
                       // Toast.makeText(getApplicationContext(), "You register successfully", Toast.LENGTH_LONG).show();
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        //for gender
                        int selectedId = gender.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) findViewById(selectedId);
                        //for current date
                        String date = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(new Date());
                        SharedPreferences shared = getApplicationContext().getSharedPreferences("EmailFile", Context.MODE_PRIVATE);
                        String email = (shared.getString("email", ""));
                        long result = db.addUser(new Signup(email,username.getText().toString(), password.getText().toString(),phone_no.getText().toString(),radioButton.getText().toString(), birthday.getText().toString(),date ));
                        if(result != -1) {
                            //Toast.makeText(getApplicationContext(), "You register successfully", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Something wrong during registration process", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and Confirm password are not matched", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Form contains error", Toast.LENGTH_LONG).show();

            }
        });
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isPhoneNumber(phone_no, true)) ret = false;
        if(!Validation.isBirthDate(birthday, true)) ret =false;
        if (gender.getCheckedRadioButtonId() == -1) ret =false;
        if( username.getText().toString().length() == 0 ) ret =false;
        if( password.getText().toString().length() == 0 ) ret=false;
        if( confirm_password.getText().toString().length() == 0 ) ret =false;

        return ret;
    }
}
