package com.nlk.note.ui.page.matter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nlk.note.R;
import com.nlk.note.databinding.FragmentIdeaBinding;
import com.nlk.note.databinding.FragmentViewBinding;
import com.nlk.note.ui.page.ViewFragment;

public class IdeaFragment extends Fragment {
    private FragmentIdeaBinding fIdeaBin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fIdeaBin =  DataBindingUtil.inflate(inflater, R.layout.fragment_idea,container,false);
        fIdeaBin.setClick(new IdeaFragment.ClickProxy());
        return fIdeaBin.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class ClickProxy {

    }
}
