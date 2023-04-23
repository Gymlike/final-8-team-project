package com.team.final8teamproject;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int data;
    public TreeNode(int item){
        left = null;
        right = null;
        data = item;
    }
    public void setLeft(TreeNode left){
        this.left = left;
    }
    public void setRight(TreeNode right){
        this.right = right;
    }

    public int getData(){
        return this.data;
    }
    public TreeNode getLeftChild(){
        return this.left;
    }
    public TreeNode getRightChild(){
        return this.right;
    }

}