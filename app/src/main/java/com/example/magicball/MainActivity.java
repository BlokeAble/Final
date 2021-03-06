package com.example.magicball;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.magicball.controller.Global;
import com.example.magicball.model.Base;
import com.example.magicball.model.Custom;
import com.example.magicball.model.Dice;
import com.example.magicball.model.MagicObj;
import com.example.magicball.model.customsettings.Pictures;

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

    //set custom
    TextView reply, input;
    Button cusButton;
    int count=1;
    public static String output="";
    final int good=11;
    final int neutral=17;
    final int bad=23;
    final int plist=5;

    //Scroll List
    TextView list;

    //custom
    TextView cusAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    //custom
    @SuppressLint("SetTextI18n")
    public void customSet(View view)
    {
        input= findViewById(R.id.customInput);
        reply= findViewById(R.id.customReplies);
        list= findViewById(R.id.customList);
        cusButton= findViewById(R.id.customEnter);

        cusButton.setEnabled(true);
        input.setEnabled(true);
        String str;
        str=input.getText().toString();

        //Ongoing String

        //Goes to Netural without saying
        if(count<good)
        {
            custom.fillGood(str);
            reply.setText((good-1) - count + ", Good");
            input.setText("");
            output= output+"Good: "+str+"\n";
            count++;
            if(count==good) { //add a final
                reply.setText(plist+ ", Neutral");
                count++;
            }
        }
        else if(count<neutral)
        {
            custom.fillNeutral(str);
            reply.setText((neutral-1)-count+", Neutral");
            input.setText("");
            output= output+"Neutral: "+str+"\n";
            count++;
            if(count==neutral) { //add a final
                reply.setText(plist+ ", Bad");
                count++;
            }
        }
        else if(count<bad)
        {
            custom.fillBad(str);
            reply.setText((bad-1)-count+", Bad");
            input.setText("");
            output+="Bad: "+str+"\n";
            count++;
        }
        else{
              reply.setText("Your Done");
              //show inputs inputs in list
              list.setText(output);
              input.setText("");
              input.setEnabled(false);
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

        cusAnswer= findViewById(R.id.customAnswer);
        cusAnswer.setText(custom.createRandom(dice.roll20()));

    }
}