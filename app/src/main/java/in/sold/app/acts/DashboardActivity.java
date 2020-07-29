package in.sold.app.acts;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import in.sold.app.R;
import in.sold.app.frags.HomeFragment;
import in.sold.app.frags.OrdersFragment;
import in.sold.app.frags.ProfileFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    // Vars
    private Context context = DashboardActivity.this;
    private static final String TAG = DashboardActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        changeFragment(new HomeFragment(), HomeFragment.class.getSimpleName());
    }

    private void init() {
        // Widgets
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                changeFragment(new HomeFragment(), HomeFragment.class.getSimpleName());
                break;
            case R.id.item_orders:
                changeFragment(new OrdersFragment(), OrdersFragment.class.getSimpleName());
                break;
            case R.id.item_profile:
                changeFragment(new ProfileFragment(), ProfileFragment.class.getSimpleName());
                break;
        }
        return true;
    }

    private void changeFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layoutContentHolder, fragment, title)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
