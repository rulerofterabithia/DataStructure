package com.cyk.search;

public class SequenceSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int index = sequenceSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标是：" + index);
        }
    }

    //线性查找
    public static int sequenceSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现有相同值，就返回下标
        //这里实现的线性查找是找到一个满足条件的值就返回
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
