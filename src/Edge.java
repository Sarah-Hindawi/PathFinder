public class Edge {

    private int element;
    private String label;
    private String direction;

    public Edge(int element, String direction) {
        this.element = element;
        this.direction = direction;
        label = "UNEXPLORED";
    }

    public Edge(int element) {
        this.element = element;
        label = "UNEXPLORED";
    }

    public void setState(String state) {
        this.label = state;
    }

    public int element() {
        return element;
    }

    public String getLabel() {
        return label;
    }

    public String getDirection() {
        return direction;
    }
}
