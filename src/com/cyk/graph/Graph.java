package com.cyk.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻阶矩阵
    private int numsOfEdges;//表示边的的数目
    private boolean[] isVisited;//记录节点是否被访问

    public static void main(String[] args) {
        //测试创建图
        int n = 5;//顶点的个数
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);//A-B
        graph.insertEdge(0, 2, 1);//A-C
        graph.insertEdge(1, 2, 1);//B-C
        graph.insertEdge(1, 3, 1);//B-D
        graph.insertEdge(1, 4, 1);//B-E

        graph.showGraph();

        //测试dfs
//        System.out.println("深度优先遍历：");
//        graph.dfs();

        //测试bfs
        System.out.println("广度优先遍历：");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numsOfEdges = 0;
        isVisited = new boolean[vertexList.size()];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示第一个顶点对应的下标，例如：A->B A的下标为0，B的下标为1
     * @param v2     表示第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numsOfEdges++;
    }

    //返回结点的个数
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumberOfEdges() {
        return numsOfEdges;
    }

    //返回节点i（下标）对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先
    private void dfs(boolean[] isVisited, int i) {
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为以访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有的节点，进行dfs
    public void dfs() {
        //遍历所有的节点，进行dfs
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头节点对应的下标
        int w;//邻接节点w
        LinkedList queue = new LinkedList();//队列，记录节点访问的顺序
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;//标记已被访问
        queue.addLast(i);//将节点加入队列

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先
            }
        }

    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
}
