package com.test.mvptest.persenter;

import com.test.mvptest.model.data.MyDepositModel;
import com.test.mvptest.model.data.listener.OnLoadListener;
import com.test.mvptest.model.data.viewmodel.DataModeIMPL;
import com.test.mvptest.view.IMainView;

public class MainPersenter implements IMainPersenter,OnLoadListener {
    protected DataModeIMPL mDataModeIMPL;
    protected IMainView mIMainView;

    public MainPersenter(IMainView iMainView) {
        mIMainView = iMainView;
        mDataModeIMPL = new DataModeIMPL(this);
    }

    @Override
    public void onLoadSuccessListener(MyDepositModel model) {
        String auth = model.isAuth;
        if ("0".equals(auth)) {
           mIMainView.upData(model);
        }else {
            mIMainView.upData(model);
        }
    }

    @Override
    public void requestMoth() {
        mDataModeIMPL.getDepositInfo();
    }
}
