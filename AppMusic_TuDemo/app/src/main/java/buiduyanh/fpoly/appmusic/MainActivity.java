package buiduyanh.fpoly.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView rotatingImage;
    private TextView timeTextView;
    private ImageButton backButton;
    private ImageButton playButton;
    private ImageButton nextButton;

    private List<String> songList;
    private int currentSongIndex;
    private MediaPlayer mediaPlayer;
    private RotateAnimation rotateAnimation;

    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotatingImage = findViewById(R.id.rotatingImage);
        timeTextView = findViewById(R.id.timeTextView);
        backButton = findViewById(R.id.backButton);
        playButton = findViewById(R.id.playButton);
        nextButton = findViewById(R.id.nextButton);

        // Khởi tạo danh sách bài hát
        songList = new ArrayList<>();
        songList.add("Bài hát 1");
        songList.add("Bài hát 2");
        songList.add("Bài hát 3");

        // Khởi tạo MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.music_b1);
        mediaPlayer = MediaPlayer.create(this, R.raw.music_b2);
        mediaPlayer = MediaPlayer.create(this, R.raw.mucsic_b3);

        // Khởi tạo animation
        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPreviousSong();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNextSong();
            }
        });
    }
    private void playMusic() {
        isPlaying = true;
        playButton.setImageResource(R.drawable.ic_pause);
        rotatingImage.startAnimation(rotateAnimation);
        mediaPlayer.start();
        timeTextView.postDelayed(updateTimeRunnable, 0);
    }

    private void pauseMusic() {
        isPlaying = false;
        playButton.setImageResource(R.drawable.ic_play);
        rotatingImage.clearAnimation();
        mediaPlayer.pause();
    }

    private void playNextSong() {
        currentSongIndex++;
        if (currentSongIndex >= songList.size()) {
            currentSongIndex = 0;
        }
        changeSong(currentSongIndex);
    }

    private void playPreviousSong() {
        currentSongIndex--;
        if (currentSongIndex < 0) {
            currentSongIndex = songList.size() - 1;
        }
        changeSong(currentSongIndex);
    }
    private void changeSong(int index) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        switch (index) {
            case 0:
                mediaPlayer = MediaPlayer.create(this, R.raw.music_b1);
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.music_b2);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.mucsic_b3);
                break;
            // Thêm các trường hợp khác tùy theo số lượng bài hát

        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                playButton.setImageResource(R.drawable.ic_pause);
                rotatingImage.startAnimation(rotateAnimation);
                isPlaying = true;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Xử lý khi bài hát kết thúc
                isPlaying = false;
                playButton.setImageResource(R.drawable.ic_play);
                rotatingImage.clearAnimation();
                // Cập nhật thông tin bài hát
                currentSongIndex++;
                if (currentSongIndex >= songList.size()) {
                    currentSongIndex = 0;
                }
                timeTextView.setText(songList.get(currentSongIndex));
                // Phát bài hát tiếp theo
                changeSong(currentSongIndex);
            }
        });
    }

    // Định nghĩa Runnable để cập nhật thời gian
    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int minutes = currentPosition / 1000 / 60;
                int seconds = currentPosition / 1000 % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timeTextView.setText(time);
            }
            // Lặp lại việc cập nhật thời gian sau 1 giây
            timeTextView.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

