package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(n^2)
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
        System.out.println("插入排序过后：" + Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertValue = 0;//定义待插入的数
        int insertIndex = 0;//即arr[1]前面的那个数的下标

        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];//定义待插入的数
            insertIndex = i - 1;//即arr[1]前面的那个数的下标

            //说明：
            //1.insertIndex >= 0，保证在给insertValue找插入位置时，不越界
            //2.insertValue < arr[insertIndex]表示待插入的数，还没有找到插入位置
            //3.需要将arr[insertIndex]后移
            //insertValue > arr[insertIndex]从大到小排序
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //当退出while时，说明插入位置找到，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            //System.out.println("第" + i + "轮过后：" + Arrays.toString(arr));
        }

        /*
        //推导过程：
        //第一轮：
        int insertValue = arr[1];//定义待插入的数
        int insertIndex = 1 - 1;//即arr[1]前面的那个数的下标

        //说明：
        //1.insertIndex >= 0，保证在给insertValue找插入位置时，不越界
        //2.insertValue < arr[insertIndex]表示待插入的数，还没有找到插入位置
        //3.需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        //当退出while时，说明插入位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertValue;
        System.out.println("第一轮过后：" + Arrays.toString(arr));

        //第二轮：
        insertValue = arr[2];
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertValue;
        System.out.println("第二轮过后：" + Arrays.toString(arr));

        //第三轮：
        insertValue = arr[3];
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertValue;
        System.out.println("第三轮过后：" + Arrays.toString(arr));*/
    }
}
