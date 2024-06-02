import java.util.Map;

public class BridgeWordQuery {
    // 查询桥接词的方法
    public static String queryBridgeWords(Graph graph, String word1, String word2) {
        // 将单词转换为小写
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        // 检查单词是否存在于图中
        if (!graph.getAdjacencyMap().containsKey(word1) || !graph.getAdjacencyMap().containsKey(word2)) {
            return "No word1 or word2 in the graph!";
        }

        StringBuilder result = new StringBuilder();
        boolean bridgeExists = false;

        // 遍历图的所有节点
        for (String bridge : graph.getAdjacencyMap().keySet()) {
            // 如果存在从 word1 到 bridge，以及从 bridge 到 word2 的边，则 bridge 是桥接词
            if (graph.getAdjacencyMap().get(word1).containsKey(bridge) && graph.getAdjacencyMap().get(bridge).containsKey(word2)) {
                // 如果已经找到桥接词，则在结果中添加逗号分隔符
                if (bridgeExists) {
                    result.append(", ");
                }
                result.append(bridge);
                bridgeExists = true;
            }
        }

        // 根据是否存在桥接词，返回不同的结果
        if (!bridgeExists) {
            return "No bridge words from " + word1 + " to " + word2 + "!";
        } else {
            return "The bridge words from " + word1 + " to " + word2 + " are: " + result.toString() + ".";
        }
    }
}
