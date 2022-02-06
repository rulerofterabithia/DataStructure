package com.cyk.stack;

public class Calculator {
    public static void main(String[] args) {
        //测试
        String exp = "7*2*2-5+1-5+3-4";
        //创建两个栈，一个是数字栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operationStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int operation = 0;
        int result = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch中
        String keepNum = "";//用于拼接多位数

        //循环扫描exp
        while (true) {
            ch = exp.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operationStack.isOperation(ch)) {//是运算符
                //判断当前的符号栈是否为空
                if (!operationStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果入数字栈，然后将当前的操作入符号栈
                    if (operationStack.priority(ch) <= operationStack.priority(operationStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operationStack.pop();
                        result = numStack.calculate(num1, num2, operation);
                        //把运算的结果入数字栈
                        numStack.push(result);
                        //把当前的运算符入符号栈
                        operationStack.push(ch);
                    } else {
                        //如果当前运算符的优先级大于战中的操作符，直接入栈
                        operationStack.push(ch);
                    }
                } else {
                    //如果为空，直接入符号栈
                    operationStack.push(ch);
                }
            } else {//是数字，直接入数字栈
                //numStack.push(ch - 48);
                //分析思路
                //1、当处理多位数时，不能发现是一个数就立即入栈，因为它可能时多位数
                //2、在处理数时，需要向exp的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3、因此我们需要定义一个变量字符串，用于拼接
                keepNum += ch;

                //如果ch是exp的最后一位，则直接入栈
                if (index == exp.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，则继续扫描
                    if (operationStack.isOperation(exp.substring(index + 1, index + 2).charAt(0))) {
                        //后一位是运算符，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //将keepNum清空，非常重要！！！！！！
                        keepNum = "";
                    }
                }
            }
            //让index + 1 ，判断是否扫描到exp的最后
            index++;
            if (index >= exp.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序地从数字栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字（就是结果）
            if (operationStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operationStack.pop();
            result = numStack.calculate(num1, num2, operation);
            //把运算的结果入数字栈
            numStack.push(result);
        }
        //将数栈的最后一个数pop
        System.out.printf("表达式为：%s = %d", exp, numStack.pop());

    }
}

//定义一个ArrayStack2类表示栈结构，需要扩展功能
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，栈的数据存放在数组中
    private int top = -1;//表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public Boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public Boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，遍历时，需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！");
            return;
        }

        //需要从栈顶开始显示
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示，数字越大，优先级越高
    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public Boolean isOperation(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public int calculate(int num1, int num2, int operation) {
        int result = 0;//用于存放计算结果
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    //获得当前栈顶的数据，不是出栈
    public int peek() {
        return stack[top];
    }
}
