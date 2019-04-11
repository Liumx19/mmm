package weektest.baway.com.paean;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import weektest.baway.com.paean.adapter.GAdapter;
import weektest.baway.com.paean.adapter.MAdapter;

public class LogActivity extends AppCompatActivity {

    private TextView text_fanhui;
    private TextView text_wancheng;
    private GridView grid_my;
    private GridView grid_gd;
    List<String> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    private MAdapter mAdapter;
    private GAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //找控件
        text_fanhui = findViewById(R.id.text_fanhui);
        text_wancheng = findViewById(R.id.text_wancheng);
        grid_my = findViewById(R.id.grid_my);
        grid_gd = findViewById(R.id.grid_gd);
        mAdapter = new MAdapter(list, LogActivity.this);
        grid_my.setAdapter(mAdapter);
        gAdapter = new GAdapter(strings,LogActivity.this);
        grid_gd.setAdapter(gAdapter);
        text_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        text_wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        list.add("风景");
        list.add("美女");
        list.add("动漫");
        list.add("卡通");
        list.add("娱乐");
        list.add("明星");
        strings.add("萌宠");
        strings.add("汽车");
        strings.add("游戏");
        strings.add("可乐");
        strings.add("雪碧");
        strings.add("柠檬茶");
        grid_my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strings.add(list.get(position));
                list.remove(position);
                mAdapter.notifyDataSetChanged();
                gAdapter.notifyDataSetChanged();
            }
        });
        grid_gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.add(strings.get(position));
                strings.remove(position);
                mAdapter.notifyDataSetChanged();
                gAdapter.notifyDataSetChanged();
            }
        });
    }
}
