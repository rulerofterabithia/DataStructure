package com.cyk.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //这里先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试，前序遍历
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        //中序遍历
        System.out.println("中序遍历：");
        binaryTree.infixOrder();
        //后序遍历
        System.out.println("后序遍历：");
        binaryTree.postOrder();

        //前序遍历查找
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了编号是%d，名字是%s的节点", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没找到编号为%d的节点", 5);
        }
        //中序遍历查找
        //后序遍历查找

        //测试删除节点
        //删除前
        System.out.println("删除前：");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        //删除后
        System.out.println("删除后：");
        binaryTree.preOrder();
    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
