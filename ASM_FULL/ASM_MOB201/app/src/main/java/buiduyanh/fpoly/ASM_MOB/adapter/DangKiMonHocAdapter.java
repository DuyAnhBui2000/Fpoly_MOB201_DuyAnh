package buiduyanh.fpoly.ASM_MOB.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ASM_MOB.R;
import buiduyanh.fpoly.ASM_MOB.RegisterActivity;
import buiduyanh.fpoly.ASM_MOB.model.MonHoc;
import buiduyanh.fpoly.ASM_MOB.model.ThongTin;
import buiduyanh.fpoly.ASM_MOB.service.RegisterCourseService;

import java.util.ArrayList;
import java.util.HashMap;

public class DangKiMonHocAdapter extends RecyclerView.Adapter<DangKiMonHocAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MonHoc> list;
    private int id;
    private boolean isAll;

    public DangKiMonHocAdapter(Context context, ArrayList<MonHoc> list, int id, boolean isAll) {
        this.context = context;
        this.list = list;
        this.id = id;
        this.isAll = isAll;
    }

    // Tạo ViewHolder cho các item trong RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        // Gắn layout cho item là R.layout.item_registercourse
        View view = inflater.inflate(R.layout.item_registercourse, parent, false);
        return new ViewHolder(view);
    }

    // Gắn dữ liệu vào ViewHolder của từng item trong RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy thông tin môn học từ danh sách
        MonHoc monHoc = list.get(position);

        // Gán thông tin vào các thành phần trong ViewHolder
        holder.txtCode.setText(String.valueOf(monHoc.getCode()));
        holder.txtName.setText(monHoc.getName());
        holder.txtTeacher.setText(monHoc.getTeacher());

        // Kiểm tra trạng thái đăng kí môn học để hiển thị nút đăng kí tương ứng
        if (monHoc.getIsRegister() == id) {
            holder.btnRegisterItem.setText("Hủy đăng kí");
            holder.btnRegisterItem.setBackgroundColor(Color.RED);
            holder.btnRegisterItem.setTextColor(Color.WHITE);
        } else {
            holder.btnRegisterItem.setText("Đăng kí");
            holder.btnRegisterItem.setBackgroundColor(Color.GREEN);
            holder.btnRegisterItem.setTextColor(Color.WHITE);
        }

        // Xử lý sự kiện khi nhấn vào nút đăng kí
        holder.btnRegisterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để gọi service đăng kí môn học
                Intent intent = new Intent(context, RegisterCourseService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("code", monHoc.getCode());
                bundle.putInt("isRegister", monHoc.getIsRegister());
                bundle.putBoolean("isAll", isAll);
                intent.putExtras(bundle);

                // Bắt đầu service đăng kí môn học
                context.startService(intent);
            }
        });

        // Xử lý sự kiện khi nhấn vào hình ảnh môn học
        holder.imvCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị dialog chứa thông tin chi tiết về môn học
                showDialog(monHoc.getListTT());
            }
        });
    }

    // Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return list.size();
    }

    // Lớp ViewHolder để giữ các thành phần UI của item trong RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCode, txtName, txtTeacher;
        CardView cvCourse;
        Button btnRegisterItem;
        ImageView imvCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            btnRegisterItem = itemView.findViewById(R.id.btnRegisterItem);
            cvCourse = itemView.findViewById(R.id.cvCourse);
            imvCourse = itemView.findViewById(R.id.imvCourse);
        }
    }

    // Hiển thị dialog chứa thông tin chi tiết về môn học
    private void showDialog(ArrayList<ThongTin> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thongtin, null);
        builder.setView(view);

        ListView listViewTT = view.findViewById(R.id.listViewTT);

        ArrayList<HashMap<String, Object>> listTT = new ArrayList<>();
        for (ThongTin tt : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("date", "Ngày học: " + tt.getDate());
            hs.put("address", "Địa điểm: " + tt.getAddress());
            listTT.add(hs);
        }

        // Sử dụng SimpleAdapter để hiển thị danh sách thông tin chi tiết
        SimpleAdapter adapter = new SimpleAdapter(context, listTT, android.R.layout.simple_list_item_2,
                new String[]{"date", "address"}, new int[]{android.R.id.text1, android.R.id.text2});
        listViewTT.setAdapter(adapter);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
