import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Arrays;

public class HW6Tester {

    @Test
    public void addNodeTester(){
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        System.out.println("Adding Node Graph Tester:");

        // Node has not been added yet => return true
        assertTrue(graph.addNode("A", 1));
        assertTrue(graph.addNode("B", 2));
        assertTrue(graph.addNode("C", 3));
        assertTrue(graph.addNode("D", 4));
        assertTrue(graph.addNode("E", 5));
        graph.printGraph();

        // Node has been added and added the second time => return false
        assertFalse(graph.addNode("A", 1));

        // add node with the same data
        assertFalse(graph.addNode("F", 1));

    }

    @Test
    public void addNodesTest(){

        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();
        System.out.println("Adding Nodes Graph Tester:");
        // Add nodes
        String[] name = {"A", "B", "C", "D", "E"};
        Integer[] data = {1, 2, 3, 4, 5};
        assertTrue(graph.addNodes(name, data));

        // Add nodes again => return false
        assertFalse(graph.addNodes(name, data));

        graph.printGraph();
    }

    @Test
    public void addEdge(){
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        System.out.println("Adding Edge Graph Tester:");

        // Add nodes
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 16));

        // Add edges
        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.addEdge("A", "C"));
        assertTrue(graph.addEdge("B", "D"));
        assertTrue(graph.addEdge("C", "D"));
        assertTrue(graph.addEdge("D", "E"));

        // try adding a duplicate edge => return false
        assertFalse(graph.addEdge("B", "D"));

        graph.printGraph();
    }

    @Test
    public void addEdges(){
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        System.out.println("Adding Edges Graph Tester:");

        // Add nodes
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 16));

        // Add edges
        String[] name2 = {"B", "C", "D", "E"};
        assertTrue(graph.addEdges("A", name2));

        // try adding a duplicate edge => return false
        assertFalse(graph.addEdges("A", name2));

        graph.printGraph();
    }

    @Test
    public void removeNode(){

        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        System.out.println("Removing Node Graph Tester:");

        // Add nodes
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 16));

        // Add edges
        assertTrue(graph.addEdge("A", "C"));
        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.addEdge("B", "D"));
        assertTrue(graph.addEdge("C", "D"));
        assertTrue(graph.addEdge("D", "E"));

        // Remove node
        System.out.println("Original graph:");
        graph.printGraph();

        graph.removeNode("C");

        System.out.println("Graph after removing node C:");
        graph.printGraph();
    }

    @Test
    public void removeNodesTest(){
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        System.out.println("Removing Nodes Graph Tester:");

        // Add nodes
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 16));

        String[] name = {"A", "B", "C", "E"};

        System.out.println("Original graph:");
        graph.printGraph();

        graph.removeNodes(name);
        System.out.println("Graph after removing nodes A, B, C, E:");
        graph.printGraph();
    }

    @Test
    public void testDFS() {
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 18));
        assertTrue(graph.addNode("F", 20));
        assertTrue(graph.addNode("G", 22));


        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.addEdge("A", "F"));
        assertTrue(graph.addEdge("A", "G"));
        assertTrue(graph.addEdge("B", "C"));
        assertTrue(graph.addEdge("E", "C"));
        assertTrue(graph.addEdge("E", "F"));
        assertTrue(graph.addEdge("G", "F"));
        assertTrue(graph.addEdge("C", "D"));
        graph.printGraph();

        System.out.println("DFS path:");
        System.out.println(Arrays.toString(graph.DFS("A", "D")));


    }

    @Test
    public void testBFS(){
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();
        assertTrue(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 4));
        assertTrue(graph.addNode("C", 10));
        assertTrue(graph.addNode("D", 12));
        assertTrue(graph.addNode("E", 18));
        assertTrue(graph.addNode("F", 20));
        assertTrue(graph.addNode("G", 22));


        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.addEdge("A", "F"));
        assertTrue(graph.addEdge("A", "G"));
        assertTrue(graph.addEdge("B", "C"));
        assertTrue(graph.addEdge("E", "C"));
        assertTrue(graph.addEdge("E", "F"));
        assertTrue(graph.addEdge("G", "F"));
        assertTrue(graph.addEdge("C", "D"));
        graph.printGraph();

        System.out.println("BFS path:");
        System.out.println(Arrays.toString(graph.BFS("A", "D")));
    }

    @Test
    public void testConstructGraph() throws IOException {
        UndirectedGraph<String, Integer> graph = new UndirectedGraph<>();

        graph.read("graph.txt");
    }

    @Test
    public void readWordGraphTest() throws IOException {
        WordLadder wd = new WordLadder();

        wd.readWordGraph("readWordGraphTest");
    }




}
