package tree.trie;

public class Trie {
    private TrieNode root;

    // 생성자: 루트 노드 초기화
    public Trie() {
        root = new TrieNode();
    }

    // 단어 삽입 메서드
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // 단어 검색 메서드
    public boolean search(String word) {
        TrieNode node = getNode(word);
        return node != null && node.isEndOfWord;
    }

    // 특정 접두사로 시작하는 단어가 있는지 확인
    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    // 단어 삭제 메서드
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode node, String word, int index) {
        if (index == word.length()) {
            if (!node.isEndOfWord) {
                return false;
            }
            node.isEndOfWord = false;
            return isEmpty(node);
        }

        char ch = word.charAt(index);
        int charIndex = ch - 'a';
        TrieNode nextNode = node.children[charIndex];
        if (nextNode == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(nextNode, word, index + 1) && !nextNode.isEndOfWord;

        if (shouldDeleteCurrentNode) {
            node.children[charIndex] = null;
            return isEmpty(node);
        }
        return false;
    }

    // 주어진 문자열의 마지막 노드 반환 (없으면 null)
    private TrieNode getNode(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    // 노드가 비어있는지 확인하는 메서드
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    // 내부 클래스: Trie 노드 정의
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }
}