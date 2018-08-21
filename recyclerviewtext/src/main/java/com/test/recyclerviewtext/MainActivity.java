package com.test.recyclerviewtext;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<TradeDetailData> mList = new ArrayList<>();
    List<DetailData> mDetailData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Map<Integer,String> keys=new HashMap<>();
    List<DetailData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recy_main);
        mList.clear();
        mDetailData.clear();
        list.clear();
        mDetailData.add(new DetailData("张三","男",20));
        mDetailData.add(new DetailData("李四","男",20));
        mDetailData.add(new DetailData("王朝","男",20));
        mDetailData.add(new DetailData("马汉","男",20));
        mDetailData.add(new DetailData("张龙","男",20));
        mDetailData.add(new DetailData("赵虎","男",20));
        mList.add(new TradeDetailData("2018-06",mDetailData));
        mList.add(new TradeDetailData("2018-07",mDetailData));
        mList.add(new TradeDetailData("2018-08",mDetailData));
        mList.add(new TradeDetailData("2018-08",mDetailData));

        for (int i = 0;i<mList.size();i++) {
            keys.put(list.size(),mList.get(i).data);
            for (int j = 0; j < mList.get(i).mList.size(); j++) {
                list.add(mList.get(i).mList.get(j));
            }
        }

//        System.out.println("数据大小"+ JSON.toJSONString(mList));
//        TradeDetailData tradeDetailData = mList.get(0);
//        int size = tradeDetailData.mList.size();
//        DetailData detailData = tradeDetailData.mList.get(0);
//        String person = detailData.age+detailData.name+detailData.sex;
//        System.out.println("size:"+size+",time"+tradeDetailData.data+",perseon"+person);


        final MyAdapter adapter = new MyAdapter();
        final FloatingItemDecoration floatingItemDecoration=new FloatingItemDecoration(this, Color.BLUE,1,1);
        floatingItemDecoration.setKeys(keys);
        floatingItemDecoration.setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics()));
        mRecyclerView.addItemDecoration(floatingItemDecoration);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(list);
                        adapter.loadMoreComplete();
                    }
                },2000);

            }
        });

    }

    class MyAdapter extends BaseQuickAdapter<DetailData,BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.item,list);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailData item) {
            helper.setText(R.id.tv,item.name+item.age+item.sex);
        }
    }
}
