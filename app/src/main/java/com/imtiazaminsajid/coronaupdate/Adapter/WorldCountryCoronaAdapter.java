package com.imtiazaminsajid.coronaupdate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.R;

public class WorldCountryCoronaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    WorldAllCountryModel worldAllCountryModel;

    public WorldCountryCoronaAdapter(Context context, WorldAllCountryModel worldAllCountryModel) {
        this.context = context;
        this.worldAllCountryModel = worldAllCountryModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.world_country_details_item, parent, false);
        WorldCountryViewHolder worldCountryViewHolder = new WorldCountryViewHolder(view);
        return worldCountryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WorldCountryViewHolder) {
            WorldCountryViewHolder mHolder = (WorldCountryViewHolder) holder;
            if (worldAllCountryModel!=null) {
                mHolder.world_country_name.setText("" + worldAllCountryModel.getData().get(position).getCountryName());
                mHolder.total_cases.setText("" + worldAllCountryModel.getData().get(position).getTotalCases().toString());
                mHolder.new_cases.setText("" + worldAllCountryModel.getData().get(position).getNewCases().toString());
                mHolder.total_death.setText("" + worldAllCountryModel.getData().get(position).getTotalDeath().toString());
                mHolder.new_death.setText("" + worldAllCountryModel.getData().get(position).getNewDeath().toString());
                mHolder.total_recovered.setText("" + worldAllCountryModel.getData().get(position).getTotalRecovered().toString());
                mHolder.active_cases.setText("" + worldAllCountryModel.getData().get(position).getActiveCases().toString());
//                mHolder.critical_cases.setText("" + worldAllCountryModel.getData().get(position).getCriticalCases().toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return worldAllCountryModel.getData().size();
    }

    public class WorldCountryViewHolder extends RecyclerView.ViewHolder {

        TextView world_country_name, total_cases, new_cases,
                total_death, new_death,total_recovered, active_cases,critical_cases;

        public WorldCountryViewHolder(@NonNull View itemView) {
            super(itemView);

            world_country_name = itemView.findViewById(R.id.world_country_name);
            total_cases = itemView.findViewById(R.id.total_cases);
            new_cases = itemView.findViewById(R.id.new_cases);
            total_death = itemView.findViewById(R.id.total_death);
            new_death = itemView.findViewById(R.id.new_death);
            total_recovered = itemView.findViewById(R.id.total_recovered);
            active_cases = itemView.findViewById(R.id.active_cases);
//            critical_cases = itemView.findViewById(R.id.critical_cases);
        }
    }
}
