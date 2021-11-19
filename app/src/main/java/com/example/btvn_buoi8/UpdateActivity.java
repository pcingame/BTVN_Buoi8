package com.example.btvn_buoi8;

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

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhone;
    private Button btnUpdate;

    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();

        student = (Student) getIntent().getExtras().get("object_student");
        if(student != null){
            edtName.setText(student.getName());
            edtAddress.setText(student.getAddress());
            edtPhone.setText(student.getPhone());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateStudent();
            }
        });
    }

    private void UpdateStudent() {
        String strName =edtName.getText().toString().trim();
        String strAddress =edtAddress.getText().toString().trim();
        String strPhone =edtPhone.getText().toString().trim();
        if(TextUtils.isEmpty(strName) || TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strPhone)){
            return;
        }

        student.setName(strName);
        student.setAddress(strAddress);
        student.setPhone(strPhone);

        StudentDatabase.getInstance(this).studentDAO().updateStudent(student);

        Intent intentResult = new Intent();
        setResult(300, intentResult);

        finish();

    }

    private void initView() {
        edtName = findViewById(R.id.edt_update_name);
        edtAddress = findViewById(R.id.edt_update_address);
        edtPhone = findViewById(R.id.edt_update_phone);
        btnUpdate = findViewById(R.id.btn_save_update);
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}