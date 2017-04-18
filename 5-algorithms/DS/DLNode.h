#include "Bhead.h"

//双链表
/**************************************************************/
typedef struct DLNode{
	int data;
	struct DLNode *prior;
	struct DLNode *next;
}DLNode, *DLinkList;
/**************************************************************/

//创建双链表。尾插法。
/**************************************************************/
DLinkList CreateDListR(DLinkList &L)
{
	int x;
	L = (DLinkList)malloc(sizeof(DLNode));
	L->data = 0;
	DLNode *s, *r = L;
	cout<<"尾插法创建双链表中..."<<endl;
	cout<<"每次输入一个元素值(以9999结束)。"<<endl;
	cin>>x;
	while(x!=9999)
	{
		s = (DLNode *)malloc(sizeof(DLNode));
		s->data = x;
		r->next = s;
		s->prior = r;
		r = s;
		++L->data;
		cin>>x;
	}
	r->next = NULL;
	return L;
}
/**************************************************************/

//遍历
/**************************************************************/
void Traversal_DL(DLinkList L)
{
	DLNode *p = L->next;
	cout<<"遍历当前双链表：";
	if(!p)
		cout<<"空！";
	while(p)
	{
		cout<<p->data<<"  ";
		p = p->next;
	}
	
	cout<<endl<<endl;
}
/**************************************************************/
