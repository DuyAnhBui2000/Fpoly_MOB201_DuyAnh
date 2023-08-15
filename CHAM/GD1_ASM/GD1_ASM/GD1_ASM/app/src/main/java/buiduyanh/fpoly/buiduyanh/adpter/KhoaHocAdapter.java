package buiduyanh.fpoly.buiduyanh.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import buiduyanh.fpoly.buiduyanh.MyDatabase.DBHelper;
import buiduyanh.fpoly.buiduyanh.R;
import buiduyanh.fpoly.buiduyanh.Service.RegisterAndUnRegisterCourseServices;
import buiduyanh.fpoly.buiduyanh.model.KhoaHoc;

public class KhoaHocAdapter extends  RecyclerView.Adapter<KhoaHocAdapter.MyItemViewHolder>{

    ArrayList<KhoaHoc> listCourse;
    Context context;
    DBHelper courseManagementSql;


    public KhoaHocAdapter(ArrayList<KhoaHoc> listCourse, Context context, DBHelper courseManagementSql) {
        this.listCourse = listCourse;
        this.context = context;
        this.courseManagementSql = courseManagementSql;
    }

    public void setFilter(ArrayList<KhoaHoc> listCourse){
        this.listCourse = listCourse;
        notifyDataSetChanged();
    }

    //phương thức onCreateViewHolder, adapter sử dụng LayoutInflater để inflate layout cho từng item trong RecyclerView
    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoa_hoc, parent, false);
        return new MyItemViewHolder(view);
    }

    //phương thức onBindViewHolder, adapter sẽ gán các giá trị của đối tượng KhoaHoc tại vị trí position
    // vào các thành phần giao diện tương ứng của item, bao gồm: ImageView imgCourse để hiển thị hình ảnh khóa học,
    // TextView tvTitleCourse để hiển thị tên khóa học và TextView tvSchedule để hiển thị lịch học của khóa học đó.
    //Nếu khóa học đã được đăng ký, adapter sẽ hiển thị nút "Hủy đăng ký" và background của nút sẽ có màu khác so
    // với khi chưa đăng ký. Nếu chưa đăng ký, adapter sẽ hiển thị nút "Đăng ký".
    //Khi người dùng click vào nút đăng ký/hủy đăng ký, adapter sẽ gửi một Intent tới một Service để thực hiện đăng ký/hủy đăng ký khóa học cho sinh viên.
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {

        final int index = position;

        holder.tvTitleCourse.setText(listCourse.get(index).getCourseName());
        holder.imgCourse.setImageResource(listCourse.get(index).getIdCoursePhoto());
        holder.tvSchedule.setText(listCourse.get(index).getSchedule());

        if(listCourse.get(index).isCheckRegistration()){
            holder.textBtn.setText("Hủy đăng kí");
            holder.imgButton.setImageResource(R.drawable.ic_check_course);
            holder.bgButton.setBackground(context.getDrawable(R.drawable.custom_bg_button_item_course_2));
        }else  {
            holder.textBtn.setText("Đăng kí");
            holder.imgButton.setImageResource(R.drawable.ic_add_course);
            holder.bgButton.setBackground(context.getDrawable(R.drawable.custom_bg_button_item_course));
        }

        holder.bgButton.setOnClickListener(btn ->{
            Intent intent = new Intent(context, RegisterAndUnRegisterCourseServices.class);
            intent.putExtra("idStudent", "ps00709");
            intent.putExtra("idCourse", listCourse.get(index).getId());
            intent.putExtra("isRegister", !(listCourse.get(index).isCheckRegistration()));

            intent.setAction("RegisterAndUnRegisterCourseServices");
            context.startService(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCourse,imgButton;
        TextView tvTitleCourse, tvSchedule;
        TextView textBtn;
        CardView cavBtn;
        ConstraintLayout bgButton;
        public MyItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCourse = itemView.findViewById(R.id.imgCourse);
            tvTitleCourse = itemView.findViewById(R.id.tvTitleCourse);
            tvSchedule = itemView.findViewById(R.id.tvSchedule);
            textBtn = itemView.findViewById(R.id.tvBtn);
            imgButton = itemView.findViewById(R.id.imgAdd);
            bgButton = itemView.findViewById(R.id.bgButton);
            cavBtn = itemView.findViewById(R.id.cavBtn);
        }
    }
}
