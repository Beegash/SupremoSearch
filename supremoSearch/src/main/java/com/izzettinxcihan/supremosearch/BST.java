package com.izzettinxcihan.supremosearch;

import java.util.HashSet;

public class BST {

    private Node root;

    public MyLinkedList<String> search(String word) {
        MyLinkedList<String> results = new MyLinkedList<>();
        Node node = searchRec(root, word);
        if (node != null) {
            DocumentFrequencyNode current = node.frequencies;
            while (current != null) {
                results.add(current.documentName + ": " + current.frequency);
                current = current.next;
            }
        } else {
            results.add("No occurrences found.");
        }
        return results;
    }

    private Node searchRec(Node node, String word) {
        if (node == null) {
            return null;
        }
        int compareResult = word.compareTo(node.word);
        if (compareResult < 0) {
            return searchRec(node.left, word);
        } else if (compareResult > 0) {
            return searchRec(node.right, word);
        } else {
            return node;
        }
    }

    public void insert(String word, String documentName) {
        root = insertRec(root, word, documentName);
    }

    private Node insertRec(Node node, String word, String documentName) {
        if (node == null) {
            return new Node(word, documentName);
        }
        int cmp = word.compareTo(node.word);
        if (cmp < 0) {
            node.left = insertRec(node.left, word, documentName);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, word, documentName);
        } else {
            node.addOrUpdateFrequency(documentName);
        }
        return node;
    }

    public MyLinkedList<String> traverse(String type, HashSet<String> ignoreWords) {
        MyLinkedList<String> result = new MyLinkedList<>();
        switch (type) {
            case "Preorder":
                preOrderHelper(root, result, ignoreWords);
                break;
            case "Inorder":
                inOrderHelper(root, result, ignoreWords);
                break;
            case "Postorder":
                postOrderHelper(root, result, ignoreWords);
                break;
        }
        return result;
    }

    private void inOrderHelper(Node node, MyLinkedList<String> result, HashSet<String> ignoreWords) {
        if (node != null) {
            inOrderHelper(node.left, result, ignoreWords);
            if (!ignoreWords.contains(node.word)) {
                result.add(node.word + " - " + frequenciesToString(node.frequencies));
            }
            inOrderHelper(node.right, result, ignoreWords);
        }
    }

    private void preOrderHelper(Node node, MyLinkedList<String> result, HashSet<String> ignoreWords) {
        if (node != null) {
            if (!ignoreWords.contains(node.word)) {
                result.add(node.word + " - " + frequenciesToString(node.frequencies));
            }
            preOrderHelper(node.left, result, ignoreWords);
            preOrderHelper(node.right, result, ignoreWords);
        }
    }

    private void postOrderHelper(Node node, MyLinkedList<String> result, HashSet<String> ignoreWords) {
        if (node != null) {
            postOrderHelper(node.left, result, ignoreWords);
            postOrderHelper(node.right, result, ignoreWords);
            if (!ignoreWords.contains(node.word)) {
                result.add(node.word + " - " + frequenciesToString(node.frequencies));
            }
        }
    }

    private String frequenciesToString(DocumentFrequencyNode frequencies) {
        StringBuilder sb = new StringBuilder();
        DocumentFrequencyNode current = frequencies;
        while (current != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(current.documentName).append(": ").append(current.frequency);
            current = current.next;
        }
        return sb.toString();
    }

}
