package buiduyanh.fpoly.buiduyanh.Fragment.TabFra;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import buiduyanh.fpoly.buiduyanh.MyDatabase.DBHelper;
import buiduyanh.fpoly.buiduyanh.R;
import buiduyanh.fpoly.buiduyanh.Service.GetAllCourseServices;
import buiduyanh.fpoly.buiduyanh.adpter.KhoaHocAdapter;
import buiduyanh.fpoly.buiduyanh.model.KhoaHoc;

public class ListKhoaHocFragment extends Fragment {

    RecyclerView listViewCourse;
    ArrayList<KhoaHoc> listCourse = new ArrayList<>();
    DBHelper courseManagementSql;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_khoa_hoc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewCourse = view.findViewById(R.id.listCourse);


        courseManagementSql = new DBHelper(getContext());

        IntentFilter filterGetAllCourse = new IntentFilter("GetAllCourseServices");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(getAllCourseReceiver, filterGetAllCourse);

        IntentFilter filterRegisterCourse = new IntentFilter("RegisterAndUnRegisterCourseServices");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(getAllCourseReceiver, filterRegisterCourse);

        Intent intent = new Intent(getContext(), GetAllCourseServices.class);
        intent.putExtra("idStudent", "ps00709");
        intent.putExtra("isMine", false);
        intent.setAction("GetAllCourseServices");
        getActivity().startService(intent);
    }

    private final BroadcastReceiver getAllCourseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode",getActivity().RESULT_OK);
            if (resultCode == getActivity().RESULT_OK) {
                String action = intent.getStringExtra("action");
                switch (action){
                    case "GetAllCourseServices":
                    case "RegisterAndUnRegisterCourseServices":
                        listCourse.clear();
                        ArrayList<KhoaHoc> allCourse = (ArrayList<KhoaHoc>) intent.getSerializableExtra("allCourse");
                        ArrayList<KhoaHoc> allCourseRegister = (ArrayList<KhoaHoc>) intent.getSerializableExtra("allCourseRegister");

                        for(KhoaHoc course : allCourse){
                            for(KhoaHoc regCourse : allCourseRegister){
                                if(course.getId() == regCourse.getId()){
                                    course.setCheckRegistration(true);
                                    break;
                                }
                            }
                            listCourse.add(course);
                        }


                        KhoaHocAdapter adapter = new KhoaHocAdapter(listCourse, getContext(),courseManagementSql);
                        listViewCourse.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        break;
                    default:
                        break;
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(getAllCourseReceiver);
    }
}