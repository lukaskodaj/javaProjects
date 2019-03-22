/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package widthalgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Lukáš
 */
public class WidthAlgorithm {

    Scanner scan;
    private Vertex[] vertexField;
    private Edge[] edgeField;
    private ArrayList<Vertex> queue;
    private ArrayList<Vertex> result;
    
    public WidthAlgorithm() {
        scan = new Scanner(System.in);
        queue = new ArrayList<>();
        result = new ArrayList<>();
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
        
        queue.add(new Vertex((char)65));
        result.add(new Vertex((char)65));
        
        char controlVertex = 0;
        
        while(queue.size() != 0) {
            controlVertex = queue.get(0).getName();
            queue.remove(0);
            
            ArrayList<Character> helpQueue = new ArrayList<>();
            
            for (Edge edge : edgeField) {
                if(edge.getStartName() == controlVertex && checkDuplicity(edge.getLastName()))
                    helpQueue.add(edge.getLastName());
            }
            
            if(helpQueue.size() != 0) {
            
            // zoradi od najmensieho po najvacsie
            Collections.sort(helpQueue);
            
            for (Character charr : helpQueue) {
                queue.add(new Vertex(charr));
                result.add(new Vertex(charr));
            }
            
            }
        }
    }
    
    
    public boolean checkDuplicity(char lastName) {
        
        for (Vertex vertex : result) {
            if(vertex.getName() == lastName) {
                return false;
            } 
        }
        
        return true;
    }
    
    public void writeResult() {
        System.out.println();
        System.out.println("Výsledok: ");
        System.out.print("Queue: ");
        for (int i = 0; i < queue.size(); i++) {
            System.out.print(queue.get(i).getName() + " ");
        }
        System.out.println();
        System.out.print("Result: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i).getName() + " ");
        }
        System.out.println();
    }
    
}
