package com.test.recyclerviewtext;

import java.util.List;

public class TradeDetailData {
    public String data;
    public List<DetailData> mList;

    public TradeDetailData(String data, List<DetailData> list) {
        this.data = data;
        mList = list;
    }
}
