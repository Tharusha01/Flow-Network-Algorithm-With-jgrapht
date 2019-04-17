import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
* w1697753 - Ishan Tharusha Wijayabahu
*/

public class JGraph extends JApplet {


    private static int edges; //variable. which holds edges number.
    private static ArrayList<String> path = new ArrayList<>(); //String ArrayList. Which holds weights.
    private static ArrayList<String> pathDis = new ArrayList<>(); //String ArrayList. Which holds paths.
    private static ArrayList<String> pathFlow = new ArrayList<>(); //String ArrayList. Which holds path flow.

    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 1000); //Set JFame size

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    public static String maxFlowDisplay; // String variable.Which holds maximum flow
    private static int sink; //Integer Variable. Which holds sink


    public void displayGUI(){

        /*
        * Creating object of MaXFLow class
        */
        MaxFlow maxFlow = new MaxFlow();
        /*
        * Assigning values to above created Variables and ArrayLists.
        */
        sink = maxFlow.lastIndex;
        edges = maxFlow.lastIndex;
        path = maxFlow.weight;
        pathFlow = maxFlow.finalPathFlow;
        pathDis = maxFlow.path;

        /*
        * Displaying data in console.
        */
        System.out.println("Edges " + (sink+1));
        System.out.println("Source : 0");
        System.out.println("Sink : "+sink +"\n");

        maxFlowDisplay = "The maximum possible flow is " +
                maxFlow.fordFulkerson(maxFlow.generateNodes(), 0, sink);

        System.out.println();

        System.out.println("Original graph");
        print2D(maxFlow.twoDim);
        System.out.println();

        System.out.println("Connected path with weights");
        printArrayList(pathDis);
        System.out.println();

        System.out.println("Final Residual graph");
        print2D(maxFlow.rGraph);
        System.out.println();

        printArrayList(maxFlow.finalPathFlow);
        System.out.println();
        System.out.println(maxFlowDisplay);



        /*
        * Creating object of this class.
        */
        JGraph applet = new JGraph();
        //JGraph applet1 = new JGraph();
        applet.display();
        applet.init();

        /*
        * Set JFrame values.
        */

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Algorithm Coursework - Flow Of NetworkS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    @Override
    public void init()
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        /*
        * Adding vertexes
        */
        for(int i = 0; i <= edges; i++){
            String v = Integer.toString(i);
            g.addVertex(v);

        }

        /*
        * Adding edges.
        */
        for(String path : path){
            String [] items = path.split(",");
            g.addEdge(items[0],items[1]);
        }

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 370;
        layout.setX0((DEFAULT_SIZE.width / 2.40) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.40) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());

        // that's all there is to it!...
    }

    public void display(){

        JLabel maxFlow;
        JLabel source;
        JLabel sinkD;
        JLabel vertex;

        /*
        * JList for display paths
        */
        JList pathD = new JList(pathFlow.toArray());
        pathD.setFont(pathD.getFont().deriveFont(16f));
        /*
         * JList for display path flow
         */
        JList weightD = new JList(pathDis.toArray());
        weightD.setFont(weightD.getFont().deriveFont(16f));

        setLayout(new FlowLayout());

        /*
        * JLable for display maximum possible flow.
        */
        maxFlow = new JLabel(maxFlowDisplay);
        maxFlow.setFont(maxFlow.getFont().deriveFont(18f));
        maxFlow.setHorizontalAlignment(JLabel.LEFT);
        /*
         * JLable for display Source.
         */
        source = new JLabel("Source : 0");
        source.setFont(source.getFont().deriveFont(18f));
        source.setHorizontalAlignment(JLabel.LEFT);
        /*
         * JLable for display Sink.
         */
        sinkD = new JLabel("Sink : "+Integer.toString(sink));
        sinkD.setFont(sinkD.getFont().deriveFont(18f));
        sinkD.setHorizontalAlignment(JLabel.LEFT);
        /*
         * JLable for display vertexes.
         */
        vertex = new JLabel("Vertex : "+Integer.toString(sink+1));
        vertex.setFont(vertex.getFont().deriveFont(18f));
        vertex.setHorizontalAlignment(JLabel.LEFT);
        //System.out.println(maxFlowDisplay);

        /*
        * Adding above created stuffs.
        */
        add(vertex);
        add(source);
        add(sinkD);
        add(weightD);
        add(pathD);
        add(maxFlow);

    }

    /*
    * Method for display 2d arrays.
    */
    private static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    /*
    * Method for display String ArrayLists.
    */
    private static void printArrayList(ArrayList<String> arrayList)
    {
        // Loop through all rows
        for (String row : arrayList)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(row);
    }
}
