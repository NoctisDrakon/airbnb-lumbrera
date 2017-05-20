package activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Item;
import mx.lumbrera.airbnb.R;

import static activity.MainActivity.PARAM_OBJECT;

public class ItemDetailActivity extends AppCompatActivity {

    @BindView(R.id.item_image)
    ImageView itemImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo_container)
    LinearLayout photoContainer;
    @BindView(R.id.accomodation_price)
    TextView accomodationPrice;
    @BindView(R.id.accomodation_rating)
    RatingBar accomodationRating;

    Item item;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        item = getIntent().getParcelableExtra(PARAM_OBJECT);
        if (item == null) {
            Toast.makeText(this, getString(R.string.error_item_null), Toast.LENGTH_SHORT).show();
            finish();
        }

        actionBar = getSupportActionBar();
        actionBar.setTitle(item.title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Glide.with(getApplicationContext())
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(itemImage);

        accomodationPrice.setText(String.format(getString(R.string.euro_format), item.price));
        accomodationRating.setRating(item.rate);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }


}
