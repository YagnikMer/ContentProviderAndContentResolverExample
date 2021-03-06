package com.worldmer.contantproviderexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.worldmer.contantproviderexample.R;
import com.worldmer.contantproviderexample.helper.DatabaseHelper;
import com.worldmer.contantproviderexample.modal.Contacts;

/**
 * Created by yagnik on 1/1/18.
 */

public class InsertActivity extends AppCompatActivity {
    EditText edtName, edtPhone;
    Button btnCreate;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        bindView();
        initialize();
        listeners();
    }

    public void bindView() {
        edtName = (EditText) findViewById(R.id.edtname);
        edtPhone = (EditText) findViewById(R.id.edtphone);
        btnCreate = (Button) findViewById(R.id.btncreate);
    }

    private void initialize() {
        edtName.requestFocus();
        dbhelper = new DatabaseHelper(InsertActivity.this);
    }

    private void listeners() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().trim().length() > 0
                        && edtPhone.getText().toString().trim().length() > 0) {
                    Contacts contact = new Contacts();
                    contact.setName(edtName.getText().toString());
                    contact.setPhone(edtPhone.getText().toString());
                    dbhelper.insertContact(contact);

                    Toast.makeText(getApplicationContext(), "Create Contacts Successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(InsertActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Required Field are empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}