//˳��ջ����
/*************************************************************

#include "SqStack.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;
	
	SqStack S;
	//��ʼ��һ��˳��ջ
	f = InitStack(S);
	if(f)
		cout<<"��ʼ��˳��ջ�ɹ���"<<endl;

	//��ջ
	cout<<"��ջ������...(��9999����)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = Push(S, e);
		if(!f){
			cout<<"��ջ����ʧ�ܣ�"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 0;
	do{

	//����
	cout<<"������˳��ջ��";
	Treaversal(S);

	//��ջ
	flag = Pop(S);
	if(flag)
		cout<<"��ջ�ɹ���"<<endl;
	else
		cout<<"��ջʧ�ܣ�"<<endl;

	//ȡջ������
	//e = GetTop(S, e);
	//cout<<"ջ��Ԫ��ֵΪ��"<<e<<endl<<endl;

	}while(flag);

	DestroyStack(S);

}
*************************************************************/