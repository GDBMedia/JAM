package net.gdbmedia.jam.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;
import net.gdbmedia.jam.models.Application;
import net.gdbmedia.jam.ui.ApplicationListActivity;
import net.gdbmedia.jam.utils.DateUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Laker77 on 9/9/2016.
 */
public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>{
    private ArrayList<Application> mApplications = new ArrayList<>();
    private Context mContext;

    public ApplicationAdapter(Context context, ArrayList<Application> applications) {
        mApplications = applications;
        mContext = context;
    }

    @Override
    public ApplicationAdapter.ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ApplicationViewHolder viewHolder = new ApplicationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApplicationAdapter.ApplicationViewHolder holder, int position) {
        holder.bindApplication(mApplications.get(position));
    }

    @Override
    public int getItemCount() {
        return mApplications.size();
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.cv) CardView cv;
        @Bind(R.id.company_name) TextView mCompanyName;
        @Bind(R.id.job_title) TextView mJobTitle;
        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.status) TextView mStatus;
        private Context mContext;

        public ApplicationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindApplication(Application app) {

            mCompanyName.setText(app.getCompanyName());
            mJobTitle.setText(app.getJobTitle());
            mDate.setText(DateUtils.formatDate(app.getDateApplied()));
            mStatus.setText(Constants.STATUS_MAP_REVERSE.get(app.getStatus()));


        }

        @Override
        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(v.getContext(), UpdateDmv.class);
//            intent.putExtra("position", itemPosition + "");
//            intent.putExtra("dmvs", Parcels.wrap(mApplications));
//            mContext.startActivity(intent);
        }
    }
}
