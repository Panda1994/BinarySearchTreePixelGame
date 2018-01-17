# BinarySearchTreePixelGame
//The program that displays the graphical objects will receive as input a file containing a list of names of
image files, each corresponding to a graphical object. The objects will be rendered on a window and the user
will move some them around using the keyboard. Graphical objects cannot overlap, so your program will
allow an object to move only when its movement would not cause it to overlap with other objects or with the
borders of the window. For this assignment, there will be four kinds of graphical objects:
• fixed objects, which cannot move
• objects that can be moved by the user
• objects that are moved by the computer
• target objects, that disappear when the user-controlled objects run into them.
These graphical objects will be part of a “pac-man”-like game. Objects moved by the computer will chase
the user controlled ones. These latter in turn will try to get rid of the target objects. Fixed objects constrain
the movement of the mobile ones.
We will provide code for reading the input file, for displaying the graphical objects and for reading the
user input. You will have to write code for storing the objects and for detecting overlaps between them.//
