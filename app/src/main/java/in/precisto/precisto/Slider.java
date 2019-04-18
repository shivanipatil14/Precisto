package in.precisto.precisto;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Slider extends AppCompatActivity {

    private ViewPager slideViewPager;
    private TextView[] dots;
    private LinearLayout dotLayout;
    private  SliderAdapter sliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        slideViewPager=findViewById(R.id.slider_view_pager);
        dotLayout =findViewById(R.id.slider_linear_layout);

        sliderAdapter=new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);




    }

    public void addDotsIndicator(){
        dots=new TextView[3];
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#98BBFA"));
            dotLayout.addView(dots[i]);

        }
    }
}