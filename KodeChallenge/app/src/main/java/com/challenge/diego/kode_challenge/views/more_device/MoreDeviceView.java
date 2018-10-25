package com.challenge.diego.kode_challenge.views.more_device;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.challenge.diego.kode_challenge.R;
import com.challenge.diego.kode_challenge.views.BaseActivity;
import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapterView;

/**
 * Created by diego on 10/24/18.
 */

public class MoreDeviceView extends BaseActivity implements MoreDeviceContract.View {
    private MoreDevicePresenter presenter = new MoreDevicePresenter(this);

    private RecyclerView recycler_device;
    private TextView txt_not_found;
    private Spinner spinner_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_serch_activity);
        setCurrentActivity(this);

        linkUI();
        setListener();
        presenter.getDevice();
    }

    private void linkUI() {
        Button btn_actualizar = findViewById(R.id.btn_action_actualizar);
        btn_actualizar.setVisibility(View.GONE);
        LinearLayout linear_bottom = findViewById(R.id.linear_content_bottom);
        linear_bottom.setVisibility(View.GONE);
        recycler_device = findViewById(R.id.list_device_search);
        txt_not_found = findViewById(R.id.txt_not_founf);
        spinner_filter = findViewById(R.id.spinner_filter);
        spinner_filter.setVisibility(View.VISIBLE);
    }

    private void setListener() {
        spinner_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.filterDevices(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showDevides(DeviceAdapterView mAdapter) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_device.setLayoutManager(llm);
        recycler_device.setAdapter(mAdapter);
    }

    @Override
    public void showNotDevice() {
        recycler_device.setVisibility(View.GONE);
        txt_not_found.setVisibility(View.VISIBLE);
        txt_not_found.setText("No tienes dispositivos almacenados");
    }
}
