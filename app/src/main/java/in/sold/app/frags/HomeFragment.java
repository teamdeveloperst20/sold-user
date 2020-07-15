package in.sold.app.frags;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import in.sold.app.R;
import in.sold.app.adapters.CityAdapter;
import in.sold.app.adapters.DashboardAdapter;
import in.sold.app.adapters.SliderAdapter;
import in.sold.app.models.City;
import in.sold.app.models.DashboardItem;
import in.sold.app.models.Slider;

public class HomeFragment extends Fragment {
    // Vars
    private Context context;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private int totalSliders = 0, currentSliderPosition = 0;

    // Widgets
    private ViewPager sliderViewPager;
    private PageIndicatorView sliderIndicator;
    private RecyclerView listDashboard;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        setSliders();
        setDashboardItems();
        return view;
    }

    private void init(View view) {
        // Vars
        context = getActivity();
        sliderViewPager = view.findViewById(R.id.sliderViewPager);
        sliderIndicator = view.findViewById(R.id.sliderIndicator);

        // Widgets
        LinearLayout layoutLocation = view.findViewById(R.id.layoutLocation);
        layoutLocation.setOnClickListener(v -> showCities());

        listDashboard = view.findViewById(R.id.listDashboard);
        listDashboard.setLayoutManager(new GridLayoutManager(context, 2));
    }

    private void showCities() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_cities);
        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

        RecyclerView listCities = dialog.findViewById(R.id.listCities);
        listCities.setLayoutManager(new GridLayoutManager(context, 3));

        ArrayList<City> cities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cities.add(new City("City" + i));
        }

        CityAdapter cityAdapter = new CityAdapter(cities);
        listCities.setAdapter(cityAdapter);

    }

    private void setDashboardItems() {
        ArrayList<DashboardItem> dashboardItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            DashboardItem item = new DashboardItem();
            item.setTitle("Item" + i);
            dashboardItems.add(item);
        }

        DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboardItems);
        listDashboard.setAdapter(dashboardAdapter);
    }

    private void setSliders() {
        ArrayList<Slider> sliders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Slider slider = new Slider();
            slider.setImage("https://bit.ly/srj-demo-img");
            slider.setTitle("This was for testing purposes");
            sliders.add(slider);
        }

        SliderAdapter sliderAdapter = new SliderAdapter(sliders, context);
        sliderViewPager.setAdapter(sliderAdapter);
        sliderIndicator.setViewPager(sliderViewPager);

        totalSliders = sliders.size();
        setAutoSlide();
    }

    private void setAutoSlide() {
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentSliderPosition == totalSliders) {
                currentSliderPosition = 0;
            }
            sliderViewPager.setCurrentItem(currentSliderPosition++, true);
        };

        Timer timer = new Timer();
        // This will create a new Thread
        long DELAY_MS = 5000;
        long PERIOD_MS = 5000;
        timer.schedule(new TimerTask() {
            // Task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

}