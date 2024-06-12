package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextProcessor {
    /**
     * 从文本文件中生成有向图
     * @param filePath 文本文件路径
     * @return 生成的有向图
     */
    public static Graph generateDirectedGraph(String filePath) {
        Graph graph = new Graph();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();

            // 用于记录单词频率
            Map<String, Integer> wordCount = new HashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line).append(" "); // 每行末尾添加空格，模拟换行
            }

            // 用空格替换非字母字符
            String text = sb.toString().replaceAll("[^a-zA-Z]", " ");

            // 使用空格分割文本
            Scanner textScanner = new Scanner(text);

            String prevWord = null;
            while (textScanner.hasNext()) {
                // 转换为小写
                String word = textScanner.next().toLowerCase();
                if (!word.isEmpty()) {
                    // 更新单词频率
                    //将单词添加到 wordCount 中
                    //如果已经存在，则增加其出现次数；如果不存在，则初始化为 1
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

                    if (prevWord != null) {
                        ////将前一个单词和当前单词之间添加一条边
                        graph.addEdge(prevWord, word);
                    }
                    else{
                        graph.addEdge(word, "第一个单词");
                    }
                    prevWord = word;
                } else {
                    prevWord = null;
                }
            }
            textScanner.close();
            scanner.close();

            // 输出单词频率
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }
}






