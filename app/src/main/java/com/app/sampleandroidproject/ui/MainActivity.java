package com.app.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.beans.result.HttpResultCityAndSpace;
import com.app.sampleandroidproject.http.HttpRequest;
import com.app.sampleandroidproject.ui.Enum.ClassEnum;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    private String[] names;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("首页");
        initData();
    }

    @Override
    protected void stopWork() {

    }

    private void initData() {
        AppManagers.getHttpManager().getCity(false, new HttpRequest<HttpResultCityAndSpace>() {

            @Override
            public void onHttpSuccess(HttpResultCityAndSpace httpResultCityAndSpace) {
                Logger.i("httpResultCityAndSpace.getCity().size()--" + httpResultCityAndSpace.getCity().get(0).getName());
            }

            @Override
            public void onHttpError() {

            }
        });
        names = new String[]{
                "DaggerActivity", "MVPActivity", "GreenDaoActivity", "RxDaoActivity", "recycleViewWithDragActivity"
        };

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1, Arrays.asList(names));
        listView.setAdapter(adapter);
        listView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        RxAdapterView.itemClicks(listView).throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .map((Func1) (integer) -> ClassEnum.valueOf((Integer) integer))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aClass -> {
                    if (aClass != null)
                        startActivity(new Intent(MainActivity.this, (Class<?>) aClass));
                });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toastor.showSingletonToast("Main");
    }
}
