package com.challenge.diego.kode_challenge.views.item_adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.model.Device;
import com.challenge.diego.kode_challenge.services.Server;
import com.challenge.diego.kode_challenge.session.Session;
import com.challenge.diego.kode_challenge.views.BaseActivity;

import java.util.Hashtable;

/**
 * Created by diego on 10/24/18.
 */

public class DeviceAdapterPresenter implements DeviceAdapterContract.Presenter {
    private DeviceAdapterView view;
    private Session session = Session.getInstance();

    DeviceAdapterPresenter(DeviceAdapterView view) {
        this.view = view;
    }

    @Override
    public void checkFlow(TextView txt_date, Button btn_save) {
        if (session.getCurrentWindow() == 1)
            view.isShowViews(false, txt_date, btn_save);
        else
            view.isShowViews(true, txt_date, btn_save);
    }

    @Override
    public void clickSave(Context context, Device device) {
        Hashtable<String, String> params = new Hashtable<>();
        params.put(Constants.KEY_PARAM_NAME, device.getmName());
        params.put(Constants.KEY_PARAM_STRENGTH, device.getmStrength());
        Server.getInstance((BaseActivity) context).doNetworkOperation(Constants.OPERATION.saveDevice, params);
    }
}
