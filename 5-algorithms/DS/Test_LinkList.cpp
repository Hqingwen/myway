//���������
/*************************************************************

#include "LNode.h"

void main()
{
	LinkList L;
	int k = 1;
	int e = 0;
	int f = 1;
	LNode *t = NULL;

	//ͷ�巨����
	//CreateListF(L);

	//β�巨����
	CreateListR(L);

	//����
	Traversal_L(L);

	int flag = 1;
	do{

	//��λ����
	cout<<"����Ҫ���ҵ�λ��";
	cin>>k;
	t = GetElem(L, k);
	if(t!=NULL)
		cout<<"��"<<k<<"������������ֵΪ��"<<t->data<<endl;
	else
		cout<<"����ʧ�ܣ�"<<endl;

	//��ֵ����
	cout<<"����Ҫ���ҵ�ֵ��";
	cin>>e;
	k = LocateElem(L, e);
	if(k!=0)
		cout<<"ֵΪ"<<e<<"�Ľ���λ��Ϊ��"<<k<<endl;
	else
		cout<<"����ʧ�ܣ�"<<endl;	

	//����
	cout<<"������Ҫ�����λ��";
	cin>>k;
	cout<<"������Ҫ�����λ�ã�";
	cin>>e;
	ListInsert_L(L, k, e);
	if(!f)
		cout<<"����ʧ�ܣ�"<<endl;
	else
		cout<<"����ɹ���"<<endl;
	Traversal_L(L);

	//ɾ��
	cout<<"������Ҫɾ����λ��";
	cin>>k;
	f = ListDelete_L(L, k);
	if(!f)
		cout<<"ɾ��ʧ�ܣ�"<<endl;
	else
		cout<<"ɾ���ɹ���"<<endl;
	Traversal_L(L);
	
	LinkList Lb;
	LinkList Lc;
	CreateListR(Lb);
	Traversal_L(L);

	cout<<"�ϲ���..."<<endl;
	MergeList_L(L,Lb,Lc);
	Traversal_L(L);

	}while(flag);
}
*************************************************************/