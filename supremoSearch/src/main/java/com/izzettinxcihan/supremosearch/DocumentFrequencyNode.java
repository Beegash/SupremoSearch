package com.izzettinxcihan.supremosearch;

class DocumentFrequencyNode {

    String documentName;
    int frequency;
    DocumentFrequencyNode next; 

    public DocumentFrequencyNode(String documentName, int frequency) {
        this.documentName = documentName;
        this.frequency = frequency;
        this.next = null;
    }
}
