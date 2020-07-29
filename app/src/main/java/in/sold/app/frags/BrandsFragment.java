package in.sold.app.frags;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.sold.app.R;
import in.sold.app.acts.FragmentActivity;
import in.sold.app.adapters.BrandAdapter;
import in.sold.app.models.Brand;
import in.sold.app.network.Config;
import in.sold.app.network.VolleyQueue;

public class BrandsFragment extends Fragment {
    // Vars
    private Context context;
    private static final String TAG = BrandsFragment.class.getSimpleName();

    // Widgets
    private ProgressBar progressBar;
    private RecyclerView listBrands;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public BrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brands, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        // Widgets
        progressBar = view.findViewById(R.id.progressBar);
        listBrands = view.findViewById(R.id.listBrands);
        listBrands.setLayoutManager(new GridLayoutManager(context, 3));

        loadBrands();
    }

    private void loadBrands() {

        showProgress(true);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Config.BRAND_URL, null, response -> {

                    showProgress(false);

                    if (response != null) {

                        Log.d(TAG, "onResponse: response = " + response.toString());

                        try {
                            JSONObject statusObject = response.getJSONArray("response")
                                    .getJSONObject(0);

                            if (statusObject.getString("status").equals("1")) {

                                JSONArray bannersArray = response.getJSONArray("brands");
                                if (bannersArray.length() > 0) {

                                    Type type = new TypeToken<List<Brand>>() {
                                    }.getType();
                                    ArrayList<Brand> brands = new Gson()
                                            .fromJson(bannersArray.toString(), type);

                                    BrandAdapter brandAdapter = new BrandAdapter(brands);
                                    listBrands.setAdapter(brandAdapter);

                                } else {
                                    Log.e(TAG, "loadBrands: No brands found");
                                    Toast.makeText(context, "No brands found",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Log.e(TAG, "loadBrands: " + statusObject.getString("message"));
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
                    showProgress(false);
                    if (error != null) {
                        Log.e(TAG, "loadBrands: " + error.getMessage());
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("TYPE", getActivity() != null ? ((FragmentActivity) getActivity()).type : "");
                Log.d(TAG, "getParams: params = " + params);
                return params;
            }
        };

        VolleyQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    private void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}