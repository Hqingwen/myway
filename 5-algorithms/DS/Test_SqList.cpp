//˳������
/*************************************************************

#include "SqList.h"

void main()
{
	SqList L;
	//����
	InitList_Sq(L);
	//����
	int a[maxSize] = {0,1,2,3,4,5,6,7,8,9};
	for(int i = 1; i <= maxSize; ++i)
		ListInsert_Sq(L, i, a[i-1]);
	ListInsert_Sq(L, 11, 20);
	//����
	cout<<"˳���������Ϊ��"<<endl;
	Traversal_Sq(L);
	//��ֵ����
	int k = 20;
	cout<<"˳����е�һ��ֵΪ"<<k<<"��Ԫ�ص��±�Ϊ��"
		<<LocateElem(L, k)<<"��"<<endl;
	//��λ����
	int w = 9;
	cout<<"˳����е�"<<w<<"��λ���ϵ�Ԫ�ص�ֵΪ��"
		<<GetElem(L, w)<<"��"<<endl;
}

*************************************************************/