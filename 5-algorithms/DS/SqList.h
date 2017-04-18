#include "Bhead.h"

//顺序表，静态存储
/**************************************************************
typedef struct
{
	int data[maxSize];
	int length;
}SqList;
**************************************************************/

//顺序表，动态存储
/**************************************************************/
typedef struct
{
	int *data;
	int length;
	int listSize;
}SqList;
/**************************************************************/

//创建顺序表，创建一个初始存储空间大小为maxSize的空的顺序表
//存储区(L.data)：0 -- L.length-1
/**************************************************************/
void InitList_Sq(SqList &L)
{	
	L.data = (int *)malloc(maxSize * sizeof(int));
	if(!L.data)
		exit(OVERFLOW);
	L.length = 0;
	L.listSize = maxSize;
	cout<<"创建顺序表成功！"<<endl;
}
/**************************************************************/

//销毁顺序表
/**************************************************************/
void DestroyList_Sq(SqList &L)
{
	
}
/**************************************************************/

//插入，在第i(1<=i<=length)个位置插入e
/**************************************************************/
int ListInsert_Sq(SqList &L, int i, int e)
{
	if(i < 1 || i > L.length+1)
	{
		cout<<"插入位置错误！"<<endl;
		return ERROR;
	}
	if(L.length >= L.listSize)
	{
		cout<<"顺序表存储空间已满！分配中..."<<endl;
		int *newbase = (int *)realloc(L.data, 
			(L.listSize + addSize)*sizeof(int));
		if(!newbase)
		{
			cout<<"申请顺序表空间失败！"<<endl;
			exit(OVERFLOW);
		}
		L.data = newbase;
		L.listSize += addSize;
		cout<<"增加存储空间完毕！"<<endl;
	}
	int *q = &(L.data[i-1]);
	for(int *p = &(L.data[L.length-1]); p >= q; --p)
		*(p+1) = *p;
	*q = e;
	++L.length;
	cout<<"插入完成！"<<endl;
	return OK;
}
/**************************************************************/

//删除，删除第i(1<=i<=L.length)个元素
/**************************************************************/
int ListDelete_Sq(SqList &L, int i)
{
	if(i < 1 || i > L.length)
	{
		cout<<"位置错误！"<<endl;
		return ERROR;
	}
	int *p = &(L.data[i-1]);
	int *q = L.data + L.length - 1;
	for(++p; p <= q; ++p)
		*(p-1) = *p;
	--L.length;
	cout<<"删除成功！"<<endl;
	return OK;

}
/**************************************************************/


//遍历单链表
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

//按值查找。查找顺序表中第一个值等于e的元素，并返回其位序
//若返回下标，可能会把下标0当做错误。
/**************************************************************/
int LocateElem(SqList L, int e)
{
	for(int i = 0; i < L.length; ++i)
		if(e == L.data[i])
			return i+1;
	return ERROR;
}
/**************************************************************/

//按位查找。获取表中第i个位置上元素的值
/**************************************************************/
int GetElem(SqList L, int i)
{
	if(i < 1 || i > L.length)
	{
		cout<<"位置错误！"<<endl;
		return ERROR;
	}
	int e = L.data[i-1];
	return e;
}
/**************************************************************/

//合并，未测试
/**************************************************************/
void MergeList_Sq(SqList La, SqList Lb, SqList &Lc)
{
	int *pa = La.data;
	int *pb = Lb.data;
	Lc.listSize = Lc.length = La.length + Lb.length;
	int *pc = Lc.data = (int *)malloc(Lc.listSize * sizeof(int));
	if(!Lc.data)
	{
		cout<<"申请顺序表空间失败！"<<endl;
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