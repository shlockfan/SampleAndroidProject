package com.app.sampleandroidproject.ui.Enum;

import com.app.sampleandroidproject.ui.AidlActivity;
import com.app.sampleandroidproject.ui.DaggerActivity;
import com.app.sampleandroidproject.ui.GreenDaoActivity;
import com.app.sampleandroidproject.ui.MVPActivity;
import com.app.sampleandroidproject.ui.RetrofitDownLoadActivity;
import com.app.sampleandroidproject.ui.SVGActivity;
import com.app.sampleandroidproject.ui.multiitem.MultiItemActivity;
import com.app.sampleandroidproject.ui.recycleview.RecycleViewWithDragActivity;
import com.app.sampleandroidproject.ui.rxexample.RxDaoActivity;
import com.app.sampleandroidproject.ui.swiperefresh.SwipeRefreshActivity;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.Enum
 *
 * @Author: xie
 * @Time: 2016/11/24 10:35
 * @Description:
 */


public class ClassEnum {

    public static Class<?> valueOf(int value) {
        switch (value){
            case 0:
                return DaggerActivity.class;
            case 1:
                return MVPActivity.class;
            case 2:
                return GreenDaoActivity.class;
            case 3:
                return RxDaoActivity.class;
            case 4:
                return RecycleViewWithDragActivity.class;
            case 5:
                return AidlActivity.class;
            case 6:
                return SVGActivity.class;
            case 7:
                return MultiItemActivity.class;
            case 8:
                return SwipeRefreshActivity.class;
            case 9:
                return RetrofitDownLoadActivity.class;
            default:
                return null;
        }
    }

}
