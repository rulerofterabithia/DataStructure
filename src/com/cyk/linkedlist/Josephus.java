package com.cyk.linkedlist;

public class Josephus {
    public static void main(String[] args) {
        //测试构建环形链表和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个节点
        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("输入的数据不正确！");
            return;
        }
        Boy currentBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                currentBoy = first;//让currentBoy指向第一个小孩
            } else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }

    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为first不能动，所以我们仍然需要一个辅助指针来遍历
        Boy currentBoy = first;
        while (true) {
            System.out.printf("小孩的编号为：%d\n", currentBoy.getNo());
            if (currentBoy.getNext() == first) {
                //说明已经遍历完
                break;
            }
            currentBoy = currentBoy.getNext();//后移
        }
    }

    /**
     * 根据用户的输入，计算小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (startNo < 1 || first == null || startNo > nums) {
            System.out.println("输入的数据有误！");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出拳
        Boy helper = first;
        //helper事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后的节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动 startNo-1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper同时移动 countNum-1 次，然后出圈
        //循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点
                break;
            }
            //让first和helper同时移动 countNum-1 次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将first指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d", helper.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
