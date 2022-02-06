package com.cyk.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        List<Integer> resultIndexList = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println(resultIndexList);
    }

    /**
     * 二分查找
     * 当有重复的数时：
     * 1.找到mid索引值，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足的元素的下标，加入到ArrayList集合中
     * 3.向mid索引值的右边扫描，将所有满足的元素的下标，加入到ArrayList集合中
     * 4.将ArrayList返回
     *
     * @param arr       待查找的数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到，返回下标；没有找到返回-1
     */
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findValue) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        if (findValue > midValue) {
            //向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findValue);
        } else {
            ArrayList<Integer> resultIndexList = new ArrayList<Integer>();
            //向mid索引值的左边扫描，将所有满足的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    //退出
                    break;
                }
                //否则，就把temp放入到resultIndexList中
                resultIndexList.add(temp);
                temp -= 1;//左移
            }
            resultIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    //退出
                    break;
                }
                //否则，就把temp放入到resultIndexList中
                resultIndexList.add(temp);
                temp += 1;//左移
            }

            return resultIndexList;
        }

    }
}
