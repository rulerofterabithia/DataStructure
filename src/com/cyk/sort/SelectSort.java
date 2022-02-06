package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(n^2)
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        selectSortByASC(arr);
        System.out.println("选择排序(从小到大)：" + Arrays.toString(arr));

        selectSortByDESC(arr);
        System.out.println("选择排序(从大到小)：" + Arrays.toString(arr));
    }

    //选择排序（从小到大）
    public static void selectSortByASC(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {//说明假定的最小值不是最小
                    minValue = arr[j];//重置最小值
                    minIndex = j;//重置最小值的下标
                }
            }

            //把最小值放在arr[0]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
            //System.out.println("选择排序第" + (i + 1) + "轮过后：" + Arrays.toString(arr));
        }

        /*//推导过程：
         *//*
        第一轮：
            原始的数组：101，34，119，1
            第一轮排序：1，34，119，101
         *//*
        int minIndex = 0;
        int minValue = arr[0];
        for (int i = 0 + 1; i < arr.length; i++) {
            if (minValue > arr[i]) {//说明假定的最小值不是最小
                minValue = arr[i];//重置最小值
                minIndex = i;//重置最小值的下标
            }
        }

        //把最小值放在arr[0]，即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = minValue;
        }
        System.out.println("第一轮过后：" + Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        minValue = arr[1];
        for (int i = 1 + 1; i < arr.length; i++) {
            if (minValue > arr[i]) {//说明假定的最小值不是最小
                minValue = arr[i];//重置最小值
                minIndex = i;//重置最小值的下标
            }
        }

        //把最小值放在arr[1]，即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = minValue;
        }
        System.out.println("第二轮过后：" + Arrays.toString(arr));

        //第三轮
        minIndex = 2;
        minValue = arr[2];
        for (int i = 2 + 1; i < arr.length; i++) {
            if (minValue > arr[i]) {//说明假定的最小值不是最小
                minValue = arr[i];//重置最小值
                minIndex = i;//重置最小值的下标
            }
        }

        //把最小值放在arr[2]，即交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = minValue;
        }
        System.out.println("第三轮过后：" + Arrays.toString(arr));*/
    }

    public static void selectSortByDESC(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            int maxValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (maxValue < arr[j]) {
                    maxValue = arr[j];
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                arr[maxIndex] = arr[i];
                arr[i] = maxValue;
            }
        }
    }
}
