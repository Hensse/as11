package com.example.as11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public class Android_Contact {

        public String android_contact_Name = "";
    }


    private final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void callContacts(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }


    String name;

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        ArrayList<Android_Contact> alist = new ArrayList<Android_Contact>();
        ListView listView = (ListView)findViewById(R.id.lvID);



        if (reqCode == PICK_CONTACT) {
            if (reqCode == AppCompatActivity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c = getContentResolver().query(contactData, null, null, null, null);




                if (c.moveToFirst()) {


                    Android_Contact android_contact = new Android_Contact();
                    name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                    android_contact.android_contact_Name = name;
                    alist.add(android_contact);
                    Adapter_for_Android_Contacts adapter = new Adapter_for_Android_Contacts(this, alist);
                    listView.setAdapter(adapter);

                }

        }


        }


    }



    public class Adapter_for_Android_Contacts extends BaseAdapter {

        public Adapter_for_Android_Contacts(Context mContext, List<Android_Contact> mContact) {
            this.mContext = mContext;
            this.mList_Android_Contacts = mContact;
        }

        Context mContext;
        List<Android_Contact> mList_Android_Contacts;


        @Override
        public int getCount() {
            return mList_Android_Contacts.size();
        }

        @Override
        public Object getItem(int position) {
            return mList_Android_Contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(mContext,R.layout.lv_item,null);

            TextView textview_contact_Name= (TextView) view.findViewById(R.id.tvID);


            textview_contact_Name.setText(mList_Android_Contacts.get(position).android_contact_Name);



            view.setTag(mList_Android_Contacts.get(position).android_contact_Name);

            return view;
        }

    }
}


