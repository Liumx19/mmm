package weektest.baway.com.paean;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import weektest.baway.com.paean.base.BaseActivity;
import weektest.baway.com.paean.bean.TitleBean;
import weektest.baway.com.paean.fragment.FaXian;
import weektest.baway.com.paean.fragment.ShouYe;
import weektest.baway.com.paean.fragment.WoDe;
import weektest.baway.com.paean.fragment.XinWen;

public class MainActivity extends BaseActivity {

    private FrameLayout frag;
    private RadioGroup radioG;
    private ShouYe shouYe;
    private WoDe woDe;
    private FragmentManager manager;
    private XinWen xinWen;
    private FaXian faXian;
    private ImageView cehua_image;
    private Button cehua_shouye;
    private DrawerLayout draw;
    private Button cehua_xinwen;
    private Button cehua_faxian;
    private Button cehua_wode;
    private ImageView title_image;
    private LinearLayout cehua;
    String titleUrl = "http://47.94.132.125:8080/zixunapi/categories";
    private TextView title_text;
    private RadioButton shouye;
    private RadioButton xinwen;
    private RadioButton faxian;
    private RadioButton wode;
    private TextView cehua_title;
    String file = Environment.getExternalStorageDirectory() + "/t.jpg";


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        frag = bindView(R.id.frag);
        radioG = bindView(R.id.radioG);
        shouye = bindView(R.id.shouye);
        xinwen = bindView(R.id.xinwen);
        faxian = bindView(R.id.faxian);
        wode = bindView(R.id.wode);
        cehua_image = bindView(R.id.cehua_image);
        cehua_shouye = bindView(R.id.cehua_shouye);
        cehua_xinwen = bindView(R.id.cehua_xinwen);
        cehua_faxian = bindView(R.id.cehua_faxian);
        cehua_wode = bindView(R.id.cehua_wode);
        draw = bindView(R.id.draw);
        title_image = bindView(R.id.title_image);
        cehua = bindView(R.id.cehua);
        title_text = bindView(R.id.title_text);
        cehua_title = bindView(R.id.cehua_title);

    }

    @Override
    protected void initData() {
        cehua_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] item = {"拍照", "相册"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("工具选择")
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(file)));
                                        startActivityForResult(intent, 100);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent(Intent.ACTION_PICK);
                                        intent1.setType("image/*");
                                        startActivityForResult(intent1, 200);
                                        break;
                                }

                            }
                        }).show();
            }
        });

        //Gson解析 给各个控件赋值
        HttpUtil.httpAsyncTask(titleUrl, new HttpUtil.CallBackString() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                TitleBean bean = gson.fromJson(s, TitleBean.class);
                List<TitleBean.ResultBean> result = bean.getResult();
                shouye.setText(result.get(0).getTitle());
                xinwen.setText(result.get(1).getTitle());
                faxian.setText(result.get(2).getTitle());
                wode.setText(result.get(3).getTitle());
                cehua_shouye.setText(result.get(0).getTitle());
                cehua_xinwen.setText(result.get(1).getTitle());
                cehua_faxian.setText(result.get(2).getTitle());
                cehua_wode.setText(result.get(3).getTitle());
                title_text.setText(bean.getMainTitle());
                cehua_title.setText(bean.getDrawerTitle());
            }
        });
        shouYe = new ShouYe();
        xinWen = new XinWen();
        faXian = new FaXian();
        woDe = new WoDe();
        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frag, shouYe)
                .add(R.id.frag, xinWen)
                .add(R.id.frag, faXian)
                .add(R.id.frag, woDe)
                .show(shouYe)
                .hide(xinWen)
                .hide(faXian)
                .hide(woDe)
                .commit();
        //导航
        radioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.shouye:
                        transaction1.show(shouYe)
                                .hide(xinWen)
                                .hide(faXian)
                                .hide(woDe);
                        break;
                    case R.id.xinwen:
                        transaction1.show(xinWen)
                                .hide(shouYe)
                                .hide(faXian)
                                .hide(woDe);
                        break;
                    case R.id.faxian:
                        transaction1.show(faXian)
                                .hide(xinWen)
                                .hide(shouYe)
                                .hide(woDe);
                        break;
                    case R.id.wode:
                        transaction1.show(woDe)
                                .hide(xinWen)
                                .hide(faXian)
                                .hide(shouYe);
                        break;
                }
                transaction1.commit();
            }
        });
        //侧滑内图片改为圆形
        Glide.with(this)
                .load(R.drawable.pink1)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(cehua_image);
        //标题内图片改为圆形
        Glide.with(this)
                .load(R.drawable.pink1)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(title_image);
        //点击标题中的图片可以打开侧滑
        title_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.openDrawer(Gravity.LEFT);
            }
        });
        //点击侧滑内按钮就会跳转到相对fragment中，并且同时关闭侧滑
        cehua_shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到相对fragment页面
                radioG.check(R.id.shouye);
                //关闭侧滑页面
                draw.closeDrawer(Gravity.LEFT);
            }
        });
        cehua_xinwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到相对fragment页面
                radioG.check(R.id.xinwen);
                //关闭侧滑页面
                draw.closeDrawer(Gravity.LEFT);
            }
        });
        cehua_faxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到相对fragment页面
                radioG.check(R.id.faxian);
                //关闭侧滑页面
                draw.closeDrawer(Gravity.LEFT);
            }
        });
        cehua_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到相对fragment页面
                radioG.check(R.id.wode);

                //关闭侧滑页面
                draw.closeDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = Uri.fromFile(new File(file));
            Crop(uri);
        } else if (requestCode == 200) {
            Uri uri = data.getData();
            Crop(uri);
        } else if (requestCode == 300) {
            Bitmap bitmap = data.getParcelableExtra("data");
            Glide.with(MainActivity.this).load(bitmap).apply(RequestOptions.circleCropTransform()).into(cehua_image);
            Glide.with(MainActivity.this).load(bitmap).apply(RequestOptions.circleCropTransform()).into(title_image);

        }
    }

    public void Crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("ascpectX", 300);
        intent.putExtra("ascpectY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 300);
    }


    @Override
    protected void bindEvent() {

    }
}
