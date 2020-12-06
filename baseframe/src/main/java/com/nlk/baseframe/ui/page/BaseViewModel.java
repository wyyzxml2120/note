package com.nlk.baseframe.ui.page;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    public final ObservableBoolean isShowBottom = new ObservableBoolean();

}
