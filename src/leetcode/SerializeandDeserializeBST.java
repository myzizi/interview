package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SerializeandDeserializeBST
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class SerializeandDeserializeBST {
    /**
     * 449. Serialize and Deserialize BST
     *
          5
         / \
        4   6
       /     \
      1       8

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    // Encodes a tree to a single string.
    // for this question, we cannot use queue to serialize the tree because 
    // 3,1,4,null,2 if we use stack, it will be 3,1,2,4, if it is queue: 
    // it will be 3,1,4,2, but when we deserialize using getNode() we use recursive way to 
    // get left and right tree, which means left tree should be together, so we use queue
    // it will lost node 2 to right tree
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.append(cur.val + " ");
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] str = data.split(" ");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : str) {
            queue.offer(Integer.parseInt(s));
        }
        return getNode(queue);
    }

    public TreeNode getNode(Queue<Integer> queue) {
        if (queue.isEmpty()) return null;
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> smallerQ = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < root.val) {
            smallerQ.offer(queue.poll());
        }
        root.left = getNode(smallerQ);
        root.right = getNode(queue);
        return root;
    }
}
