//顺序表测试
/*************************************************************

#include "SqList.h"

void main()
{
	SqList L;
	//创建
	InitList_Sq(L);
	//插入
	int a[maxSize] = {0,1,2,3,4,5,6,7,8,9};
	for(int i = 1; i <= maxSize; ++i)
		ListInsert_Sq(L, i, a[i-1]);
	ListInsert_Sq(L, 11, 20);
	//遍历
	cout<<"顺序表内数据为："<<endl;
	Traversal_Sq(L);
	//按值查找
	int k = 20;
	cout<<"顺序表中第一个值为"<<k<<"的元素的下标为："
		<<LocateElem(L, k)<<"。"<<endl;
	//按位查找
	int w = 9;
	cout<<"顺序表中第"<<w<<"个位置上的元素的值为："
		<<GetElem(L, w)<<"。"<<endl;
}

*************************************************************/