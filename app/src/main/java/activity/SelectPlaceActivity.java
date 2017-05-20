package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import adapter.PlacesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import mx.lumbrera.airbnb.R;

import static fragment.ExploreFragment.PARAM_PLACE;

public class SelectPlaceActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.places_list)
    RecyclerView placesList;
    @BindView(R.id.search_place)
    EditText searchPlace;

    PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        placesList.setLayoutManager(llm);

        adapter = getDummyAdapter();
        placesList.setAdapter(adapter);
        searchPlace.addTextChangedListener(this);
    }

    private PlacesAdapter getDummyAdapter() {
        List<String> places = new ArrayList<>();
        places.add("Antigua y Barbuda");
        places.add("Argentina");
        places.add("Bahamas");
        places.add("Barbados");
        places.add("Belice");
        places.add("Bolivia");
        places.add("Brasil");
        places.add("Canadá");
        places.add("Chile");
        places.add("Colombia");
        places.add("Costa Rica");
        places.add("Cuba");
        places.add("Dominica");
        places.add("Ecuador");
        places.add("El Salvador");
        places.add("Estados Unidos");
        places.add("Granada");
        places.add("Guatemala");
        places.add("Guyana");
        places.add("Haití");
        places.add("Honduras");
        places.add("Jamaica");
        places.add("México");
        places.add("Nicaragua");
        places.add("Panamá");
        places.add("Paraguay");
        places.add("Perú");
        places.add("República Dominicana");
        places.add("San Cristóbal y Nieves");
        places.add("San Vicente y las Granadinas");
        places.add("Santa Lucía");
        places.add("Suriname");
        places.add("Trinidad y Tobago");
        places.add("Uruguay");
        places.add("Venezuela");
        return new PlacesAdapter(places, this);
    }

    @Override
    public void onClick(View view) {
        Intent result = new Intent();
        result.putExtra(PARAM_PLACE, (String) view.getTag());
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        adapter.filter(editable.toString());
    }
}
