package leetcode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class MinimumDepthofBinaryTree {
    /**
     * 111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

     time : O(n);
     space : O(n);

     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //only 1 branch which means this node is not leaf node, we need to find another branch
        if (root.left == null || root.right == null) {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
    // another solution
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
        
    }
}
