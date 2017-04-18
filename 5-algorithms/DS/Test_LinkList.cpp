//单链表测试
/*************************************************************

#include "LNode.h"

void main()
{
	LinkList L;
	int k = 1;
	int e = 0;
	int f = 1;
	LNode *t = NULL;

	//头插法创建
	//CreateListF(L);

	//尾插法创建
	CreateListR(L);

	//遍历
	Traversal_L(L);

	int flag = 1;
	do{

	//按位查找
	cout<<"输入要查找的位序：";
	cin>>k;
	t = GetElem(L, k);
	if(t!=NULL)
		cout<<"第"<<k<<"个结点的数据域值为："<<t->data<<endl;
	else
		cout<<"查找失败！"<<endl;

	//按值查找
	cout<<"输入要查找的值：";
	cin>>e;
	k = LocateElem(L, e);
	if(k!=0)
		cout<<"值为"<<e<<"的结点的位序为："<<k<<endl;
	else
		cout<<"查找失败！"<<endl;	

	//插入
	cout<<"请输入要插入的位序：";
	cin>>k;
	cout<<"请输入要插入的位置：";
	cin>>e;
	ListInsert_L(L, k, e);
	if(!f)
		cout<<"插入失败！"<<endl;
	else
		cout<<"插入成功！"<<endl;
	Traversal_L(L);

	//删除
	cout<<"请输入要删除的位序：";
	cin>>k;
	f = ListDelete_L(L, k);
	if(!f)
		cout<<"删除失败！"<<endl;
	else
		cout<<"删除成功！"<<endl;
	Traversal_L(L);
	
	LinkList Lb;
	LinkList Lc;
	CreateListR(Lb);
	Traversal_L(L);

	cout<<"合并中..."<<endl;
	MergeList_L(L,Lb,Lc);
	Traversal_L(L);

	}while(flag);
}
*************************************************************/