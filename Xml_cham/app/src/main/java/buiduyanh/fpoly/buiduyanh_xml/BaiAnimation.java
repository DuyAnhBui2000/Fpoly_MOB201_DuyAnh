package buiduyanh.fpoly.buiduyanh_xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Calendar;

public class BaiAnimation extends AppCompatActivity {
    private ImageView hourHandImageView, minuteHandImageView, secondHandImageView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_animation);

        hourHandImageView = findViewById(R.id.hourHandImageView);
        minuteHandImageView = findViewById(R.id.minuteHandImageView);
        secondHandImageView = findViewById(R.id.secondHandImageView);

        startClockAnimation();
    }

    private void startClockAnimation() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                float hourRotation = 30 * hour + 0.5f * minute; // 360/12 = 30 degrees per hour
                float minuteRotation = 6 * minute; // 360/60 = 6 degrees per minute
                float secondRotation = 6 * second; // 360/60 = 6 degrees per second

                hourHandImageView.setRotation(hourRotation);
                minuteHandImageView.setRotation(minuteRotation);
                secondHandImageView.setRotation(secondRotation);

                handler.postDelayed(this, 1000);
            }
        });
    }
}