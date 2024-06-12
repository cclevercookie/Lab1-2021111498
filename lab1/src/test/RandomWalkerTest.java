package test;

import demo.TextProcessor;
import org.junit.jupiter.api.Test;
import demo.Graph;
import demo.RandomWalker;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;


public class RandomWalkerTest {

    // 综合模糊测试：验证随机游走的各个方面是否符合预期
    @Test
    public void testRandomWalk() {
        // 创建一个模拟的有向图
        Graph graph = TextProcessor.generateDirectedGraph("test5.txt");
        String randomWalkResult = RandomWalker.randomWalk(graph);

        // 检查路径格式是否正确
        assertTrue(isValidPathFormat(randomWalkResult), "Path format should be correct.");

        // 检查路径是否包含循环
        assertFalse(hasLoop(randomWalkResult), "Path should not contain loop.");

        // 检查路径中的节点是否都存在于图中
        assertTrue(allNodesExist(randomWalkResult, graph), "All nodes in path should exist in graph.");

        // 将随机游走路径写入文件
        writePathToFile(randomWalkResult);


        System.out.println("Test 5 passed successfully!");
    }

    // 验证路径格式是否正确
    private boolean isValidPathFormat(String path) {
        // 这里可以根据路径格式编写相应的检查逻辑
        return path.matches("^\\w+( -> \\w+)*$"); // 简单地验证路径是否由节点和箭头连接组成
    }

    // 验证路径中是否存在循环
    private boolean hasLoop(String path) {
        // 这里可以根据路径中是否出现循环进行检查
        // 这里只是一个示例，你可以根据实际情况编写具体的检查逻辑
        // 检查是否存在重复的节点
        String[] nodes = path.split(" -> ");
        Set<String> visited = new HashSet<>();
        for (String node : nodes) {
            if (!visited.add(node)) {
                // 如果节点已经访问过，检查路径是否已经结束
                int lastIndex = path.lastIndexOf(node);
                if (lastIndex == path.length() - node.length()) {
                    return false; // 如果路径已经结束，说明没有循环
                } else {
                    return true; // 如果路径还没有结束，说明存在循环
                }
            }
        }
        return false; // 路径中不存在循环
    }


    // 验证路径中的节点是否都存在于图中
    private boolean allNodesExist(String path, Graph graph) {
        if (path == null || path.isEmpty()) {
            return true; // 如果路径为空，则认为所有节点都存在
        }

        String[] nodes = path.split(" -> ");
        // 检查路径中的节点是否存在于图中
        for (String node : nodes) {
            if (!graph.getAdjacencyMap().containsKey(node)) {
                System.out.println("Node " + node + " does not exist in the graph.");
                return false; // 如果路径中存在图中不存在的节点，返回false
            }
        }
        return true; // 如果路径中的所有节点都存在于图中，则返回true
    }


    // 将随机游走路径写入文件
    private void writePathToFile(String path) {
        // 测试写入只读文件时是否会捕获IOException
        assertThrows(IOException.class, () -> {
            try (FileWriter writer = new FileWriter("random_text5.txt")) {
                writer.write(path);
            }
        }, "Writing to read-only file should throw IOException.");
    }
}
