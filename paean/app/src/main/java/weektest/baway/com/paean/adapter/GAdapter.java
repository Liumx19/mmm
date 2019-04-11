package weektest.baway.com.paean.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import weektest.baway.com.paean.R;

public class GAdapter extends BaseAdapter {
    List<String> list;
    Context context;

    public GAdapter(List<String> list, Context context) {
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
            convertView = View.inflate(context, R.layout.item_gdtext, null);
            holder = new ViewHolder();
            holder.gdtext = (TextView) convertView.findViewById(R.id.gdtext);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gdtext.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView gdtext;
    }
}
