package com.ddw.util.bst;

import java.util.*;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-15 23:04
 * @Description
 */
public class BST<E extends Comparable<E>> {

    private class Node{

        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 第一种实现
     */
    /*public void add(E e){
        if (root == null){
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    private void add(Node node, E e){
        if (e.equals(node.e)){
            return;
        } else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) <0 ){
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }*/

    public void add(E e){
        root = add(root, e);
    }

    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) <0 ){
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }


    public boolean contains(Node node, E e){
        if (node == null){
            return false;
        }

        if (e.compareTo(node.e) == 0){
            return true;
        } else if (e.compareTo(node.e) < 0){
            return contains(node.left ,  e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }


    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 非递归的前序遍历
     */
    public void preOrderIterator(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

    }

    /**
     * 中序遍历
     */

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 非递归的中序遍历
     */
    public void inOrderIterator(){

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();

        Node node = root;

        while (node != null || !stack.isEmpty()){

            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            System.out.println(node.e);
            node = node.right;
        }
    }


    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node  node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }


    public List<E> postOrderIterator(){
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<E> nodes = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            nodes.addFirst(node.e);
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }

        }
        return nodes;
    }


    public void levelOrder(){
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node = null;
        while (! queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.e);
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


}
