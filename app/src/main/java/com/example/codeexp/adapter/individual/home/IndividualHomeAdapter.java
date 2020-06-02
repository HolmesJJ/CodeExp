package com.example.codeexp.adapter.individual.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeexp.R;
import com.example.codeexp.callback.IOnItemClickListener;
import com.example.codeexp.entity.individual.home.IndividualHome;

import java.util.ArrayList;
import java.util.List;

public class IndividualHomeAdapter extends RecyclerView.Adapter<IndividualHomeAdapter.IndividualHomeViewHolder> {

    private List<IndividualHome> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private IOnItemClickListener iOnItemClickListener;

    public IndividualHomeAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<IndividualHome> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public IndividualHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_individual_home, parent, false);
        final IndividualHomeViewHolder holder = new IndividualHomeViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (iOnItemClickListener != null) {
                    iOnItemClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(IndividualHomeViewHolder holder, int position) {
        IndividualHome item = mItems.get(position);
        holder.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        holder.tvName.setText(item.getAvatar());
        holder.tvPosition.setText(item.getPosition());
        holder.tvNumberOfEmployees.setText(String.valueOf(item.getNumberOfEmployees()));
        holder.tvSalary.setText(item.getSalary());
        holder.tvWorkingDate.setText(item.getWorkingDate());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public IndividualHome getItem(int position) {
        return mItems.get(position);
    }

    class IndividualHomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName, tvPosition, tvNumberOfEmployees, tvSalary, tvWorkingDate;

        public IndividualHomeViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
            tvNumberOfEmployees = (TextView) itemView.findViewById(R.id.tv_number_of_employees);
            tvSalary = (TextView) itemView.findViewById(R.id.tv_salary);
            tvWorkingDate = (TextView) itemView.findViewById(R.id.tv_working_date);
        }
    }

    public void setOnItemClickListener(IOnItemClickListener iOnItemClickListener) {
        this.iOnItemClickListener = iOnItemClickListener;
    }
}
