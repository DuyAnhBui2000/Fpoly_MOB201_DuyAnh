package buiduyanh.fpoly.buiduyanh_xml;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterXml extends BaseAdapter {

    private Context context;
    private List<Item> arr = new ArrayList<>();

    public AdapterXml(Context context) {
        this.context = context;
    }

    public void setData(List<Item> arritem) {
        this.arr = arritem;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (arr != null) {
            return arr.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyNewsHolder holder;
        Item item = arr.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item, null, false);
            holder = new MyNewsHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvNoidung = convertView.findViewById(R.id.tvNoidung);
            holder.tvDateTime = convertView.findViewById(R.id.tvDate);
            holder.imgAvt = convertView.findViewById(R.id.imgAvt);
            convertView.setTag(holder);
        } else {
            holder = (MyNewsHolder) convertView.getTag();
        }

        holder.tvTitle.setText(item.getTitle());
        holder.tvNoidung.setText(item.getDescription());
        holder.tvDateTime.setText(item.getPubDate());
        Picasso.get().load(item.getImgAvt()).into(holder.imgAvt);

        String link = item.getLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        holder.tvNoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainWeb.class);
                intent.putExtra(MainWeb.EXTRA_URL, link);
                context.startActivity(intent);
            }
        });
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(context, MainWeb.class);
                mintent.putExtra(MainWeb.EXTRA_URL, link);
                context.startActivity(mintent);
            }
        });

        return convertView;
    }

    public static class MyNewsHolder {
        private TextView tvTitle, tvNoidung, tvDateTime;
        private ImageView imgAvt;
    }
}
