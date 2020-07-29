package in.sold.app.frags;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import in.sold.app.R;
import in.sold.app.acts.FragmentActivity;

public class SeriesFragment extends Fragment {
    // Vars
    private static final String TAG = SeriesFragment.class.getSimpleName();

    public SeriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_series, container, false);
    }
}