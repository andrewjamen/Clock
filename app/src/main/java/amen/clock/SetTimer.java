package amen.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SetTimer extends Activity implements OnClickListener {

    private countDown countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;

    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new countDown(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));

    }

    @Override
    public void onClick(View v)
    {
        if (!timerHasStarted)
        {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("Start");
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText("RESET");
        }
    }


    // CountDownTimer class
    public class countDown extends CountDownTimer
    {
        public countDown(long startTime, long interval)
        {
            super(startTime, interval);
        }
        @Override
        public void onFinish()
        {
            text.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " +
                    String.valueOf(startTime/1000 + " seconds"));
        }
        @Override
        public void onTick(long millisUntilFinished)
        {
            text.setText("Time remain:" + millisUntilFinished/1000 + " seconds");
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Time Elapsed: " +
                    String.valueOf(timeElapsed/1000 + " seconds"));
        }
    }
}


