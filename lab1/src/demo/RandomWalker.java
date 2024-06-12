package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RandomWalker {
    /**
     * 随机游走的方法
     * @param graph 有向图
     * @return 随机游走的路径
     */
    public static String randomWalk(Graph graph) {
        // 用于生成随机数的随机对象
        Random random = new Random();
        // 获取图中所有节点的列表
        List<String> nodes = new ArrayList<>(graph.getAdjacencyMap().keySet());
        // 从图中随机选择一个节点作为起点
        String startNode;
        if (nodes.size() == 1) {
            startNode = nodes.get(0);
        } else {
            startNode = nodes.get(random.nextInt(nodes.size()));
        }

        // 用于构建游走路径的字符串构建器
        StringBuilder walkPath = new StringBuilder(startNode);
        // 当前节点初始化为起始节点
        String currentNode = startNode;

        try {
            FileWriter writer = new FileWriter("random_walk.txt"); // 创建一个 FileWriter 对象，用于将路径写入文件
            writer.write(currentNode); // 写入起始节点到文件
            writer.write(" "); // 写入空格分隔符

            while (true) {
                // 获取当前节点的邻居节点
                Map<String, Integer> neighbors = graph.getAdjacencyMap().get(currentNode);

                if (neighbors == null || neighbors.isEmpty()) {
                    break; // 如果当前节点没有出边，则结束游走
                }
                // 获取所有邻居节点的列表
                List<String> nextNodes = new ArrayList<>(neighbors.keySet());
                // 随机选择一个邻居节点作为下一个节点
                String nextNode = nextNodes.get(random.nextInt(nextNodes.size()));

                walkPath.append(" -> ").append(nextNode); // 将下一个节点添加到游走路径中
                writer.write(" -> " + nextNode); // 将下一个节点写入文件
                writer.write(" "); // 写入空格分隔符

                if (hasLoop(walkPath.toString())) {
                    break; // 如果出现路径形成循环，则结束游走
                }

                currentNode = nextNode; // 更新当前节点为下一个节点
            }

            writer.close(); // 关闭 FileWriter 对象
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印可能的 IO 异常
        }
        // 打印遍历路径
        System.out.println("Traversal Path: " + walkPath.toString());
        return walkPath.toString(); // 返回游走路径的字符串表示形式
    }

    /**
     * 检查路径是否形成循环
     * @param path 路径字符串
     * @return 如果路径形成循环，则返回true；否则返回false
     */
    private static boolean hasLoop(String path) {
        String[] nodes = path.split(" -> "); // 将路径字符串拆分为节点数组
        Set<String> visited = new HashSet<>(); // 用于存储访问过的节点

        for (String node : nodes) {
            if (!visited.add(node)) {
                return true; // 如果访问过该节点，则表示路径形成循环
            }
        }

        return false; // 否则路径未形成循环
    }
}
