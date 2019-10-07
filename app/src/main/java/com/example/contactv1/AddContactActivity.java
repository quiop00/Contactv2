package com.example.contactv1;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class AddContactActivity extends AppCompatActivity {
    EditText nameContact;
    EditText phoneNumber;
    ContactData newContact;
    String name;
    String number;
    String avatar="person_profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        //Intent intent=getIntent();
        init();
    }
    public void init(){
        nameContact=(EditText) findViewById(R.id.edt_name);
        phoneNumber=(EditText) findViewById(R.id.edt_number);
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
            newContact=new ContactData();
            newContact.setContactName(name);
            newContact.setPhoneNumber(number);
            Intent data=new Intent();
            data.putExtra("new",newContact);
            setResult(Activity.RESULT_OK,data);
            finish();
        }
    }
    public void onBackPressed(){
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}
