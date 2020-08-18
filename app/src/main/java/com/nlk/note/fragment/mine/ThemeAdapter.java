package com.nlk.note.fragment.mine;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nlk.note.R;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private List<ThemeBean> comList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView key;
        TextView value;

        public ViewHolder(View view) {
            super(view);
//            key = (TextView) view.findViewById(R.id.comparison_subjectkey);
//            value = (TextView) view.findViewById(R.id.comparison_subjectvalue);
        }
    }

    public ThemeAdapter(List<ThemeBean> list) {
        comList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ThemeBean data = comList.get(position);

    }

    @Override
    public int getItemCount() {
        return comList.size();
    }
}