import java.util.*;

public class ShortestPathCalculator {
    /**
     * 计算两个单词之间的最短路径，并在原图上以某种突出的方式标注并展示路径
     * @param graph 有向图
     * @param word1 单词1
     * @param word2 单词2
     * @return 最短路径及其长度
     */
    public static String calcShortestPath(Graph graph, String word1, String word2) {
        // 将单词转换为小写
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        // 使用 BFS 进行最短路径计算
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(word1);
        visited.add(word1);
        parentMap.put(word1, null);

        while (!queue.isEmpty()) {
            String currentWord = queue.poll();
            if (currentWord.equals(word2)) {
                break;
            }
            Map<String, Integer> neighbors = graph.getAdjacencyMap().get(currentWord);
            if (neighbors != null) {
                for (String neighbor : neighbors.keySet()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parentMap.put(neighbor, currentWord);
                    }
                }
            }
        }

        // 构建路径
        List<String> path = new ArrayList<>();
        String current = word2;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);

        // 如果无法到达目标单词，则返回提示信息
        if (!path.get(0).equals(word1)) {
            return "No path from " + word1 + " to " + word2 + "!";
        }

        // 在原图上标注路径并展示
        System.out.println("最短路径为:");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
            graph.highlightEdge(path.get(i), path.get(i + 1));
        }
        System.out.println(path.get(path.size() - 1));

        // 计算路径长度
        int pathLength = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            pathLength += graph.getAdjacencyMap().get(path.get(i)).get(path.get(i + 1));
        }

        return "路径长度为: " + pathLength;
    }
}
