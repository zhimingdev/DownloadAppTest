package com.test.vlayouttest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        mRecylerView = (RecyclerView) findViewById(R.id.vlayout_recyclerview);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mRecylerView.setLayoutManager(virtualLayoutManager);

        //设置缓存view的个数
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecylerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        //初始化DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager,false);
        delegateAdapter.addAdapter(new MyAdapter(this,new StickyLayoutHelper(true),1));//添加吸顶效果
        delegateAdapter.addAdapter(new MyAdapter(this,new SingleLayoutHelper(),1));
        delegateAdapter.addAdapter(new MyAdapter(this,new LinearLayoutHelper(),20));
        delegateAdapter.addAdapter(new MyAdapter(this,new GridLayoutHelper(4),50));
        mRecylerView.setAdapter(delegateAdapter);

    }


    public class MyAdapter extends DelegateAdapter.Adapter<MyViewholder> {

        private Context context;
        private LayoutHelper mHelper;
        private int count = 0;

        public MyAdapter(Context context, LayoutHelper helper, int count) {
            this.context = context;
            mHelper = helper;
            this.count = count;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mHelper;
        }

        @Override
        public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHelper instanceof SingleLayoutHelper) {
                return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.image_item,parent,false));
            }else {
                return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final MyViewholder holder, final int position) {
            final View itemView = holder.itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = holder.text.getText().toString();
                    Toast.makeText(context,"点击的是: "+position+" 文本:"+s,Toast.LENGTH_SHORT).show();
                }
            });
            if (mHelper instanceof SingleLayoutHelper) {
                ((MyViewholder) holder).mImageView.setBackgroundResource(R.drawable.banner1);
            }else {
                ((MyViewholder) holder).text.setText(position + 1 + "");
            }

        }

        @Override
        public int getItemCount() {
            return count;
        }
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private TextView text;
        private ImageView mImageView;

        public MyViewholder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text_item);
            mImageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}
