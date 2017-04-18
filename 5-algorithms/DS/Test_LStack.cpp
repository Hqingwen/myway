//单向链栈测试
/*************************************************************

#include "LStack.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;

	LinkStack S;
	//初始化一个链栈
	f = InitStack(S);
	if(f)
		cout<<"初始化链栈成功！"<<endl;

	//进栈
	cout<<"进栈操作中...(以9999结束)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = Push(S, e);
		if(!f){
			cout<<"进栈操作失败！"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 1;
	do{

	//遍历
	cout<<"遍历该顺序栈：";
	Treaversal(S);

	//出栈
	flag = Pop(S);
	if(flag)
		cout<<"出栈成功！"<<endl;
	else
		cout<<"出栈失败！"<<endl;


	
	}while(flag);

}
*************************************************************/