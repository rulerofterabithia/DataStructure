package com.cyk.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes(root);

        //测试，以10号节点测试
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点是：" + leftNode);
        System.out.println("10号节点的后继节点是：" + node5.getRight());

        //线索化的方式遍历二叉树
        System.out.println("线索化的方式遍历二叉树：");
        threadedBinaryTree.threadedList();
    }
}

//定义ThreadedBinaryTree二叉树，实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //中序遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            /*
            循环地找到leftType == 1的节点，第一个找到的就是8这个节点，后面会随着遍历而变化，
            因为当leftType == 1时，说明该节点是按照线索化处理后的有效节点
             */
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }

        //（一）先线索化左子树
        threadedNodes(node.getLeft());
        //（二）线索化当前节点
        //处理当前节点的前驱节点
        //以8这个节点来理解
        //8节点的left == null,8节点的leftType == 1
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        //此时的pre是8
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个结点的前驱节点
        pre = node;
        //（三）再线索化右子树
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void deleteNode(int no) {
        if (root != null) {
            //如果只有一个root节点，判断root是不是就是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树不能删除！");
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    //1.如果leftType == 0表示指向的是左子树，如果是1表示指向前驱节点
    //2.如果rightType == 0表示指向的是右子树，如果是1表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this);//输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 要查找的no
     * @return 如果找到返回对应的节点，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //1.判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.左递归前序查找，找到节点则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }
        //1.左递归前序查找，找到节点则返回，否则继续判断
        //2.当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断左子树是否为空
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //没有找到，比较当前节点是否为空
        if (this.no == no) {
            return this;
        }
        //向右子树查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        //左子树查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //左子树没有找到，向右子树查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //比较当前节点
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //1、如果删除的节点是叶子节点，则删除该节点
    //2、如果删除的节点是非叶子节点，则删除该子树
    public void deleteNode(int no) {
        /*
        思路：
        1.因为我们的二叉树是单向的，所以我们是判断的是当前节点的子节点是否需要删除节点，而不能去判断这个节点是不是需要删除的节点
        2.如果当前节点的左子节点不为空，并且左子节点就是需要删除的节点，就将this.left = null，并且返回（结束递归删除）
        3.如果当前节点的右子节点不为空，并且右子节点就是需要删除的节点，就将this.right = null，并且返回（结束递归删除）
        4.如果第2和第3步没有删除节点，那么我们就需要向左子树进行递归删除
        5.如果第4步也没有删除节点，则应当向右子树进行递归删除
         */
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        //右子树递归删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }
}
