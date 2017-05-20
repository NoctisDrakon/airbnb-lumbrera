package adapter;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.lumbrera.airbnb.R;

/**
 * Created by NoctisDrakon on 19/05/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {

    List<String> places;
    List<String> placesSearchable = new ArrayList<>();
    View.OnClickListener listener;

    public PlacesAdapter(List<String> places, View.OnClickListener listener) {
        this.places = places;
        this.listener = listener;
        placesSearchable.addAll(places);

    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        PlacesViewHolder holder = new PlacesViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        String place = places.get(position);
        holder.name.setText(place);
        holder.parent.setTag(place);
        holder.parent.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        places.clear();

        if (charText.length() == 0) {
            places.addAll(placesSearchable);
        } else {

            for (String place : placesSearchable) {
                if (place.toLowerCase().contains(charText)) {
                    places.add(place);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class PlacesViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout parent;

        PlacesViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.place_name);
            parent = (LinearLayout) itemView.findViewById(R.id.parent);
        }
    }
}
