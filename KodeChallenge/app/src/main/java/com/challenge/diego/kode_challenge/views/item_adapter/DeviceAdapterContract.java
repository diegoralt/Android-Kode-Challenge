package com.challenge.diego.kode_challenge.views.item_adapter;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by diego on 10/24/18.
 */

public interface DeviceAdapterContract {
    interface View {
        void isShowViews(boolean isShowDate, TextView txt_date, Button btn_save);
    }

    interface Presenter {
        void checkFlow(TextView txt_date, Button btn_save);
        void clickSave();
    }
}
