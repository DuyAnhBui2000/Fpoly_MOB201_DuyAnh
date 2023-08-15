package buiduyanh.fpoly.cham_them;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
        private Context context;
        private List<Contact> contacts;

        public ContactAdapter(Context context, List<Contact> contacts) {
                this.context = context;
                this.contacts = contacts;
        }

        @Override
        public int getCount() {
                return contacts.size();
        }

        @Override
        public Object getItem(int position) {
                return contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
                return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder;

                if (convertView == null) {
                        convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
                        viewHolder = new ViewHolder();
                        viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
                        viewHolder.textViewPhone = convertView.findViewById(R.id.textViewPhone);
                        viewHolder.buttonCall = convertView.findViewById(R.id.buttonCall);
                        viewHolder.buttonVideoCall = convertView.findViewById(R.id.buttonVideoCall);
                        convertView.setTag(viewHolder);
                } else {
                        viewHolder = (ViewHolder) convertView.getTag();
                }

                final Contact contact = contacts.get(position);

                viewHolder.textViewName.setText(contact.getName());
                viewHolder.textViewPhone.setText(contact.getPhoneNumber());

                viewHolder.buttonCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                String phoneNumber = contact.getPhoneNumber();
                                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                                        Intent intent = new Intent(Intent.ACTION_CALL);
                                        intent.setData(Uri.parse("tel:" + phoneNumber));
                                        context.startActivity(intent);
                                } else {
                                        Toast.makeText(context, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                viewHolder.buttonVideoCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                String phoneNumber = contact.getPhoneNumber();
                                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("tel:" + phoneNumber));
                                        intent.putExtra("videocall", true);
                                        context.startActivity(intent);
                                } else {
                                        Toast.makeText(context, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                return convertView;
        }

        private static class ViewHolder {
                TextView textViewName;
                TextView textViewPhone;
                ImageButton buttonCall;
                ImageButton buttonVideoCall;
        }
}
