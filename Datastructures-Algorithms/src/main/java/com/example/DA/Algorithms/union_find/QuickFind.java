package com.example.DA.Algorithms.union_find;

/**
 * （Ⅰ）quick-find算法
 */
public class QuickFind {

    private int[] ids; // 索引为触点，索引处储存分量的标识符（表示整个分量）

    private int size;  // 分量数

    public QuickFind(int N) {
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
        size = N;
    }

    /**
     * 合并两个分量
     *
     * <p>如果p、q触点处的值一样，则表示处于同一条分量，不做更改
     * 否则，表示处于不同分量，那么连接两条分量，具体操作为：便利数组，将所有与p触点值相同的触点的值改为q触点的值
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pSign = find(p);
        int qSign = find(q);
        if (pSign != qSign) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == ids[p]) {
                    ids[i] = ids[q];
                }
            }
            size--;
        }
    }

    /**
     * 查找p触点所在分量的标识符
     * @param p
     */
    public int find(int p) {
        return ids[p];
    }

    /**
     * 两个触点是否连通
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
