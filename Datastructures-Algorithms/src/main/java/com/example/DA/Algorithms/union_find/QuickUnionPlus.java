package com.example.DA.Algorithms.union_find;


/**
 * （Ⅲ）加权 quick-union 算法
 */
public class QuickUnionPlus {

    private int[] ids; // 索引为触点，索引处储存的元素为所在分量的另一个触点的名称
    private int[] rootSizes; // 索引为触点，索引处储存当前触点所在根节点的深度（连通分量的大小）
    private int size;  // 分量数

    public QuickUnionPlus(int N) {
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
        rootSizes = new int[N];
        for (int i = 0; i < N; i++) {
            rootSizes[i] = 1;
        }
        size = N;
    }

    /**
     * 合并两个分量
     *
     * <p>如果find(p)、find(q)触点处的值一样，则表示处于同一条分量，不做更改
     * 否则，表示处于不同分量，那么连接两条分量，具体操作为：直接修改p触点所在根节点的值为q所在触点的根节点的值
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            if (rootSizes[p] >= rootSizes[q])
                ids[qRoot] = pRoot;
            else
                ids[pRoot] = qRoot;
            size--;
        }
    }

    /**
     * 查找p触点所在分量的根触点(ids[root] = root)
     *
     * @param p
     */
    public int find(int p) {
        while (ids[p] != p) {
            p = ids[p];
        }
        return p;
    }

    /**
     * 两个触点是否连通
     *
     * @param p
     * @param q
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 触点的数量
     */
    public int count() {
        return size;
    }
}
