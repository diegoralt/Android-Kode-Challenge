package com.challenge.diego.kode_challenge.views.item_adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.challenge.diego.kode_challenge.model.Device;

/**
 * Created by diego on 10/24/18.
 */

public interface DeviceAdapterContract {
    interface View {
        void isShowViews(boolean isShowDate, TextView txt_date, Button btn_save);
    }

    interface Presenter {
        void checkFlow(TextView txt_date, Button btn_save);
        void clickSave(Context context, Device device);
    }
}
