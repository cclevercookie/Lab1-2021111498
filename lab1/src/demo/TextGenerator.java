package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextGenerator {
    private static Graph graph;

    // 静态方法，用于设置有向图
    public static void setGraph(Graph graph) {
        TextGenerator.graph = graph;
    }

    /**
     * 根据给定的输入文本生成新文本，插入桥接词。
     *
     * @param inputText 输入文本
     * @return 生成的新文本
     */
    public static String generateNewText(String inputText) {
        // 将输入文本拆分成单词
        String[] words = inputText.split("\\s+");

        // 处理相邻的单词对
        StringBuilder newTextBuilder = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];

            // 将当前单词追加到新文本中
            newTextBuilder.append(currentWord).append(" ");

            // 检查当前单词和下一个单词之间是否存在桥接词
            String bridgeWord = findBridgeWord(currentWord, nextWord);

            // 如果存在桥接词，则将其插入到新文本中
            if (bridgeWord != null) {
                newTextBuilder.append(bridgeWord).append(" ");
            }
        }

        // 将最后一个单词追加到新文本中
        newTextBuilder.append(words[words.length - 1]);

        return newTextBuilder.toString();
    }

    // 查找两个给定单词之间的桥接词
    private static String findBridgeWord(String word1, String word2) {
        // 将单词转换为小写
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        // 检查单词是否存在于图中
        if (!graph.getAdjacencyMap().containsKey(word1) || !graph.getAdjacencyMap().containsKey(word2)) {
            return null; // 一个或两个单词不在图中
        }
        // 声明并初始化一个用于存储公共桥接词的 ArrayList
        List<String> commonBridgeWords = new ArrayList<>();

        // 遍历图的所有节点，查找桥接词
        for (String bridge : graph.getAdjacencyMap().keySet()) {
            if (graph.getAdjacencyMap().get(word1).containsKey(bridge) && graph.getAdjacencyMap().get(bridge).containsKey(word2)) {
                commonBridgeWords.add(bridge);
            }
        }

        // 如果没有共同的桥接词，则返回 null
        if (commonBridgeWords.isEmpty()) {
            return null;
        }

        // 随机选择一个共同的桥接词
        Random random = new Random();
        return commonBridgeWords.get(random.nextInt(commonBridgeWords.size()));
    }
}
