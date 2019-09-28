# Path search

The program reads in a map file that specifies the map in the following format. The file name is specified on the command line. Map consists of digits between 0 and 5, separated by spaces. These numbers represent the movement cost for moving to a given space on the grid. The number 0 is a special case and is considered impassable terrain. The numbers 1-5 are the number of turns required to move to the given square, with 1 being the lowest cost and 5 being the highest. There is no cost for moving to the starting location.  

To run the project from the command line type the following:\
	java -jar "Search.jar" 'Filename' 'Search algorithm'

Filename - path to the file with stored data\
Search algorithm: BFS, AS, IDS (accepts only one at a time)

## Structure of the file
	1. first line - size of the map given by 2 integers: row column
	2. position of the start: row column 
	3. position of the goal: row column
	4. map with the given number of rows and columns
	
	The following is an example of the map format:
	
	5 7
	1 2
	4 3
	2 4 2 1 4 5 2
	0 1 2 3 5 3 1
	2 0 4 4 1 2 4
	2 5 5 3 2 0 1
	4 3 3 2 1 0 1

	



## Output structure
	- Number of expanded nodes
	- Maximum number of nodes stored in memory at one time
	- Path cost
	- Path
	- Time to execute search in miliseconds

### No solution found in 3 minutes and program still runs, stop searching and display
	- Number of expanded nodes until that time
	- Maximum number of nodes stored in memory at one time until that time
	- Path cost: -1 (path wasnt found)
	- Time
