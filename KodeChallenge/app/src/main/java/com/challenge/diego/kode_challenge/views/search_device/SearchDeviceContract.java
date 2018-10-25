package com.challenge.diego.kode_challenge.views.search_device;

import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapterView;

/**
 * Created by diego on 10/23/18.
 */

public interface SearchDeviceContract {
    interface View {
        void infoNotSupportBluetooth();
        void showScanDevice();
        void showDevides(DeviceAdapterView mAdapter);
        void showNotFound();
    }

    interface Presenter {
        void checkStateBluetooth();
        void clickActualizar();
        void clickVerMas();
        void turnOnBluetooth();
    }
}
