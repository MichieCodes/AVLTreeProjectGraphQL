# Project Two: AVL Trees & Binary Trees
I created a Java API that generates an AVL Tree from a list of books from a default file or from the database. A user can upload a file listing the ISBN, title, and author of an selection of books, and the server will read the files into the database. After the Tree is created the user can view the logs to see all the rotations that were done to to maintain the AVL Tree balanced property.

The API can also randomly generate a Binary Tree and check to see if the Binary Tree is a BST and an AVL Tree. The logs will show if the Tree is a BST and/or AVL

The API is deployed to Heroku, while the Fontend is deployed to Github pages. Here is the link: https://michiecodes.github.io/AVLTreeProject/

## Meeting the Assignment Requirements
#### Part A
1. Text file with enteries can be found in the resources folder
2. Function to read book records from file can be found in Services > AVLTreeService > createTreeFromFile
3. Function to create AVL tree from file can be found in Services > AVLTreeService > createTreeFromFile
4. Modified AVLTree class can be found in Trees > AVLTree
5. Utilization of the AVLTree insert function can be found in Services > AVLTreeService > createTreeFromFile
6. To see the output, on the website, view the logs.

#### Part B
7. Random ISBN Generator function can be found in Services > BinaryTreeService > generateISBNList
8. Random Binary Tree Generator function can be found in Trees > AVLTree > generateRandomBinaryTree
9. BST Validator function can be found in Services > BinaryTreeService > checkBST
10. AVL Tree Validator function can be found in Trees > AVLTree > buildAVLFromBinaryTree

#### Testing if a randomly generated Binary Tree is an AVL Tree
To validate the AVL property, I create a breadth first traversal of the Binary Tree, and then I create a AVL Tree from that traversal list. If any rotations are performed inside the AVLTree, then the Binary Tree is not an AVL Tree.

### Installation
- To run the API on your local machine
    - In your favorite terminal type **gradlew bootRun** (or **gradle bootRun** for Unix/Linux machines)
- To run the Frontend on your local machine, pull the code from my repo: https://github.com/MichieCodes/AVLTreeProject
    - In your favorite terminal run **npm install** to install the needed dependencies
    - Run **npm start** to run the server

### File Format For Loading Books From File
- Each entry needs to be on a new line
- <ISBN> <TITLE (underscores in place of spaces)> <Author>
- Example: 9780584812022 Two_of_a_Kind Oliver

### Technologies Used
- FrontEnd
    - React.js
    - HTML/CSS/SASS
- Backend
    - Java Spring Boot
    - GraphQL
    - SQLite

## Available Scripts

In the project directory, you can run:

### `gradlew bootRun`

Runs the API in the development mode.<br />
Make requests to base endpoint [http://localhost:8080](http://localhost:8080).
