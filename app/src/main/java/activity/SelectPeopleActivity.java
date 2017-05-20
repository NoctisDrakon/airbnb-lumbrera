package activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.lumbrera.airbnb.R;

import static fragment.ExploreFragment.PARAM_PEOPLE;

public class SelectPeopleActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.less_adults)
    Button lessAdults;
    @BindView(R.id.more_adults)
    Button moreAdults;
    @BindView(R.id.less_kids)
    Button lessKids;
    @BindView(R.id.more_kids)
    Button moreKids;
    @BindView(R.id.less_babies)
    Button lessBabies;
    @BindView(R.id.more_babies)
    Button moreBabies;

    @BindView(R.id.adults_number)
    TextView tAdultsNumber;
    @BindView(R.id.kids_number)
    TextView tKidsNumber;
    @BindView(R.id.babies_number)
    TextView tBabiesNumber;

    @BindView(R.id.switch_pets)
    SwitchCompat switchPets;
    @BindView(R.id.fab_people)
    FloatingActionButton fabPeople;

    private int adultsNumber = 1;
    private int kidsNumber = 0;
    private int babysNumber = 0;
    private boolean pets = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_people);
        ButterKnife.bind(this);
        switchPets.setOnCheckedChangeListener(this);
        adjustValuesAndViews();
    }

    private void adjustValuesAndViews() {
        if (adultsNumber <= 1)
            lessAdults.setEnabled(false);
        else
            lessAdults.setEnabled(true);

        if (kidsNumber <= 0)
            lessKids.setEnabled(false);
        else
            lessKids.setEnabled(true);

        if (babysNumber <= 0)
            lessBabies.setEnabled(false);
        else
            lessBabies.setEnabled(true);

        tAdultsNumber.setText(adultsNumber + "");
        tKidsNumber.setText(kidsNumber + "");
        tBabiesNumber.setText(babysNumber + "");
    }

    @OnClick({R.id.more_adults, R.id.less_adults, R.id.more_kids, R.id.less_kids, R.id.more_babies, R.id.less_babies})
    public void setNewValues(View v) {
        switch (v.getId()) {
            case R.id.more_adults:
                adultsNumber++;
                break;
            case R.id.less_adults:
                adultsNumber--;
                break;
            case R.id.more_kids:
                kidsNumber++;
                break;
            case R.id.less_kids:
                kidsNumber--;
                break;
            case R.id.more_babies:
                babysNumber++;
                break;
            case R.id.less_babies:
                babysNumber--;
                break;
        }
        adjustValuesAndViews();
    }

    @OnClick(R.id.fab_people)
    public void setResult() {
        String result = String.format(getString(adultsNumber > 1 ? R.string.adults_format : R.string.adult_format), adultsNumber + "");

        if (kidsNumber > 0) {
            result = result + ", " + String.format(getString(kidsNumber > 1 ? R.string.kids_format : R.string.kid_format), kidsNumber + "");
        }

        if (babysNumber > 0) {
            result = result + ", " + String.format(getString(babysNumber > 1 ? R.string.babies_format : R.string.baby_format), babysNumber + "");
        }

        if (pets) {
            result = result + ", " + getString(R.string.with_pet);
        }

        Intent i = new Intent();
        i.putExtra(PARAM_PEOPLE, result);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        pets = b;
    }
}
