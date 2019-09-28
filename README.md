# Path search

To run the project from the command line type the following:\
	java -jar "Search.jar" 'Filename' 'Search algorithm'

Filename - path to the file with stored data\
Search algorithm: BFS, AS, IDS (accepts only one at a time)

## Structure of the file
	1. first line - size of the map given by 2 integers: row column
	2. position of the start: row column 
	3. position of the goal: row column
	4. map with the given number of rows and columns



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
