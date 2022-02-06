package com.cyk.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        //测试编码
        byte[] bytes = str.getBytes();
        byte[] huffmanZipBytes = huffmanZip(bytes);
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanZipBytes));

        //测试解码
        byte[] sourceBytes = decode(huffmanCodes, huffmanZipBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));

        //测试压缩文件
//        String srcFile = "d://write.xlsx";
//        String dstFile = "d://dst.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功");

        //测试解压文件
        String zipFile = "d://dst.zip";
        String dstFile = "d://write2.xlsx";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功");

    }

    /**
     * 对文件进行解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    /**
     * 编写方法，将一个文件进行压缩
     *
     * @param srcFile 需要压缩的文件的全路径
     * @param dstFile 压缩后文件存放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入哈夫曼编码，为了以后恢复源文件时使用
            //一定要把哈夫曼编码写入文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 对数据进行解码
     *
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行调换，反向查询
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增的取出key 1
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for循环结束后，list中就存放了所有的字符 "i like like like java do you like a java"
        //把list中的数据放入到byte[],并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位：true表示需要补高位，false表示不需要
     * @param b    传入的byte
     * @return b对应的二进制的字符串（按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数还存在补高位
        if (flag) {
            temp |= 256;//按位与256
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    /**
     * 编写一个方法，将前面的步骤封装起来
     *
     * @param bytes 原始字符串对应的byte数组
     * @return 经过哈夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNodes(bytes);
        //根据nodes创建哈夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //生成对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
     *
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的哈夫曼编码map
     * @return 返回哈夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将 1010100010111111110...转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建压缩存储后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte,放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1.将哈夫曼编码表存放在Map<Byte,String>
    //类似于32 -> 01, 97 -> 100, 100->11000这样的形式
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2中
        stringBuilder2.append(code);
        if (node != null) {//如果node == null则不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @param bytes 接收的字节数组
     * @return 返回的就是List形式 [Node{date = 97, weight = 5}, Node{date = 32, weight = 9} ......]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数（用map[key,value]）
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转换成Node对象，并放入nodes集合中
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根节点没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes最后的节点，就是哈夫曼树的根节点
        return nodes.get(0);
    }

    //前序遍历
    private static void preNode(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空，不能遍历！");
        }
    }
}

//创建Node
class Node implements Comparable<Node> {
    Byte data;//存放数据（字符）本身
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
