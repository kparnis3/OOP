#include <iostream>
#include <memory>
#include <vector>
#include <set>

template <typename Template>
class Node
{
	public:
	Node(Template msg)
	{
		data = msg;
		std::cout<< "Node " << data << " created" << std::endl;

	}
	~Node()
	{
		std::cout << "Node " << data << " destroyed" << std::endl; 
	}

	Template data;
	std::vector<std::shared_ptr<Node>> children;
	std::vector<std::weak_ptr<Node>> parents;

	void AddChild(std::shared_ptr<Node> newNode)
	{
		children.push_back(newNode);

	}

	void AddParent(std::weak_ptr<Node> newNode)
	{
		parents.push_back(newNode);
	} 


};

class Edge
{
	public:
	int parentIndex;
	int childIndex;

	Edge(int p, int c):parentIndex(p), childIndex(c)
	{}
};

template <typename Template>
std::shared_ptr<Node<Template>> buildDAG(std::vector<Template>& data, std::vector<Edge>& edges)
{
	std::vector<std::shared_ptr<Node<Template>>> nodes(data.size());
	for(auto& b: nodes)
	{
		b = nullptr;
	}

	for(Edge& edge : edges)
	{
		if(nodes[edge.parentIndex] ==  nullptr)
		{
			nodes[edge.parentIndex] = std::shared_ptr<Node<Template>>(new Node<Template>(data[edge.parentIndex]));
		}

		if(nodes[edge.childIndex] == nullptr)
		{
			nodes[edge.childIndex] = std::shared_ptr<Node<Template>>(new Node<Template>(data[edge.childIndex]));
		}

		std::shared_ptr<Node<Template>> parent = nodes[edge.parentIndex];
		std::shared_ptr<Node<Template>> child = nodes[edge.childIndex];
		child->AddParent(parent);
		parent->AddChild(child);
	}

	return nodes[0];

};

template <typename Template>
void returnList(std::shared_ptr<Node<Template>> root, std::vector<Template>& val)
{	
	
	if(root == nullptr)
	{
		return;
	}

	val.push_back(root->data);

	for(std::shared_ptr<Node<Template>> child: root->children)
	{
		returnList(child, val);
	}

}

template <typename T, typename Template>
void deleteNode(T val, std::shared_ptr<Node<Template>> node, int childlocation)
{

	if(node == nullptr)
	{
		return;
	}

	if(node->data == val) //if what i want to delete
	{
		node->children[childlocation] = nullptr;
	}

	for(std::shared_ptr<Node<Template>> child: node->children)
	{
		deleteNode(val, child, childlocation);
	}
	
}

template <typename Template>
void printDAG(std::vector<Template> &graph)
{
	std::cout << std::endl;
	std::cout << "DFS of directed acyclic graph: " << std::endl;
	for(auto i: graph)
		std::cout << i << " ";
	std::cout<<"\n"<<std::endl;
}

class CycleChecker
{
	public:
		std::vector<bool>visited;

		bool DFS(int count, std::vector<int>adj[], std::set<int>&st)
		{
			std::vector<int>store=adj[count];
			int storeSize=store.size();
			visited[count] = true;
			st.insert(count);
			for(int i=0;i<storeSize;i++)
			{
				int var = store[i];
				
				if(st.find(var)!=st.end())
				{
					return true;
				}
				if(visited[var]==false)
				{
					bool flag= DFS(var,adj,st);
					if(flag)
					{
						return true;
					}
				}
			}
			st.erase(count);
			return false;
		}

		bool isCyclic(int edgeSize, std::vector<int> adj[])
		{
			std::set<int> setStore;
			std::vector<bool> flagStore(edgeSize,false);

			visited=flagStore;
			for(int i=0; i<edgeSize; i++)
			{
				if(visited[i]==false)
				{
					bool flag = DFS(i,adj,setStore);
					if(flag)
					{
						return true;
					}
				}
			}
			return false;
		}
};

void Validate(std::vector<Edge>& edges)
{

	int edgeSize = edges.size();
	
	std::vector<int> adj[edgeSize];	
	for(int i = 0; i< edgeSize; i++)
	{
		int c,p;
		for(auto i: edges)
		{
			
			c = i.childIndex;	
			p = i.parentIndex;
			if((p >= edgeSize || p < 0) || (c >= edgeSize || c < 0)) //node cant be less that 0
			{	
				std::cout << "Error: Not a valid link" << std::endl;
				exit(0);
			}
			adj[c].push_back(p);
		}
		
	}

	CycleChecker graph;

	if(graph.isCyclic(edgeSize, adj))
	{
		std::cout<<"Error: graph contain's cycles"<<std::endl;
		exit(0);
	}


}