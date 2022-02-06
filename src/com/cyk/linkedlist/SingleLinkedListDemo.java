package com.cyk.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //向单链表中添加数据
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //测试逆序打印单链表
        System.out.println("逆序打印前：");
        singleLinkedList.list();
        System.out.println("逆序打印后：");
        reversePrint(singleLinkedList.getHead());

        //测试单链表的反转
//        System.out.println("反转前：");
//        singleLinkedList.list();
//        System.out.println("反转后：");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

        /*

        //测试顺序插入
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //测试修改节点的代码
        System.out.println("修改前");
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2, "小卢", "小麒麟");
        singleLinkedList.update(newHeroNode);

        //显示
        System.out.println("修改后");
        singleLinkedList.list();

        //测试删除节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        System.out.println("删除后");
        singleLinkedList.list();

        //求单链表有效结点的个数
        System.out.println("有效的节点个数是：" + getLength(singleLinkedList.getHead()));

        //查找单链表中的倒数第k个节点
        HeroNode lastHeroNode = findLastHeroNode(singleLinkedList.getHead(), 1);
        System.out.println(lastHeroNode);

         */
    }

    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果（百度面试题）
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode current = head.next;
        while (current != null) {
            stack.push(current);
            current = current.next;//后移
        }

        //将栈中的数据取出
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //单链表反转（腾讯面试题）
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或只有一个节点，就无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode current = head.next;
        HeroNode next = null;//指向当前节点（current）的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，放到新的链表reverseHead的最前端
        while (current != null) {
            next = current.next;//先暂时保存当前节点的下一个节点，后面需要使用到
            current.next = reverseHead.next;//将current的下一个节点指向reverseHead的最前端
            reverseHead.next = current;//将current连接到新的链表上
            current = next;//current后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个节点（新浪面试题）
    //思路：
    //1、编写一个方法，接收head节点，同时接收一个index
    //2、index表示是倒数第index个节点
    //3、先把链表从头到尾遍历，得到链表的总长度getLength
    //4、得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
    //5、如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastHeroNode(HeroNode head, int index) {
        //如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一次遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历(size-index)位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for循环定位到倒数的index
        HeroNode current = head.next;
        for (int i = 0; i < size - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头节点）
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，没有统计头节点
        HeroNode current = head.next;
        while (current != null) {
            length++;
            current = current.next;//遍历
        }
        return length;
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号的顺序时
    //1、找到当前链表的最后一个节点
    //2、将当前列表的最后一个节点的next指向新添加进来的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，所以我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后一个节点
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //没有找到最后一个节点，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，就找到了最后一个节点
        //将最后一个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种添加方式，在添加英雄时，根据英雄的编号将英雄插入到指定的位置
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，所以我们仍然需要一个辅助指针（临时变量temp）来帮助我们找到添加的位置
        //在单链表中，我们要找的temp要位于添加位置的前一个结点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的位置是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;//可以添加
            } else if (temp.next.no > heroNode.no) {//位置找到，就在temp后面插入
                break;//可以添加
            } else if (temp.next.no == heroNode.no) {//编号已经存在
                flag = true;
                break;//不能添加
            }
            temp = temp.next;//将temp后移，遍历当前链表
        }

        //判断flag的值，flag=false可以添加，flag=true不能添加
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能添加\n", heroNode.no);
        } else {
            heroNode.next = temp.next;//将temp的下一个节点变为要插入的节点的下一个节点
            temp.next = heroNode;//从temp的后面插入到链表中
        }
    }

    //修改节点的信息，根据编号修改信息，编号不能改（否则相当于添加）
    //说明：
    //1、根据newHeroNode的no修改即可
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //不为空，找到需要修改的节点
        //先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到需要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号为：%d的节点，不能修改", newHeroNode.no);
        }
    }

    //删除节点
    //思路：1、head不能动，因此我们需要一个temp辅助节点的前一个节点
    //2、说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除的节点
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除结点的前一个结点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        if (flag) {
            //找到
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点：%d不存在，无法删除", no);
        }
    }


    //显示链表（通过遍历的方式）
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //链表不为空，因为头节点不能动，所以我们需要一个辅助变量temp来遍历
        HeroNode temp = head.next;
        //循环遍历
        while (true) {
            //判断是否是最后一个节点
            if (temp == null) {
                break;
            }
            //不是最后一个节点，输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickname = nickName;
    }

    //为了显示方便，重写一下toString方法
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ",name=" + name + ",nickname = " + nickname + "]";
    }

}
