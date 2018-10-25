package com.challenge.diego.kode_challenge.views.more_device;

import com.challenge.diego.kode_challenge.model.Device;
import com.challenge.diego.kode_challenge.session.Session;
import com.challenge.diego.kode_challenge.views.item_adapter.DeviceAdapterView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by diego on 10/24/18.
 */

public class MoreDevicePresenter implements MoreDeviceContract.Presenter {
    private MoreDeviceView view;
    private Session session = Session.getInstance();

    MoreDevicePresenter(MoreDeviceView view) {
        this.view = view;
    }

    @Override
    public void getDevice() {
        if (session.getListDevices().isEmpty())
            view.showNotDevice();
        else
            setAdapter();
    }

    @Override
    public void filterDevices(int position) {
        if (position == 1)
            orderFirst();
        else if (position == 2)
            orderLast();
        setAdapter();
    }

    private void orderFirst() {
        Collections.sort(session.getListDevices(), new Comparator<Device>() {
            @Override
            public int compare(Device device1, Device device2) {
                return Long.valueOf(device1.getmTime()).compareTo(device2.getmTime());
            }
        });
    }

    private void orderLast() {
        Collections.sort(session.getListDevices(), new Comparator<Device>() {
            @Override
            public int compare(Device device1, Device device2) {
                return Long.valueOf(device2.getmTime()).compareTo(device1.getmTime());
            }
        });
    }

    private void setAdapter() {
        DeviceAdapterView adapter = new DeviceAdapterView(session.getListDevices());
        view.showDevides(adapter);
    }
}
