package com.challenge.diego.kode_challenge.views.more_device;

import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapterView;

/**
 * Created by diego on 10/24/18.
 */

public interface MoreDeviceContract {
    interface View {
        void showDevides(DeviceAdapterView mAdapter);
        void showNotDevice();
    }

    interface Presenter {
        void getDevice();
        void filterDevices(int position);
    }
}
