package buiduyanh.fpoly.lab2_nc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import buiduyanh.fpoly.lab2_nc.BroadCast.BroadCastReceiverBai2;
import buiduyanh.fpoly.lab2_nc.R;


public class Fragment_Bai2 extends Fragment {
    private EditText edEnterString;
    private Button btnSendBroadCast;
    private Intent intent;

    public Fragment_Bai2() {
        // Required empty public constructor
    }


    public static Fragment_Bai2 newInstance(String param1, String param2) {
        Fragment_Bai2 fragment = new Fragment_Bai2();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai2, container, false);
        this.initViewByID(view);

        return view;
    }

    private void initBroadCast() {
        String enterString = this.edEnterString.getText().toString();
        this.intent = new Intent(getActivity(), BroadCastReceiverBai2.class);
        this.intent.putExtra("enterString", enterString);
        requireActivity().sendBroadcast(intent);
    }

    private void initViewByID(View view) {
        this.edEnterString = view.findViewById(R.id.ed_EnterString);
        this.btnSendBroadCast = view.findViewById(R.id.btn_sendBroadCast);
        this.btnSendBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBroadCast();
            }
        });
    }
}