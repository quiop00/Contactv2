package com.example.contactv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class EditContactActivity extends AppCompatActivity {
    EditText nameContact;
    EditText phoneNumber;
    ContactData myContact;
    int position;
    String name;
    String number;
    String avatar="person_profile";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        intent=getIntent();
        myContact=(ContactData)intent.getSerializableExtra("item");
        position=(int) intent.getIntExtra("position",-1);
        init();
        nameContact.setText(myContact.getContactName());
        phoneNumber.setText(myContact.getPhoneNumber());
    }
    public void init(){
        nameContact=(EditText) findViewById(R.id.name_contact);
        phoneNumber=(EditText) findViewById(R.id.phone_number);
    }
    public void Cancel(View v) {
        onBackPressed();
    }
    public void doCompleted(View v) {

        if(nameContact.getText().toString().equals("")||phoneNumber.getText().toString().equals("")){
            Toast toast= Toast.makeText(this,"Please input name and number phone",   Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            name=nameContact.getText().toString();
            number=phoneNumber.getText().toString();
            myContact=new ContactData();
            myContact.setContactName(name);
            myContact.setPhoneNumber(number);
            Intent data=new Intent();
            data.putExtra("new",myContact);
            data.putExtra("position",position);
            setResult(Activity.RESULT_OK,data);
            finish();
        }
    }
    public void onBackPressed(){
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}
