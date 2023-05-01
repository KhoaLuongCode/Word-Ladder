import java.io.*;
import java.util.*;

public class UndirectedGraph<K, V> {

    // create array of verticies
    private ArrayList<Node> graph;

    int size;

    public UndirectedGraph() {
        graph = new ArrayList<>();
        size = 0;
    }

    public ArrayList<Node> getNodes() {
        return graph;
    }

    public Node getNode(K valueOf) {
        return findNode(valueOf);
    }

    static class Node<K, V>  {

        public Node parent;
        K name;
        V data;
        boolean visited; // used for traversals

        ArrayList<Edge> edges;

        public Node(K name, V data) {
            this.name = name;
            this.data = data; // data can be null
            edges = new ArrayList<>();
        }

        public K getName() {
            return name;
        }

        public V getData() {
            return data;
        }


    }

    // create a private static class Edge
    private static class Edge<K, V> {
        Node<K, V> vertexTo;

        public Edge(Node<K, V> vertexTo) {
            this.vertexTo = vertexTo;
        }

        public Node<K, V> getVertexTo() {
            return vertexTo;
        }
    }

    // creates a method that add Node with parameter of K name and V data
    public boolean addNode(K name, V data) {

        // checks for duplicates
        if (findNode(name) != null) {
            return false;
        }
        // check for same data
        for (Node<K, V> n : graph) {
            if (n.data == data) {
                return false;
            }
        }
        // create a new vertex with name and data
        Node<K, V> newVertex = new Node<>(name, data);

        // add the new vertex to the graph
        graph.add(newVertex);
        // increment size
        size++;
        return true;

    }

    // creates a boolean addNodes(K[] names, V[] data) - adds a list of nodes to the graph and checks for duplicates
    public boolean addNodes(K[] name, V[] data) {

        if (name.length != data.length) {
            throw new IllegalArgumentException("The length of the name array and the data array must be the same.");
        }

        // checks for duplicates
        for (K k : name) {
            if (findNode(k) != null) {
                return false;
            }
        }
        // create a new vertex with name and data
        for (int i = 0; i < name.length; i++) {
            Node<K, V> newVertex = new Node<>(name[i], data[i]);

            // add the new vertex to the graph
            graph.add(newVertex);
            // increment size
            size++;
        }
        return true;
    }

    // add Edge method that adds an undirected edge from node from to node to
    public boolean addEdge(K from, K to) {

        // Find the nodes for the given names
        Node fromNode = findNode(from);
        Node toNode = findNode(to);

        // Check if either node does not exist, or if they are the same node
        if (fromNode == null || toNode == null || fromNode == toNode) {
            return false;
        }

        // check for duplicates
        for (int i = 0; i < fromNode.edges.size(); i++) {
            Edge<K, V> e = (Edge<K, V>) fromNode.edges.get(i);
            if (e.vertexTo == toNode) {
                return false;
            }
        }

        // Create the new edges to be added
        Edge<K, V> fromEdge = new Edge<K, V>(toNode);
        Edge<K, V> toEdge = new Edge<K, V>(fromNode);

        // Add the edges to the nodes' edge lists
        fromNode.edges.add(fromEdge);
        toNode.edges.add(toEdge);

        return true;
    }

    //  adds an undirected edge from node from to each node in toList
    public boolean addEdges(K from, K[] toList) {

            // Find the node for the given name
            Node fromNode = findNode(from);

            // Check if the node does not exist
            if (fromNode == null) {
                return false;
            }

            // chcek for duplicates
            for (int i = 0; i < fromNode.edges.size(); i++) {
                Edge<K, V> e = (Edge<K, V>) fromNode.edges.get(i);
                for (K k : toList) {
                    Node toNode = findNode(k);
                    if (e.vertexTo == toNode) {
                        return false;
                    }
                }
            }

            // Loop through the toList and add an edge from the fromNode to each node in the toList
            for (K k : toList) {
                // Find the node for the given name
                Node toNode = findNode(k);

                // Check if the node does not exist
                if (toNode == null) {
                    return false;
                }

                // Create the new edges to be added
                Edge<K, V> fromEdge = new Edge<K, V>(toNode);
                Edge<K, V> toEdge = new Edge<K, V>(fromNode);

                // Check if the edge already exists in either node's edge list
                if (fromNode.edges.contains(fromEdge) || toNode.edges.contains(toEdge)) {
                    return false;
                }

                // Add the edges to the nodes' edge lists
                fromNode.edges.add(fromEdge);
                toNode.edges.add(toEdge);
            }
            return true;
    }

    public boolean removeNode(K name) {

        // checking if it exists
        if (findNode(name) == null) {
            return false;
        }

        // loop through the nodes and remove the edges that point to the deleted node
        for (int i = 0; i < size; i++){
            for (int j = 0; j < graph.get(i).edges.size(); j++){
                Edge<K, V> e = (Edge<K, V>) graph.get(i).edges.get(j);
                if (e.vertexTo.name.equals(name)){
                    graph.get(i).edges.remove(j);
                }
            }
        }
        // remove node and decrement size
        for (int i = 0; i < size - 1; i++){
            if (graph.get(i).name.equals(name)){
                graph.remove(i);
            }
        }

        size--;
        return true;
    }

