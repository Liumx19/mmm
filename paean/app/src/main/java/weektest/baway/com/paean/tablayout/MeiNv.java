package weektest.baway.com.paean.tablayout;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import weektest.baway.com.paean.HttpUtil;
import weektest.baway.com.paean.Pull2Activity;
import weektest.baway.com.paean.R;
import weektest.baway.com.paean.adapter.Pull_mnAdapter;
import weektest.baway.com.paean.base.BaseFragment;
import weektest.baway.com.paean.bean.Pull_mnBean;

public class MeiNv extends BaseFragment {


    private PullToRefreshListView pull_mn;
    String Url = "http://www.xieast.com/api/news/news.php?";
    private Pull_mnAdapter pull_mnAdapter;
    private List<Pull_mnBean.DataBean> data;

    @Override
    protected int bindLayout() {
        return R.layout.meinv;
    }

    @Override
    protected void initView() {
        pull_mn = bindView(R.id.pull_mn);
        pull_mn.setMode(PullToRefreshListView.Mode.BOTH);
        pull_mn.setScrollingWhileRefreshingEnabled(true);
    }

    @Override
    protected void initData() {
        pull_mn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Pull2Activity.class);
                intent.putExtra("url",data.get(position-1).getUrl());
                startActivity(intent);
            }
        });
        HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {

            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                Pull_mnBean pull_mnBean = gson.fromJson(s, Pull_mnBean.class);
                data = pull_mnBean.getData();
                pull_mnAdapter = new Pull_mnAdapter(data, getActivity());
                pull_mn.setAdapter(pull_mnAdapter);
            }
        });
        pull_mn.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        Pull_mnBean pull_mnBean = gson.fromJson(s, Pull_mnBean.class);
                        data = pull_mnBean.getData();
                        pull_mnAdapter = new Pull_mnAdapter(data, getActivity());
                        pull_mn.setAdapter(pull_mnAdapter);
                        pull_mn.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        Pull_mnBean pull_mnBean = gson.fromJson(s, Pull_mnBean.class);
                        List<Pull_mnBean.DataBean> data1 = pull_mnBean.getData();
                        data.addAll(data1);
                        pull_mnAdapter.notifyDataSetChanged();
                        pull_mn.onRefreshComplete();
                    }
                });
            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
