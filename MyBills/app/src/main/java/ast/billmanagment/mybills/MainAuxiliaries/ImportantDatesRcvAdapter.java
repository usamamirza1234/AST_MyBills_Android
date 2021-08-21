package ast.billmanagment.mybills.MainAuxiliaries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.IAdapterCallback;


public class ImportantDatesRcvAdapter extends RecyclerView.Adapter<ImportantDatesRcvAdapter.ViewHolder> {


    private final ArrayList<DModel_ImportantDates> mData;
    private final Context mContext;
    private final IAdapterCallback iAdapterCallback;


    public ImportantDatesRcvAdapter(Context mContext, ArrayList<DModel_ImportantDates> mData,
                                    IAdapterCallback iAdapterCallback) {
        this.mContext = mContext;
        this.mData = mData;

        this.iAdapterCallback = iAdapterCallback;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_item_important_date, null);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.txv_date.setText(mData.get(position).Date);
        holder.txv_amount.setText(mData.get(position).Amount);
        holder.txv_biller.setText(mData.get(position).Biller);

        holder.itemView.setOnClickListener(v -> iAdapterCallback.onAdapterEventFired(IAdapterCallback.EVENT_A, position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txv_date, txv_amount,  txv_biller;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         
            txv_amount = itemView.findViewById(R.id.lay_important_date_txv_amount);
            txv_date = itemView.findViewById(R.id.lay_important_date_txv_date);
            txv_biller = itemView.findViewById(R.id.lay_important_date_txv_biller);


        }
    }

}