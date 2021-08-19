package ast.billmanagment.mybills.MainAuxiliaries;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;
import ast.billmanagment.mybills.Utils.PinEntry;

public class MyBillsFragment extends Fragment implements View.OnClickListener {

    IBadgeUpdateListener mBadgeUpdateListener;

    ArrayList <DModel_Bills> lstMyBills;

    BillListingRcvAdapter billListingRcvAdapter;
    BillTypeSpinnerAdapter billTypeSpinnerAdapter;
    BillerSpinnerAdapter billerSpinnerAdapter;

    RecyclerView rcv_myBills;
    Spinner spinnerBillType;
    Spinner spinnerBiller;
    private TextView txvBillType;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_my_bills, container, false);

        init();
        bindviews(frg);


        populateMyBills();
        populateSpinnerBillType();
        populateSpinnerBiller();




        return frg;
    }


    private void populateSpinnerBillType() {
        ArrayList<String> lstGender = new ArrayList<>();
   

        lstGender.add("Electricity");
        lstGender.add(getResources().getString(R.string.select_bill_type));


        billTypeSpinnerAdapter = new BillTypeSpinnerAdapter(getContext(), lstGender);
        spinnerBillType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                int Pos = Integer.parseInt(selectedItem);
//                txvBillType.setText(lstGender.get(position));


            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBillType.setAdapter(billTypeSpinnerAdapter);
        spinnerBillType.setSelection(billTypeSpinnerAdapter.getCount());


    }



    private void populateSpinnerBiller() {
        ArrayList<String> lstBiller = new ArrayList<>();

        lstBiller.add("IESCO");
        lstBiller.add("WAPDA");
        lstBiller.add(getResources().getString(R.string.select_biller_type));


        billerSpinnerAdapter = new BillerSpinnerAdapter(getContext(), lstBiller);
        spinnerBiller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                int Pos = Integer.parseInt(selectedItem);
//                txvBillType.setText(lstGender.get(position));


            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBiller.setAdapter(billerSpinnerAdapter);
        spinnerBiller.setSelection(billerSpinnerAdapter.getCount());


    }


    private void populateMyBills()
    {
        int size =( 5 );
        for (int i = size; i >=0 ; i--) {
            lstMyBills.add(new DModel_Bills("Bill"," " ,(i+1) + ""));

        }

        if (billListingRcvAdapter == null) {

            billListingRcvAdapter = new BillListingRcvAdapter(getActivity(), lstMyBills, (eventId, position) -> {

            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcv_myBills.setLayoutManager(linearLayoutManager);
            rcv_myBills.setAdapter(billListingRcvAdapter);

        } else {
            billListingRcvAdapter.notifyDataSetChanged();
        }

    }

    private void init() {
        setBottomBar();
        lstMyBills = new ArrayList<>();
    }

    private void bindviews(View view) {
        rcv_myBills = view.findViewById(R.id.frg_my_bills_rcvBills);
        spinnerBillType = view.findViewById(R.id.frg_my_bills_spinnerBilltpe);
        spinnerBiller = view.findViewById(R.id.frg_my_bills_spinnerBiller);
        txvBillType = view.findViewById(R.id.frg_my_bills_txvBilltype);
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
            mBadgeUpdateListener.setHeaderTitle(getString(R.string.frg_my_bills));

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
