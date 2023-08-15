package buiduyanh.fpoly.ot_app_nhac;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageView rotatingImage;
    private ProgressBar timeProgressBar;
    private TextView timeTextView;
    private ImageButton backButton;
    private ImageButton playButton;
    private ImageButton nextButton;

    private List<String> songList;
    private int currentSongIndex;
    private MediaPlayer mediaPlayer;
    private ObjectAnimator progressAnimator;

    private boolean isPlaying = false;
    private boolean isSeekBarDragging = false;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotatingImage = findViewById(R.id.rotatingImage);
        timeProgressBar = findViewById(R.id.timeProgressBar);
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
        progressAnimator = ObjectAnimator.ofInt(timeProgressBar, "progress", 0, 100);
        progressAnimator.setDuration(mediaPlayer.getDuration());

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

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                playButton.setImageResource(R.drawable.ic_pause);
                rotatingImage.setRotation(0);
                rotateImage();
                updateTime();
                progressAnimator.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                playButton.setImageResource(R.drawable.ic_play);
                rotatingImage.clearAnimation();
                currentSongIndex++;
                if (currentSongIndex >= songList.size()) {
                    currentSongIndex = 0;
                }
                changeSong(currentSongIndex);
            }
        });
    }

    private void playMusic() {
        isPlaying = true;
        playButton.setImageResource(R.drawable.ic_pause);
        rotateImage();
        mediaPlayer.start();
        progressAnimator.resume();
    }

    private void pauseMusic() {
        isPlaying = false;
        playButton.setImageResource(R.drawable.ic_play);
        rotatingImage.clearAnimation();
        mediaPlayer.pause();
        progressAnimator.pause();
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
        mediaPlayer = MediaPlayer.create(this, getSongResource(index));
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                playButton.setImageResource(R.drawable.ic_pause);
                rotatingImage.setRotation(0);
                rotateImage();
                updateTime();
                progressAnimator.setDuration(mediaPlayer.getDuration());
                progressAnimator.start();
            }
        });
    }

    private void rotateImage() {
        rotatingImage.animate().rotationBy(360).setDuration(3000).setInterpolator(new LinearInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                if (isPlaying) {
                    rotateImage();
                }
            }
        }).start();
    }

    private void updateTime() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isSeekBarDragging && mediaPlayer.isPlaying()) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int totalDuration = mediaPlayer.getDuration();
                    timeProgressBar.setProgress((int) (((float) currentPosition / totalDuration) * 100));
                    timeTextView.setText(getTimeString(currentPosition) + "/" + getTimeString(totalDuration));
                }
                updateTime();
            }
        }, 1000);
    }

    private String getTimeString(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    private int getSongResource(int index) {
        switch (index) {
            case 0:
                return R.raw.music_b1;
            case 1:
                return R.raw.music_b2;
            case 2:
                return R.raw.mucsic_b3;
            default:
                return R.raw.music_b1;
        }
    }
}