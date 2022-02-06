package com.cyk.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明：
        //1.1+((2+3)*4)-5转成 1 2 3 + 4 * + 5 -
        //2.因为直接对str进行操作，不方便，因此先将"1+((2+3)*4)-5" =>中缀表达式对应的List
        //即1+((2+3)*4)-5 => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将中缀表达式对应的list转换成后缀表达式对应的list
        //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList [1,2,3,+,4,*,+,5,–]

        //测试
        List<String> infixExp = toInfixExpList("1+((2+3)*4)-5");
        System.out.println("中缀表达式对应的List：" + infixExp);

        //测试中缀表达式转换成后缀表达式
        List<String> suffixExpList = parseSuffixExpList(infixExp);
        System.out.println("后缀表达式对应的List：" + suffixExpList);

        //测试计算的结果
        System.out.printf("Exp = %d", calculate(suffixExpList));

        //先定义一个逆波兰表达式
        //(3+4)×5-6=>3 4 + 5 * 6 -
//        String suffixExp = "4 5 * 8 - 60 + 8 2 / +";
        //思路：
        //1、先将"3 4 + 5 x 6 -"放到ArrayList中
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

//        List<String> listString = getListString(suffixExp);
//        System.out.println(listString);

        //测试
//        int result = calculate(listString);
//        System.out.println("计算的结果是：" + result);
    }

    //将中缀表达式对应的list转换成后缀表达式对应的list
    public static List<String> parseSuffixExpList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //Stack<String> s2 = new Stack<String>();//储存中间结果的栈s2
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String>，而是直接使用List<String> s2
        ArrayList<String> s2 = new ArrayList<>();//储存中间结果的list s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                //如果是左括号
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到（4.1）步骤与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List，因此按顺序输出就是对应的后缀表达式对应的List
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> list = new ArrayList<String>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，就放入到c中
        do {
            //如果c是一个非数字，需要加入到list中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;//i后移
            } else {//如果是一个数（需要考虑多位数）
                str = "";//先将str置为空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }

        } while (i < s.length());
        return list;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExp) {
        //将suffixExp分割
        String[] split = suffixExp.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
    （1）从左至右扫描，将3和4压入堆栈
    （2）遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈
    （3）将5入栈
    （4）接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈
    （5）将6入栈
    （6）最后是-运算符，计算出35-6的值，即29，由此得出最终的结果
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.add(item);
            } else {
                //pop出两个数，并计算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num1 - num2;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把result入栈
                stack.push(result + "");
            }

        }
        //最后留在stack中的数据就是结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级的数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符！");
                break;
        }
        return result;
    }
}


