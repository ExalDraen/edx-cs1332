package org.alnx.edx.mod4;

public class Main {

    public static void main(String[] args) {
	// write your code here

        TreeNode<Integer> myBST = new TreeNode<>(10);
        TreeNode<Integer> left = new TreeNode<>(5);
        TreeNode<Integer> leftleft = new TreeNode<>(2);
        TreeNode<Integer> leftright = new TreeNode<>(7);
        left.setLeft(leftleft);
        left.setRight(leftright);

        TreeNode<Integer> right = new TreeNode<>(15);
        TreeNode<Integer> rightright = new TreeNode<>(25);
        TreeNode<Integer> rightrightleft = new TreeNode<>(23);
        rightright.setLeft(rightrightleft);
        right.setRight(rightright);

        myBST.setLeft(left);
        myBST.setRight(right);

        Traversals<Integer> trav = new Traversals<>();
        System.out.println("Preorder: " + trav.preorder(myBST));
        System.out.println("Inorder: " + trav.inorder(myBST));
        System.out.println("Postorder: " + trav.postorder(myBST));
    }
}
