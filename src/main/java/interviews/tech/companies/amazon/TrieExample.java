package interviews.tech.companies.amazon;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve
 * keys in a dataset of strings. There are various applications of this data structure, such as autocomplete
 * and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before),
 * and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the
 * prefix prefix, and false otherwise.
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 *
 */

public class TrieExample {

    public static void main(String[] args){

        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("able");

        System.out.println("Apple is present -> " + trie.search("apple"));
        System.out.println("app is present -> " + trie.search("app"));
        System.out.println("able is present -> " + trie.search("able"));
        System.out.println("starts with abl -> " + trie.startsWith("abl"));
        System.out.println("starts with abc -> " + trie.startsWith("abc"));
        System.out.println("Search abc -> " + trie.search("abc"));
        System.out.println("Search with ability -> " + trie.search("ability"));
        System.out.println("starts with ability -> " + trie.startsWith("ability"));
    }

}

class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;

        for(int i=0; i< word.length(); i++){
            Character ch = word.charAt(i);
            curr = curr.put(ch);
        }

        curr.isEnd = true;

    }

    public boolean search(String word) {
        TrieNode node = findWord(word);

        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = findWord(prefix);
        return node != null;
    }

    private TrieNode findWord(String prefix) {
        TrieNode curr = root;

        for(int i=0; i< prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            curr = curr.get(ch);
            if (curr == null) {
                return null;
            }
        }

        return curr;
    }
}

class TrieNode{
    TrieNode[] children;
    boolean isEnd;

    public TrieNode(){
        this.children = new TrieNode[26];
        this.isEnd = false;
    }

    public TrieNode put(Character ch){
        int index = ch - 'a';
        if(children[index] == null){
            children[index] = new TrieNode();
        }

        return children[index];
    }

    public TrieNode get(Character ch){
        int index = ch - 'a';
        return children[index];
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */