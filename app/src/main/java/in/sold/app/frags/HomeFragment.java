package in.sold.app.frags;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rd.PageIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import in.sold.app.R;
import in.sold.app.acts.FragmentActivity;
import in.sold.app.adapters.CityAdapter;
import in.sold.app.adapters.SliderAdapter;
import in.sold.app.models.Banner;
import in.sold.app.models.City;
import in.sold.app.network.Config;
import in.sold.app.network.VolleyQueue;

public class HomeFragment extends Fragment {
    // Vars
    private Context context;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private int totalSliders = 0, currentSliderPosition = 0;

    // Widgets
    private ViewPager sliderViewPager;
    private PageIndicatorView sliderIndicator;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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
        loadBanners();
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

        LinearLayout itemPhone = view.findViewById(R.id.itemPhone);
        LinearLayout itemTablet = view.findViewById(R.id.itemTablet);
        LinearLayout itemSmartWatch = view.findViewById(R.id.itemSmartWatch);
        LinearLayout itemRecycle = view.findViewById(R.id.itemRecycle);

        itemPhone.setOnClickListener(v -> showBrands("1"));
        itemTablet.setOnClickListener(v -> showBrands("3"));
        itemSmartWatch.setOnClickListener(v -> showBrands("2"));
    }

    private void showBrands(String type) {
        startActivity(new Intent(context, FragmentActivity.class).putExtra("type", type));
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

    private void loadBanners() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Config.BANNER_URL, null, response -> {

                    if (response != null) {

                        Log.d(TAG, "onResponse: response = " + response.toString());

                        try {
                            JSONObject statusObject = response.getJSONArray("response")
                                    .getJSONObject(0);

                            if (statusObject.getString("status").equals("1")) {

                                JSONArray bannersArray = response.getJSONArray("banners");
                                if (bannersArray.length() > 0) {

                                    Type type = new TypeToken<List<Banner>>() {
                                    }.getType();
                                    ArrayList<Banner> banners = new Gson()
                                            .fromJson(bannersArray.toString(), type);

                                    SliderAdapter sliderAdapter = new SliderAdapter(banners, context);
                                    sliderViewPager.setAdapter(sliderAdapter);
                                    sliderIndicator.setViewPager(sliderViewPager);

                                    totalSliders = banners.size();
                                    setAutoSlide();

                                } else {
                                    Log.e(TAG, "loadBanners: No banners found ");
                                    Toast.makeText(context, "No banners found",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Log.e(TAG, "loadBanners: " + statusObject.getString("message"));
                                Toast.makeText(context, statusObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.e(TAG, "onResponse: " + e.getMessage());
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {

                    if (error != null) {
                        Log.e(TAG, "loadBanners: " + error.getMessage());
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                });

        VolleyQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
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