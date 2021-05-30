package com.nlk.note.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.nlk.baseframe.ui.page.BaseViewModel;
import com.nlk.note.NoteContext;
import com.nlk.note.data.NoteRepository;

public class ShareViewModel extends BaseViewModel {
    public final ObservableField<String> changeName;//技能标题

    public ShareViewModel(){
        changeName = new ObservableField<>();
    }
}
