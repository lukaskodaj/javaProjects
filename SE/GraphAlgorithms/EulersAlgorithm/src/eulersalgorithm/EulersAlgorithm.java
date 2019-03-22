package eulersalgorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class EulersAlgorithm {

    private Scanner scan;
    private Vertex[] vertexField;
    private Edge[] edgeField;
    private ArrayList<Character> foundVertex;
    private char controlVertex;
    private boolean end;
    private Edge foundEdge;
    private ArrayList<Edge> result;

    public EulersAlgorithm() {
        scan = new Scanner(System.in);
        foundVertex = new ArrayList<>();
        result = new ArrayList<>();
        end = false;
    }

    public void enterEdgesAndVertex() {

        System.out.println("Zadaj pocet Vrcholov");
        vertexField = new Vertex[scan.nextInt()];
        System.out.println("Zadaj pocet Hran:");
        edgeField = new Edge[scan.nextInt() * 2];

        for (int i = 0; i < vertexField.length; i++) {
            vertexField[i] = new Vertex((char) (i + 65));
        }

        for (int i = 0; i < edgeField.length / 2; i++) {
            System.out.println("Zadaj hranu č. " + (i + 1));
            edgeField[i] = new Edge(scan.next().charAt(0), scan.next().charAt(0));
        }

        for (int i = edgeField.length / 2; i < edgeField.length; i++) {
            edgeField[i] = new Edge(edgeField[i - edgeField.length / 2].getLastName(), edgeField[i - edgeField.length / 2].getStartName());
        }

    }

    public void showEdgesAndVertex() {

        System.out.print("Vrcholy:");
        for (int i = 0; i < vertexField.length; i++) {
            System.out.print(" (" + vertexField[i].getName() + ")");
        }

        System.out.println();
        for (int i = 0; i < edgeField.length; i++) {
            System.out.println("Hrana (" + edgeField[i].getStartName() + ", " + edgeField[i].getLastName() + ")");
        }

    }

    public void algorithm() {

        System.out.println("Zadaj štartovný vrchol: ");
        controlVertex = scan.next().charAt(0);
        foundVertex.add(controlVertex);

        while (end == false) {

            faEgdeResearch();
            controlVertex = foundEdge.getLastName();
            checkEnd();

        }
    }

    public boolean foundVertexResearch(char vertex) {
        for (Character charr : foundVertex) {
            if (vertex == charr) {
                return true;
            }
        }
        return false;
    }

    public void faEgdeResearch() {

        for (Edge edge : edgeField) {

            if (edge.isUsed() == false) {
                if (edge.getStartName() == controlVertex && edge.isFirstArrive() == false && enableSecArrive() == false) {

                    if (foundVertexResearch(edge.getLastName()) == false) {

                        edge.setFirstArrive(true);
                        reverseEdge(edge.getStartName(), edge.getLastName(), 1);
                        foundVertex.add(edge.getLastName());
                    }

                    foundEdge = new Edge(edge.getStartName(), edge.getLastName());
                    result.add(foundEdge);
                    edge.setUsed(true);
                    reverseEdge(edge.getStartName(), edge.getLastName(), 2);
                    return;

                }
            }
        }

    }

    public void reverseEdge(char start, char last, int type) {

        for (Edge reverseEdge : edgeField) {
            if (reverseEdge.getStartName() == last && reverseEdge.getLastName() == start) {
                if (type == 1) {
                    reverseEdge.setFirstArrive(true);
                } else if (type == 2) {
                    reverseEdge.setUsed(true);
                }

            }
        }

    }

    public boolean enableSecArrive() {

        for (Edge edge : edgeField) {
            if (edge.isUsed() == false) {
                return false;
            }
        }
        return true;

    }

    public boolean checkEnd() {

        for (Edge edge : edgeField) {
            if (edge.isUsed() == false) {
                return false;
            }
        }
        end = true;
        return true;

    }

    public void writeResult() {

        System.out.println("------------------------------------------------------");
        System.out.println("* Result *");
        System.out.println("Objavene vrcholy: ");
        for (Character charr : foundVertex) {
            System.out.print(charr + " ");
        }

        System.out.println();
        System.out.println("Hrany prveho prichodu: ");
        for (Edge edge : edgeField) {
            if (edge.isFirstArrive()) {
                System.out.print("Edge(" + edge.getStartName() + ", " + edge.getLastName() + ")  ");
            }
        }
        System.out.println();
        System.out.println(" ");
        System.out.println("Vysledne hrany: ");
        for (Edge edge : result) {
            System.out.println("Edge(" + edge.getLastName() + ", " + edge.getStartName() + ")");
        }

        System.out.println();
        System.out.println("Vysledny sled: ");
        System.out.print(result.get(0).getStartName() + " ");
        for (int i = 1; i < result.size(); i++) {
            System.out.print(result.get(i).getLastName() + " ");
        }
        System.out.println();
        System.out.println("------------------------------------------------------");
    }

}
