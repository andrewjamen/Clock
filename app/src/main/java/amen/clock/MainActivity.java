package amen.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

//      https://www.youtube.com/playlist?list=PL4uut9QecF3DLAacEoTctzeqTyvgzqYwA
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button setAlarms = (Button) findViewById(R.id.setAlarms);

        Button setTimer = (Button) findViewById(R.id.setTimer);

        setAlarms.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){

            }


        });

        setTimer.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){

            }


        });
    }
}
