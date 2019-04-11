package weektest.baway.com.paean;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import weektest.baway.com.paean.base.BaseActivity;

public class Pull2Activity extends BaseActivity implements View.OnClickListener {

    private Button fanhui_pull2;
    private WebView web_pull2;
    private String urls;

    @Override
    public int bindLayout() {
        return R.layout.activity_pull2;
    }

    @Override
    protected void initView() {
        fanhui_pull2 = bindView(R.id.fanhui_pull2);
        web_pull2 = bindView(R.id.web_pull2);
    }

    @Override
    protected void initData() {
        urls = getIntent().getStringExtra("url");
        web_pull2.loadUrl(urls);
        fanhui_pull2.setOnClickListener(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
