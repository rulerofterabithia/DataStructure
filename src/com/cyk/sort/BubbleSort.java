package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(n^2)
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, 20};

        bubbleSort(arr);

        //为了容易理解，将冒泡排序的演变过程展示出来
        /*//第一趟排序，就是将最大的数排在后面
        for (int i = 0; i < arr.length - 1; i++) {
            //如果前面的数比后面大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组：" + Arrays.toString(arr));

        //第二趟排序，将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            //如果前面的数比后面大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组：" + Arrays.toString(arr));

        //第三趟排序，将第三大的数排在倒数第三位
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            //如果前面的数比后面大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组：" + Arrays.toString(arr));

        //第四趟排序，将第四大的数排在倒数第四位
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            //如果前面的数比后面大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组：" + Arrays.toString(arr));*/

    }

    public static void bubbleSort(int arr[]) {
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                //如果前面的数比后面大，则交换
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            System.out.println("第" + (j + 1) + "趟冒泡排序后的数组：" + Arrays.toString(arr));
            if (!flag) {
                break;//一次交换都没有发生过
            } else {
                flag = false;//重置flag，进行下一次判断
            }
        }
    }
}
