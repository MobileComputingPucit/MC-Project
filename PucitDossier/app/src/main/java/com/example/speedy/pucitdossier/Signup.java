package com.example.speedy.pucitdossier;

/**
 * Created by Speedy on 13/01/2018.
 */

public class Signup {
    private int Account_id;
    private String Email;
    private String Username;
    private String Password;
    private String Phone_no;
    private String Gender;
    private String Date_of_Birth;
    private String Entry_date;

    // Empty constructor
    public Signup(){

    }
    // constructor
    public Signup(int id, String email, String name, String password, String _phone_number, String gender, String date_of_Birth, String entry_date){
        this.Account_id = id;
        this.Email = email;
        this.Username = name;
        this.Password = password;
        this.Phone_no = _phone_number;
        this.Gender = gender;
        this.Date_of_Birth = date_of_Birth;
        this.Entry_date = entry_date;
    }

    // constructor
    public Signup(String email, String name, String password, String _phone_number, String gender, String date_of_Birth, String entry_date){
        this.Email = email;
        this.Username = name;
        this.Password = password;
        this.Phone_no = _phone_number;
        this.Gender = gender;
        this.Date_of_Birth = date_of_Birth;
        this.Entry_date = entry_date;
    }

    public int getAccount_id(){
        return Account_id;
    }
    public void setAccount_id(int Account_id){
        this.Account_id = Account_id;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }

    public String getPhone_no() {
        return Phone_no;
    }
    public void setPhone_no(String Phone_no){
        this.Phone_no = Phone_no;
    }

    public String getGender() {
        return Gender;
    }
    public void setGender(String Gender){
        this.Gender = Gender;
    }

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }
    public void setDate_of_Birth(String Date_of_Birth){
        this.Date_of_Birth = Date_of_Birth;
    }

    public String getEntry_date() {
        return Entry_date;
    }
    public void setEntry_date(String Entry_date){
        this.Entry_date= Entry_date;
    }
}
