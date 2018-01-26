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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Speedy on 06/01/2018.
 */

public class Signup1Activity extends Activity {
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    EditText emailAddress;
    TextView learn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

    }
    @Override
    public void onStart(){
        super.onStart();
        registerView();

    }


    private void registerView(){
        emailAddress=(EditText)findViewById(R.id.et_email_signup1);
        // TextWatcher would let us check validation error on the fly
        emailAddress.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(emailAddress, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        Button nextStep = (Button)findViewById(R.id.btn_next_step_signup1);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if(checkValidation()){
                    sharedpreferences = getApplicationContext().getSharedPreferences("EmailFile", Context.MODE_PRIVATE);
                    editor = sharedpreferences.edit();
                    editor.putString("email", emailAddress.getText().toString());
                    editor.apply();
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(),SignupStudentActivity.class);
                     startActivity(i);
                 }
                  else
                      Toast.makeText(getApplicationContext(), "Form contains error", Toast.LENGTH_LONG).show();

            }
        });

    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isEmailAddress(emailAddress, true)) ret = false;
        return ret;
    }
}
