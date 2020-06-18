public class Vertex {

    private String element;
    private String label;

    /**
     * constructor
     * @param element the name of the airport
     */
    public Vertex(String element) {
        this.element = element;
        label = "UNEXPLORED";
    }

    /**
     *
     * @param state the state of the object
     */
    public void setLabel(String state) {
        this.label = state;
    }

    /**
     *
     * @return the name of the airport
     */
    public String element() {
        return element;
    }

    /**
     *
     * @return the state
     */
    public String getLabel() {
        return label;
    }
}
