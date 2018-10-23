package com.challenge.diego.kode_challenge.views.search_device;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.diego.kode_challenge.R;
import com.challenge.diego.kode_challenge.views.BaseActivity;
import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapter;

/**
 * Created by diego on 10/23/18.
 */

public class SearchDeviceView extends BaseActivity implements SearchDeviceContract.View {
    private SearchDevicePresenter presenter = new SearchDevicePresenter(this);

    private Button btn_actualizar, btn_more;
    private RecyclerView recycler_device;
    private ProgressBar progress;
    private TextView txt_not_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_serch_activity);

        linkUI();
        setListener();
        presenter.checkStateBluetooth();
    }

    private void linkUI() {
        btn_actualizar = findViewById(R.id.btn_action_actualizar);
        btn_more = findViewById(R.id.btn_mas);
        recycler_device = findViewById(R.id.list_device_search);
        progress = findViewById(R.id.progress_search);
        txt_not_found = findViewById(R.id.txt_not_founf);
    }

    private void setListener() {
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clickActualizar();
            }
        });

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clickVerMas();
            }
        });
    }

    @Override
    public void infoNotSupportBluetooth() {
        new AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Tu dispositivo no soporta Bluetooth")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();
    }

    @Override
    public void showScanDevice() {
        btn_actualizar.setText("Buscando...");
        btn_actualizar.setEnabled(false);
        btn_more.setEnabled(false);
        progress.setVisibility(View.VISIBLE);
        recycler_device.setVisibility(View.GONE);
        txt_not_found.setVisibility(View.GONE);
    }

    @Override
    public void showDevides(DeviceAdapter mAdapter) {
        activateBtn();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_device.setLayoutManager(llm);
        recycler_device.setVisibility(View.VISIBLE);
        recycler_device.setAdapter(mAdapter);
    }

    @Override
    public void showNotFound() {
        activateBtn();
        txt_not_found.setVisibility(View.VISIBLE);
    }

    private void activateBtn() {
        btn_actualizar.setText("Actualizar");
        btn_actualizar.setEnabled(true);
        btn_more.setEnabled(true);
        progress.setVisibility(View.GONE);
    }

    private void showNotActivateBluetooth(){
        new AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Es necesario que actives tu bluetooth")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.clickActualizar();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == presenter.REQUEST_BLUETOOTH && resultCode == RESULT_OK)
            presenter.clickActualizar();
        else
            showNotActivateBluetooth();
    }
}
