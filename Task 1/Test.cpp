
#include "DAG.hpp"

void TestCase1()
{
	std::vector<std::string> data = {"Kian", "Sharon", "Khaila", "Stephen", "Ludwig"};

	std::vector<Edge> edges;

	edges.push_back({0,1}); //    0            Kian
	edges.push_back({0,2}); //  1   2	 Sharon     Khaila
 	edges.push_back({1,3}); // 3 ->  4	Stephen  ->	  Ludwig
	edges.push_back({2,4});  
	edges.push_back({3,4});

	Validate(edges);
	
	std::shared_ptr<Node<std::string>> DAG = buildDAG(data, edges);
	
	std::vector<std::string> getinfo = {}; //list of edges are returned

	returnList(DAG, getinfo);

	printDAG(getinfo); //print the DAG in order of DFS

	deleteNode("Sharon", DAG, 0); //delete first child of Sharon

	std::vector<std::string> getinfo2 = {}; //list of edges are returned

	returnList(DAG, getinfo2);

	printDAG(getinfo2);
}

void TestCase2()
{
	std::vector<int> data = {20, 5};

	std::vector<Edge> edges;

	edges.push_back({0,1});
	edges.push_back({1,0}); 
 	
	Validate(edges);
	
	std::shared_ptr<Node<int>> DAG = buildDAG(data, edges);
	
}

void TestCase3()
{
	std::vector<double> data = {11.2, 5.2, 6.3};

	std::vector<Edge> edges;

	edges.push_back({0,1});
	edges.push_back({0,2}); 
	edges.push_back({0,3});
 	
	Validate(edges);
	
	std::shared_ptr<Node<double>> DAG = buildDAG(data, edges);
}

void TestCase4()
{							    
	std::vector<double> data = {2.2, 3.8, 7.4, 1.11, 8.3}; // 0 1 2 3 4

	std::vector<Edge> edges;

	edges.push_back({0,1});          //2.2
	edges.push_back({1,2});       //3.8
	edges.push_back({1,3});    //7.4  1.11
 	edges.push_back({2,4});       //8.3  
	edges.push_back({3,4});

	Validate(edges);
	
	std::shared_ptr<Node<double>> DAG = buildDAG(data, edges);
	
	std::vector<double> getinfo = {}; //list of edges are returned

	returnList(DAG, getinfo);

	printDAG(getinfo); //print the DAG in order of DFS

}

int main()
{
	//The following are the test cases dound in the report, kindly uncomment which you would like to see. 

	TestCase1();
	//TestCase2(); 
	//TestCase3();
	//TestCase4();
	
	return 0;
}