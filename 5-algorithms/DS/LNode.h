#include "Bhead.h"

//单链表结点
/**************************************************************/
typedef struct LNode
{
	int data;
	struct LNode *next;
}LNode, *LinkList;
/**************************************************************/

//创建单链表。头插法，每次均在头结点之后插入元素。
/**************************************************************/
LinkList CreateListF(LinkList &L)
{
	LNode *s;
	int x;
	L = (LinkList)malloc(sizeof(LNode));
	//L->data = 0;		//头结点数据域中存储链表长度。
	L->next = NULL;
	cout<<"头插法创建单链表中..."<<endl;
	cout<<"每次输入一个元素值(以9999结束)。"<<endl;
	cin>>x;
	while(x!=9999)
	{
		s = (LNode *)malloc(sizeof(LNode));
		s->data = x;
		s->next = L->next;
		L->next = s;
		//++L->data;
		cin>>x;
	}
	return L;
}
/**************************************************************/

//创建单链表。尾插法,每次均在最后一个结点之后插入元素。
/**************************************************************/
LinkList CreateListR(LinkList &L)
{
	int x;
	L = (LinkList)malloc(sizeof(LNode));
	L->data = 0;
	LNode *s, *r = L;
	cout<<"尾插法创建单链表中..."<<endl;
	cout<<"每次输入一个元素值(以9999结束)。"<<endl;
	cin>>x;
	while(x!=9999)
	{
		s = (LNode *)malloc(sizeof(LNode));
		s->data = x;
		r->next = s;
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
void Traversal_L(LinkList L)
{
	LNode *p = L->next;
	cout<<"遍历当前单链表：";
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

//按值查找。查找链表中数据域值等于e的结点的位序。
/**************************************************************/
int LocateElem(LinkList L, int e)
{
	LNode *p = L->next;
	int i = 1;		
	while(p!=NULL && p->data!=e)
	{
		p = p->next;
		++i;
	}
	if(i > L->data)
		return ERROR;
	return i;
}
/**************************************************************/

//按位查找。获取单链表上的第i个结点。（头结点为位序0）
/**************************************************************/
LNode *GetElem(LinkList L, int i)
{
	LNode *p = L->next;
	int j = 1;

	if(i<1 || i>L->data)
		return NULL;
	while(p && j<i)
	{
		p = p->next;
		++j;
	}
	return p;
}
/**************************************************************/

//插入，在第i个结点之前插入一个值为e的新结点
//后插入时，可以先用该法前插入，再交换p->data与s->data即可。
/**************************************************************/
int ListInsert_L(LinkList &L, int i, int e)
{
	LNode *p = L;
	int j = 0;
	while(p && j<i-1)
	{
		p = p->next;
		++j;
	}
	if(!p || j>i-1)
		return ERROR;
	LNode *s = (LNode *)malloc(sizeof(LNode));
	s->data = e;
	s->next = p->next;
	p->next = s;
	return OK;

}
/**************************************************************/

//删除，将第i个结点删除注销
/**************************************************************/
int ListDelete_L(LinkList &L, int i)
{
	LNode *p = L;
	int j = 0;
	while(p->next && j<i-1)
	{
		p = p->next;
		++j;
	}
	if(!(p->next) || j>i-1)
		return ERROR;
	LNode *q = p->next;
	p->next = q->next;
	free(q);
	return OK;	
}
/**************************************************************/

//合并两个递增有序链表A和B，成为一个非递减有序链表C
/**************************************************************/
void MergeList_L(LinkList &La, LinkList &Lb, LinkList &Lc)
{
	LNode *pa = La->next;
	LNode *pb = Lb->next;
	LNode *pc;
	Lc = pc = La;
	while(pa && pb)
	{
		if(pa->data <= pb->data)
		{
			pc->next = pa;
			pc = pa;
			pa = pa->next;
		}
		else
		{
			pc->next = pb;
			pc = pb;
			pb = pb->next;
		}
	}
	pc->next = pa ? pa : pb;
	
}
/**************************************************************/
