package com.nlk.note.ui.page.matter;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nlk.note.R;
import com.nlk.note.data.bean.SkillBean;
import com.nlk.note.utils.ParticleView;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    private int SHOWDELETE = 1;//显示删除
    private int HIDEDELETE = 2;//隐藏删除
    private int DELETE = 3;//删除数据
    private int SHOWNOTIFICATION = 4;//显示通知栏
    private int HIDENOTIFICATION = 5;//隐藏通知栏

    private List<SkillBean> workList;
    private Handler serviceHandler;


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCancelDelete;
        TextView tvTitle;
        TextView tvStartTime;
        ImageView ivDelete;
        TextView tvSkillTime;
        TextView tvSkillLevel;
        Button btnEnd;
        Button btnStart;

        public ViewHolder (View view)
        {
            super(view);
            ivCancelDelete = view.findViewById(R.id.ivCancelDelete);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvStartTime = view.findViewById(R.id.tvStartTime);
            ivDelete = view.findViewById(R.id.ivDelete);
            tvSkillTime = view.findViewById(R.id.tvSkillTime);
            tvSkillLevel = view.findViewById(R.id.tvSkillLevel);
            btnEnd = view.findViewById(R.id.btnEnd);
            btnStart = view.findViewById(R.id.btnStart);
        }

    }


    public SkillAdapter(List<SkillBean> list, Handler serviceHandler) {
        workList = list;
        this.serviceHandler = serviceHandler;
    }

    @NotNull
    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NotNull SkillAdapter.ViewHolder holder, int position) {
        SkillBean skillBean = workList.get(position);

        if (skillBean.isDelete()){
            holder.ivCancelDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setVisibility(View.VISIBLE);
        }else {
            holder.ivCancelDelete.setVisibility(View.GONE);
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.tvSkillLevel.setText(skillBean.getLevel());
        holder.tvSkillTime.setText(skillBean.getSkillTime());
        holder.tvStartTime.setText(skillBean.getStartTime());
        holder.tvTitle.setText(skillBean.getTitle());

        //长按显示删除键
        holder.itemView.setOnLongClickListener(view -> {
            //暂时刷新整个数据 后期找到方法再更改
            skillBean.setDelete(true);
            notifyDataSetChanged();
            return false;
        });
        //开始计时
        holder.btnStart.setOnClickListener(view -> {
            //传入position
            Log.d("abcd","btnStart");
            Message message = new Message();
            message.what = SHOWNOTIFICATION;
            message.arg1 = workList.get(position).getId();
            message.obj = workList.get(position).getTitle();
            serviceHandler.sendMessage(message);
        });
        //结束计时
        holder.btnEnd.setOnClickListener(view -> {
            //传入position
            Message message = new Message();
            message.what = HIDENOTIFICATION;
            message.arg1 = workList.get(position).getId();
            serviceHandler.sendMessage(message);
        });
        //取消删除
        holder.ivCancelDelete.setOnClickListener(view -> {
            skillBean.setDelete(false);
            notifyDataSetChanged();
        });
        //删除
        holder.ivDelete.setOnClickListener(view -> {
            Message message = new Message();
            message.what = DELETE;
            message.arg1 = position;
            message.obj = holder.itemView;
            serviceHandler.sendMessage(message);
        });
    }



    @Override
    public int getItemCount() {
        return workList == null ? 0 : workList.size();
    }
}