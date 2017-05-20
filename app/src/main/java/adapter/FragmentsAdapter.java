package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NoctisDrakon on 19/05/2017.
 */

public class FragmentsAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private final List<String> names = new ArrayList<>();

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment f, String title) {
        fragments.add(f);
        names.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
