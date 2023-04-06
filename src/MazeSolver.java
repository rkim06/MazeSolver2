/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Queue;
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

        //this while loop utilizes the stack's popping property
        //to reverse the stack and add back to arraylist in proper order
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
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell end = maze.getEndCell();
        MazeCell start = maze.getStartCell();
        MazeCell currentCell = start;

        MazeCell north;
        MazeCell east;
        MazeCell south;
        MazeCell west;

        Queue<MazeCell> mazeQueue= new Queue <MazeCell>();

        while(mazeQueue.size() >= 0){
            //if the north cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow()-1, currentCell.getCol())){
                north = maze.getCell(currentCell.getRow()-1, currentCell.getCol());

                mazeQueue.add(north);
                north.setParent(currentCell);
            }
            //if the east cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()+1)){
                east = maze.getCell(currentCell.getRow(), currentCell.getCol()+1);

                mazeQueue.add(east);
                east.setParent(currentCell);
            }
            //if the south cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow()+1, currentCell.getCol())){
                south = maze.getCell(currentCell.getRow()+1, currentCell.getCol());

                mazeQueue.add(south);
                south.setParent(currentCell);
            }
            //if the west cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()-1)){
                west = maze.getCell(currentCell.getRow(), currentCell.getCol()-1);

                mazeQueue.add(west);
                west.setParent(currentCell);
            }
        }

        currentCell = mazeQueue.remove();

        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell end = maze.getEndCell();
        MazeCell start = maze.getStartCell();
        MazeCell currentCell = start;

        MazeCell north;
        MazeCell east;
        MazeCell south;
        MazeCell west;

        Stack <MazeCell> mazeStack = new Stack <MazeCell>();
        mazeStack.push(start);

        //adds valid cells to a stack of cells
        //adds them in backwards order to pop out in right precedence
        while(currentCell != end){

            //if the west cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()-1)){
                west = maze.getCell(currentCell.getRow(), currentCell.getCol()-1);

                mazeStack.push(west);
                west.setParent(currentCell);
            }
            //if the south cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow()+1, currentCell.getCol())){
                south = maze.getCell(currentCell.getRow()+1, currentCell.getCol());

                mazeStack.push(south);
                south.setParent(currentCell);
            }
            //if the east cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow(), currentCell.getCol()+1)){
                east = maze.getCell(currentCell.getRow(), currentCell.getCol()+1);

                mazeStack.push(east);
                east.setParent(currentCell);
            }
            //if the north cell is a valid cell add to stack
            if(maze.isValidCell(currentCell.getRow()-1, currentCell.getCol())){
                north = maze.getCell(currentCell.getRow()-1, currentCell.getCol());

                mazeStack.push(north);
                north.setParent(currentCell);
            }

            //sets the next cell (by precedence) to the
            currentCell = mazeStack.pop();
        }

        return getSolution();
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
