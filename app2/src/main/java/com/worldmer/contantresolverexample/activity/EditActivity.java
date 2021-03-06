package com.worldmer.contantresolverexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.worldmer.contantresolverexample.R;
import com.worldmer.contantresolverexample.helper.ContentResolveHelper;
import com.worldmer.contantresolverexample.modal.Contacts;


/**
 * Created by yagnik on 1/1/18.
 */

public class EditActivity extends AppCompatActivity {
    EditText edtName, edtPhone;
    Button btnCreate;
    ContentResolveHelper contentResolveHelper;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
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
        contentResolveHelper = new ContentResolveHelper(EditActivity.this);
        edtName.requestFocus();
        try {
            index = getIntent().getIntExtra("Index", -1);
            ContentResolveHelper contentResolveHelper = new ContentResolveHelper(EditActivity.this);
            Contacts contacts= contentResolveHelper.getContact(index);
            edtName.setText(contacts.getName());
            edtPhone.setText(contacts.getPhone());
        } catch (Exception e) {
        }
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

                    contentResolveHelper.updateContact(contact, index);

                    Toast.makeText(getApplicationContext(), "Update Contact Successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Required Field are empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}