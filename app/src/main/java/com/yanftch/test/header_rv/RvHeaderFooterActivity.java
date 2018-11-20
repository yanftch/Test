package com.yanftch.test.header_rv;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yanftch.test.R;
import com.yanftch.test.bigkoo.convenientbanner.ConvenientBanner;
import com.yanftch.test.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.yanftch.test.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RvHeaderFooterActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnItemClickListener {
    private static final String TAG = "dah_RvHeaderFooterActivity";
    private RecyclerView recyclerview;
    private MyAdapter adapter;
    private List<Bean> data;
    private View headView;
    private ConvenientBanner mConvenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_header_footer);
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new Bean("name" + i, "2019-09-0" + i));
        }
        initRecyc();
        initListener();
    }

    private void initListener() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int top = headView.getTop();
                Log.e(TAG, "onScrolled: " + top);
            }
        });
    }

    private void initRecyc() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new MyItemDecoration2());
        initHeadView();

        adapter = new MyAdapter(data, this);
        recyclerview.setAdapter(adapter);
        adapter.addHeaderView(headView);
//        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer_layout, null));
        adapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RvHeaderFooterActivity.this, "" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHeadView() {
        headView = LayoutInflater.from(RvHeaderFooterActivity.this).inflate(R.layout.item_header_layout, null, false);
        mConvenientBanner = (ConvenientBanner) headView.findViewById(R.id.convenientBanner);
        headView.findViewById(R.id.tv_head1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RvHeaderFooterActivity.this, "我是Head1，TextView", Toast.LENGTH_SHORT).show();
            }
        });
        headView.findViewById(R.id.ic_clidk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RvHeaderFooterActivity.this, "点点点嗲", Toast.LENGTH_SHORT).show();
            }
        });
        headView.findViewById(R.id.tttttttttt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RvHeaderFooterActivity.this, "点击切换数据", Toast.LENGTH_SHORT).show();
                data.clear();
                for (int i = 0; i < 10; i++) {
                    data.add(new Bean("NEW" + i, "TIME=" + i));
                }
                adapter.notifyDataSetChanged();
            }
        });
        init();
    }

    private void init() {
        initImageLoader();
        loadTestDatas();
        //本地图片例子
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /*
加入测试Views
* */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        mConvenientBanner.startTurning(500);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        mConvenientBanner.stopTurning();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }
}
