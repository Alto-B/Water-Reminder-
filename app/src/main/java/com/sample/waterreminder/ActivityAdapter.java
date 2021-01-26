package com.sample.waterreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private WeekDateItem[] mWeekDateList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ActivityViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.week_date_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ActivityAdapter(WeekDateItem[] weekDateList){
        mWeekDateList = weekDateList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_reminder_item, parent, false);
        ActivityViewHolder avh = new ActivityViewHolder(v,mListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        WeekDateItem currentDate = mWeekDateList[position];

        holder.mTextView.setText(currentDate.getWeekDate());

    }

    @Override
    public int getItemCount() {
        return mWeekDateList.length;
    }
}
