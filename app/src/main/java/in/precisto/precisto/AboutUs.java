package in.precisto.precisto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AboutUs extends Fragment {
    ImageView iva,ivu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        iva=(ImageView)container.findViewById(R.id.about_us_abhinav);
       // iva=new AppCompatActivity().findViewById(R.id.about_us_abhinav);
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.abhinav);
        RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        iva.setImageDrawable(roundedBitmapDrawable);

        ivu=(ImageView)container.findViewById(R.id.about_us_umair);
        //ivu=new AppCompatActivity().findViewById(R.id.about_us_umair);
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.mipmap.umair);
        RoundedBitmapDrawable roundedBitmapDrawable1=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        ivu.setImageDrawable(roundedBitmapDrawable1);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
