package in.sold.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.sold.app.R;
import in.sold.app.models.Slider;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class SliderAdapter extends PagerAdapter {
    private static final String TAG = SliderAdapter.class.getSimpleName();
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Slider> sliders;

    public SliderAdapter(ArrayList<Slider> sliders, Context context) {
        this.sliders = sliders;
        this.context = context;
        layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.list_item_slider, container, false);

        ImageView imgSlider = view.findViewById(R.id.imgSlider);
        TextView textTitle = view.findViewById(R.id.textTitle);

        final Slider slider = sliders.get(position);
        Picasso.get().load(slider.getImage()).into(imgSlider, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: imgSlider -> " + e.getMessage());
            }
        });

        textTitle.setText(slider.getTitle());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }
}
