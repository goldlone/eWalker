package cn.goldlone.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.goldlone.car.Configs;
import cn.goldlone.car.MyApplication;
import cn.goldlone.car.R;
import cn.goldlone.car.model.HelpContact;

/**
 * @author : Created by CN on 2018/6/27 10:02
 */
public class ContactAdapter extends BaseAdapter {

    private Context context;
    private ListView listView;
    private List<HelpContact> items;
    private LayoutInflater inflater;

    public ContactAdapter(Context context, ListView listView, List<HelpContact> items) {
        this.context = context;
        this.listView = listView;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_contact, null);
        if(view != null) {
            TextView tvName = (TextView) view.findViewById(R.id.name);
            TextView tvPhone = (TextView) view.findViewById(R.id.phone);
            Button btn = (Button) view.findViewById(R.id.btn_contact_delete);
            tvName.setText(items.get(i).getName());
            tvPhone.setText(items.get(i).getPhone());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Configs.contacts.remove(items.get(i));
                    MyApplication.setContacts(Configs.contacts);
                    items.remove(i);
                    Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show();
                    ContactAdapter.this.notifyDataSetChanged();
                }
            });
        }
        return view;
    }

    public List<HelpContact> getItems() {
        return items;
    }

    public void setItems(List<HelpContact> items) {
        this.items = items;
    }
}
