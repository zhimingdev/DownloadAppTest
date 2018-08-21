package com.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private List<Bean> list;
    private int[] icons={R.drawable.ic_share_qq,R.drawable.ic_share_qzone,R.drawable.ic_share_wechat,R.drawable.ic_share_wechat_moments,R.drawable.ic_share_qq};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //List效果展示
        initViewOper();
        //瀑布流展示
//        initViewStaggered();
    }

    //初始化控件
    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
    }

    //初始化数据
    private void initData() {
        //创建集合
        list = new ArrayList<>();
        //随机数
        Random random = new Random();
        //填充数据
        for (int i = 0; i < icons.length ; i++) {
            Bean bean = new Bean();

            bean.pic = icons[i];
            bean.type = random.nextInt(3);
            list.add(bean);
        }
    }
    //展示效果
    private void initViewOper() {
        //布局管理器
        LinearLayoutManager lineatlayoutmanager = new LinearLayoutManager(this);
        lineatlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(lineatlayoutmanager);
        //创建适配器
        Recycler_adapter adapter = new Recycler_adapter(list);
        //设置适配器
        recycler_view.setAdapter(adapter);


    }
    private void initViewStaggered(){
        //布局管理器
        StaggeredGridLayoutManager staggerd = new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(staggerd);
        //创建适配器
        Recycler_adapter adapter = new Recycler_adapter(list);
        //设置适配器
        recycler_view.setAdapter(adapter);
    }


    public class Recycler_adapter extends RecyclerView.Adapter{
        //定义三种常量  表示三种条目类型
        public static final int TYPE_PULL_IMAGE = 0;
        public static final int TYPE_RIGHT_IMAGE = 1;
        public static final int TYPE_THREE_IMAGE = 2;

        private List<Bean> list;

        public Recycler_adapter(List<Bean> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view ;
            if(i == TYPE_PULL_IMAGE){
                view = View.inflate(viewGroup.getContext(),R.layout.item_pull_img,null);
                return new PullimageHolder(view);
            }else if(i == TYPE_RIGHT_IMAGE){
                view = View.inflate(viewGroup.getContext(),R.layout.item_right_img,null);
                return new RightimageHolder(view);
            }else{
                view = View.inflate(viewGroup.getContext(),R.layout.item_three_img,null);
                return new ThreeimageHolder(view);
            }


        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType = viewHolder.getItemViewType();
            switch (itemViewType) {
                case TYPE_PULL_IMAGE:
                    ImageView viewById = (ImageView) viewHolder.itemView.findViewById(R.id.iv_pull);
                    Glide.with(getApplicationContext()).load(icons[i]).into(viewById);
                    break;
                case TYPE_RIGHT_IMAGE:
                    ImageView view1 = (ImageView) viewHolder.itemView.findViewById(R.id.iv_right);
                    Glide.with(getApplicationContext()).load(icons[i]).into(view1);
                    break;
                case TYPE_THREE_IMAGE:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            if(list != null && list.size() > 0){
                return list.size();
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            Bean bean = list.get(position);
            if(bean.type==0){
                return TYPE_PULL_IMAGE;
            }else if(bean.type == 1){
                return TYPE_RIGHT_IMAGE;
            }else{
                return  TYPE_THREE_IMAGE;
            }
        }

        private class PullimageHolder extends RecyclerView.ViewHolder{

            public PullimageHolder(View itemView) {
                super(itemView);
            }
        }
        private class RightimageHolder extends  RecyclerView.ViewHolder{

            public RightimageHolder(View itemView) {
                super(itemView);
            }
        }
        private class ThreeimageHolder extends  RecyclerView.ViewHolder{

            public ThreeimageHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
