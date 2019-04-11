package weektest.baway.com.paean.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initData();
        bindEvent();
    }
  //布局
    public abstract int bindLayout();
    //找控件
    protected abstract void initView();
    //代码
    protected abstract void initData();
    protected abstract void bindEvent();
    protected <T extends View>T bindView(int resId){
        return (T)findViewById(resId);
    }
protected void getToast(String text){
    Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
}
}
