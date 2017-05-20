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

public class AccomodationFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AccomodationFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.loading)
    LinearLayout loading;

    public AccomodationFragment() {

    }

    public static AccomodationFragment newInstance() {
        AccomodationFragment fragment = new AccomodationFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_accomodation, container, false);
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
                        AccomodationFragment.this, getString(R.string.acommodation_title_rv)));
                recyclerView.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);

            }
        }, 3000);
    }

    private List<Item> createDummies() {
        List<Item> list = new ArrayList<>();

        list.add(new Item());
        list.add(new Item("Casa en La Toscana", "65", "http://destinolandia.com/wp-content/2012/01/casa-rural-con-piscina-en-la-toscana1.jpg", 4));
        list.add(new Item("Villa en La Rioja", "42", "http://www.holiday-apartment-tuscany.net/images/tuscany_farmhouse/villa_bucine/grandi/_1.jpg", 4));
        list.add(new Item("Casa en San Sebastián", "99", "http://nuevo-estilo.micasarevista.com/var/decoracion/storage/images/nuevo-estilo/casas-lujo/casa-campo-toscana/comedor-de-exterior/919184-1-esl-ES/comedor-de-exterior_ampliacion.jpg", 4));
        list.add(new Item("Finca en Los Alpes", "81", "http://cdn.lionard.com/files/323/foto/3847_radicondoli.jpg", 4));
        list.add(new Item("Casa en Burgos", "44", "https://blog.homeexchange.com/intercambiocasas/files/2013/06/04-CASA-DIACCIANO-APPROACH-copy.jpg", 4));
        list.add(new Item("Casa en La Toscana", "65", "http://destinolandia.com/wp-content/2012/01/casa-rural-con-piscina-en-la-toscana1.jpg", 4));
        list.add(new Item("Villa en La Rioja", "42", "http://www.holiday-apartment-tuscany.net/images/tuscany_farmhouse/villa_bucine/grandi/_1.jpg", 4));
        list.add(new Item("Casa en San Sebastián", "99", "http://nuevo-estilo.micasarevista.com/var/decoracion/storage/images/nuevo-estilo/casas-lujo/casa-campo-toscana/comedor-de-exterior/919184-1-esl-ES/comedor-de-exterior_ampliacion.jpg", 4));
        list.add(new Item("Finca en Los Alpes", "81", "http://cdn.lionard.com/files/323/foto/3847_radicondoli.jpg", 4));
        list.add(new Item("Casa en Burgos", "44", "https://blog.homeexchange.com/intercambiocasas/files/2013/06/04-CASA-DIACCIANO-APPROACH-copy.jpg", 4));

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
