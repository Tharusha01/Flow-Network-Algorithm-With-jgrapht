import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;

public class Edge extends DefaultEdge {

    ArrayList<String> arr = new ArrayList<>();

    private int capacity;

    MaxFlow m = new MaxFlow();
    private int sink = m.lastIndex;



    public void getValue(){
        //String maxFlowDisplay = "The maximum possible flow is " +
                m.fordFulkerson(m.generateNodes(), 0, sink);
        arr = m.pathCapacitiy;

        for(String path : arr){
            System.out.println(path);
            this.capacity = Integer.valueOf(path);
        }
        System.out.println(this.capacity);
    }


    public String toString() {
        return "(" + this.capacity + ")";
    }
}
