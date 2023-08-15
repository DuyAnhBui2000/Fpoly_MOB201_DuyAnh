package buiduyanh.fpoly.lab2_nc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import buiduyanh.fpoly.lab2_nc.BroadCast.BroadCastRecieverBai3;
import buiduyanh.fpoly.lab2_nc.R;


public class Fragment_Bai3 extends Fragment {
    private Button btnCheck;
    private EditText edVoucher;
    private Intent intent;

    public Fragment_Bai3() {
        // Required empty public constructor
    }


    public static Fragment_Bai3 newInstance(String param1, String param2) {
        Fragment_Bai3 fragment = new Fragment_Bai3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bai3, container, false);
        this.initViewByID(view);
        this.onClickButton();
        return view;
    }

    private void onClickButton() {
        this.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String voucher = edVoucher.getText().toString();
                intent = new Intent(getActivity(), BroadCastRecieverBai3.class);
                intent.putExtra("voucher", voucher);
                requireActivity().sendBroadcast(intent);
            }
        });
    }

    private void initViewByID(View view) {
        this.btnCheck = view.findViewById(R.id.btn_check);
        this.edVoucher = view.findViewById(R.id.ed_Voucher);
    }
}