    // removes each node in nodelist and their edges from the graph
    public boolean removeNodes(K[] nodeList) {

        // checking if it exists
        for (K k : nodeList) {
            if (findNode(k) == null) {
                return false;
            }
        }

        // loop through the nodes and remove the edges that point to the deleted node
        for (int i = 0; i < size; i++){
            for (int j = 0; j < graph.get(i).edges.size(); j++){
                Edge<K, V> e = (Edge<K, V>) graph.get(i).edges.get(j);
                for (K k : nodeList) {
                    if (e.vertexTo.name.equals(k)){
                        graph.get(i).edges.remove(j);
                    }
                }
            }
        }

        // remove nodes and decrement size
        for (K k : nodeList) {
            for (int i = 0; i < size; i++){
                if (graph.get(i).name.equals(k)){
                    graph.remove(i);
                    size--;
                }
            }
        }
        return true;
    }

    // print graph and its neighbors
    public void printGraph() {
        // going through array of verticies
        for (int i = 0; i < size; i++){
            StringBuilder line = new StringBuilder(graph.get(i).name + "");


            // adding all neighbors
            for (int j = 0; j < graph.get(i).edges.size(); j++){
                Edge<K, V> e = (Edge<K, V>) graph.get(i).edges.get(j);
                line.append(" ").append(e.vertexTo.name);
            }
            System.out.println(line);
        }

    }

    private Node findNode(K from) {
        for (int i = 0; i < size; i++) {
            if (graph.get(i).name.equals(from)) {
                return graph.get(i);
            }
        }
        return null;
    }


    public K[] DFS(K from, K to){

        Node<K, V> fromNode = findNode(from);
        Node<K, V> toNode = findNode(to);

        // if start or end node is null, return null
        if (fromNode == null || toNode == null) {
            return null;
        }

        // if start == end, return start
        if (fromNode == toNode){
            return (K[]) new Object[]{fromNode.name, toNode.name};
        }

        // set up visited set
        Set<Node> visited = new HashSet<>();
        visited.add(fromNode);

        // start DFS search
        List<K> path = new ArrayList<>();
        path.add(from);

        while (!path.isEmpty()) {
            int index = path.size() - 1;
            Node<K, V> currentNode = findNode(path.get(index));

            if (currentNode == toNode) {
                path.add(to);
                return path.toArray((K[]) new Object[path.size()]);
            }
            boolean allNeighborsVisited = true;
            for (int i = 0; i < Objects.requireNonNull(currentNode).edges.size(); i++) {
                Edge<K, V> edge = (Edge<K, V>) currentNode.edges.get(i);
                Node<K, V> neighborNode = edge.vertexTo;
                if (!visited.contains(neighborNode)) {
                    visited.add(neighborNode);
                    path.add(neighborNode.name);
                    allNeighborsVisited = false;
                    break;
                }
            }
            if (allNeighborsVisited) {
                path.remove(path.size() - 1);
            }
        }
        // path not found
        return null;
    }

    public K[] BFS(K from, K to){
        Set<K> visited = new HashSet<>();
        Map<K, K> parent = new HashMap<>();
        Queue<K> queue = new LinkedList<>();
        ArrayList<K> path = new ArrayList<>();

        queue.add(from);
        parent.put(from, null);

        while(!queue.isEmpty()) {
            Node current = findNode(queue.poll());
            visited.add((K) current.getName());

            if (current.name.equals(to)) {
                K temp = (K) current.getName();
                path.add(0, temp);
                while (parent.get(temp) != null) {
                    temp = parent.get(temp);
                    path.add(0, temp);
                }
                return (K[]) path.toArray();
            }

            for (int i = 0; i < current.edges.size(); i++) {
                Edge<K, V> e = (Edge<K, V>) current.edges.get(i);
                if (!visited.contains(e.getVertexTo().getName())) {
                    parent.put(e.getVertexTo().getName(), (K) current.getName());
                    queue.add(e.getVertexTo().getName());
                    visited.add(e.getVertexTo().getName());
                }
            }
        }
        return (K[]) path.toArray();
    }



    public static <V> UndirectedGraph<String, V> read(String fileName) throws IOException {

        Scanner scanner = new Scanner(new File(fileName));
        UndirectedGraph<String, V> graph = new UndirectedGraph<>();

        // read each line of the file, which contains a vertex and its edges
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            // add the node
            for (String node : parts) {
                graph.addNode(node, null);
            }

            // add neighbors of the node
            for (int i = 1; i < parts.length; i++) {
                graph.addEdge(parts[0], parts[i]);
            }

        }
        scanner.close();
        // print graph
        return graph;
    }


}
