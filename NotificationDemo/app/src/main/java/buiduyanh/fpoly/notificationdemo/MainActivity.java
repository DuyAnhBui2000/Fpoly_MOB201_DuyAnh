package buiduyanh.fpoly.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button BtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnShow = findViewById(R.id.BtnButton);

        BtnShow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("data","Bùi Duy Anh");
                intent.putExtras(bundle);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(
                        MainActivity.this,MyApplication.CHANEL_ID);
                noBuilder.setContentTitle("Tieu de");
                noBuilder.setContentText("Noi dung");
                noBuilder.setContentIntent(pendingIntent);
                noBuilder.setSound(null); // tat chuong
                noBuilder.setSmallIcon(R.drawable.ic_chat);
                NotificationManagerCompat compat = NotificationManagerCompat.from(MainActivity.this);
                compat.notify(1,noBuilder.build());
            }
        });
    }
}