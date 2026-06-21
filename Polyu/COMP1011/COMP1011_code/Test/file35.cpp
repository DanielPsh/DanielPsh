#include <iostream>
using namespace std;

//linked list construct
struct Node
{
    int data;
    Node *next;
};

void printList(Node *head)
{
    Node *cur = head;
    while(cur != NULL) //cur reaches null then stop the loop
    {
        cout << cur -> data << " ";
        cur = cur -> next;
    }
    cout << endl;
}

void addNodeEnd(Node *head, int value)
{
    Node *cur = head;
    while(cur -> next != NULL)
    {
        cur = cur -> next;
    }
    /*
    Node newNode;
    newNode.data = value;
    newNode.next = NULL;
    cur -> next = &newNode;
    */

    cur -> next = new Node;
    cur = cur -> next;
    cur -> data = value;
    cur -> next = NULL;
}

int main()
{
    //node
    Node n1, n2, n3;
    n1.data = 1;
    n2.data = 2;
    n3.data = 3;

    //pointer -> stores address
    n1.next = &n2;
    n2.next = &n3;
    n3.next = NULL;


    //head pointer
    Node *head = &n1; //give linkedlist a name
    printList(head);

    //add Node End
    for(int i = 4; i < 10; i++)
    {
        addNodeEnd(head, i);
    }

    //head.data == error
    cout << (*head).data << endl; //dereference
    cout << head -> data << endl; //dereference

    printList(head);

    return 0;
}