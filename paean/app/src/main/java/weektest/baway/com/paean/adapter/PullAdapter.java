package weektest.baway.com.paean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import weektest.baway.com.paean.R;
import weektest.baway.com.paean.bean.PullBean;
import weektest.baway.com.paean.bean.UrlBeaNn;

public class PullAdapter extends BaseAdapter {
    List<UrlBeaNn.DataBean.NewsBean> list;
    Context context;

    public PullAdapter(List<UrlBeaNn.DataBean.NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_pull, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.pull_image);
            holder.title = (TextView) convertView.findViewById(R.id.pull_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UrlBeaNn.DataBean.NewsBean bean = list.get(position);
        holder.title.setText(bean.getTitle());
        Glide.with(context).load("http://47.94.132.125:8080/zixunapi/"+bean.getImageUrl()).into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView title, url;
    }
}
