package com.example.contactv1;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    MyDatabase database;
    FloatingActionButton floatingActionButton;
    ListView lvContact;
    EditText edtSearch;
    ImageView tvSearch;
    Intent intent;
    ContactData myContact;
    List<ContactData> listNumber;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        database=new MyDatabase(this);
        //database.deleteAll();
        //setData();
        setIdList();
        listNumber=database.getAllContact();
        adapter=new CustomAdapter(this,listNumber);
        lvContact.setAdapter(adapter);
        setFloatingActionButton();
        setEdit();

    }
    public void init(){
        floatingActionButton=(FloatingActionButton)findViewById(R.id.btn_add_contact);
        lvContact=(ListView) findViewById(R.id.lv_contact);
        edtSearch=(EditText)findViewById(R.id.edt_search);
        listNumber=new ArrayList<ContactData>();
        tvSearch=(ImageView) findViewById(R.id.tv_search);
    }
    public void setIdList(){
        for(int i=0;i<listNumber.size();i++){
            listNumber.get(i).setId(i);
        }
    }
    public void setData(){
        ContactData contactData=new ContactData();
        database=new MyDatabase(this);
        contactData.setAvatarName("person_profile");
        contactData.setContactName("Nguyen Van A");
        contactData.setPhoneNumber("0373595052");
        database.addContact(contactData);

    }
    public void setFloatingActionButton(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,AddContactActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    public void doSearch(View v){
        if(!edtSearch.getText().toString().equals("")){
            String a=edtSearch.getText().toString();
            ArrayList<ContactData> myContacts=new ArrayList<ContactData>();
            for(ContactData myContact:listNumber){
                if(myContact.getContactName().equalsIgnoreCase(a)){
                    myContacts.add(myContact);
                }
            }
            CustomAdapter adap=new CustomAdapter(this,myContacts);
            lvContact.setAdapter(adap);
        }
        else{
            lvContact.setAdapter(adapter);
        }

    }
    public int getPosition(String a){
        for(ContactData myContact:listNumber){
            if(myContact.getContactName().equals(a))
                return listNumber.indexOf(myContact);
        }
        return -1;
    }
    public void setEdit() {
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(MainActivity.this, EditContactActivity.class);
                String name = listNumber.get(i).getContactName();
                String number = listNumber.get(i).getPhoneNumber();
                myContact = new ContactData();
                myContact.setAvatarName("person_profile");
                myContact.setContactName(name);
                myContact.setPhoneNumber(number);
                intent.putExtra("item", myContact);
                intent.putExtra("position", i);
                startActivityForResult(intent, 2);
            }
        });
    }
    @Override
    public void onActivityResult(int req,int resultCode,Intent data){
        super.onActivityResult(req,resultCode,data);
        if(req==1){
            if(resultCode== Activity.RESULT_OK){
                myContact=(ContactData) data.getSerializableExtra("new");
                listNumber.add(myContact);
                myContact.setId(listNumber.size());
                database.addContact(myContact);
                adapter.notifyDataSetChanged();
            }
        }
        else
        if(req==2){
            if(resultCode== Activity.RESULT_OK){
                myContact=(ContactData) data.getSerializableExtra("new");
                int pos=(int)data.getIntExtra("position",-1);
                listNumber.get(pos).setContactName(myContact.getContactName());
                listNumber.get(pos).setPhoneNumber(myContact.getPhoneNumber());
                myContact.setId(listNumber.get(pos).getID());
                database.editContact(myContact);
                adapter.notifyDataSetChanged();
            }

        }
    }
}
