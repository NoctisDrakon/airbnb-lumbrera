package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import model.Item;
import model.ProfileItem;
import mx.lumbrera.airbnb.R;

import static model.ProfileItem.TYPE_PROFILE_HEADER;
import static model.ProfileItem.TYPE_PROFILE_ITEM;


public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ProfileAdapter";
    private List<ProfileItem> list;
    private Context context;

    public ProfileAdapter(Context context, List<ProfileItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case TYPE_PROFILE_HEADER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_header, parent, false);
                return new HeaderViewHolder(v);
            case TYPE_PROFILE_ITEM:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
                return new ItemViewHolder(v);
        }
        throw new IllegalArgumentException("None of the declared types match with the current found type, please implement it correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProfileItem a = getItem(position);

        switch (a.type) {
            case TYPE_PROFILE_HEADER:
                //nothing
                break;

            case TYPE_PROFILE_ITEM:
                ItemViewHolder ivh = (ItemViewHolder) holder;
                ivh.name.setText(a.name);
                break;


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private ProfileItem getItem(int index) {
        return list.get(index);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.profile_item_name);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}