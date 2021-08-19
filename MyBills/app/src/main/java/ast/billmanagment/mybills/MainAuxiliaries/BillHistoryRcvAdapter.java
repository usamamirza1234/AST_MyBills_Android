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


public class BillHistoryRcvAdapter extends RecyclerView.Adapter<BillHistoryRcvAdapter.ViewHolder> {


    private final ArrayList<DModel_BillsHistory> mData;
    private final Context mContext;
    private final IAdapterCallback iAdapterCallback;


    public BillHistoryRcvAdapter(Context mContext, ArrayList<DModel_BillsHistory> mData,
                                 IAdapterCallback iAdapterCallback) {
        this.mContext = mContext;
        this.mData = mData;

        this.iAdapterCallback = iAdapterCallback;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_item_my_billshistory, null);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.txv_bill.setText(mData.get(position).billType);
        holder.txv_amount.setText(mData.get(position).amount);
        holder.txv_dueDate.setText(mData.get(position).dueDate);
        holder.txv_status.setText(mData.get(position).status);

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
        TextView txv_bill, txv_amount, txv_dueDate, txv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txv_dueDate = itemView.findViewById(R.id.lay_my_bill_txv_acc);
            txv_amount = itemView.findViewById(R.id.lay_my_bill_txv_Ref);
            txv_bill = itemView.findViewById(R.id.lay_my_bill_txv_billtype);
            txv_status = itemView.findViewById(R.id.lay_my_bill_txv_stattus);


        }
    }

}
