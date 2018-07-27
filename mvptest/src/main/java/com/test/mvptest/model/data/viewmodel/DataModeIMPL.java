package com.test.mvptest.model.data.viewmodel;

import com.test.mvptest.http.HttpTask;
import com.test.mvptest.model.data.MyDepositModel;
import com.test.mvptest.model.data.listener.OnLoadListener;

public class DataModeIMPL implements DataMode {

    protected OnLoadListener mOnLoadListener;

    public DataModeIMPL(OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;
    }

    @Override
    public void getDepositInfo() {
        new HttpTask() {
            @Override
            public void onRequestSuccess(MyDepositModel model) {
                mOnLoadListener.onLoadSuccessListener(model);
            }
        }.path("http://xxxx/getMydeposit").execute();
    }

}
