package com.naltel.hackerrank.ai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}
class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;    
        }
    }
 
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
 
        if(t != null && t.isLeaf)
            return true;
        else
            return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) 
            return false;
        else
            return true;
    }
 
    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
 
        return t;
    }
}
public class UrlHashTagSegmentation {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		FileReader fileRead = new FileReader("words.txt");
		BufferedReader bf = new BufferedReader(fileRead);
		String nums = "1234567890.";
			//{'1','2','3','4','5','6','7','8','9','0','.'};
		String line;
		Trie trie = new Trie();
		while((line = bf.readLine()) != null) {
			trie.insert(line.toLowerCase());
		}
		int n = Integer.parseInt(in.nextLine());
		while(n > 0) {
			String input = in.nextLine();
			String curWord = "";
			if(input.contains("#")) {
				input = input.substring(1);
				
			} else {
				String[] splits = input.split("\\.");
				input = splits[1];
			}
			int index = 0;
			int length = input.length();
			List<String> words = new ArrayList<String>();
			while(index < length) {
				if(nums.indexOf(input.charAt(index)) >= 0) {
					curWord += input.charAt(index);
					if(index + 1 != length && nums.indexOf(input.charAt(index)) == -1){
						words.add(curWord);
						curWord = "";
					}
					index++;
				} else {
					curWord += input.charAt(index);
					if(trie.search(curWord.toLowerCase())) {
						words.add(curWord);
						curWord = "";
					}
					index++;
				}
			}
			StringBuilder sb = new StringBuilder();
			for(String word:words) {
				sb.append(word).append(" ");
			}
			System.out.println(sb.toString());
			n--;
			
		}
	}
}
