package com.cyk.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建一个环形队列
        CircleArray queue = new CircleArray(4);//队列的有效数据最大是3
        char key = ' ';//接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("s(show)：显示队列的所有数据");
            System.out.println("e(exit)：退出");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：获取队列的数据");
            System.out.println("h(head)：显示队列的头数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用环形数组模拟队列
class CircleArray {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头，front指向队列的第一个元素，也就是arr[front]，front的初始值 = 0
    private int rear;//队列尾，rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定，rear的初始值 = 0
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，无法添加数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        //1、先把front对应的值保存到一个临时变量，直接返回的话front就没有后移的机会了
        int value = arr[front];
        //2、将front后移，要考虑取模
        front = (front + 1) % maxSize;
        //3、将临时保存的变量返回
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
