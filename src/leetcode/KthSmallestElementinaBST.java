package leetcode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class KthSmallestElementinaBST {
    /**
     * 230. Kth Smallest Element in a BST
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     *
     * time : O(n)
     * space : O(n);
     * @param root
     * @param k
     * @return
     */

    private static int count = 0;
    private static int res = 0;

    public static int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return res;
    }
    public static void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        count--;
        if (count == 0) {
            res = root.val;
        }
        helper(root.right);
    }
    
    // so we count left tree nodes and decide where to go
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null || k < 1) {
            return -1;
        }
        return helper(root, k);
    }
    
    public int helper(TreeNode node, int k) {
        int leftCount = countNodes(node.left);
        if (k <= leftCount) {
            return helper(node.left, k);
        } else if (k > leftCount + 1){
            return helper(node.right, k - 1 - leftCount);
        } else {
            return node.val;
        }
    }
    
    public int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
