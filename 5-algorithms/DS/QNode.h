#include "Bhead.h"

//单链队列
/**************************************************************/
//结点
typedef struct QNode
{
	int data;
	struct QNode *next;
}QNode;

//队列
typedef struct
{
	QNode *front;
	QNode *rear;
}LinkQueue;
/**************************************************************/

//初始化
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

//销毁
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

//判空
/**************************************************************/
int isEmpty(LinkQueue Q)
{
	if(Q.front == Q.rear)
		return TRUE;
	else
		return FALSE;
}
/**************************************************************/

//进队
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

//出队
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

//遍历
/**************************************************************/
void Treaversal(LinkQueue Q)
{
	if(isEmpty(Q)){
		cout<<"空！"<<endl<<endl;
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
