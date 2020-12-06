package com.nlk.note.data.callback;

import androidx.lifecycle.ViewModel;

import com.nlk.baseframe.ui.callback.UnPeekLiveData;

public class SharedViewModel extends ViewModel {
    public final UnPeekLiveData<Boolean> isShowBottom = new UnPeekLiveData<>();
}
