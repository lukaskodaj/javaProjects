package depthalgorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class DepthAlgorithm {

    private Vertex[] vertexField;
    private Edge[] edgeField;
    private Scanner scan;
    private ArrayList<Vertex> stack;
    private ArrayList<Vertex> result;
    char check = 0;

    public DepthAlgorithm() {

        scan = new Scanner(System.in);
        stack = new ArrayList<>();
        result = new ArrayList<>();

        enterEdgesAndVertex();

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

        char helpVertex = 65;
        stack.add(new Vertex(helpVertex));
        result.add(new Vertex(helpVertex));

        while (smallestVertex(helpVertex) < 200 && stack.size() != 0) {

            char inStack = smallestVertex(helpVertex);
            check = 0;
            for (int i = 0; i < result.size(); i++) {
                if (inStack != result.get(i).getName()) {
                    check = 1;
                } else {
                    check = 0;
                    break;
                }
            }

            if (check == 0) {
                for (Vertex vertex : stack) {
                    if (vertex.getName() == helpVertex) {
                        stack.remove(vertex);
                        
                        if(stack.size() > 0)
                        helpVertex = stack.get(stack.size() - 1).getName();
                        
                        break;
                    }
                }
            }

            if (check == 1) {
                stack.add(new Vertex(inStack));
                result.add(new Vertex(inStack));
                helpVertex = inStack;
            }


        }

    }

    public char smallestVertex(char startName) {

        char helpVertex = startName;
        char minVertex = 200;
        boolean change = false;

        for (int i = 0; i < edgeField.length; i++) {
            check = 0;
            for (int j = 0; j < result.size(); j++) {
                if (edgeField[i].getStartName() == helpVertex && edgeField[i].getLastName() != result.get(j).getName()) {
                    check = 1;
                } else {
                    check = 0;
                    break;
                }
            }
            
            if (edgeField[i].getStartName() == helpVertex && check == 1) {
                if (minVertex > edgeField[i].getLastName()) {
                    minVertex = edgeField[i].getLastName();
                }
                change = true;
            }

        }
        
        if(check == 0 && change == false) {
            return helpVertex;
        }

        return minVertex;

    }

    public void writeResult() {
        
        System.out.println();
        System.out.println("Výsledok: ");
        System.out.print("Stack: ");
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i).getName() + " ");
        }
        System.out.println();
        System.out.print("Result: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i).getName() + " ");
        }
        System.out.println();
    }

}
