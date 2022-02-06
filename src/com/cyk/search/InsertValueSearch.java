package com.cyk.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int resultIndex = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(resultIndex);
    }

    /**
     * 插值查找
     * 需要数组是有序的
     *
     * @param arr       传入的数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到，就返回对应的下标；没有找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {

        //findValue < arr[0] || findValue > arr[arr.length - 1]这两个条件不但能起到优化的作用而且必须有，否则得到的mid可能越界
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        //自适应算法
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {
            //向右递归
            return insertValueSearch(arr, midValue + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左递归
            return insertValueSearch(arr, left, midValue - 1, findValue);
        } else {
            return mid;
        }
    }
}
