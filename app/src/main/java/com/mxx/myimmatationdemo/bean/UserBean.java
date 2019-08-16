//package com.mxx.myimmatationdemo.bean;
//
//import android.databinding.BaseObservable;
//import android.databinding.Bindable;
//
//
//
///**
// * Created by 98179 on 2019/6/26.
// */
//
//public class UserBean extends BaseObservable {
//
//    private String firstName;
//    private String lastName;
//
//    public UserBean(String firstName,String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    @Bindable
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
////        notifyPropertyChanged(BR.firstName);
//    }
//
//    @Bindable
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
////        notifyPropertyChanged(BR.lastName);
//    }
//
//
////    @BindingAdapter(value = {"url","placeHolder"},requireAll = false)
////    public static void setImg(ImageView imageView,String url,int placeHolder) {
////        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).into(imageView);
////    }
//
//}
