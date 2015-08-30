package com.example.siawns.moma_lab;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private static final int MENU_MORE_INFORMATION = Menu.FIRST;
    private static final String URL = "http://www.moma.org";
    private static final String CHOOSER_TEXT = "Inspired by artists showcased at the Museum of " +
            "Modern Art.\n         Visit MOMA's website?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        button1 = (Button) findViewById(R.id.teal);
        button2 = (Button) findViewById(R.id.neonGreen);
        button4 = (Button) findViewById(R.id.fuchsia);
        button5 = (Button) findViewById(R.id.orange);
        final Button[] buttonArray = {button1, button2, button3, button4, button5};


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub

                button1.setBackgroundColor(Color.argb(255, 0x00, 0x70, 0x71 + progress));
                button2.setBackgroundColor(Color.argb(255, 0x93, 0xfd, 0x18 + progress));
                button4.setBackgroundColor(Color.argb(255, 0xcf, 0x89, 0x92 + progress));
                button5.setBackgroundColor(Color.argb(255, 0xf2, 0x7d, 0x0c + progress));
            }




        });
    }



        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_MORE_INFORMATION, Menu.NONE, "More Information");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == MENU_MORE_INFORMATION) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(CHOOSER_TEXT);
            builder1.setCancelable(true);
            builder1.setPositiveButton("Visit Moma",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Uri website = Uri.parse(URL);
                            Intent baseIntent = new Intent(Intent.ACTION_VIEW, website);
                            startActivity(baseIntent);
                        }
                    });
            builder1.setNegativeButton("Not Now",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
