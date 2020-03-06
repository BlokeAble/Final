package com.example.magicball;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.magicball.controller.Global;
import com.example.magicball.model.Base;
import com.example.magicball.model.Custom;
import com.example.magicball.model.Dice;
import com.example.magicball.model.MagicObj;
import com.example.magicball.ui.slideshow.Pictures;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int index = 0;
    ImageView ivChoices;
    Pictures pictures = new Pictures();

    Dice dice = new Dice();
    MagicObj magicEightBall = new Base();
    Custom custom = new Custom();
    TextView magicAnswer;
    TextView reply, input;
    ScrollView list;
    Button cusButton;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.goodReplies.clear();
        Global.neutralReplies.clear();
        Global.badReplies.clear();
        pictures.add(R.drawable.cat);
        pictures.add(R.drawable.packettracer);
        pictures.add(R.drawable.panda);
        pictures.add(R.drawable.magic8ballthingrevised);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        magicAnswer = findViewById(R.id.magicAnswer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void magicSubmit(View view) {
        magicAnswer = findViewById(R.id.magicAnswer);
        magicAnswer.setText(magicEightBall.createRandom(dice.roll20()));
    }

/*
   public void customSubmit(View view)
   {
        //customAnswer= findViewById(R.id.customAnswer);
        //customAnswer.setText(custom.createRandom(dice.roll20()));//broke
   }
*/
    //custom

    @SuppressLint("SetTextI18n")
    public void customSet(View view)
    {

        input= findViewById(R.id.customInput);
        reply= findViewById(R.id.customReplies);
        list= findViewById(R.id.customScrollView);
        cusButton= findViewById(R.id.customEnter);
        cusButton.setEnabled(true);
        String str=input.toString();

        if(count<=10)
        {
            custom.fillGood(str);
            reply.setText(count +", Good");
            input.setText("");
            count++;
        }
        else if(count<=15)
        {
            custom.fillNeutral(str);
            reply.setText(count +", Neutral");
            input.setText("");
            count++;
        }
        else if(count<=20)
        {
            custom.fillBad(str);
            reply.setText(count +", Bad");
            input.setText("");
            count++;
        }
        else{
                input.setText("Your Done");
              //show inputs inputs in list
              cusButton.setEnabled(false);
              count=1;
        }
    }

    public void clickToGoBack(View view) {
        ivChoices = findViewById(R.id.ivChoices);
        if(index > 0){
            --index;
        }
        ivChoices.setImageResource(pictures.get(index));
        Global.image = pictures.get(index);
    }

    public void clickToGoNext(View view) {
        ivChoices = findViewById(R.id.ivChoices);
        if(index < pictures.size() - 1){
            ++index;
        }

        ivChoices.setImageResource(pictures.get(index));
        Global.image = pictures.get(index);
    }

    public void getFortuneForCustom(View view) {



    }
}