#include "Bhead.h"

//˫����
/**************************************************************/
typedef struct DLNode{
	int data;
	struct DLNode *prior;
	struct DLNode *next;
}DLNode, *DLinkList;
/**************************************************************/

//����˫����β�巨��
/**************************************************************/
DLinkList CreateDListR(DLinkList &L)
{
	int x;
	L = (DLinkList)malloc(sizeof(DLNode));
	L->data = 0;
	DLNode *s, *r = L;
	cout<<"β�巨����˫������..."<<endl;
	cout<<"ÿ������һ��Ԫ��ֵ(��9999����)��"<<endl;
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

//����
/**************************************************************/
void Traversal_DL(DLinkList L)
{
	DLNode *p = L->next;
	cout<<"������ǰ˫����";
	if(!p)
		cout<<"�գ�";
	while(p)
	{
		cout<<p->data<<"  ";
		p = p->next;
	}
	
	cout<<endl<<endl;
}
/**************************************************************/
