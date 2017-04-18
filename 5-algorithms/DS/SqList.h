#include "Bhead.h"

//˳�����̬�洢
/**************************************************************
typedef struct
{
	int data[maxSize];
	int length;
}SqList;
**************************************************************/

//˳�����̬�洢
/**************************************************************/
typedef struct
{
	int *data;
	int length;
	int listSize;
}SqList;
/**************************************************************/

//����˳�������һ����ʼ�洢�ռ��СΪmaxSize�Ŀյ�˳���
//�洢��(L.data)��0 -- L.length-1
/**************************************************************/
void InitList_Sq(SqList &L)
{	
	L.data = (int *)malloc(maxSize * sizeof(int));
	if(!L.data)
		exit(OVERFLOW);
	L.length = 0;
	L.listSize = maxSize;
	cout<<"����˳���ɹ���"<<endl;
}
/**************************************************************/

//����˳���
/**************************************************************/
void DestroyList_Sq(SqList &L)
{
	
}
/**************************************************************/

//���룬�ڵ�i(1<=i<=length)��λ�ò���e
/**************************************************************/
int ListInsert_Sq(SqList &L, int i, int e)
{
	if(i < 1 || i > L.length+1)
	{
		cout<<"����λ�ô���"<<endl;
		return ERROR;
	}
	if(L.length >= L.listSize)
	{
		cout<<"˳���洢�ռ�������������..."<<endl;
		int *newbase = (int *)realloc(L.data, 
			(L.listSize + addSize)*sizeof(int));
		if(!newbase)
		{
			cout<<"����˳���ռ�ʧ�ܣ�"<<endl;
			exit(OVERFLOW);
		}
		L.data = newbase;
		L.listSize += addSize;
		cout<<"���Ӵ洢�ռ���ϣ�"<<endl;
	}
	int *q = &(L.data[i-1]);
	for(int *p = &(L.data[L.length-1]); p >= q; --p)
		*(p+1) = *p;
	*q = e;
	++L.length;
	cout<<"������ɣ�"<<endl;
	return OK;
}
/**************************************************************/

//ɾ����ɾ����i(1<=i<=L.length)��Ԫ��
/**************************************************************/
int ListDelete_Sq(SqList &L, int i)
{
	if(i < 1 || i > L.length)
	{
		cout<<"λ�ô���"<<endl;
		return ERROR;
	}
	int *p = &(L.data[i-1]);
	int *q = L.data + L.length - 1;
	for(++p; p <= q; ++p)
		*(p-1) = *p;
	--L.length;
	cout<<"ɾ���ɹ���"<<endl;
	return OK;

}
/**************************************************************/


//����������
/**************************************************************/
void Traversal_Sq(SqList L)
{
	for(int i=0; i<L.length; ++i)
	{
		cout<<L.data[i]<<"  ";
	}
	cout<<endl;
}
/**************************************************************/

//��ֵ���ҡ�����˳����е�һ��ֵ����e��Ԫ�أ���������λ��
//�������±꣬���ܻ���±�0��������
/**************************************************************/
int LocateElem(SqList L, int e)
{
	for(int i = 0; i < L.length; ++i)
		if(e == L.data[i])
			return i+1;
	return ERROR;
}
/**************************************************************/

//��λ���ҡ���ȡ���е�i��λ����Ԫ�ص�ֵ
/**************************************************************/
int GetElem(SqList L, int i)
{
	if(i < 1 || i > L.length)
	{
		cout<<"λ�ô���"<<endl;
		return ERROR;
	}
	int e = L.data[i-1];
	return e;
}
/**************************************************************/

//�ϲ���δ����
/**************************************************************/
void MergeList_Sq(SqList La, SqList Lb, SqList &Lc)
{
	int *pa = La.data;
	int *pb = Lb.data;
	Lc.listSize = Lc.length = La.length + Lb.length;
	int *pc = Lc.data = (int *)malloc(Lc.listSize * sizeof(int));
	if(!Lc.data)
	{
		cout<<"����˳���ռ�ʧ�ܣ�"<<endl;
		exit(OVERFLOW);
	}
	int *pa_last = La.data + La.length - 1;
	int *pb_last = Lb.data + Lb.length - 1;
	while(pa <= pa_last && pb <= pb_last)
	{
		if(*pa <= *pb)
			*pc++ = *pa++;
		else
			*pc++ = *pb++;
	}
	while(pa <= pa_last)
		*pc++ = *pa++;
	while(pb <= pb_last)
		*pc++ = *pb++;
		
}
/**************************************************************/