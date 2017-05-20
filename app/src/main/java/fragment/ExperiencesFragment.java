package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import activity.ItemDetailActivity;
import adapter.ItemsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Item;
import mx.lumbrera.airbnb.R;

import static activity.MainActivity.PARAM_OBJECT;

public class ExperiencesFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ExperiencesFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.loading)
    LinearLayout loading;

    public ExperiencesFragment() {

    }

    public static ExperiencesFragment newInstance() {
        ExperiencesFragment fragment = new ExperiencesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_experiences, container, false);
        ButterKnife.bind(this, v);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        startSimulation();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void startSimulation() {
        recyclerView.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isAdded())
                    return;

                recyclerView.setAdapter(new ItemsAdapter(createDummies(), getContext(),
                        ExperiencesFragment.this, getString(R.string.experiences_title_rv)));
                recyclerView.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }
        }, 3000);
    }

    private List<Item> createDummies() {
        List<Item> list = new ArrayList<>();

        list.add(new Item());
        list.add(new Item("Viaje en Kayak", "450", "https://www.seatrek.com/wp-content/uploads/2016/10/Traditional-Sea-Kayak-3.jpg", 4));
        list.add(new Item("Alpinismo extremo", "900", "https://thenypost.files.wordpress.com/2014/06/shutterstock_145698575.jpg", 4));
        list.add(new Item("Ciclismo en la montaña", "750", "http://images.singletracks.com/blog/wp-content/uploads/2015/02/IMG_1743.jpg", 4));
        list.add(new Item("Paracaidismo", "1200", "https://i0.wp.com/www.dondeir.com/wp-content/uploads/2016/09/donde-hacer-paracaidismo-f.jpg", 4));
        list.add(new Item("Esquí acuático", "1200", "http://wwwdubai2020.com/images/684566Water-Skiing(1).jpg", 4));
        list.add(new Item("Viaje en Kayak", "450", "https://www.seatrek.com/wp-content/uploads/2016/10/Traditional-Sea-Kayak-3.jpg", 4));
        list.add(new Item("Alpinismo extremo", "900", "https://thenypost.files.wordpress.com/2014/06/shutterstock_145698575.jpg", 4));
        list.add(new Item("Ciclismo en la montaña", "750", "http://images.singletracks.com/blog/wp-content/uploads/2015/02/IMG_1743.jpg", 4));
        list.add(new Item("Paracaidismo", "1200", "https://i0.wp.com/www.dondeir.com/wp-content/uploads/2016/09/donde-hacer-paracaidismo-f.jpg", 4));
        list.add(new Item("Esquí acuático", "1200", "http://wwwdubai2020.com/images/684566Water-Skiing(1).jpg", 4));
        return list;
    }

    @Override
    public void onClick(View view) {
        Item a = (Item) view.getTag();
        Intent i = new Intent(getContext(), ItemDetailActivity.class);
        i.putExtra(PARAM_OBJECT, a);
        startActivity(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateEvent(ExploreFragment.UpdateEvent event) {
        startSimulation();
    }
}
