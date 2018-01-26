package com.example.speedy.pucitdossier;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    EditText e;
    String emailFinal;
    String passwordFinal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email = (EditText)findViewById(R.id.et_email_login);
        EditText password = (EditText)findViewById(R.id.et_password_login);

        //opening sqlite
        dbhelper= new DatabaseHandler(this);
        db = dbhelper.getReadableDatabase();

        Button login =(Button)findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btn_login) {
                    e =(EditText)findViewById(R.id.et_email_login);

                    e.addTextChangedListener(new TextWatcher() {
                        // after every change has been made to this editText, we would like to check validity
                        public void afterTextChanged(Editable s) {
                            Validation.isEmailAddress(e, true);
                        }
                        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
                        public void onTextChanged(CharSequence s, int start, int before, int count){}
                    });


                    EditText p =(EditText)findViewById(R.id.et_password_login);
                    if( p.getText().toString().length() == 0 )
                        p.setError( "Enter password!" );
                    if(checkValidation()) {
                        emailFinal = e.getText().toString();
                        passwordFinal = p.getText().toString();
                    }
                    if(db.isOpen()) {
                        //cursor = db.rawQuery("SELECT *FROM "+DatabaseHandler.TABLE_NAME+" WHERE "+DatabaseHandler.KEY_PASSWORD + "=?",new String[] {password});
                        cursor = db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_NAME + " WHERE " + DatabaseHandler.KEY_EMAIL + " = '" + emailFinal + "' AND " + DatabaseHandler.KEY_PASSWORD + " = '" + passwordFinal + "'", null);
                        //cursor = db.rawQuery("select * from " +
                        //              DatabaseHandler.TABLE_NAME + " where " + DatabaseHandler.KEY_EMAIL + " = ? AND " + DatabaseHandler.KEY_PASSWORD +
                        //            "= ?", new String[] { email, password});
                        // MyAsyncTask task = new MyAsyncTask();
                        // task.execute(email,password);
                  //      cursor = db.rawQuery("select * from " + DatabaseHandler.TABLE_NAME + " where " + DatabaseHandler.KEY_PASSWORD + "= '"+ password+"'", null);
                    }
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);

                            //Toast.makeText(getApplicationContext(), "Login successfully!" + cursor.getString(1) + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email or password is wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        TextView signup = (TextView)findViewById(R.id.tv_signup_login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Signup1Activity.class);
                startActivity(i);
            }
        });
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isEmailAddress(e, true)) ret = false;
        return ret;
    }
}
/*public class MyAsyncTask extends AsyncTask<String,Integer,String>{
    protected String doInBackground(String... params) {

        return ;
    }
}*/

