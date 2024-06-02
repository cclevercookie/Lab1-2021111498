import java.util.Map;

public class GraphViewer {
    public static void showDirectedGraph(Graph graph) {
        for (String from : graph.getAdjacencyMap().keySet()) {
            System.out.print(from + " -> ");
            Map<String, Integer> edgeMap = graph.getAdjacencyMap().get(from);
            for (String to : edgeMap.keySet()) {
                int weight = edgeMap.get(to);
                System.out.print(to + "(" + weight + ") ");
            }
            System.out.println();
        }
    }
}
