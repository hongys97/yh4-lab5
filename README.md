# Lab5
LinkedList

1) The 1 parameter constructor assumes that the new node does not have
any nodes that come before or after it- namely, previousElement and
nextElement are null. Therefore, the if-statements become unnecessary
because we do not have to check that they exist.

2) The indexOf method essentially iterates through the list and keeps track of whether the value passed through the parameter matches any value in the list. As such, it can also tell us whether the list contains a value or not. However, because indexOf has to keep track of value i to return the specfic index at which this value occurs, contains does not suffice to serve the purpose of indexOf.

3) There isn't any point to having a method that "removes after" as
opposed to one that simply "removes." First, we already have to check
whether the node we want to remove isn't null. If we were to implement
a "removes after" method, we would have to check for the existance of
both the node we want to remove after, and the node we want to remove.
It is simply more efficient to remove a specified node.

4) Case 1: Add to the front of the list:
Case 2: Add to the end of the list:
Case 3: Add at any other index:
By having "add" handle these three cases, we can consolidate the
three methods, addFirst, addLast, and add into one single method.

5) This file is 12KB without the thought questions, as opposed to
the original source file's 13KB!
