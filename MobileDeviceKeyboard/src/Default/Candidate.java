/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Default;

import java.util.HashMap;
/**
 *
 * @author chr
 */
public class Candidate {
    
    private String s;
    
    private HashMap<String, Candidate> children = new HashMap<String, Candidate>();

    private boolean isLeaf;

    private int freq;

    public Candidate() {}

    public Candidate(String s){
        this.s = s;
        this.freq = 1;
    }

    public HashMap<String, Candidate> getChildren() {
        return this.children;
    }

    public void setChildren(HashMap<String, Candidate> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getWord(){
        return this.s;
    }

    public void incrementFreq(){
        this.freq++;
    }

    public int getConfidence(){
        return this.freq - 1;
    }

    
    
}
