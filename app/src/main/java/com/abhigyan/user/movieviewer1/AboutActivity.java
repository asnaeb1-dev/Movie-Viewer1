package com.abhigyan.user.movieviewer1;
//DESIGNED BY ABHIGYAN RAHA

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

   String str ="Hi! Presenting the Movie Viewer application Guide.\n" +
            "\n"+
            "Movie Viewer gives the user information about any movie.\n"
            +"The user can search for desired movies or get information about the latest, popular, top-rated movies. \n"
            +"\n"
            +" FEATURES: \n"
            +"\n"
            +"a) Get information about the latest, popular, top-rated and upcoming movies. \n"
            +"\n"
            +"b) Search for any movie and get detailed description and detailed information about the movie.\n"
            +"\n"
            +"c) Save your favourite movies online and view the data whenever desired.\n"
            +"\n"
            +"d) Get multiple pictures/poster/images of the movies. \n"
            +"\n"
            +"e) Watch videos/trailers of movies.\n"
            +"\n"
            +"f) Read reviews of the movies.\n"
            +"\n"
            +"g) Get movie recommendations and decide what else to watch of the same genre.\n"
            +"\n"
            +"h) Login/register into your account and make your experience more personalized. Also, no fear of using data.\n\n"
            +"       *******DATA OBTAINED FROM:*******" +"\n\n"
            +"       This application is developed using TMDb API, but is neither endorsed nor certified by TMDb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = findViewById(R.id.textView9);
        textView.setText(str);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
}
