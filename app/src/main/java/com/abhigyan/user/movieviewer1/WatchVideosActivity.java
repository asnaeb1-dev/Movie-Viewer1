package com.abhigyan.user.movieviewer1;
//DESIGNED BY ABHIGYAN RAHA

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatchVideosActivity extends YouTubeBaseActivity {

    private static String BASE_URL = "https://api.themoviedb.org";
    private static String APIKEY = "17d99bf38e7ffbebabfc4d9713b679a8";
    private static String YOUTUBE_API_KEY = "AIzaSyBTLY6BjIP4Onjv6UBA57e-btxwAS4ejvk";

    ArrayList<String> youtubevideokeys = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();

    ListView trailerList;

    YouTubePlayerView youTubePlayerView;
    ArrayAdapter arrayAdapter;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_videos);

        linearLayout = findViewById(R.id.linearLayout);
        youTubePlayerView = findViewById(R.id.youtubeVideos);
        trailerList = findViewById(R.id.trailerList);
        Intent getIntent = getIntent();
        int movieID = getIntent.getIntExtra("id", 0);
        Log.i("id**********", String.valueOf(movieID));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YoutubeVideoInterfaceAPI getvid = retrofit.create(YoutubeVideoInterfaceAPI.class);

        Call<Videos> call = getvid.getVideos(movieID, APIKEY);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                List<Videos.Result> vid = videos.getResults();
                for (int i = 0; i < vid.size(); i++) {
                    //obtain youtube video keys and store in an arraylist
                    Videos.Result getitem = vid.get(i);
                    String videokey = getitem.getKey();
                    youtubevideokeys.add(videokey);
                    String name = getitem.getName();
                    nameList.add(name);
                }
                 arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,nameList);
                 trailerList.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("abhigyans error", t.getMessage());
            }
        });


        trailerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                youTubePlayerView.initialize(YOUTUBE_API_KEY,
                        new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {

                                // do any work here to cue video, play video, etc.
                                youTubePlayer.loadVideo( youtubevideokeys.get(position));
                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
}


       /* for(int i=0;i<youtubevideokeys.size();i++)
        {
            final int position = i;
            YouTubePlayerView youTubePlayerView = new YouTubePlayerView(getApplicationContext());
            youTubePlayerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            youTubePlayerView.initialize(YOUTUBE_API_KEY,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(youtubevideokeys.get(position));
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                        }
                    });
            youtubeLinearLayout.addView(youTubePlayerView);
        }
    }
}


       // YouTubePlayerView youTubePlayerView = new YouTubePlayerView(getApplicationContext());
        //youtubeLinearLayout.addView(youTubePlayerView);
        /*youTubePlayerView.initialize(YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("0Pi5dbdryHY");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });



        //youtubeVidoesgetter(movieID);
    });*/


  //   public void youtubeVidoesgetter(int movieiD)
    //{
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YoutubeVideoInterfaceAPI getvid = retrofit.create(YoutubeVideoInterfaceAPI.class);

        Call<Videos> call = getvid.getVideos(movieiD,APIKEY);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response)
            {
                Videos videos = response.body();
                List<Videos.Result> vid = videos.getResults();
                for(int i=0;i<vid.size();i++)
               {
                   //obtain youtube video keys and store in an arraylist
                   Videos.Result getitem = vid.get(i);
                   String videokey = getitem.getKey();
                   youtubevideokeys.add(videokey);
               }
            }
            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("abhigyans error", t.getMessage());
            }
        });*/

       /* for(int i=0;i<youtubevideokeys.size();i++)
        {
            final int pos = i;
            YouTubePlayerView youTubePlayerView = new YouTubePlayerView(getApplicationContext());
            youtubeLinearLayout.addView(youTubePlayerView);
            youTubePlayerView.initialize(YOUTUBE_API_KEY,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(youtubevideokeys.get(pos));
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                        }
                    });
        }*/



