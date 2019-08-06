package com.hfad.downloadingawebcontent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage(View view){

        ImageDownloader task = new ImageDownloader();
        Bitmap myBitmap;

        try {
          myBitmap =  task.execute("https://vignette.wikia.nocookie.net/sonic-and-friends/images/5/5d/Homer_Simpson.jpg/revision/latest?cb=20180218220951").get();
          imageView.setImageBitmap(myBitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                //get a url connection in order to connect to any http address
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                //use the inputstream to obtain the stream through the connection
                InputStream inputStream = urlConnection.getInputStream();

                //use the bitmap decoder to translate the inputstream into a bitmap data type
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        }
    }
}
