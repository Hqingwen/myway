#include "Bhead.h"

//��������
/**************************************************************/
typedef struct LNode
{
	int data;
	struct LNode *next;
}LNode, *LinkList;
/**************************************************************/

//����������ͷ�巨��ÿ�ξ���ͷ���֮�����Ԫ�ء�
/**************************************************************/
LinkList CreateListF(LinkList &L)
{
	LNode *s;
	int x;
	L = (LinkList)malloc(sizeof(LNode));
	//L->data = 0;		//ͷ����������д洢�����ȡ�
	L->next = NULL;
	cout<<"ͷ�巨������������..."<<endl;
	cout<<"ÿ������һ��Ԫ��ֵ(��9999����)��"<<endl;
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

//����������β�巨,ÿ�ξ������һ�����֮�����Ԫ�ء�
/**************************************************************/
LinkList CreateListR(LinkList &L)
{
	int x;
	L = (LinkList)malloc(sizeof(LNode));
	L->data = 0;
	LNode *s, *r = L;
	cout<<"β�巨������������..."<<endl;
	cout<<"ÿ������һ��Ԫ��ֵ(��9999����)��"<<endl;
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

//����
/**************************************************************/
void Traversal_L(LinkList L)
{
	LNode *p = L->next;
	cout<<"������ǰ������";
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

//��ֵ���ҡ�����������������ֵ����e�Ľ���λ��
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

//��λ���ҡ���ȡ�������ϵĵ�i����㡣��ͷ���Ϊλ��0��
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

//���룬�ڵ�i�����֮ǰ����һ��ֵΪe���½��
//�����ʱ���������ø÷�ǰ���룬�ٽ���p->data��s->data���ɡ�
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

//ɾ��������i�����ɾ��ע��
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

//�ϲ�����������������A��B����Ϊһ���ǵݼ���������C
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
