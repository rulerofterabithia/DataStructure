package com.cyk.tree;

import java.util.Arrays;

//时间复杂度：O(nlogn)
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};
        heapSort(arr);
    }

    //堆排序
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次：" + Arrays.toString(arr));
//        adjustHeap(arr,0, arr.length);
//        System.out.println("第二次：" + Arrays.toString(arr));
        //1.将无序序列构建成一个堆，根据升序或降序的需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //System.out.println(Arrays.toString(arr));
        /*
        2.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
         */
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("数组=" + Arrays.toString(arr));
    }

    /**
     * 功能：将一棵在数组中以i为下标的非叶子节点的树调整成大顶堆
     * 举例：arr = {4, 6, 8, 5, 9} => i = 1 => adjustHeap => arr = {4, 9, 8, 5, 6} => i = 0 => {9, 6, 8, 5, 4}
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点再数组中的索引
     * @param length 表示对多少个元素进行调整，length实在逐渐减少的
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点的值
                k++;//这时k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k,继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束以后，我们已经将以i为父节点的数的最大值，放在了最顶端（局部）
        arr[i] = temp;
    }
}
