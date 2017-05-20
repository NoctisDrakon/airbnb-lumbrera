package fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import activity.SelectDateActivity;
import activity.SelectPeopleActivity;
import adapter.FragmentsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.lumbrera.airbnb.R;
import activity.SelectPlaceActivity;

public class ExploreFragment extends Fragment {

    private static final String TAG = "ExploreFragment";

    public static final String PARAM_PLACE = "param_place";
    public static final String PARAM_DATE = "param_date";
    public static final String PARAM_PEOPLE = "param_people";

    private static final int REQUEST_CODE_PLACE = 222;
    private static final int REQUEST_CODE_DATE = 333;
    private static final int REQUEST_CODE_PEOPLE = 444;

    @BindView(R.id.button_place)
    Button buttonPlace;
    @BindView(R.id.button_date)
    Button buttonDate;
    @BindView(R.id.button_people)
    Button buttonPeople;

    @BindView(R.id.view_pager)
    ViewPager pager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    FragmentsAdapter adapter;

    public ExploreFragment() {
        // Required empty public constructor
    }


    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //args
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);
        tabs.setupWithViewPager(pager);
        setUpFragments();
        return view;
    }

    private void setUpFragments() {
        adapter = new FragmentsAdapter(getChildFragmentManager());
        adapter.addFragment(AccomodationFragment.newInstance(), getString(R.string.acommodation_title));
        adapter.addFragment(ExperiencesFragment.newInstance(), getString(R.string.experiences_title));
        adapter.addFragment(PlacesFragment.newInstance(), getString(R.string.places_title));
        pager.setAdapter(adapter);

    }

    @OnClick({R.id.button_place, R.id.button_date, R.id.button_people})
    public void startActivity(View v) {
        switch (v.getId()) {
            case R.id.button_place:
                startActivityForResult(new Intent(getContext(), SelectPlaceActivity.class), REQUEST_CODE_PLACE);
                break;

            case R.id.button_date:
                startActivityForResult(new Intent(getContext(), SelectDateActivity.class), REQUEST_CODE_DATE);
                break;

            case R.id.button_people:
                startActivityForResult(new Intent(getContext(), SelectPeopleActivity.class), REQUEST_CODE_PEOPLE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_PLACE:
                    buttonPlace.setText(data.getStringExtra(PARAM_PLACE));
                    break;

                case REQUEST_CODE_DATE:
                    buttonDate.setText(data.getStringExtra(PARAM_DATE));
                    break;

                case REQUEST_CODE_PEOPLE:
                    buttonPeople.setText(data.getStringExtra(PARAM_PEOPLE));
                    break;
            }
            EventBus.getDefault().post(new UpdateEvent());
        }
    }

    public static class UpdateEvent {
        public UpdateEvent() {
        }
    }
}
