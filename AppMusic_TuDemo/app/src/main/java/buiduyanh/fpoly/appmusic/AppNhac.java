package buiduyanh.fpoly.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AppNhac extends AppCompatActivity {

    private ImageButton playButton;
    private ImageButton backButton;
    private ImageButton nextButton;
    private TextView timeTextView;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private int currentSongIndex = 0;
    private int[] songs = {R.raw.music_b1, R.raw.music_b2, R.raw.mucsic_b3};
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_nhac);

        playButton = findViewById(R.id.playButton);
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        timeTextView = findViewById(R.id.timeTextView);
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pauseSong();
                } else {
                    playSong();
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

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pauseSong();
            }
        });
    }

    private void playSong() {
        mediaPlayer.start();
        isPlaying = true;
        playButton.setImageResource(R.drawable.ic_pause); // Thay thế R.drawable.ic_pause bằng tài nguyên hình ảnh pause của bạn
        updateSeekBar();
    }

    private void pauseSong() {
        mediaPlayer.pause();
        isPlaying = false;
        playButton.setImageResource(R.drawable.ic_play); // Thay thế R.drawable.ic_play bằng tài nguyên hình ảnh play của bạn
    }

    private void playPreviousSong() {
        currentSongIndex = (currentSongIndex - 1 + songs.length) % songs.length;
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);
        if (isPlaying) {
            playSong();
        }
    }

    private void playNextSong() {
        currentSongIndex = (currentSongIndex + 1) % songs.length;
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);
        if (isPlaying) {
            playSong();
        }
    }

    private void updateSeekBar() {
        timeTextView.setText(formatTime(mediaPlayer.getCurrentPosition()));
        mRunnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };
        mHandler.postDelayed(mRunnable, 1000);
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mHandler.removeCallbacks(mRunnable);
    }
}