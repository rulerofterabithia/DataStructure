package com.cyk.sort;

import java.util.Arrays;

//时间复杂度：O(nlogn)
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //希尔排序：交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        /*
        //推导过程
        //第一轮：将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素（共5组，每组有2个元素），步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮过后：" + Arrays.toString(arr));

        //第二轮：将10个数据分成了5/2=2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮过后：" + Arrays.toString(arr));

        //第三轮：将10个数据分成了2/2=1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮过后：" + Arrays.toString(arr));
*/
        //根据前面的推导过程，可以使用循环处理
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组），步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮过后：" + Arrays.toString(arr));
        }


    }

    //希尔排序：移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入位置
                    arr[j] = temp;
                }
            }
        }
    }
}
