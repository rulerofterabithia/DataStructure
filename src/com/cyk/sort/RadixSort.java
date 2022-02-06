package com.cyk.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {
    /*
        //推导过程：
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        *//*
        说明：
        1.二维数组包含10个一维数组
        2.为了放置在放入数的时候，数据溢出，所以每一个一维数组（桶）大小定义为arr.length
        3.基数排序是使用空间换取时间的经典算法
         *//*
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据的个数
        //可以这样理解：bucketElementCounts[0],记录的就是bucket[0]这个桶放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //第1轮（针对每个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        int index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，则放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶（第k个一维数组），放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //在每一轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        //******************************************

        //第2轮（针对每个元素的十位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，则放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶（第k个一维数组），放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //在每一轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        //******************************************

        //第3轮（针对每个元素的百位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，则放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个桶（第k个一维数组），放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //在每一轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));*/

        //根据前面的推导过程，得到最终的基数排序的代码：
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一位数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        /*
        说明：
        1.二维数组包含10个一维数组
        2.为了放置在放入数的时候，数据溢出，所以每一个一维数组（桶）大小定义为arr.length
        3.基数排序是使用空间换取时间的经典算法
        */
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据的个数
        //可以这样理解：bucketElementCounts[0],记录的就是bucket[0]这个桶放入的数据的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素对应的位进行排序，第一次是个位，第二次是十位，第三次是百位...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，则放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶，即第k个桶（第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //在每一轮处理后，需要将每个bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮处理后：" + Arrays.toString(arr));
        }
    }
}
