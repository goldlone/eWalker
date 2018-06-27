package cn.goldlone.car.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.goldlone.car.Configs;
import cn.goldlone.car.adapter.ContactAdapter;
import cn.goldlone.car.MyApplication;
import cn.goldlone.car.R;
import cn.goldlone.car.model.HelpContact;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/26 23:59
 */
public class ContactActivity extends BaseActivity implements View.OnClickListener {

    private ListView lv_contact;
    private ContactAdapter contactAdapter;

    private FloatingActionButton fb_contact_add = null;

    // 设置紧急联系人对话框
    private AlertDialog contactDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initToolBar();
    }

    private void initView() {
        setContentView(R.layout.activity_contact);
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        List<HelpContact> list = new ArrayList<>(Configs.contacts);
        contactAdapter = new ContactAdapter(this, lv_contact, list);
        lv_contact.setAdapter(contactAdapter);

        fb_contact_add = (FloatingActionButton) findViewById(R.id.fb_contact_add);
        fb_contact_add.setOnClickListener(this);
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_contact_add:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("设置紧急联系人")
                        .setView(R.layout.dialog_contact)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText contactNameEt = (EditText) contactDialog.findViewById(R.id.tv_dialog_contact_name);
                                EditText contactPhoneEt = (EditText) contactDialog.findViewById(R.id.tv_dialog_contact_phone);
//                                contactNameEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                                String name = contactNameEt.getText().toString();
                                String phone = contactPhoneEt.getText().toString();
                                HelpContact contact = new HelpContact(name, phone);
                                if(!"".equals(phone) && !"".equals(name)) {
                                    if(Configs.contacts.contains(contact)) {
                                        Toast.makeText(ContactActivity.this, "该紧急联系人已存在", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Configs.contacts.add(contact);
                                    contactAdapter.setItems(new ArrayList<HelpContact>(Configs.contacts));
                                    contactAdapter.notifyDataSetChanged();
                                    MyApplication.setContacts(Configs.contacts);
//                                    Toast.makeText(ContactActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ContactActivity.this, "请输入有效的信息", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                contactDialog = builder3.show();
                break;
        }
    }
}
