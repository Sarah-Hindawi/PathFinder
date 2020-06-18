import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AirlineConnections {

    //The main array list
    private ArrayList<AdjacencyList> list;
    //A stack that will store the path between two airports
    private Stack<String> stack;
    //The number of flights
    private int count;

    public AirlineConnections(String fileName) throws IOException {
        list = new ArrayList<>();
        stack = new Stack<>();
        count = 0;
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNext()) {
            String line = in.nextLine();
            //first three letters is the departure airport
            String source = line.substring(0, 3);
            String destination = line.substring(4, 7);
            //adding the airports to the array list
            addAirport(source);
            addAirport(destination);
            //adding a flight between the two airports
            addFlight(source, destination);
        }
    }

    public void addAirport(String vertexElement) {
        //if the airport already exists in the list
        if (vertexIndex(vertexElement) != -1) return;
        //creating a new adjacencyList object
        AdjacencyList adjacencyList = new AdjacencyList();
        //a vertex that represents the airport
        Vertex vertex = new Vertex(vertexElement);
        //the array list will hold the airport as its instance variable of Vertex
        adjacencyList.setVertex(vertex);
        //adding the newly created array list to the main one
        list.add(adjacencyList);
    }

    public void addFlight(String DEP, String DES) {
        //the index of the departure airport in this array list
        int depIndex = vertexIndex(DEP);
        //adds to its array list a flight by adding the connected airport
        boolean added = list.get(depIndex).addNeighbour(DES);
        if (added)
            //increase the number of flights
            count++;
    }

    public void removeFlight(String DEP, String DES) {
        int depIndex = vertexIndex(DEP);
        //calling remove upon the list to delete the flight
        boolean removed = list.get(depIndex).removeNeighbour(DES);
        //if the flight exists and removed decrease the number of flights
        if (removed) count--;
            //otherwise print no flight
        else System.out.println("There is no such flight!");
    }

    public int totalCount() {
        return count;
    }

    public Stack<String> findFlight(Vertex vertex, Vertex targetVertex) {
        //set the state of the current vertex as visited
        vertex.setLabel("VISITED");
        //then add it to the path
        stack.push(vertex.element());
        //if it was the destination airport
        if (stack.peek().equals(targetVertex.element())) {
            //get out of the recursion method and return the stack
            return stack;
        }
        //getting the list of neighbouring airports of the current one
        AdjacencyList adj = list.get(vertexIndex(vertex.element()));
        //for each one of them
        for (Vertex neighbour : adj.getNeighbours()) {
            //if its state wasn't visited before
            if (neighbour.getLabel().equals("UNEXPLORED") && !stack.contains(neighbour.element())) {
                //call the current method about that vertex
                findFlight(neighbour, targetVertex);
                //break the for loop once the destination airport was reached
                if (stack.peek().equals(targetVertex.element())) {
                    return stack;
                }
            }
        }
        //backtracking
        stack.pop();
        return stack;
    }


    public int vertexIndex(String vertex) {
        //for each element of the array list
        for (int i = 0; i < list.size(); i++) {
            AdjacencyList adj = list.get(i);
            //if the vertex of the adjacencyList associated is the target return the index
            if (adj.getVertex().element().equals(vertex)) return i;
        }
        //if it wasn't found in the list return -1
        return -1;
    }


    public static void main(String[] args) throws IOException {
        String path = new java.io.File(".").getCanonicalPath() + "\\src\\routes.csv";

        AirlineConnections g = new AirlineConnections(path);

        Scanner in = new Scanner(System.in);
        System.out.println("The departure airport? ");
        String DPR = in.nextLine().toUpperCase();
        System.out.println("The arrival airport? ");
        String ARV = in.nextLine().toUpperCase();

        Vertex depVertex = new Vertex(DPR);
        Vertex arvVertex = new Vertex(ARV);

        for (String v : g.findFlight(depVertex, arvVertex)) System.out.print(v + " ");

    }
}