/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/submissions/
import java.util.*;
class Solution {
      long firstMin = Long.MAX_VALUE;
    long secondMin = Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) return -1;
        find(root);
        return secondMin == Long.MAX_VALUE ? -1 : (int)secondMin;
    }
    public void find(TreeNode node){
        //노드가 존재하지 않을 때
        if(node == null) return;
        
        //노드 값이 최소값보다 더 작을 때
        if(node.val < firstMin){
            secondMin = firstMin; //두번째 최소값이 이전 최소값이 되고
            firstMin = node.val; //현재 노드 값이 첫번째 최소값으로 설정됨.
        }
        
        if(node.val > firstMin && node.val < secondMin){
            secondMin = node.val;
        }
        
        find(node.left);
        find(node.right);
    }
}