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
import com.imtiazaminsajid.coronaupdate.Model.DistrictModel;
import com.imtiazaminsajid.coronaupdate.R;

import java.util.ArrayList;
import java.util.List;

public class DistrictDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<DistrictModel> districtModels = new ArrayList<>();


    public DistrictDetailsAdapter(Context context) {
        this.context = context;
        this.districtModels = districtModels;
    }

    public List<DistrictModel> getDistrictModels() {
        return districtModels;
    }

    public void setDistrictModels(List<DistrictModel> districtModels) {
        this.districtModels = districtModels;
        notifyDataSetChanged();
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
            if (districtModels!=null) {
                mHolder.index.setText("" + (position+1));
                mHolder.districtName.setText("" + districtModels.get(position).getName());
                mHolder.districtTotalConfirmed.setText("" + districtModels.get(position).getConfirmed());
                mHolder.districtTotalDeath.setText("" + districtModels.get(position).getDeaths());
            }
        }
    }

    @Override
    public int getItemCount() {
        return districtModels.size();
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
