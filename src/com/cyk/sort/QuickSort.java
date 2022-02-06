package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(nlogn)
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//中轴值
        int temp = 0;//临时变量，作为交换时使用
        //while循环的目的是让比pivot值小的放到左边，比pivot值大的放到右边
        while (l < r) {
            //在pivot左边一直找，找到一个大于等于pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot右边一直找，找到一个小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r，说明pivot左边全部是小于等于pivot的值，pivot右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l]==pivot，则r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r]==pivot，则l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r，必须l++,r--,否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
