package buiduyanh.fpoly.danh_ba_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private ContactAdapter contactAdapter;

    private static final int REQUEST_CODE_ADD_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView contactRecyclerView = findViewById(R.id.contactRecyclerView);
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactRecyclerView.setAdapter(contactAdapter);

        findViewById(R.id.addContactButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");

            Contact contact = new Contact(name, phone);
            contactList.add(contact);
            contactAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã lưu liên hệ mới", Toast.LENGTH_SHORT).show();
        }
    }
}