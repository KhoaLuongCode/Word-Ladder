import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordLadder {

    static UndirectedGraph<Integer, String> graph;
    static HashMap<String, Integer> h;

    public WordLadder(){
        graph = new UndirectedGraph<>();
        h = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Word Ladder Game");

        // scan
        Scanner scan = new Scanner(System.in);

        // prompt user for first word
        System.out.println("Enter first word: ");
        String firstWord = scan.nextLine();

        // prompt user for second word
        System.out.println("Enter second word: ");
        String secondWord = scan.nextLine();

        // prompt user for filename
        System.out.println("Enter filename: ");
        String filename = scan.nextLine();

        // read file
        WordLadder wd = new WordLadder();
        graph = wd.readWordGraph(filename);
        makeHashTable(graph);

        // ask user what path want to use
        System.out.println("Which path do you want to use? type 1 for DFS, 2 for BFS");
        int choice = scan.nextInt();

        // The BFS/DFS routines will return an array of node names that represent the path. Your program should
        //print the associated node values. Since here the node names are integers and the node values are strings,
        //this will print a list of strings.
        if (choice == 1) {
            //call DFS
            Object[] path = graph.DFS(h.get(firstWord), h.get(secondWord));
            String[] pathValues = new String[path.length];
            for (int i = 0; i < path.length; i++) {
                pathValues[i] = graph.getNode(Integer.parseInt(path[i].toString())).data.toString();
            }
            System.out.println(Arrays.toString(pathValues));


        } else if (choice == 2) {
            // call BFS
            Object[] path = graph.BFS(h.get(firstWord), h.get(secondWord));
            String[] pathValues = new String[path.length];
            for (int i = 0; i < path.length; i++) {
                pathValues[i] = graph.getNode(Integer.parseInt(path[i].toString())).data.toString();
            }
            System.out.println(Arrays.toString(pathValues));

        } else {
            System.out.println("Invalid choice");
        }

        // ask the user if he or she wishes to continue playing the game or stop
        System.out.println("Do you want to continue? Type 1 for yes, 2 for no");
        int choice2 = scan.nextInt();

        // if the user wants to continue, then repeat the process

        if (choice2 == 1){
            main(args);
        }


        // if the user wants to stop, then print "Goodbye" and exit the program

        else {
            System.out.println("Goodbye. Gracias. Merci. Danke. Arrivederci. Sayonara. Zaijian. 再见");
            System.exit(0);
        }


        // close scanner

        scan.close();
    }


    public UndirectedGraph<Integer, String> readWordGraph(String filename) throws IOException {
        graph = new UndirectedGraph<>();
        Scanner scanner = new Scanner(new File(filename));

        // read each line of the file, which contains a vertex, the data, and its edges
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            // add the node and its data
            Integer node = Integer.valueOf(parts[0]);
            String data = parts[1];
            graph.addNode(node, data);
            h.put(data, node);

            // add edges
            for (int i = 2; i < parts.length; i++) {
                String neighbor = parts[i];
                graph.addEdge(node, Integer.valueOf(neighbor));
            }

        }

        scanner.close();
        return graph;
    }

    // a a method that takes the graph, gets the list of nodes and places them into a
    //hashtable where the node data is the key for the hashtable and the node name is the data associated with that key
    public static void makeHashTable (UndirectedGraph <Integer, String > graph){
        // for each node, place the node name as the key and the node data as the associated value
        for (UndirectedGraph.Node node : graph.getNodes()) {
            h.put(node.data.toString(), (Integer) node.name);
        }
    }


}
