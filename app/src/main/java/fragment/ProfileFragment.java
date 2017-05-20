package fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.ProfileAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.OnExploreSelected;
import model.ProfileItem;
import mx.lumbrera.airbnb.R;

public class ProfileFragment extends Fragment {

    private OnExploreSelected mListener;

    @BindView(R.id.rv)
    RecyclerView rv;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        setAdapter();
        return v;
    }

    private void setAdapter() {
        List<ProfileItem> items = new ArrayList<>();
        items.add(new ProfileItem());
        items.add(new ProfileItem(getString(R.string.travel_credit_label)));
        items.add(new ProfileItem(getString(R.string.become_host_label)));
        items.add(new ProfileItem(getString(R.string.settings_label)));
        items.add(new ProfileItem(getString(R.string.help_label)));
        items.add(new ProfileItem(getString(R.string.give_feedback_label)));

        ProfileAdapter adapter = new ProfileAdapter(getContext(), items);
        rv.setAdapter(adapter);


    }
}
