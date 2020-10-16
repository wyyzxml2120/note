package com.nlk.note.ui.page.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nlk.note.databinding.ItemThemeBinding;
import com.nlk.note.db.ThemeCode;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private List<ThemeCode> themeList;

//    static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//
//        public ViewHolder(View view) {
//            super(view);
//            imageView = (ImageView) view.findViewById(R.id.ivBg);
//        }
//    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemThemeBinding mBinding;

        static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemThemeBinding binding = ItemThemeBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }

        private ViewHolder(ItemThemeBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(ThemeCode theme) {
            mBinding.setTheme(theme);
            mBinding.executePendingBindings();
        }

    }


    public ThemeAdapter(List<ThemeCode> list) {
        themeList = list;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        ItemThemeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_theme, parent, false);
//        return new ViewHolder(binding.getRoot());

        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
//        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindTo(themeList.get(position));

    }

    @Override
    public int getItemCount() {
        return themeList == null ? 0 :themeList.size();
    }
}