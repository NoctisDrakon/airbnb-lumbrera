package activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.lumbrera.airbnb.R;

import static fragment.ExploreFragment.PARAM_DATE;

public class SelectDateActivity extends AppCompatActivity implements CalendarPickerView.OnDateSelectedListener, View.OnClickListener {

    private static final String TAG = "SelectDateActivity";
    private Date date1;
    private Date date2;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    @BindView(R.id.date_1)
    TextView tvDate1;
    @BindView(R.id.date_2)
    TextView tvDate2;
    @BindView(R.id.calendar_view)
    CalendarPickerView calendarPickerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        ButterKnife.bind(this);

        fab.setOnClickListener(this);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        Date today = new Date();
        calendarPickerView.init(today, nextYear.getTime())
                .withSelectedDate(today).inMode(CalendarPickerView.SelectionMode.RANGE);

        calendarPickerView.setOnDateSelectedListener(this);
        date1 = today;
        printDate();

    }

    private void printDate() {
        if (date1 != null)
            tvDate1.setText(format.format(date1));
        else
            tvDate1.setText(getString(R.string.from));

        if (date2 != null)
            tvDate2.setText(format.format(date2));
        else
            tvDate2.setText(getString(R.string.to));
    }

    @Override
    public void onDateSelected(Date date) {

        if (date1 != null && date2 != null) {
            date1 = null;
            date2 = null;
        }

        if (date1 == null)
            date1 = date;
        else
            date2 = date;

        printDate();

    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onClick(View view) {
        if (date1 == null) {
            Toast.makeText(this, getString(R.string.error_select_date), Toast.LENGTH_SHORT).show();
            return;
        }

        String date = format.format(date1);

        if (date2 != null) {
            date = date + " / " + format.format(date2);
        }

        Intent i = new Intent();
        i.putExtra(PARAM_DATE, date);
        setResult(RESULT_OK, i);
        finish();
    }
}
