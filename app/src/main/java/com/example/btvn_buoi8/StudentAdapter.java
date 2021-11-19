package com.example.btvn_buoi8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private IClickItemStudent iClickItemStudent;


    public StudentAdapter(IClickItemStudent iClickItemStudent) {
        this.iClickItemStudent = iClickItemStudent;
    }

    public interface IClickItemStudent {
        void updateStudent(Student student);
        void deleteStudent(Student student);
    }

    public void setData(List<Student> list){
        this.studentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final Student student = studentList.get(position);
        if(student == null){
            return;
        }

        holder.tvName.setText("Tên: "+student.getName());
        holder.tvAddress.setText("Địa chỉ: "+student.getAddress());
        holder.tvPhone.setText("Phone: "+student.getPhone());

        /*holder.btnUpdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.updateUser(user);
            }
        });*/

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemStudent.deleteStudent(student);
            }
        });

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemStudent.updateStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(studentList != null){
            return studentList.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layoutItem;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhone;
        private ImageView imgDelete;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            tvName= itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
}
