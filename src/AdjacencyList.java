import java.util.ArrayList;

public class AdjacencyList {

    //The airport
    private Vertex vertex;
    //a list of the airports connected to vertex
    private ArrayList<Vertex> verticesList;


    /**
     * constructor
     */
    public AdjacencyList() {
        verticesList = new ArrayList<>();
    }

    /**
     * @param vertex the airport
     */
    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    /**
     * @param neighbour an airport linked to vertex
     */
    public boolean addNeighbour(String neighbour) {
        boolean added = true;
        Vertex vertex = new Vertex(neighbour);
        //if the list of connected airports doesn't already contains the airport
        if (!verticesList.contains(vertex)) added = verticesList.add(vertex);
        //whether it was added to the list
        return added;
    }

    /**
     *
     * @return the main vertex
     */
    public Vertex getVertex() {
        return vertex;
    }

    /**
     *
     * @param neighbour connected airport
     * @return whether that airport was removed or not
     */
    public boolean removeNeighbour(String neighbour) {
        boolean removed=false;
        for (int i = 0; i < verticesList.size(); i++) {
            Vertex target = verticesList.get(i);
            if (target.element().equals(neighbour))
            {
                removed=true;
                verticesList.remove(i);
                break;
            }
        }
        return removed;
    }

    /**
     *
     * @return a list of the connected airports
     */

    public ArrayList<Vertex> getNeighbours() {
        return verticesList;
    }

}