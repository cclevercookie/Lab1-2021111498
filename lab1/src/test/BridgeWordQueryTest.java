package test;

import static org.junit.jupiter.api.Assertions.*;

import demo.BridgeWordQuery;
import demo.Graph;
import demo.TextProcessor;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BridgeWordQueryTest {

    @Test
    void testBridgeWordsExist() {
        Graph graph = TextProcessor.generateDirectedGraph("test.txt");

        // 输入 "sample", "text"
        String expectedResult1 = "The bridge words from sample to text are: with.";
        String result1 = BridgeWordQuery.queryBridgeWords(graph, "sample", "text");
        assertEquals(expectedResult1, result1);
        System.out.println("Test 1 passed successfully!");

        // 输入 "word", "random"
        String expectedResult2 = "No word1 or word2 in the graph!";
        String result2 = BridgeWordQuery.queryBridgeWords(graph, "word", "random");
        assertEquals(expectedResult2, result2);
        System.out.println("Test 2 passed successfully!");

        // 输入 "it", "and"
        String expectedResult3 = "No bridge words from it to and!";
        String result3 = BridgeWordQuery.queryBridgeWords(graph, "it", "and");
        assertEquals(expectedResult3, result3);
        System.out.println("Test 3 passed successfully!");

        // 输入 "random", "sample"
        String expectedResult4 = "No word1 or word2 in the graph!";
        String result4 = BridgeWordQuery.queryBridgeWords(graph, "random", "sample");
        assertEquals(expectedResult4, result4);
        System.out.println("Test 4 passed successfully!");

        // 输入 "text", "random"
        String expectedResult5 = "No word1 or word2 in the graph!";
        String result5 = BridgeWordQuery.queryBridgeWords(graph, "text", "random");
        assertEquals(expectedResult5, result5);
        System.out.println("Test 5 passed successfully!");

        // 输入 "a", "with"
        String expectedResult6 = "The bridge words from a to with are: sentences, sample.";
        String result6 = BridgeWordQuery.queryBridgeWords(graph, "a", "with");
        assertEquals(expectedResult6, result6);
        System.out.println("Test 6 passed successfully!");
    }

}
