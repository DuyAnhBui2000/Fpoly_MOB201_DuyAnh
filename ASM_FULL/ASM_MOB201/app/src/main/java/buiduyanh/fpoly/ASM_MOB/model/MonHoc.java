package buiduyanh.fpoly.ASM_MOB.model;

import buiduyanh.fpoly.ASM_MOB.RegisterActivity;
import java.io.Serializable;
import java.util.ArrayList;

public class MonHoc extends RegisterActivity implements Serializable {
    private int imgID;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public ArrayList<ThongTin> getListTT() {
        return listTT;
    }

    public void setListTT(ArrayList<ThongTin> listTT) {
        this.listTT = listTT;
    }

    public MonHoc(String code, String name, String teacher, int isRegister, ArrayList<ThongTin> listTT) {
        this.imgID = imgID;
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.isRegister = isRegister;
        this.listTT = listTT;
    }


    private String code ;
    private String name;
    private String teacher;
    private int isRegister;
    private ArrayList<ThongTin> listTT;

    public MonHoc(int isRegister) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.isRegister = isRegister;
        this.listTT = listTT;
    }

    public MonHoc(String code, String name, String teacher) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
    }

    public int getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(int isRegister) {
        this.isRegister = isRegister;
    }

    public MonHoc(String code, String name, String teacher, int isRegister) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.isRegister = isRegister;

    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
//    public ArrayList<MonHoc> getImg(){
//    List<MonHoc> list = new ArrayList<>();
//    list.add(new MonHoc(R.drawable.ic_course_android));
//    list.add(new MonHoc(R.drawable.ic_course_reactnative));
//    list.add(new MonHoc(R.drawable.ic_course_project));
//    return (ArrayList<MonHoc>) list;
//}
//    public class MyData{
//        Integer[] drawableArray = {R.drawable.ic_course_android, R.drawable.ic_course_reactnative, R.drawable.ic_course_project};
//    }
}
