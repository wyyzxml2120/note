package com.nlk.note.ui.state;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nlk.note.BuildConfig;
import com.nlk.note.data.NoteRepository;
import com.nlk.note.data.bean.ViewMatterBean;
import com.nlk.note.db.ThemeCode;
import com.nlk.note.db.WorkCode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MatterViewModel extends ViewModel {
    private LiveData<List<WorkCode>> works;
    private NoteRepository noteRepository;

    public MutableLiveData<ViewMatterBean> ldViewMatter = new MutableLiveData<>();

    public final ObservableField<String> addContent = new ObservableField<>();
    public final ObservableBoolean addIconShow = new ObservableBoolean();
    public final ObservableBoolean addColumnShow = new ObservableBoolean();
    public final ObservableInt addType = new ObservableInt();

    public MatterViewModel(@NonNull Application application) {
        //super(application);
        noteRepository = new NoteRepository(application);
        works = noteRepository.getWorks();

        ViewMatterBean viewMatterBean = new ViewMatterBean();
        viewMatterBean.setAddContent("");
        viewMatterBean.setAddColumnShow(false);
        viewMatterBean.setAddIconShow(true);
        viewMatterBean.setAddType(0);

        ldViewMatter.postValue(viewMatterBean);
    }

    public LiveData<List<WorkCode>> getWorks() {
        return works;
    }

    public LiveData<ViewMatterBean> getViewMatter(){return ldViewMatter;}

    public void insertWork(WorkCode...workCode) {
        noteRepository.insertWork(workCode);
    }

}