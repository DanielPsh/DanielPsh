#include <iostream>
using namespace std;

// Struct Node
struct Node
{
	int data;
	Node *prev;
	Node *next;
};

// It prints all nodes of the linked list one by one.
void printList(Node *head)
{
	Node *current = head;
    while (current != nullptr)
    {
        cout << current -> data << " ";
        current = current -> next;
    }
    cout << endl;
}

// It inserts a new node to the linked list such that all nodes are still sorted in ascending order after the insertion.
void insertList(Node **headptr, int value)
{
	Node *newNode = new Node();
    newNode -> data = value;
    newNode -> prev = nullptr;
    newNode -> next = nullptr;

    // Case for empty list
    if (*headptr == nullptr)
    {
        *headptr = newNode;
        return;
    }

    Node *current = *headptr;
    // Case for new head
    if (current -> data >= value)
    {
        newNode -> next = current;
        current -> prev = newNode;
        *headptr = newNode;
        return;
    }

    while (current -> next != nullptr && current -> next -> data < value)
    {
        current = current -> next;
    }

    newNode -> next = current -> next;
    newNode -> prev = current;

    if (current -> next != nullptr)
    {
        current -> next -> prev = newNode;
    }
    current -> next = newNode;
}

// It checks whether all nodes of the linked list are sorted in ascending order. It returns true if the nodes are sorted; otherwise false.
// An empty linked list is regarded as sorted.
bool isSortedList(Node *head)
{
	if (head == nullptr)
        return true;

    Node *current = head;
    while (current -> next != nullptr)
    {
        if (current -> data > current -> next -> data)
        {
            return false;
        }
        current = current -> next;
    }
    return true;
}

// It merges two sorted linked lists into a single sorted linked list and returns the head of the merged list.
// It should be able to handle empty linked lists.
// No new node should be created in this function.
Node *mergeSortedLists(Node *head1, Node *head2)
{
	if (head1 == nullptr) return head2;
    if (head2 == nullptr) return head1;

    Node *mergedHead = nullptr;

    if (head1 -> data < head2 -> data)
    {
        mergedHead = head1;
        head1 = head1 -> next;
    } else {
        mergedHead = head2;
        head2 = head2 -> next;
    }
    
    Node *tail = mergedHead;
    tail -> prev = nullptr;
    tail -> next = nullptr;

    while (head1 != nullptr && head2 != nullptr)
    {
        Node *newNode = nullptr;
        if (head1 -> data < head2 -> data)
        {
            newNode = head1;
            head1 = head1 -> next;
        } else {
            newNode = head2;
            head2 = head2 -> next;
        }
        tail -> next = newNode;
        newNode -> prev = tail;
        tail = newNode;
    }

    if (head1 != nullptr)
    {
        tail -> next = head1;
        head1 -> prev = tail;
    } else {
        tail -> next = head2;
        head2 -> prev = tail;
    }

    return mergedHead;
}

int main()
{
	Node *list1 = NULL;
	insertList(&list1, 6);
	insertList(&list1, 2);
	insertList(&list1, 8);
	insertList(&list1, 4);
	insertList(&list1, 9);
	printList(list1);

	Node *list2 = NULL;
	if (isSortedList(list1) && isSortedList(list2))
	{
		Node *mergedList = mergeSortedLists(list1, list2);
		printList(mergedList);
	}
	
	insertList(&list2, 3);
	insertList(&list2, 7);
	insertList(&list2, 1);
	insertList(&list2, 5);
	printList(list2);
	
	if (isSortedList(list1) && isSortedList(list2))
	{
		Node *mergedList = mergeSortedLists(list1, list2);
		printList(mergedList);
	}

	return 0;
}