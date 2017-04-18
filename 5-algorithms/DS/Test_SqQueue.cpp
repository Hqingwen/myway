//顺序（循环）队列测试
/*************************************************************

#include "SqQueue.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;
	
	SqQueue Q;
	//初始化顺序队列
	InitQueue(Q);
	cout<<"初始化顺序队列成功！"<<endl;

	//进栈
	cout<<"进队操作中...(以9999结束)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = EnQueue(Q, e);
		if(!f){
			cout<<"队满！进队操作失败！"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 1;
	do{

	//遍历
	cout<<"遍历该顺序队列：";
	Treaversal(Q);

	//出栈
	flag = DeQueue(Q);
	if(flag)
		cout<<"出栈成功！"<<endl;
	else
		cout<<"出栈失败！"<<endl;
	
	}while(flag);

}
*************************************************************/