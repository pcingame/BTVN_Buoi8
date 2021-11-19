package com.example.btvn_buoi8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btvn_buoi8.database.StudentDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    //private static final int REQUEST_CODE = 111;
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhone;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

//        adapter.setData(mStudentList);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewStudent();
            }
        });
    }

    private void saveNewStudent() {
        String strName =edtName.getText().toString().trim();
        String strAddress =edtAddress.getText().toString().trim();
        String strPhone =edtPhone.getText().toString().trim();

        if(TextUtils.isEmpty(strName) || TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strPhone)){
            displayToast("Please insert a information");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("name", strName);
        intent.putExtra("address", strAddress);
        intent.putExtra("phone", strPhone);
        setResult(200, intent);
        Toast.makeText(this, "Add successfully", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void initView() {
        edtName = findViewById(R.id.edt_input_name);
        edtAddress = findViewById(R.id.edt_input_address);
        edtPhone = findViewById(R.id.edt_input_phone);
        btnAdd = findViewById(R.id.btn_save_add);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}