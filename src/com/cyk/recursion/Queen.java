package com.cyk.recursion;

public class Queen {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个数组，保存皇后放置位置的结果，比如：arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;//统计解法的个数
    static int judgeCount = 0;//统计判断冲突的次数

    public static void main(String[] args) {
        //测试8皇后
        Queen queen = new Queen();
        queen.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.println();
        System.out.printf("一共判断了%d次是否冲突", judgeCount);
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for(int i = 0; i< max; i++)，因此会有回溯
    private void check(int n) {
        if (n == max) { //比如：n = 8，表示第8个皇后已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前的皇后n，放置到该行的第1列
            array[n] = i;
            //判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //不冲突，接着放第n+1个皇后，即开始递归
                check(n + 1);//产生回溯
            }
            //如果冲突，就继续执行array[n] = i，即将第n个皇后放置在本行后移的一个位置
        }
    }

    /**
     * 当我们放置了第n个皇后，就去检测是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return 返回true表示不冲突，false表示冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /*
            说明：
            1.array[i] == array[n]：表示判断第n个皇后是否和前面的n-1个皇后在同一列
            2.Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线
                假如n = 1：第2个皇后，放置到第2列，array[1]=1
                Math.abs(1-0) == 1，Math.abs(array[n] - array[i] = Math.abs(1-0) = 1)
            3.没有必要判断是否在同一行，因为n每次都在递增
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
