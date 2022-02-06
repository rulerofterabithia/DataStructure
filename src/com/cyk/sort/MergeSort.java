package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(nlogn)
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 0, 234};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后的数组是：" + Arrays.toString(arr));
    }

    //归并排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   待排序的数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的初始索引
     * @param temp  做中转的临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //1.先把左右两边的数据按照规则填充到temp数组，直到左右两边的有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边有序序列的当前元素小于或等于右边有序序列的当前元素，就把左边有序序列的当前元素（arr[i]）拷贝到temp中去
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                //反之，右边有序序列的当前元素小于或等于左边有序序列的当前元素，就把右边有序序列的当前元素（arr[i]）拷贝到temp中去
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //2.把有剩余数据的一边的数据一次全部填充到temp中
        //左边有剩余元素
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        //右边右剩余元素
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //3.将temp数组的元素拷贝到arr中（不是每次都拷贝所有）
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
