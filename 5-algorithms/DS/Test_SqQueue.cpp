//˳��ѭ�������в���
/*************************************************************

#include "SqQueue.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;
	
	SqQueue Q;
	//��ʼ��˳�����
	InitQueue(Q);
	cout<<"��ʼ��˳����гɹ���"<<endl;

	//��ջ
	cout<<"���Ӳ�����...(��9999����)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = EnQueue(Q, e);
		if(!f){
			cout<<"���������Ӳ���ʧ�ܣ�"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 1;
	do{

	//����
	cout<<"������˳����У�";
	Treaversal(Q);

	//��ջ
	flag = DeQueue(Q);
	if(flag)
		cout<<"��ջ�ɹ���"<<endl;
	else
		cout<<"��ջʧ�ܣ�"<<endl;
	
	}while(flag);

}
*************************************************************/