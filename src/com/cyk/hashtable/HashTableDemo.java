package com.cyk.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTable hashTable = new HashTable(7);

        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------");
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            System.out.println("---------------------");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入id:");
                    int id = scanner.nextInt();
                    System.out.print("输入名字:");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的雇员的id:");
                    id = scanner.nextInt();
                    hashTable.findEmployeeById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建哈希表
class HashTable {
    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size;

    public HashTable(int size) {
        this.size = size;
        //初始化
        employeeLinkedListArray = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    //添加
    public void add(Employee employee) {
        //根据员工的id,得到该员工应当添加到哪条链表
        int employeeLinkedListNo = hashFunction(employee.id);
        //将employee添加到对应的链表中
        employeeLinkedListArray[employeeLinkedListNo].add(employee);
    }

    //遍历所有的链表，遍历hashtable
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmployeeById(int id) {
        //使用散列函数，确定到哪条链表查找
        int employeeLinkedListNo = hashFunction(id);
        Employee employee = employeeLinkedListArray[employeeLinkedListNo].findEmployeeById(id);
        if (employee != null) {//找到
            System.out.printf("在第%d条链表中找到该雇员，id=%d\n", (employeeLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFunction(int id) {
        return id % size;
    }
}

//表示一个雇员
class Employee {
    public int id;
    public String name;
    public Employee next;//next默认为空

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmployeeLinkedList,表示链表
class EmployeeLinkedList {
    //头指针，指向第一个Employee,因此我们这个链表的head是指向第一个Employee
    private Employee head;//默认为null

    //添加雇员到链表的方法
    //说明：
    //1.假定，当添加雇员时，id是自增长的，即id的分配是从小到大的，因此我们将该雇员直接加入到本链表的最后即可
    public void add(Employee employee) {
        //如果是第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Employee currentEmployee = head;
        while (true) {
            if (currentEmployee.next == null) {//说明到链表的最后
                break;
            }
            currentEmployee = currentEmployee.next;//后移
        }
        //退出时直接将employee加入链表
        currentEmployee.next = employee;
    }

    //遍历链表的信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的信息为：");
        Employee currentEmployee = head;//辅助指针
        while (true) {
            System.out.printf("id = %d,name = %s", currentEmployee.id, currentEmployee.name);
            if (currentEmployee.next == null) {
                //已经是最后节点
                break;
            }
            currentEmployee = currentEmployee.next;//后移，遍历
        }
        System.out.println();
    }

    //查找雇员的信息
    //如果找到，就返回雇员信息；如果没有找到，就返回null
    public Employee findEmployeeById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Employee currentEmployee = head;//辅助指针
        while (true) {
            if (currentEmployee.id == id) {//找到
                break;//这时currentEmployee就指向要查找的雇员
            }
            if (currentEmployee.next == null) {//说明遍历当前链表没有找到该雇员
                currentEmployee = null;
                break;
            }
            currentEmployee = currentEmployee.next;
        }
        return currentEmployee;
    }

    //TODO:删除
}
