package in.precisto.precisto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {
    ImageView iva,ivu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        iva=findViewById(R.id.about_us_abhinav);
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.abhinav);
        RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        iva.setImageDrawable(roundedBitmapDrawable);

       ivu.findViewById(R.id.about_us_umair);
       Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.mipmap.umair);
       RoundedBitmapDrawable roundedBitmapDrawable1=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
       roundedBitmapDrawable.setCircular(true);
       ivu.setImageDrawable(roundedBitmapDrawable1);



        /*imageView=findViewById(R.id.iv);


        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.cm);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);*/


    }
}
