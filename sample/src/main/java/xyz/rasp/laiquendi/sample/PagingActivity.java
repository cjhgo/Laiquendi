package xyz.rasp.laiquendi.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.Singleton;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import xyz.rasp.laiquendi.core.Eru;
import xyz.rasp.laiquendi.sample.components.Header;
import xyz.rasp.laiquendi.sample.components.StateLayout;
import xyz.rasp.laiquendi.sample.helper.PagingHelper;

/**
 * Created by twiceYuan on 2017/3/24.
 * <p>
 * 分页简单实现
 */
public class PagingActivity extends Activity {

    @BindView(R.id.recyclerView) RecyclerView       mRecyclerView;
    @BindView(R.id.refresh)      SwipeRefreshLayout mRefresh;

    public static void start(Context context) {
        context.startActivity(new Intent(context, PagingActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        ButterKnife.bind(this);

        //noinspection ConstantConditions
        Eru.get(Header.class, this, R.id.header).attach(this);

        PagingHelper.<String>create()
                .setAdapter(new CommonAdapter<>(this, ItemHolder.class))
                .setRecyclerView(mRecyclerView)
                .setRefreshLayout(mRefresh)
                .setStateLayout(Eru.get(StateLayout.class, this, R.id.state))
                .setDataSource(this::mockDataSource)
                .setSize(5)
                .init();
    }

    /**
     * 模拟五页数据
     */
    private Observable<List<String>> mockDataSource(int page, int size) {

        String url = "https://bing.ioliu.cn/v1?w=800&d=%s";
        if (page >= 5) return Observable.just(new ArrayList<>());

        return Observable.range(page * size, size)
                .map(number -> String.format(url, number))
                .toList()
                .delay(1, TimeUnit.SECONDS)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressWarnings("WeakerAccess")
    @LayoutId(R.layout.item_sample)
    public static class ItemHolder extends CommonHolder<String> {

        @ViewId(R.id.iv_image) ImageView mImageView;

        @Singleton
        RequestManager mManager;

        @Override
        public void initSingleton() {
            mManager = Glide.with(getItemView().getContext());
        }

        @Override
        public void bindData(String s) {
            mManager.load(s).into(mImageView);
        }
    }
}
