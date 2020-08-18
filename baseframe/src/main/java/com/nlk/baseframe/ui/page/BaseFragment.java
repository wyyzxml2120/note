package com.nlk.baseframe.ui.page;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public abstract class BaseFragment extends Fragment {

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }
}
