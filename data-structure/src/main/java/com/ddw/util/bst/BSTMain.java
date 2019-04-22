package com.ddw.util.bst;

import java.io.*;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-16 18:00
 * @Description
 */
public class BSTMain {

    /**
     *
     *        4
     *       / \
     *      2   6
     *     /\  /\
     *    1 3 5 7
     *          \
     *          8
     * @param args
     */
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(8);

//      System.out.println(bst.contains(7));
//        bst.preOrder();
//        System.out.println("--------------");
//        bst.preOrderIterator();
//        System.out.println("--------------");
//        bst.inOrder();
//        System.out.println("--------------");
//        bst.inOrderIterator();
//        System.out.println("--------------");
//        bst.postOrder();
//        System.out.println("-----");
//        System.out.println(bst.postOrderIterator());

        bst.levelOrder();
    }
}
