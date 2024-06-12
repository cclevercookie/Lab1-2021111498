package demo;

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
        //创建一个队列，用于存储待访问的节点
        Queue<String> queue = new LinkedList<>();
        //// 记录父节点,用于后续最短路径的构建
        Map<String, String> parentMap = new HashMap<>();
        //创建一个集合，用于记录已访问的节点，防止重复访问
        Set<String> visited = new HashSet<>();

        // 初始化 BFS 的起点，将输入的第一个单词加入队列
        queue.add(word1);
        //标记为访问
        visited.add(word1);
        parentMap.put(word1, null);

        // BFS 遍历
        while (!queue.isEmpty()) {
            String currentWord = queue.poll(); // 取出队列头部的节点
            if (currentWord.equals(word2)) { // 如果找到目标节点，退出循环
                break;
            }
            Map<String, Integer> neighbors = graph.getAdjacencyMap().get(currentWord); // 获取邻居节点
            if (neighbors != null) {
                for (String neighbor : neighbors.keySet()) {
                    if (!visited.contains(neighbor)) { // 如果邻居节点没有被访问过
                        queue.add(neighbor); // 加入队列
                        visited.add(neighbor); // 标记为已访问
                        parentMap.put(neighbor, currentWord); // 记录父节点
                    }
                }
            }
        }

        // 构建路径
        List<String> path = new ArrayList<>();
        String current = word2;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current); // 追踪父节点，构建路径
        }
        Collections.reverse(path); // 反转路径，使其从起点到终点

        // 如果无法到达目标单词，则返回提示信息
        if (!path.get(0).equals(word1)) {
            return "No path from " + word1 + " to " + word2 + "!";
        }

        // 在原图上标注路径并展示
        System.out.println("最短路径为:");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
            graph.highlightEdge(path.get(i), path.get(i + 1)); // 标注路径
        }
        System.out.println(path.get(path.size() - 1));

        // 计算路径长度
        int pathLength = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            pathLength += graph.getAdjacencyMap().get(path.get(i)).get(path.get(i + 1)); // 累加路径长度
        }

        return "路径长度为: " + pathLength;
    }
}
