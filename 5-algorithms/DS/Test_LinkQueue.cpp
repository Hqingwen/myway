//�������в���
/*************************************************************

#include "QNode.h"

void main()
{

	int k = 1;
	int e = 0;
	int f = 1;

	LinkQueue Q;
	//��ʼ��һ����������
	InitQueue(Q);
	cout<<"��ʼ���������гɹ���"<<endl;

	//����
	cout<<"���Ӳ�����...(��9999����)"<<endl;
	cin>>e;
	while(e!=9999)
	{
		f = EnQueue(Q, e);
		if(!f){
			cout<<"���Ӳ���ʧ�ܣ�"<<endl;
			break;
		}
		cin>>e;
	}

	int flag = 1;
	do{
	
	//����
	cout<<"�����õ������У�";
	Treaversal(Q);

	//��ջ
	flag = DeQueue(Q);
	if(flag)
		cout<<"���ӳɹ���"<<endl;
	else
		cout<<"����ʧ�ܣ�"<<endl;
	
	}while(flag);

}
*************************************************************/