package net.gdbmedia.jam.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.ui.ApplicationListActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Laker77 on 9/11/2016.
 */
public class StatusListAdapter  extends RecyclerView.Adapter<StatusListAdapter.StatusViewHolder> {
    public ArrayList<String> mStatusList;
    public Context mContext;

    public StatusListAdapter(ArrayList<String> statusList, Context context){
        mStatusList = statusList;
        mContext = context;
    }

    @Override
    public StatusListAdapter.StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item, parent, false);
        StatusViewHolder viewHolder = new StatusViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StatusListAdapter.StatusViewHolder holder, int position) {
        holder.bindStatus(mStatusList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.titleTextView) TextView mTitle;
        private Context mContext;

        public StatusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindStatus(String title) {
            mTitle.setText(title);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(v.getContext(), ApplicationListActivity.class);
            intent.putExtra(Constants.STATUS_REF, Constants.STATUS_MAP.get(mStatusList.get(itemPosition)));

            mContext.startActivity(intent);
        }
    }

}
