package weektest.baway.com.paean.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import weektest.baway.com.paean.LogActivity;
import weektest.baway.com.paean.R;
import weektest.baway.com.paean.adapter.FengjingAdapter;
import weektest.baway.com.paean.base.BaseFragment;
import weektest.baway.com.paean.tablayout.FengJing;
import weektest.baway.com.paean.tablayout.MeiNv;

public class ShouYe extends BaseFragment {

    private TabLayout tab;
    private ViewPager vp;
    private TextView pdgl;
    private ArrayList<Fragment> list;
    private ArrayList<String> listG;

    @Override
    protected int bindLayout() {
        return R.layout.shouye;
    }

    @Override
    protected void initView() {
        tab = bindView(R.id.tab);
        vp = bindView(R.id.vp);
        pdgl = bindView(R.id.pdgl);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new FengJing());
        list.add(new MeiNv());
        String[] str = {"风景", "美女"};
      /*  listG = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listG.add(i);
        }*/
        FengjingAdapter adapter = new FengjingAdapter(getActivity().getSupportFragmentManager(), list, str);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);


    }

    @Override
    protected void bindEvent() {
        pdgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogActivity.class);
                // intent.putStringArrayListExtra("key", listG);
                startActivity(intent);
            }
        });
    }
}
