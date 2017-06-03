package fr.unice.polytech.si3.gregorymerlet.enseigne;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImagesAsyncTask extends AsyncTask<String, Void, Bitmap>{

    private ImageView imageView;

    public ImagesAsyncTask(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream inputStream = null;

        String url = params[0];

        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(inputStream);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
    }
}
