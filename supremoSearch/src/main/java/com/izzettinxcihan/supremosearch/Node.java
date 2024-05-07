package com.izzettinxcihan.supremosearch;

class Node {

    String word;
    Node left, right;
    DocumentFrequencyNode frequencies;

    public Node(String word, String documentName) {
        this.word = word;
        this.frequencies = new DocumentFrequencyNode(documentName, 1);
        this.left = null;
        this.right = null;
    }

    public void addOrUpdateFrequency(String documentName) {
        DocumentFrequencyNode current = this.frequencies;
        while (current != null) {
            if (current.documentName.equals(documentName)) {
                current.frequency++;
                return;
            }
            if (current.next == null) {
                current.next = new DocumentFrequencyNode(documentName, 1);
                return;
            }
            current = current.next;
        }
    }
}
