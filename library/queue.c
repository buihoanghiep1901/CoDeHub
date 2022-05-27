#include<stdio.h>
#include<string.h>
#include<stdlib.h>
typedef struct node{
    char name[20];
    struct node*next;
}node;

typedef struct queue{
    struct node*head;
    struct node*tail;
}queue;

node*nodecre(char name[]){
	node*a=(struct node *) malloc(sizeof(struct node));
    strcpy(a->name,name);
    a->next=NULL;   
    return a;
}

queue*null(queue*q){
	q->head=NULL;
	q->tail=NULL;
	return q;
}

queue*enqueue(queue*q,char name[]){  
    node*p=nodecre(name);
    if(q->head==NULL) {
		q->head=q->tail=p;
	}else{
	 	q->tail->next=p;
	 	q->tail=p;	 	
	 }
	 return q;
}

void peek(queue*q){
	
    printf(" NAME: %s\n",q->head->name);
}

queue*dequeue(queue*q){
    if(q->head==NULL){
		printf(" queue is empty\n");		
	}else if(q->head==q->tail){		
		q->head=NULL;
		q->tail=NULL;		
	}else{
		node*a=q->head;;				
		q->head=q->head->next;
		a->next=NULL;
		free(a);
	}
	return q;
}
  
void print(queue*q){
	printf(" In ca chuoi\n");
	node*a=q->head;   
    while(q->head!=NULL){
    	printf("NAME: %s\n",q->head->name);
		q->head=q->head->next;
	}
	q->head=a;
}

int size(queue*q){
	int k=0;
	node*a=q->head;
	while(q->head!=NULL){
		q->head=q->head->next;
		k++;
	}
	q->head=a;
	return k;
	
}

queue*docdanhba( FILE*f1,queue*q){
	queue*p=q;
	char ten[20];
	while(fscanf(f1, "name: %s\t\t\n", &ten) != EOF ){
		printf("%s  \n",ten);
		p=enqueue(p,ten);
    }
    return p;
}

void indanhba(FILE*f2,queue*q){
	node*a=q->head;
	while(q->head!=NULL){
		fprintf(f2,"name: %s\t\t\n",q->head->name);
		q->head=q->head->next;
	}
	q->head=a;	
}

int main(){
    int n,i,l,menu;
    char ten[20];
    struct queue *q=(struct queue*)malloc (sizeof(struct queue*));
	q=null(q);
    FILE* f1,*f2;
    char a[]="danhba.txt";
    char b[]="indanhba.txt";
   	f1=fopen(a,"r");
   	f2=fopen(b,"w");
   	if(f1==NULL){
   		printf("ko mo dc file");
   		return 1;
	}
	do{
		printf("------------CHUC NANG-------------\n");
		printf("1.\n");
		printf("2.\n");
		printf("3.\n");
		printf("4.\n");
		printf("----------------------------------\n");
		printf("chon chuc nang: "); scanf("%d",&menu);		
			switch(menu){
				case 1:
					/*printf("nhap so luong:");scanf("%d",&n);
                    for(i=1;i<=n;i++){
						printf("NAME.%d: ",i);fflush(stdin);gets(ten);					
						q=enqueue(q,ten);
                    } */
					q=docdanhba(f1,q);
					n=size(q);					               						
					break;
				case 2:
					peek(q);
					break;
				case 3:					
					if(q=dequeue(q)){
						n=n-1;
						printf("da xoa\n");
						print(q);
					}					
					break;
				case 4:
					printf("%d\n",n);
					printf("nhap so luong them: ");scanf("%d",&l);
					for(i=n+1;i<=n+l;i++){
						printf("NAME.%d: ",i);fflush(stdin);gets(ten);					
						q=enqueue(q,ten);							
					}
					n=n+l;
					print(q);
					break;
				case 5:
					n=size(q);
					printf("%d\n",n);
				case 6:
					indanhba(f2,q);
					break;
				case 7:
					return 0;			
		}
	}while(0==0);
}
