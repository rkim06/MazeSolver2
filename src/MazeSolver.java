/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        Stack<MazeCell> cellStack = new Stack<MazeCell>();
        ArrayList<MazeCell> cellList = new ArrayList<MazeCell>();

        MazeCell currentCell = maze.getEndCell();
        MazeCell end = maze.getEndCell();
        MazeCell start = maze.getStartCell();

        cellStack.push(currentCell);

        while(!currentCell.equals(start)){
            //adds the parent of the cell to the stack
            //the parent is the previous cell
            currentCell = currentCell.getParent();
            cellStack.push(currentCell);
        }

        //utilizes stack to flip the order to correct order
        //puts the cells into an arraylist
//        for(int i = 0; i<cellStack.size(); i++){
//            cellList.add(cellStack.pop());
//            i--;
//        }
//
//        cellStack.push(cellStack.pop().getParent());

        while(cellStack.size()>0){
            cellList.add(cellStack.pop());
        }

        // Should be from start to end cells
        //returns the arrayList of cells
        return cellList;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        Stack <MazeCell> cellStack = new Stack <MazeCell>();
        cellStack.push(maze.getStartCell());

        MazeCell currentCell = maze.getStartCell();

        //when to setParent?

        while(cellStack.size()!= 0){

        }

        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell end = maze.getEndCell();
        MazeCell start = maze.getStartCell();
        MazeCell currentCell = start;

        MazeCell <Stack> mazeStack = new MazeCell <Stack>();

        while(currentCell != end){

        }

        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
