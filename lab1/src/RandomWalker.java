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
        Random random = new Random();
        List<String> nodes = new ArrayList<>(graph.getAdjacencyMap().keySet());
        String startNode = nodes.get(random.nextInt(nodes.size())); // 从图中随机选择一个节点作为起点

        StringBuilder walkPath = new StringBuilder(startNode);
        String currentNode = startNode;

        try {
            FileWriter writer = new FileWriter("random_walk.txt");
            writer.write(currentNode);
            writer.write(" ");

            while (true) {
                Map<String, Integer> neighbors = graph.getAdjacencyMap().get(currentNode);
                List<String> nextNodes = new ArrayList<>(neighbors.keySet());

                if (nextNodes.isEmpty()) {
                    break; // 如果当前节点没有出边，则结束游走
                }

                String nextNode = nextNodes.get(random.nextInt(nextNodes.size())); // 随机选择一个邻居节点作为下一个节点

                walkPath.append(" -> ").append(nextNode);
                writer.write(" -> " + nextNode);
                writer.write(" ");

                if (hasLoop(walkPath.toString())) {
                    break; // 如果出现路径形成循环，则结束游走
                }

                currentNode = nextNode;
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return walkPath.toString(); // 返回游走路径的字符串表示形式
    }

    /**
     * 检查路径是否形成循环
     * @param path 路径字符串
     * @return 如果路径形成循环，则返回true；否则返回false
     */
    private static boolean hasLoop(String path) {
        String[] nodes = path.split(" -> "); // 将路径字符串拆分为节点数组
        Set<String> visited = new HashSet<>();

        for (String node : nodes) {
            if (!visited.add(node)) {
                return true; // 如果访问过该节点，则表示路径形成循环
            }
        }

        return false; // 否则路径未形成循环
    }
}


