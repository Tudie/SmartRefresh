package tudie.com.smartrefreshlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.adapter.adapterrecyclerview.CommonAdapter;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.base.ViewHolder;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.wrapper.EmptyWrapper;
import com.scwang.smartrefresh.adapter.adapterrecyclerview.wrapper.HeaderAndFooterWrapper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<String> data = new ArrayList<>();
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmartRefreshLayout refreshLayout = (SmartRefreshLayout) findViewById(R.id.smartLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);

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
        for (int i = 0; i < 5; i++) {
            data.add(i + "Data");
        }

    }
}
