My name: Jiayi Peng
===================Projects I chose =============
Problems I chose: Initially, I chose Problem 1 because it seems that I can learn more about string manipulation by doing that project. However, I only finished 1-5 subproblems and realized it took way too much time to implement a graph data structure. I can only devote two days to this assignment, so I started working on Problem 2, which is finished in 4 hours.

I am using NetBeans as IDE and Java as programming language. See the two snapshots on where my java files and the input files reside. In the snapshots, you can see I have two NetBeans project folders under NetBeansProjects folder: SalesTax(problem 2) and Trains (problem 1). Each of them contains the input files and a src folder where all the java files reside. Note that in NetBeans projects, the input files should be in the same directory as src folder, not inside it. (see snapshots)

For Problem 1: 
	source files: Trains.java Data.java Station.java
	input files: input.txt
For Problem 2: 
	source files: SalesTax.java Item.java
	input files: Input1.txt Input2.txt Input3.txt
===================How to Compile and Run My Projects=============

The folders I submit are the stripped version of the project folder in the snapshot: SalesTax and Trains. In order to run my project, you need to create NetBeans projects using NetBeans, or you can compile them in other IDEs. I havent figured out how to set class path for "java" command on Mac terminal, so I don't know how to run my project on command line. However, since IDEs set the path for 'java' command automatically, I suggest you use a familar IDE to compile and run my projects. 

By the way, if you encounter the same error : "Could not find or load main class" in NetBeans as the error I got when compiling and running my projects on Mac terminal, you just click on "clean and build project" button in NetBeans to fix this error.

==============design rationale======================================== 
1. the output is not saved to a file. Rather, they are just printed out in the console using system.out.println().
2. the main() methods are in Trains.java and SalesTax.java. They executes all the logic for solving each problem.
==============Program Output======================================== 
See the output1 and output2 snapshots for the output from project 1 and project 2, respectively
==============Program Correctness======================================== 
For problem 1, I only managed to finish 1-5 correctly.

For problem 2, a very small proportion of the TOTAL TAX (e.g in Input3.txt) are 5 cents higher than the correct value. I am rounding up to the nearest .05 dollars on the basic tax as well as the import tax, which is different from rounding up to the nearest .05 dollars on the sum of them(TOTAL TAX). This might explain where the difference of 5 cents on TOTAL TAX comes from.
