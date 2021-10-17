/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Default;

//import java.util.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author chr
 */
public class AutocompleteProvider {
    
    private Candidate root;

    public AutocompleteProvider() {
        root = new Candidate();
    }
    
    public Candidate getRoot(){
        return root;
    }

    public void insert(String word) {
        HashMap<String, Candidate> children = root.getChildren();
        String s = "";
        for(int i = 0; i < word.length(); i++){
            s += word.charAt(i);
            Candidate node;
            if(children.containsKey(s)) {
                node = children.get(s);
                //node.incrementFreq();
            } 
            else { 
                node = new Candidate(s);
                children.put(s, node);
            }
            children = node.getChildren();

            if(i == word.length() - 1) {
                node.setLeaf(true);
                node.incrementFreq();
            }
        }
    }

    public List<Candidate> getLeafNodes(Candidate node){
        List<Candidate> output = new ArrayList<Candidate>();
        if(node.isLeaf()){
            output.add(node);
        }
        if(!node.getChildren().isEmpty()){
            for(String s : node.getChildren().keySet()){
                output.addAll(getLeafNodes(node.getChildren().get(s)));
            }
        }
        return output;
        
    }

    public List<Candidate> getWords(String fragment){
        HashMap<String, Candidate> children = root.getChildren();
        String s = "";
        Candidate node = new Candidate();
        for(int i = 0; i < fragment.length(); i++){
            s += fragment.charAt(i);
            if(children.containsKey(s)) {
                node = children.get(s);
                children = node.getChildren();
            }
            else{
                return new ArrayList<Candidate>();
            }
        }
        return getLeafNodes(node);
    }

    public void train(String passage){
        
        root.setChildren(new HashMap<String, Candidate>());
        
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
        Matcher wordMatches = wordPattern.matcher(passage);

        while (wordMatches.find()){
            this.insert(wordMatches.group().toLowerCase());
        }
    }

    public String PrintCandidates(List<Candidate> candList){
        
        String output = "";
        
        Collections.sort(candList, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate c1, Candidate c2) {
                return c2.getConfidence() - c1.getConfidence();
            }
	    });
        
        for(int i = 0; i < candList.size(); i++){
            if(i == candList.size() - 1){
                output += (candList.get(i).getWord() + " (" + candList.get(i).getConfidence()+ ") ");
            }
            else{
                output += (candList.get(i).getWord() + " (" + candList.get(i).getConfidence()+ "), ");
            }
            
        }
        
        return output;
    }
    

}



    

