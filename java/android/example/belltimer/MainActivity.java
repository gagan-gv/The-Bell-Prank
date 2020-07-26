package android.example.belltimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Boolean counter = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        button.setText("Start");
        countDownTimer.cancel();
        counter=false;
    }

    public void start(View view){
        if(counter){
            resetTimer();
        }
        else{
            counter = true;
            button =(Button) findViewById(R.id.button);
            seekBar.setEnabled(false);
            button.setText("Stop");
            countDownTimer = new CountDownTimer(seekBar.getProgress()*1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                updateTimer((int) (l/1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.videoplayback1);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft-(minutes*60);

        String second = Integer.toString(seconds);
        if(seconds <=9){
            second = "0" + second;
        }
        textView.setText(Integer.toString(minutes) + ":" + second);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);

        seekBar.setMax(600*6);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}