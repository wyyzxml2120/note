package com.nlk.note.ui.model.changeTheme;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nlk.note.databinding.ItemWorkBinding;
import com.nlk.note.db.WorkCode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {

    private List<WorkCode> workList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkBinding mBinding;

        static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemWorkBinding binding = ItemWorkBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }

        private ViewHolder(ItemWorkBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(WorkCode workCode) {
            mBinding.setWork(workCode);
            mBinding.executePendingBindings();
        }

    }


    public WorkAdapter(List<WorkCode> list) {
        workList = list;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindTo(workList.get(position));

    }

    @Override
    public int getItemCount() {
        return workList == null ? 0 : workList.size();
    }
}