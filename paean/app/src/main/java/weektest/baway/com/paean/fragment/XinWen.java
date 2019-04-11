package weektest.baway.com.paean.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import weektest.baway.com.paean.HttpUtil;
import weektest.baway.com.paean.R;
import weektest.baway.com.paean.base.BaseFragment;

public class XinWen extends BaseFragment {

    private ImageView imageView_xinwen;
    String imageUrl="http://img5q.duitang.com/uploads/item/201506/03/20150603215620_xTSuc.jpeg";

    @Override
    protected int bindLayout() {
        return R.layout.xinwen;
    }

    @Override
    protected void initView() {
        imageView_xinwen = bindView(R.id.imageView_xinwen);
    }

    @Override
    protected void initData() {
new AsyncTask<String,Void,Bitmap>(){

    @Override
    protected Bitmap doInBackground(String... strings) {
        return HttpUtil.WhatImg(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView_xinwen.setImageBitmap(bitmap);
    }
}.execute(imageUrl);
    }

    @Override
    protected void bindEvent() {

    }
}
