package buiduyanh.fpoly.buiduyanh.Fragment.TabFra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import buiduyanh.fpoly.buiduyanh.Fragment.HomeFragment;
import buiduyanh.fpoly.buiduyanh.HomeActivity;
import buiduyanh.fpoly.buiduyanh.R;


public class MapFragment extends Fragment {

    private String url = "https://www.google.com/maps/place/Tr%C6%B0%E1%BB%9Dng+Cao+%C4%91%E1%BA%B3ng+FPT+Polytechnic/@21.0381278,105.7467871,15z/data=!4m5!3m4!1s0x0:0x53cefc99d6b0bf6f!8m2!3d21.0381278!4d105.7467871";
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.webMaps);
        view.findViewById(R.id.ic_back).setOnClickListener(ic ->{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new HomeFragment()).commit();
        });
        view.findViewById(R.id.ic_menu).setOnClickListener(ic ->{
            HomeActivity.drawerLayout.openDrawer(Gravity.LEFT);
        });

        webView.setVisibility(View.VISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.loadUrl(url);

    }
}