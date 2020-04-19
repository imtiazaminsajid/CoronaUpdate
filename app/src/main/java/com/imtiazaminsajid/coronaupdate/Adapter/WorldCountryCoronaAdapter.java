package com.imtiazaminsajid.coronaupdate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldDatumModel;
import com.imtiazaminsajid.coronaupdate.R;

import java.util.ArrayList;
import java.util.List;

public class WorldCountryCoronaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<WorldDatumModel> worldDatumModels = new ArrayList<>();

    public List<WorldDatumModel> getWorldDatumModels() {
        return worldDatumModels;
    }

    public void setWorldDatumModels(List<WorldDatumModel> worldDatumModels) {
        this.worldDatumModels = worldDatumModels;
        notifyDataSetChanged();
    }

    public WorldCountryCoronaAdapter(Context context, List<WorldDatumModel> worldDatumModels) {
        this.context = context;
        this.worldDatumModels = worldDatumModels;
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
            if (worldDatumModels !=null) {
                mHolder.world_country_name.setText("" + worldDatumModels.get(position).getCountryName());
                mHolder.total_cases.setText("" + worldDatumModels.get(position).getTotalCases().toString());
                mHolder.new_cases.setText("" + worldDatumModels.get(position).getNewCases().toString());
                mHolder.total_death.setText("" + worldDatumModels.get(position).getTotalDeath().toString());
                mHolder.new_death.setText("" + worldDatumModels.get(position).getNewDeath().toString());
                mHolder.total_recovered.setText("" + worldDatumModels.get(position).getTotalRecovered().toString());
                mHolder.active_cases.setText("" + worldDatumModels.get(position).getActiveCases().toString());
//                mHolder.critical_cases.setText("" + worldAllCountryModel.getData().get(position).getCriticalCases().toString());
            }
        }
    }

    @Override
    public int getItemCount() {

//        if(worldAllCountryModel.getData() != null){
//            return worldDatumModelsFiltered.size();
//        } else {
//            return 0;
//        }

        return worldDatumModels.size();
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
