package com.nlk.note.ui.state;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.data.bean.GlobalDataBean;
import com.nlk.note.data.bean.ViewMatterBean;

public class MainViewModel extends BaseViewModel {
    public MutableLiveData<GlobalDataBean> globalData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {

        GlobalDataBean globalDataBean = new GlobalDataBean();
        globalDataBean.setShowBottom(true);

        globalData.postValue(globalDataBean);
    }

}
