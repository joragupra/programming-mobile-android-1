package com.joragupra.modernart.modernart;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ModernArtActivity extends ActionBarActivity {

    public static final String TAG = "ModernArt";

    private List<View> rectangles;

    private Random randomGenerator = new Random();

    private int immutableRectangle = -1;

    private List<ModernArtColor> usedColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modern_art);

        initRectangles();
        colourRectangles();

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ModernArtActivity.this.changeRectanglesColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nothing to do here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing to do here
            }
        });

    }

    private void initRectangles() {
        rectangles = new ArrayList<>();

        int totalWidth = getWindowManager().getDefaultDisplay().getWidth();
        int totalHeight = getWindowManager().getDefaultDisplay().getHeight();

        initFirstRow(totalWidth, totalHeight);
        initSecondRow(totalWidth, totalHeight);
        initThirdRow(totalWidth, totalHeight);
    }

    private void initFirstRow(int totalWidth, int totalHeight) {
        rectangles.add(findViewById(R.id.rec1));
        rectangles.add(findViewById(R.id.rec2));
        rectangles.add(findViewById(R.id.rec3));

        rectangles.get(0).getLayoutParams().width = totalWidth / 4;
        rectangles.get(1).getLayoutParams().width = totalWidth / 4;
        rectangles.get(2).getLayoutParams().width = totalWidth / 4;

        rectangles.get(0).getLayoutParams().height = totalHeight / 5;
        rectangles.get(1).getLayoutParams().height = totalHeight / 5;
        rectangles.get(2).getLayoutParams().height = totalHeight / 5;
    }

    private void initSecondRow(int totalWidth, int totalHeight) {
        rectangles.add(findViewById(R.id.rec4));
        rectangles.add(findViewById(R.id.rec5));

        rectangles.get(3).getLayoutParams().width = totalWidth / 3;
        rectangles.get(4).getLayoutParams().width = totalWidth / 3;

        rectangles.get(3).getLayoutParams().height = totalHeight / 3;
        rectangles.get(4).getLayoutParams().height = totalHeight / 3;
    }

    private void initThirdRow(int totalWidth, int totalHeight) {
        rectangles.add(findViewById(R.id.rec6));
        rectangles.add(findViewById(R.id.rec7));
        rectangles.add(findViewById(R.id.rec8));
        rectangles.add(findViewById(R.id.rec9));

        rectangles.get(5).getLayoutParams().width = totalWidth / 5;
        rectangles.get(6).getLayoutParams().width = totalWidth / 5;
        rectangles.get(7).getLayoutParams().width = totalWidth / 5;
        rectangles.get(8).getLayoutParams().width = totalWidth / 5;

        rectangles.get(5).getLayoutParams().height = totalHeight / 6;
        rectangles.get(6).getLayoutParams().height = totalHeight / 6;
        rectangles.get(7).getLayoutParams().height = totalHeight / 6;
        rectangles.get(8).getLayoutParams().height = totalHeight / 6;
    }

    private void colourRectangles() {
        //select randomly which rectangle will not change its color
        this.immutableRectangle = randomGenerator.nextInt(rectangles.size());

        usedColors = new ArrayList<>();
        List<ModernArtColor> colors = new ArrayList<>(Arrays.asList(ModernArtColor.availableColors));

        for (int i = 0; i < this.rectangles.size(); i++) {
            if (i != immutableRectangle) {
                int color = randomGenerator.nextInt(colors.size());
                this.rectangles.get(i).setBackgroundColor(Color.parseColor(colors.get(color).getRgbCode()));
                usedColors.add(colors.get(color));
                colors.remove(color);
            } else {
                ModernArtColor immutableColor = i%2 == 0 ? ModernArtColor.WHITE:ModernArtColor.LIGHT_GRAY;
                this.rectangles.get(i).setBackgroundColor(Color.parseColor(immutableColor.getRgbCode()));
                usedColors.add(immutableColor);
            }
        }

    }

    private void changeRectanglesColor(int progress) {
        for (int i = 0; i < rectangles.size(); i++) {

            if (i != immutableRectangle) {
                View rectangle = rectangles.get(i);
                rectangle.setBackgroundColor(ModernArtColorInverter.invert(usedColors.get(i), progress));
                rectangle.invalidate();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modern_art, menu);
        return true;
    }

    public void showDialog(MenuItem item) {
        new MoreInformationDialog().show( getFragmentManager(), TAG );
    }

}
