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

public class DeviceAdapterView extends RecyclerView.Adapter<DeviceAdapterView.DevicesViewHolder> implements DeviceAdapterContract.View {
    private DeviceAdapterPresenter presenter = new DeviceAdapterPresenter(this);
    private ArrayList<Device> list;
    private Context context;

    @Override
    public DevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);
        context = parent.getContext();
        return new DevicesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position) {
        holder.device = list.get(position);

        holder.txt_name.setText(holder.device.getmName());
        String strength = "Intensidad: " + holder.device.getmStrength();
        holder.txt_strength.setText(strength);
        String date = TextUtils.isEmpty(holder.device.getmDateCreation()) ? "" : "Creado: " + holder.device.getmDateCreation();
        holder.txt_date.setText(date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void isShowViews(boolean isShowDate, TextView txt_date, Button btn_save) {
        if (isShowDate) {
            txt_date.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.GONE);
        } else {
            btn_save.setVisibility(View.VISIBLE);
            txt_date.setVisibility(View.GONE);
        }
    }

    class DevicesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_name, txt_strength, txt_date;
        private Button btn_save;
        private Device device;

        DevicesViewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_label_name);
            txt_strength = itemView.findViewById(R.id.txt_label_strength);
            txt_date = itemView.findViewById(R.id.txt_label_date);

            btn_save = itemView.findViewById(R.id.btn_save);
            btn_save.setOnClickListener(this);
            presenter.checkFlow(txt_date, btn_save);
        }

        @Override
        public void onClick(View view) {
            presenter.clickSave(context, device);
        }
    }

    public DeviceAdapterView(ArrayList<Device> list) {
        this.list = list;
    }
}
