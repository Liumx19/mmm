package weektest.baway.com.paean;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import weektest.baway.com.paean.base.BaseActivity;

public class TwoActivirt extends BaseActivity implements View.OnClickListener {

    private Button fanhui_two;
    private WebView web;
    private String urls;

    @Override
    public int bindLayout() {
        return R.layout.activity_two;
    }

    @Override
    protected void initView() {
        fanhui_two = bindView(R.id.fanhui_two);
        web = bindView(R.id.web);
    }

    @Override
    protected void initData() {
        web.getSettings().setJavaScriptEnabled(true);
        urls = getIntent().getStringExtra("url");
        web.loadUrl("http://47.94.132.125:8080/zixunapi/"+urls);
        fanhui_two.setOnClickListener(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
