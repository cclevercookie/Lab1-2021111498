package demo;

import java.util.Map;

public class GraphViewer {
    // 显示有向图的邻接表表示
    public static void showDirectedGraph(Graph graph) {
        // 遍历图的邻接表的每一个键
        for (String from : graph.getAdjacencyMap().keySet()) {
            // 输出起始节点，并准备显示它的边
            System.out.print(from + " -> ");
            // edgeMap存放从该起始节点出发的所有边及其权重
            Map<String, Integer> edgeMap = graph.getAdjacencyMap().get(from);
            // 遍历起始节点到各个目标节点的边
            for (String to : edgeMap.keySet()) {
                // 获取边的权重
                int weight = edgeMap.get(to);
                // 输出目标节点及其边的权重
                System.out.print(to + "(" + weight + ") ");
            }
            // 换行，表示下一个起始节点及其边
            System.out.println();
        }
    }
}
