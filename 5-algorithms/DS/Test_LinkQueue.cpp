//单链队列测试
/*************************************************************

#include "QNode.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;

	LinkQueue Q;
	//初始化一个单链队列
	InitQueue(Q);
	cout<<"初始化单链队列成功！"<<endl;

	//进队
	cout<<"进队操作中...(以9999结束)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = EnQueue(Q, e);
		if(!f){
			cout<<"进队操作失败！"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 1;
	do{
	
	//遍历
	cout<<"遍历该单链队列：";
	Treaversal(Q);

	//出栈
	flag = DeQueue(Q);
	if(flag)
		cout<<"出队成功！"<<endl;
	else
		cout<<"出队失败！"<<endl;
	
	}while(flag);

}
*************************************************************/