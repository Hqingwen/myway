#include "Bhead.h"

//��������
/**************************************************************/
//���
typedef struct QNode
{
	int data;
	struct QNode *next;
}QNode;

//����
typedef struct
{
	QNode *front;
	QNode *rear;
}LinkQueue;
/**************************************************************/

//��ʼ��
/**************************************************************/
int InitQueue(LinkQueue &Q)
{
	Q.front = Q.rear = (QNode *)malloc(sizeof(QNode));
	if(!Q.front)
		exit(OVERFLOW);
	Q.front->next = NULL;
	return OK;
}
/**************************************************************/

//����
/**************************************************************/
int DestroyQueue(LinkQueue &Q)
{
	while(Q.front)
	{
		Q.rear = Q.front->next;
		free(Q.front);
		Q.front = Q.rear;
	}
	return OK;
}
/**************************************************************/

//�п�
/**************************************************************/
int isEmpty(LinkQueue Q)
{
	if(Q.front == Q.rear)
		return TRUE;
	else
		return FALSE;
}
/**************************************************************/

//����
/**************************************************************/
int EnQueue(LinkQueue &Q, int e)
{
	QNode *p = (QNode *)malloc(sizeof(QNode));
	if(!p)
		exit(OVERFLOW);
	p->data = e;
	p->next = NULL;
	Q.rear->next = p;
	Q.rear = p;
	return OK;
}
/**************************************************************/

//����
/**************************************************************/
int DeQueue(LinkQueue &Q)
{
	if(isEmpty(Q))
		return ERROR;
	QNode *p = Q.front->next;
	Q.front->next = p->next;
	if(Q.rear == p)
		Q.rear = Q.front;
	free(p);
	return OK;
}
/**************************************************************/

//����
/**************************************************************/
void Treaversal(LinkQueue Q)
{
	if(isEmpty(Q)){
		cout<<"�գ�"<<endl<<endl;
		return;
	}
	QNode * r = Q.front->next;
	while(!isEmpty(Q) && r!=NULL){
		cout<<r->data<<"  ";
		r = r->next;
	}
	cout<<endl<<endl;
}
/**************************************************************/


//
/**************************************************************/

/**************************************************************/
