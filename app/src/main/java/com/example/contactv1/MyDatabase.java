package com.example.contactv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DATABASE_NAME="Contact_Manager";
   public MyDatabase(Context context){
       super(context,DATABASE_NAME,null,VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script="create table contact(id INTEGER primary Key,avatar TEXT,name TEXT,phone TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addContact(ContactData myContact){
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("id",myContact.getID());
       values.put("name",myContact.getContactName());
       values.put("avatar",myContact.getAvatar());
       values.put("phone",myContact.getPhoneNumber());
       db.insert("contact",null,values);
       db.close();
   }
   public ArrayList<ContactData> getAllContact(){
       ArrayList<ContactData> contacts=new ArrayList<ContactData>();
       String script="select*from contact";
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor=db.rawQuery(script,null);
       cursor.moveToFirst();
       while(cursor.isAfterLast() == false){
           ContactData contact=new ContactData();
           contact.setId(cursor.getInt(0));
           contact.setAvatarName(cursor.getString(1));
           contact.setContactName(cursor.getString(2));
           contact.setPhoneNumber(cursor.getString(3));
           contacts.add(contact);
           cursor.moveToNext();
       }
       return contacts;
   }
    public void editContact(ContactData myContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("avatar", myContact.getAvatar());
        values.put("name", myContact.getContactName());
        values.put("phone",myContact.getPhoneNumber());
        db.update("contact", values, "id=?", new String[] { String.valueOf(myContact.getID()) });
        db.close();
    }
    public void deleteContact(int contactId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contact", "id = ?", new String[] { String.valueOf(contactId) });
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contact", null, null);
        db.close();
    }
}
