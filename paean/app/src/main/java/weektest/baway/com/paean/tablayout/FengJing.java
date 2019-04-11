package weektest.baway.com.paean.tablayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import weektest.baway.com.paean.HttpUtil;
import weektest.baway.com.paean.R;
import weektest.baway.com.paean.TwoActivirt;
import weektest.baway.com.paean.adapter.PullAdapter;
import weektest.baway.com.paean.base.BaseFragment;
import weektest.baway.com.paean.bean.BannerBean;
import weektest.baway.com.paean.bean.PullBean;
import weektest.baway.com.paean.bean.UrlBeaNn;

public class FengJing extends BaseFragment {

    private Banner banner;
    private PullToRefreshListView pull;
    private PullAdapter pullAdapter;
    private List<PullBean.DataBean> data;
    String Url = "http://47.94.132.125:8080/zixunapi/fengjing";
    private List<UrlBeaNn.DataBean.NewsBean> news;

    @Override
    protected int bindLayout() {
        return R.layout.fengjing;
    }

    @Override
    protected void initView() {
        // banner = bindView(R.id.banner);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.head, null);
        banner = view.findViewById(R.id.banner);
        pull = bindView(R.id.pull);
        pull.setMode(PullToRefreshListView.Mode.BOTH);
        pull.setScrollingWhileRefreshingEnabled(true);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 400);
        banner.setLayoutParams(layoutParams);
        ListView listView = pull.getRefreshableView();
        listView.addHeaderView(banner);

    }

    @Override
    protected void initData() {
        //轮播图解析并展示
        HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                UrlBeaNn urlBeaNn = gson.fromJson(s, UrlBeaNn.class);
                List<UrlBeaNn.DataBean.NewsBean> news = urlBeaNn.getData().getNews();
                banner.setImages(news);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        UrlBeaNn.DataBean.NewsBean bean1 = (UrlBeaNn.DataBean.NewsBean) path;
                        Glide.with(getActivity()).load("http://47.94.132.125:8080/zixunapi/" + bean1.getImageUrl()).into(imageView);

                    }
                });
                banner.isAutoPlay(true);
                banner.setDelayTime(3000);
                banner.start();
            }
        });
        //pull解析 展示数据
        HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                UrlBeaNn urlBeaNn = gson.fromJson(s, UrlBeaNn.class);
                news = urlBeaNn.getData().getNews();
                pullAdapter = new PullAdapter(news, getActivity());
                pull.setAdapter(pullAdapter);
            }
        });
        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TwoActivirt.class);
                intent.putExtra("url",news.get(position).getUrl());
                startActivity(intent);
            }
        });
        //上拉下拉
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            //上拉刷新
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        UrlBeaNn urlBeaNn = gson.fromJson(s, UrlBeaNn.class);
                        news = urlBeaNn.getData().getNews();
                        pullAdapter = new PullAdapter(news, getActivity());
                        pull.setAdapter(pullAdapter);
                        pull.onRefreshComplete();
                    }
                });
            }

            //下拉加载更多数据
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpUtil.httpAsyncTask(Url, new HttpUtil.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        UrlBeaNn urlBeaNn1 = gson.fromJson(s, UrlBeaNn.class);
                        List<UrlBeaNn.DataBean.NewsBean> news1 = urlBeaNn1.getData().getNews();
                        news.addAll(news1);
                        pullAdapter.notifyDataSetChanged();
                        pull.onRefreshComplete();

                    }
                });
            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
