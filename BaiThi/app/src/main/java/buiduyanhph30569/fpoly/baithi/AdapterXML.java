package buiduyanhph30569.fpoly.baithi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterXML extends BaseAdapter {
    private DB_XML dbXml;

    private Context context;
    private List<Item> arr = new ArrayList<>();

    public AdapterXML(Context context) {
        this.context = context;
        dbXml = new DB_XML(context);
    }

    public void setData(List<Item> arritem){
        this.arr = arritem;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (arr != null){
            return arr.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyNewsHolder holder;
        Item item = arr.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = inflater.inflate(R.layout.layout_item,null,false);
            holder = new MyNewsHolder();
            holder.tvTitle = view.findViewById(R.id.tvTitle);
            holder.tvNoidung = view.findViewById(R.id.tvNoidung);
            holder.tvDateTime = view.findViewById(R.id.tvDateTime);
            holder.imgAvt = view.findViewById(R.id.imgAvt);
            holder.imglike = view.findViewById(R.id.imglike);
            view.setTag(holder);
        }else {
            holder = (MyNewsHolder) view.getTag();
        }
        holder.tvTitle.setText(item.getTitle());
        holder.tvNoidung.setText(item.getDescription());
        holder.tvDateTime.setText(item.getPubDate());
        Picasso.get().load(item.getImgAvt()).into(holder.imgAvt);
        holder.imglike.setImageResource(R.drawable.heart_icon);


        if (item.getTrangThai() == 0) {
            holder.imglike.setImageResource(R.drawable.heart_icon);
        } else {
            holder.imglike.setImageResource(R.drawable.heart_icon1);
        }

        holder.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trangthai = item.getTrangThai();

                if (trangthai == 0) {
                    trangthai = 1;
                } else {
                    trangthai = 0;
                }

                item.setTrangThai(trangthai);
                dbXml.updateItemStatus(item.getIdXML(), trangthai);


                if (trangthai == 0) {
                    holder.imglike.setImageResource(R.drawable.heart_icon);
                } else {
                    holder.imglike.setImageResource(R.drawable.heart_icon1);
                }
                arr = dbXml.getAllDB();
                notifyDataSetChanged();

                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){
                }else {
                    Collections.sort(arr, new Comparator<Item>() {
                        @Override
                        public int compare(Item item1, Item item2) {
                            return Integer.compare(item2.getTrangThai(), item1.getTrangThai());
                        }
                    });
                }


            }
        });





        String link = item.getLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        holder.tvNoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(link);
            }
        });
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent.setData(Uri.parse(link));
//                context.startActivity(intent);
                connect(link);
            }
        });
        return view;
    }

    public static class MyNewsHolder{
        private TextView tvTitle, tvNoidung, tvDateTime;
        private ImageView imgAvt, imglike;
    }
    public void connect(String link){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Có kết nối mạng, chuyển đến trình duyệt hoặc màn hình khác
            Intent intent = new Intent(context, MainActivity2_WebV.class);
            intent.putExtra(MainActivity2_WebV.EXTRA_URL, link);
            context.startActivity(intent);
        } else {
            // Không có kết nối mạng, chuyển đến màn hình cài đặt
            Toast.makeText(context, "Hãy kết nối mạng!", Toast.LENGTH_SHORT).show();
            Intent settingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            context.startActivity(settingsIntent);

        }
    }
}
