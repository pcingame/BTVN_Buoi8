package com.example.btvn_buoi8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btvn_buoi8.database.StudentDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 111;
    private static final int REQUEST_CODE_UPDATE = 222;
    private ImageView imgAdd;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Student> mStudentList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



        /*getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        adapter = new StudentAdapter(new StudentAdapter.IClickItemStudent() {
            @Override
            public void updateStudent(Student student) {
                clickUpdateStudent(student);
            }

            @Override
            public void deleteStudent(Student student) {
                clickDeleteStudent(student);
            }

        });
        mStudentList = new ArrayList<>();
        adapter.setData(mStudentList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(adapter);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
        loadData();
    }

    private void clickUpdateStudent(Student student) {
        Intent intentUpdate = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_student", student);
        intentUpdate.putExtras(bundle);
        startActivityForResult(intentUpdate, REQUEST_CODE_UPDATE);
        /*loadData();*/
    }

    private void clickDeleteStudent(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StudentDatabase.getInstance(MainActivity.this).studentDAO().deleteStudent(student);
                        Toast.makeText(MainActivity.this, "Delete user successfully", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }).setNegativeButton("No", null).show();
    }


    private void loadData() {
        mStudentList = StudentDatabase.getInstance(this).studentDAO().getListStudent();
        adapter.setData(mStudentList);
    }

    private void addStudent() {

        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);

    }

    private void initView() {
        imgAdd = findViewById(R.id.img_add);
        recyclerView = findViewById(R.id.recycleview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        /*TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*Bundle bundle = data.getExtras();
        String strName = bundle.getString("name");
        String strAddress = bundle.getString("address");
        String strPhone = bundle.getString("phone");*/
        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == 200) {
                String strName = data.getStringExtra("name");
                String strAddress = data.getStringExtra("address");
                String strPhone = data.getStringExtra("phone");
                Student student = new Student(strName, strAddress, strPhone);
                StudentDatabase.getInstance(this).studentDAO().insertStudent(student);
                displayToast("Add Item Sucessfully");

            }
            if (resultCode == REQUEST_CODE_UPDATE) {
                if(resultCode == 300)

                    displayToast("Update successfully");
                /*Student student = new Student(strName, strAddress, strPhone);
                StudentDatabase.getInstance(this).studentDAO().updateStudent(student);*/
                /*loadData();*/
            }
        }
        loadData();

    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}