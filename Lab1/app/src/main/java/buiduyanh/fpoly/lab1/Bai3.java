package buiduyanh.fpoly.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bai3 extends AppCompatActivity {
    private EditText Edt1 , Edt2 ;
    private Button BtnShow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        Edt1 = findViewById(R.id.Edt1);
        Edt2 = findViewById(R.id.Edt2);
        BtnShow = findViewById(R.id.BtnShow);

        createNotificationChannel();

        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = Edt1.getText().toString();
                String content = Edt2.getText().toString();

                // Phương thức gọi để hiển thị thông báo
                showNotification(title, content);
            }
        });

    }

    private void showNotification(String title, String content) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_chat)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_ew NotificationChDEFAULT;
            NotificationChannel channel = nannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}