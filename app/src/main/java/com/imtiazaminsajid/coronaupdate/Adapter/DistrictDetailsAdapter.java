package com.imtiazaminsajid.coronaupdate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.R;

public class DistrictDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    BangladeshAllDataModel bangladeshAllDataModel;


    public DistrictDetailsAdapter(Context context, BangladeshAllDataModel bangladeshAllDataModel) {
        this.context = context;
        this.bangladeshAllDataModel = bangladeshAllDataModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.districts_details_item, parent, false);
        DistrictViewHolder districtViewHolder = new DistrictViewHolder(view);
        return districtViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DistrictViewHolder) {
            DistrictViewHolder mHolder = (DistrictViewHolder) holder;
            mHolder.index.setText(""+bangladeshAllDataModel.getDistricts().get(position).getId());
            mHolder.districtName.setText(""+bangladeshAllDataModel.getDistricts().get(position).getName());
            mHolder.districtTotalConfirmed.setText(""+bangladeshAllDataModel.getDistricts().get(position).getConfirmed());
            mHolder.districtTotalDeath.setText(""+bangladeshAllDataModel.getDistricts().get(position).getDeaths());
        }
    }

    @Override
    public int getItemCount() {
        return bangladeshAllDataModel.getDistricts().size();
    }

    public class DistrictViewHolder extends RecyclerView.ViewHolder {

        TextView index, districtName, districtTotalConfirmed, districtTotalDeath;

        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.index);
            districtName = itemView.findViewById(R.id.districtName);
            districtTotalConfirmed = itemView.findViewById(R.id.districtTotalConfirmed);
            districtTotalDeath = itemView.findViewById(R.id.districtTotalDeath);
        }
    }
}
