package buiduyanh.fpoly.buiduyanh.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import buiduyanh.fpoly.buiduyanh.Fragment.TabFra.ListKhoaHocFragment;
import buiduyanh.fpoly.buiduyanh.Fragment.TabFra.MyKhoaHocFragment;
import buiduyanh.fpoly.buiduyanh.HomeActivity;
import buiduyanh.fpoly.buiduyanh.R;


public class KhoaHocFragment extends Fragment {

    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khoa_hoc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ic_show_nav).setOnClickListener(ic -> {
            HomeActivity.drawerLayout.openDrawer(Gravity.LEFT);
        });

        tabLayout = view.findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText("Danh sách"));
        tabLayout.addTab(tabLayout.newTab().setText("Khóa học của bạn"));
        if (tabLayout.getTabAt(0).isSelected()) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListKhoaHocFragment()).commit();
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListKhoaHocFragment()).commit();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyKhoaHocFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}