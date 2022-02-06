package com.cyk.sparsearray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组11*11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组为：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //1、先遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
//        System.out.println(sum);

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];

        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的数据放到稀疏数组中
        int count = 0;//count用于记录是第几个非零元素
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //TODO 将稀疏数组保存到文件中
//        File file = new File("saveArr.txt");
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(file);
//            for (int i = 0; i < sparseArr.length; i++) {
//                for (int j = 0; j < sparseArr[0].length; j++) {
//                    fileWriter.write(sparseArr[i][j]);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //流资源的关闭
//            if (fileWriter != null)
//                try {
//                    fileWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }

        //TODO 读取稀疏数组

        //将稀疏数组恢复成原始的二维数组
        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2、再读取稀疏数组的后几行数据，并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //3、输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组为：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }

//    //保存数组到磁盘中
//    public void saveArr(int[][] arr) {
//        FileWriter fileWriter = null;
//        try {
//            //1、提供File类的对象，指明写出到的文件
//            File file = new File("saveArr.txt");
//
//            //2、提供FileWriter的对象，用于数据的写出
//            fileWriter = new FileWriter(file, true);
//
//            //3、循环数组写入到文件中
//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < 2; j++) {
//                    fileWriter.write(arr[i][j] + "\t");
//                }
//            }
////            fileWriter.write("I have a dream!\n");
////            fileWriter.write("You need to have a dream!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //4.流资源的关闭
//            if (fileWriter != null)
//                try {
//                    fileWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//    }
}
