
import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toLowerCase().toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    public List<String> autoComplete(String prefix) {
        List<String> result = new ArrayList<>();

        TrieNode current = root;

        for (char ch : prefix.toLowerCase().toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                return result;
            }

            current = current.children[index];
        }

        findWords(current, prefix.toLowerCase(), result);
        return result;
    }

    private void findWords(TrieNode node, String word, List<String> result) {
        if (node.isEndOfWord) {
            result.add(word);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                findWords(node.children[i], word + (char) ('a' + i), result);
            }
        }
    }
}

public class AutoCompleteTextEditor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Trie trie = new Trie();

        String[] words = {
                "apple", "apply", "april",
                "banana", "band", "bank",
                "cat", "call", "camera",
                "dog", "door", "doll"
        };

        for (String word : words) {
            trie.insert(word);
        }

        System.out.println("=== Auto Complete Text Editor ===");
        System.out.print("Enter a prefix: ");

        String prefix = sc.nextLine();

        List<String> suggestions = trie.autoComplete(prefix);

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions found.");
        } else {
            System.out.println("\nSuggestions:");
            for (String word : suggestions) {
                System.out.println(word);
            }
        }

        sc.close();
    }
}