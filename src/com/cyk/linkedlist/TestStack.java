package com.cyk.linkedlist;

import java.util.Stack;

//演示栈（stack）的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
