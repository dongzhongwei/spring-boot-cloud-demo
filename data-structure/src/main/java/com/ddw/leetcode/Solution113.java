package com.ddw.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
class Solution113 {


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, path, res);
        return res;
    }


    private void dfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> res){
        if(root == null) {

            return;
        }

        path.add(root.val);

        if(root.left == null && root.right == null && target - root.val == 0){

            res.add(new ArrayList(path));
        }

        dfs(root.left,target-root.val, path, res);

        dfs(root.right,target-root.val, path, res);

        path.remove(path.size()-1);
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}