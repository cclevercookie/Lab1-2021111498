package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * demo.Main class for running the program.
 */
public class Main {

    /**
     * demo.Main method for executing the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("请输入文本文件的路径：");
        String filePath = scanner.nextLine();
        Graph graph = TextProcessor.generateDirectedGraph(filePath);
        System.out.println("有向图生成成功！");

        TextGenerator.setGraph(graph);

        while (true) {
            System.out.println("请选择功能：");
            System.out.println("1. 展示有向图");
            System.out.println("2. 查询桥接词");
            System.out.println("3. 根据桥接词生成新文本");
            System.out.println("4. 计算最短路径");
            System.out.println("5. 随机游走");
            System.out.println("6. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    GraphViewer.showDirectedGraph(graph);
                    break;
                case 2:
                    System.out.println("请输入两个英文单词，用空格分隔：");
                    String[] words = scanner.nextLine().split("\\s+");
                    String bridgeWords = BridgeWordQuery.queryBridgeWords(graph, words[0], words[1]);
                    System.out.println(bridgeWords);
                    break;
                case 3:
                    System.out.println("请输入原文本：");
                    String inputText = scanner.nextLine();
                    String newText = TextGenerator.generateNewText(inputText);
                    System.out.println("生成的新文本为：\n" + newText);

                    // 创建新文件并将新文本写入其中
                    try (FileWriter writer = new FileWriter("new_text.txt", StandardCharsets.UTF_8)) {
                        writer.write(newText);
                        System.out.println("新文本已保存到 new_text.txt 文件中。");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("请输入两个英文单词，用空格分隔：");
                    String[] wordsForShortestPath = scanner.nextLine().split("\\s+");
                    String shortestPath = ShortestPathCalculator.calcShortestPath(graph, wordsForShortestPath[0], wordsForShortestPath[1]);
                    System.out.println(shortestPath);
                    break;
                case 5:
                    String randomWalkResult = RandomWalker.randomWalk(graph);
                    try (FileWriter writer = new FileWriter("random_walk.txt", StandardCharsets.UTF_8)) {
                        writer.write(randomWalkResult);
                        System.out.println("随机游走路径已保存到 random_walk.txt 文件中。");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("程序退出！");
                    System.exit(0);
                default:
                    System.out.println("无效的选项，请重新输入！");
            }
        }
    }
}
