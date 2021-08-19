package ast.billmanagment.mybills.MainAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class HistoryFragment extends Fragment implements View.OnClickListener {

    IBadgeUpdateListener mBadgeUpdateListener;
    ArrayList <DModel_BillsHistory> lstBillHistory;
    BillHistoryRcvAdapter billHistoryRcvAdapter;
    RecyclerView rcv_myBillsHistory;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_history, container, false);

        init();
        bindviews(frg);
        populateBillHistory();

        return frg;
    }


    private void populateBillHistory() {
        int size = (2);
        for (int i = size; i >= 0; i--) {
            lstBillHistory.add(new DModel_BillsHistory("Bill", "100"+i, (i + 1) + "", "Paid"));

        }

        if (billHistoryRcvAdapter == null) {

            billHistoryRcvAdapter = new BillHistoryRcvAdapter(getActivity(), lstBillHistory, (eventId, position) -> {

            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcv_myBillsHistory.setLayoutManager(linearLayoutManager);
            rcv_myBillsHistory.setAdapter(billHistoryRcvAdapter);

        } else {
            billHistoryRcvAdapter.notifyDataSetChanged();
        }

    }

    private void init() {
        setBottomBar();
        lstBillHistory = new ArrayList<>();
    }

    private void bindviews(View view) {
        rcv_myBillsHistory = view.findViewById(R.id.frg_my_bills_rcvHistory);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.frg_bill_type_txv_view_electricty:
//                navToElectricityHomeFragment();
//                break;

        }
    }


    void setBottomBar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_VISIBLE);
            mBadgeUpdateListener.setHeaderTitle(getString(R.string.frg_history));

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            setBottomBar();
        }
    }






}
