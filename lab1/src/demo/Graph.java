package demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    // 用于存储图的邻接表，键是节点，值是一个 Map，该 Map 存储了与键节点相邻的节点及其边的权重
    private Map<String, Map<String, Integer>> adjacencyMap;
    private Set<String> highlightedEdges;

    // 构造方法，初始化邻接表
    public Graph() {
        // 创建一个新的 HashMap 实例用于存储邻接表
        this.adjacencyMap = new HashMap<>();
        this.highlightedEdges = new HashSet<>();
    }

    // 添加边的方法
    // 两个参数：起始节点 from 和目标节点 to
    public void addEdge(String from, String to) {
        // 如果目标节点是第一个单词，且邻接表中不存在，则将其添加到邻接表中
        if (to.equals("第一个单词") && !adjacencyMap.containsKey(to)) {
            adjacencyMap.put(from, new HashMap<>());
            return;
        }
        // 从邻接表中获取以 from 为键的值，如果该键不存在，则返回一个新的空的 HashMap
        Map<String, Integer> neighbors = adjacencyMap.getOrDefault(from, new HashMap<>());

        // 获取从节点 from 到节点 to 的边的当前权重
        // 如果这条边已经存在，则获取它的当前权重值；否则，返回默认值 0。然后加 1，表示将要添加的新边的权重
        neighbors.put(to, neighbors.getOrDefault(to, 0) + 1);

        // 将更新后的邻接信息放回邻接表中
        adjacencyMap.put(from, neighbors);

        // 如果 to 节点还没有出现在邻接表中，则将其添加到邻接表中，以确保即使没有出边，该节点也存在于图中
        adjacencyMap.putIfAbsent(to, new HashMap<>());
    }

    // 获取邻接表的方法，返回整个邻接表
    public Map<String, Map<String, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    // 高亮边的方法，接受两个参数：起始节点 from 和目标节点 to
    public void highlightEdge(String from, String to) {
        // 在图中标注路径的方法，将边加入集合中
        String edge = from + "->" + to;
        highlightedEdges.add(edge);
        //System.out.println("Edge from " + from + " to " + to + " is highlighted.");
    }

    // 检查边是否被标注
    public boolean isEdgeHighlighted(String from, String to) {
        String edge = from + "->" + to;
        return highlightedEdges.contains(edge);
    }
}
