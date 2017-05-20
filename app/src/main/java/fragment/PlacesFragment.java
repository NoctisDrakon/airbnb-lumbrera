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

public class PlacesFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PlacesFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.loading)
    LinearLayout loading;

    public PlacesFragment() {

    }

    public static PlacesFragment newInstance() {
        PlacesFragment fragment = new PlacesFragment();
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
        View v = inflater.inflate(R.layout.fragment_places, container, false);
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
                        PlacesFragment.this, getString(R.string.places_title_rv)));
                recyclerView.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }
        }, 3000);
    }

    private List<Item> createDummies() {
        List<Item> list = new ArrayList<>();

        list.add(new Item());
        list.add(new Item("La Toscana", "65", "http://cdn.traveler.es/uploads/images/thumbs/201348/los_10_pueblos_mas_bonitos_de_la_toscana_958744053_1200x800.jpg", 4));
        list.add(new Item("La Rioja", "42", "https://content1.lariojaturismo.com/imagenes/Documentos/imgsem/57/577b/577b965d-bc19-4837-9ac9-f4b84ea136cc/6aef680a-283d-4fab-4135-2c50b09df747.jpg", 4));
        list.add(new Item("San Sebastián", "99", "https://turismo.euskadi.eus/contenidos/d_destinos_turisticos/0000005101_d2_rec_turismo/es_5101/images/galeria_sansebastian_12.jpg", 4));
        list.add(new Item("Los Alpes", "81", "http://farm7.static.flickr.com/6012/6019178708_02802155b7_b.jpg", 4));
        list.add(new Item("Burgos", "44", "http://4.bp.blogspot.com/-D7C3ruDrKmY/VF3lVwFPAyI/AAAAAAAAawM/BFmhSi6kInA/s1600/La%2BCatedral%2Bde%2BBurgos.jpg", 4));
        list.add(new Item("La Toscana", "65", "http://cdn.traveler.es/uploads/images/thumbs/201348/los_10_pueblos_mas_bonitos_de_la_toscana_958744053_1200x800.jpg", 4));
        list.add(new Item("La Rioja", "42", "https://content1.lariojaturismo.com/imagenes/Documentos/imgsem/57/577b/577b965d-bc19-4837-9ac9-f4b84ea136cc/6aef680a-283d-4fab-4135-2c50b09df747.jpg", 4));
        list.add(new Item("San Sebastián", "99", "https://turismo.euskadi.eus/contenidos/d_destinos_turisticos/0000005101_d2_rec_turismo/es_5101/images/galeria_sansebastian_12.jpg", 4));
        list.add(new Item("Los Alpes", "81", "http://farm7.static.flickr.com/6012/6019178708_02802155b7_b.jpg", 4));
        list.add(new Item("Burgos", "44", "http://4.bp.blogspot.com/-D7C3ruDrKmY/VF3lVwFPAyI/AAAAAAAAawM/BFmhSi6kInA/s1600/La%2BCatedral%2Bde%2BBurgos.jpg", 4));


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
