package lukaskodaj.planner;

public class Filter {

    private String type;

    private String order;

    public Filter()
    {

    }

    public Filter(String type, String order) {
        this.type = type;
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
