package com.imtiazaminsajid.coronaupdate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldDatumModel;
import com.imtiazaminsajid.coronaupdate.NewModel.AllCountryData;
import com.imtiazaminsajid.coronaupdate.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorldCountryCoronaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<WorldDatumModel> worldDatumModels = new ArrayList<>();

    List<AllCountryData> allCountryData = new ArrayList<>();

    public List<WorldDatumModel> getWorldDatumModels() {
        return worldDatumModels;
    }

    public void setWorldDatumModels(List<WorldDatumModel> worldDatumModels) {
        this.worldDatumModels = worldDatumModels;
        notifyDataSetChanged();
    }

    public WorldCountryCoronaAdapter(Context context, List<AllCountryData> allCountryData) {
        this.context = context;
        this.allCountryData = allCountryData;
    }

    public List<AllCountryData> getAllCountryData() {
        return allCountryData;
    }

    public void setAllCountryData(List<AllCountryData> allCountryData) {
        this.allCountryData = allCountryData;
        notifyDataSetChanged();
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
//            if (worldDatumModels !=null) {
//                mHolder.world_country_name.setText("" + worldDatumModels.get(position).getCountryName());
//                mHolder.total_cases.setText("" + worldDatumModels.get(position).getTotalCases());
//                mHolder.new_cases.setText("" + worldDatumModels.get(position).getNewCases());
//                mHolder.total_death.setText("" + worldDatumModels.get(position).getTotalDeath());
//                mHolder.new_death.setText("" + worldDatumModels.get(position).getNewDeath());
//                mHolder.total_recovered.setText("" + worldDatumModels.get(position).getTotalRecovered());
//                mHolder.active_cases.setText("" + worldDatumModels.get(position).getActiveCases());
//                mHolder.critical_cases.setText("" + worldDatumModels.get(position).getCriticalCases());
//            }

            if (allCountryData !=null) {
                mHolder.world_country_name.setText("" + allCountryData.get(position).getCountry());
                mHolder.total_cases.setText("" + allCountryData.get(position).getCases());
                mHolder.new_cases.setText("" + allCountryData.get(position).getTodayCases());
                mHolder.total_death.setText("" + allCountryData.get(position).getTodayDeaths());
                mHolder.new_death.setText("" + allCountryData.get(position).getDeaths());
                mHolder.total_recovered.setText("" + allCountryData.get(position).getRecovered());
                mHolder.active_cases.setText("" + allCountryData.get(position).getActive());
                mHolder.critical_cases.setText("" + allCountryData.get(position).getCritical());
                mHolder.tests.setText("" + allCountryData.get(position).getTests());
                mHolder.casesPerOneMillion.setText("" + allCountryData.get(position).getCasesPerOneMillion());
                mHolder.deathsPerOneMillion.setText("" + allCountryData.get(position).getDeathsPerOneMillion());

                Glide.with(context)
                        .load(allCountryData.get(position).getCountryInfo().getFlag())
                        .into(mHolder.countryFlag);
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

        return allCountryData.size();
    }


    public class WorldCountryViewHolder extends RecyclerView.ViewHolder {

        TextView world_country_name, total_cases, new_cases,
                total_death, new_death,total_recovered, active_cases,critical_cases,
                casesPerOneMillion, deathsPerOneMillion,tests;
        CircleImageView countryFlag;

        public WorldCountryViewHolder(@NonNull View itemView) {
            super(itemView);

            world_country_name = itemView.findViewById(R.id.world_country_name);
            total_cases = itemView.findViewById(R.id.total_cases);
            new_cases = itemView.findViewById(R.id.new_cases);
            total_death = itemView.findViewById(R.id.total_death);
            new_death = itemView.findViewById(R.id.new_death);
            total_recovered = itemView.findViewById(R.id.total_recovered);
            active_cases = itemView.findViewById(R.id.active_cases);
            critical_cases = itemView.findViewById(R.id.critical_cases);
            countryFlag = itemView.findViewById(R.id.country_flag);
            casesPerOneMillion = itemView.findViewById(R.id.casesPerOneMillion);
            deathsPerOneMillion = itemView.findViewById(R.id.deathsPerOneMillion);
            tests = itemView.findViewById(R.id.tests);
        }
    }


}
