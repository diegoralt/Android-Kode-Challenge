package com.challenge.diego.kode_challenge.views.search_device;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;

import com.challenge.diego.kode_challenge.model.Device;
import com.challenge.diego.kode_challenge.session.Session;
import com.challenge.diego.kode_challenge.utils.PrintConsole;
import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapterView;

import java.util.ArrayList;

/**
 * Created by diego on 10/23/18.
 */

public class SearchDevicePresenter implements SearchDeviceContract.Presenter {
    private SearchDeviceView view;
    private Session session = Session.getInstance();

    private BluetoothAdapter mBTAdapter;
    static int REQUEST_BLUETOOTH = 1;
    private ArrayList<Device> listDevice;

    SearchDevicePresenter(SearchDeviceView view) {
        this.view = view;
        session.setCurrentWindow(1);
    }

    @Override
    public void checkStateBluetooth() {
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBTAdapter == null)
            view.infoNotSupportBluetooth();
        else if (!mBTAdapter.isEnabled()) {
            turnOnBluetooth();
        } else {
            clickActualizar();
        }
    }

    @Override
    public void clickActualizar() {
        new ActivateScanAsync().execute();
    }

    @Override
    public void clickVerMas() {

    }

    @Override
    public void turnOnBluetooth() {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        view.startActivityForResult(i, REQUEST_BLUETOOTH);
    }

    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getName() != null) {
                    //Obtener intensidad
                    int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                    PrintConsole.d("NAME DEVICE", device.getName());
                    //Crea nuevo dispositivo
                    Device mDevice = new Device(device.getName(), String.valueOf(rssi));
                    listDevice.add(mDevice);
                }
            }
        }
    };

    class ActivateScanAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            view.showScanDevice();
            listDevice = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                view.registerReceiver(bReceiver, filter);
                mBTAdapter.startDiscovery();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                PrintConsole.e("InterruptedException", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            view.unregisterReceiver(bReceiver);
            mBTAdapter.cancelDiscovery();
            if (listDevice.isEmpty())
                view.showNotFound();
            else {
                DeviceAdapterView adapter = new DeviceAdapterView(listDevice);
                view.showDevides(adapter);
            }
        }
    }
}
