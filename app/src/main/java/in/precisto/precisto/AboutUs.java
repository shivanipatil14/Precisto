package in.precisto.precisto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AboutUs extends Fragment {
    ImageView iva,ivu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_about_us,container,false);

       /* iva=(ImageView)view.findViewById(R.id.about_us_abhinav);
       // iva=new AppCompatActivity().findViewById(R.id.about_us_abhinav);
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.abhinav);
        RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        iva.setImageDrawable(roundedBitmapDrawable);

        ivu=(ImageView)view.findViewById(R.id.about_us_umair);
        //ivu=new AppCompatActivity().findViewById(R.id.about_us_umair);
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.mipmap.umair);
        RoundedBitmapDrawable roundedBitmapDrawable1=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        ivu.setImageDrawable(roundedBitmapDrawable1);
*/

        return view;
    }
}
