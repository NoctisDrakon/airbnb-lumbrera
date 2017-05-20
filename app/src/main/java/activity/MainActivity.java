package activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import fragment.ExploreFragment;
import fragment.SavedFragment;
import mx.lumbrera.airbnb.R;

public class MainActivity extends AppCompatActivity implements SavedFragment.OnExploreSelected {

    public static final String PARAM_OBJECT = "param_object";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_explore:
                        switchFragment(ExploreFragment.newInstance());
                        break;

                    case R.id.action_saved:
                        switchFragment(SavedFragment.newInstance());
                        break;

                    case R.id.action_travels:
                        break;

                    case R.id.action_messages:
                        break;

                    case R.id.action_profile:
                        break;

                }
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                return;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_explore);
        switchFragment(ExploreFragment.newInstance());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void switchFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onExploreSelected() {
        bottomNavigationView.setSelectedItemId(R.id.action_explore);
    }
}
