package com.cyk.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序：");
        arrayBinaryTree.preOrder();

        System.out.println("中序：");
        arrayBinaryTree.infixOrder();

        System.out.println("后序：");
        arrayBinaryTree.postOrder();
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树的遍历
class ArrayBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    //重载postOrder
    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归这个元素
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //中序
    public void infixOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }
        //向左递归这个元素
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序
    public void postOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }
        //向左递归这个元素
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }

        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}
