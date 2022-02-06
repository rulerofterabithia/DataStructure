package com.cyk.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        //测试顺序插入
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);

        doubleLinkedList.list();

        //测试修改
//        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println("修改后：");
//        doubleLinkedList.list();

        //测试删除
//        doubleLinkedList.delete(3);
//        System.out.println("删除后：");
//        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表（遍历）
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }

        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断链表是否为空
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //辅助变量temp
        HeroNode2 temp = head;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                //找到了链表的最后
                break;
            }
            //没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照编号顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        //因为头节点不能动，所以我们仍然需要一个辅助指针（临时变量temp）来帮助我们找到添加的位置
        //在单链表中，我们要找的temp要位于添加位置的前一个结点，否则插入不了
        HeroNode2 temp = head;
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
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.pre = temp;
            } else {
                heroNode.next = temp.next;//将temp的下一个节点变为要插入的节点的下一个节点
                heroNode.pre = temp;
                temp.next = heroNode;//从temp的后面插入到链表中
                if (heroNode.next != null) {
                    //避免空指针
                    heroNode.next.pre = heroNode;
                }
            }

        }
    }

    //修改一个结点的内容（双向链表的修改和单向链表一样）
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            return;
        }

        //找到需要修改的节点，根据no编号
        HeroNode2 temp = head.next;//辅助变量
        boolean flag = false;//表示是否找到
        while (true) {
            if (temp == null) {
                break;//遍历完
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为：%d的节点，不能修改", newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    //说明
    //1、对于双向链表，我们可以直接找到要删除的这个节点
    //2、找到后，自我删除即可
    public void delete(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标志是否找到
        while (true) {
            if (temp == null) {
                //找到最后
                break;
            }

            if (temp.no == no) {
                flag = true;//找到
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点%d不存在，无法删除！", no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个结点，默认为null

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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
