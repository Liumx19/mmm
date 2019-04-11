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
import weektest.baway.com.paean.bean.Pull_mnBean;

public class Pull_mnAdapter extends BaseAdapter {
    List<Pull_mnBean.DataBean> list;
    Context context;

    public Pull_mnAdapter(List<Pull_mnBean.DataBean> list, Context context) {
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
       int type=getItemViewType(position);
        switch (type){
            case 0:
                ViewHolder0 holder0;
                if (convertView==null){
                    convertView=View.inflate(context, R.layout.item_pull0,null);
                    holder0=new ViewHolder0();
                    holder0.imageView0=(ImageView)convertView.findViewById(R.id.imageView0);
                    holder0.name0=(TextView)convertView.findViewById(R.id.name_item0);
                    holder0.title0=(TextView)convertView.findViewById(R.id.title_item0);
                    convertView.setTag(holder0);
                }else{
                    holder0=(ViewHolder0)convertView.getTag();
                }
                Pull_mnBean.DataBean bean=list.get(position);
                holder0.name0.setText(bean.getAuthor_name()+"");
                holder0.title0.setText(bean.getTitle()+"");
                Glide.with(context).load(bean.getThumbnail_pic_s()+"").into(holder0.imageView0);
                break;
            case 1:
                ViewHolder1 holder1;
                if (convertView==null){
                    convertView=View.inflate(context, R.layout.item_pull1,null);
                    holder1=new ViewHolder1();
                    holder1.imageView1=(ImageView)convertView.findViewById(R.id.imageView1);
                    holder1.name1=(TextView)convertView.findViewById(R.id.name_item1);
                    holder1.time1=(TextView)convertView.findViewById(R.id.time_item1);
                    convertView.setTag(holder1);
                }else{
                    holder1=(ViewHolder1)convertView.getTag();
                }
                Pull_mnBean.DataBean bean1=list.get(position);
                holder1.name1.setText(bean1.getAuthor_name()+"");
                holder1.time1.setText(bean1.getDate());
                Glide.with(context).load(bean1.getThumbnail_pic_s()+"").into(holder1.imageView1);
                break;
       }
        return convertView;
    }

    class ViewHolder0 {
        ImageView imageView0;
        TextView name0, title0;
    }

    class ViewHolder1 {
        ImageView imageView1;
        TextView name1, time1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }
}
