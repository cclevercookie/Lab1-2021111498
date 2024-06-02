import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyMap;
    //构造方法
    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        //从邻接表中获取以 from 为键的值，如果该键不存在，则返回一个新的空的 HashMap
        Map<String, Integer> neighbors = adjacencyMap.getOrDefault(from, new HashMap<>());
        //获取从节点 from 到节点 to 的边的当前权重
        //如果这条边已经存在，则获取它的当前权重值；
        // 否则，返回默认值 0。然后加 1，表示将要添加的新边的权重。
        neighbors.put(to, neighbors.getOrDefault(to, 0) + 1);
        adjacencyMap.put(from, neighbors);
    }

    public Map<String, Map<String, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public void highlightEdge(String from, String to) {
        // 在图中标注路径的方法，根据具体需求来实现
    }
}

