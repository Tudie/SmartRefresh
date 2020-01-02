package tudie.com.smartrefreshlayoutdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.adapter.adapterrecyclerview.CommonAdapter;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.base.ViewHolder;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.wrapper.EmptyWrapper;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.wrapper.HeaderAndFooterWrapper;
import com.scwang.smartrefresh.header.CircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<String> data = new ArrayList<>();
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimaryDark, android.R.color.black);//全局设置主题颜色
                return new CircleHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
      final   SmartRefreshLayout refreshLayout = (SmartRefreshLayout) findViewById(R.id.smartLayout);
        CircleHeader circleHeader=new CircleHeader(this);
        circleHeader.setmBackPaintColor(0x0000ff);
        circleHeader.setmFrontPaintColor(0xff02c293);
        circleHeader.setmOuterPaintColor(0xff0000);
        refreshLayout.setRefreshHeader(circleHeader);
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
//        refreshLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                Log.d("","sssssss"+refreshLayout.getTranslationY());
//            }
//        });
        refreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }

            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

            }
        });

        final RecyclerView pullToRefreshRV = (RecyclerView) findViewById(R.id.id_recyclerview);
        pullToRefreshRV.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        GetList();
        adapter = new CommonAdapter<String>(this, R.layout.item_mode, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.textView, s);
            }
        };

        final EmptyWrapper emptyWrapper = new EmptyWrapper(adapter);
        final HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        final View emptyView = LayoutInflater.from(this).inflate(R.layout.emty_view, null);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));//设置LayoutParams
        emptyWrapper.setEmptyView(emptyView);
        pullToRefreshRV.setAdapter(mHeaderAndFooterWrapper);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {

                data.clear();
                mHeaderAndFooterWrapper.notifyDataSetChanged();
                refreshlayout.finishRefresh();

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                GetList();
                mHeaderAndFooterWrapper.notifyDataSetChanged();
//                pullToRefreshRV.setEmptyView(emptyView);
                refreshlayout.finishLoadmore();
            }
        });

    }

    private void GetList() {
        for (int i = 0; i < 30; i++) {
            data.add(i + "Data");
        }

    }
}
