package com.challenge.diego.kode_challenge.views.item_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.challenge.diego.kode_challenge.R;
import com.challenge.diego.kode_challenge.model.Device;

import java.util.ArrayList;

/**
 * Created by diego on 10/23/18.
 */

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DevicesViewHolder> {
    private ArrayList<Device> list;
    private Context context;

    @Override
    public DevicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);
        context = parent.getContext();
        return new DevicesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position) {
        Device device = list.get(position);

        holder.txt_name.setText(device.getmName());
        String strength = "Intensidad: " + device.getmStrength();
        holder.txt_strength.setText(strength);

        if (!TextUtils.isEmpty(device.getmDateCreation())) {
            holder.txt_date.setVisibility(View.VISIBLE);
            String date = "Se dio de alta el " + device.getmDateCreation();
            holder.txt_date.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DevicesViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name, txt_strength, txt_date;
        private Button btn_save;

        DevicesViewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_label_name);
            txt_strength = itemView.findViewById(R.id.txt_label_strength);
            txt_date = itemView.findViewById(R.id.txt_label_date);

            btn_save = itemView.findViewById(R.id.btn_save);
        }
    }

    public DeviceAdapter(ArrayList<Device> list) {
        this.list = list;
    }
}
