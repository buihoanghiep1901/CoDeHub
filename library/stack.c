#include<stdio.h>
#include<string.h>
#include<stdlib.h>
typedef struct node{
    char name[20];
    struct node*next;
}node;

typedef struct stack{
    struct node*head;
    struct node*tail;
}stack;

node*nodecre(char name[]){
	node*a=(struct node *) malloc(sizeof(struct node));
    strcpy(a->name,name);
    a->next=NULL;   
    return a;
}

stack*null(stack*q){
	q->head=NULL;
	q->tail=NULL;
	return q;
}

stack*push(stack*q,char name[]){  
    node*p=nodecre(name);
    if(q->head==NULL) {
		q->head=q->tail=p;
	}else{
	 	p->next=q->tail;
	 	q->tail=p;
	 }
	 return q;
}

void top(stack*q){
	
    printf(" NAME: %s\n",q->tail->name);
}

stack*pop(stack*q){
    if(q->tail==NULL){
		printf(" stack is empty\n");		
	}else if(q->tail==q->head){		
		q->head=NULL;
		q->tail=NULL;		
	}else{
		node*a=q->tail;;				
		q->tail=q->tail->next;
		a->next=NULL;
		free(a);
	}
	return q;
}
  
void print(stack*q){
	printf(" In ca chuoi\n");
	node*a=q->tail;   
    while(q->tail!=NULL){
    	printf("NAME: %s\n",q->tail->name);
		q->tail=q->tail->next;
	}
	q->tail=a;
}

int size(stack*q){
	int k=0;
	node*a=q->tail;
	while(q->tail!=NULL){
		q->tail=q->tail->next;
		k++;
	}
	q->tail=a;
	return k;
	
}

stack*docdanhba( FILE*f1,stack*q){
	stack*p=q;
	char ten[20];
	while(fscanf(f1, "name: %s\t\t\n", &ten) != EOF ){
		printf("%s  \n",ten);
		p=push(p,ten);
    }
    return p;
}

void indanhba(FILE*f2,stack*q){
	node*a=q->tail;
	while(q->tail!=NULL){
		fprintf(f2,"name: %s\t\t\n",q->tail->name);
		q->tail=q->tail->next;
	}
	q->tail=a;	
}


int main(){
    int n,i,l,menu;
    char ten[20];
    struct stack *q=(struct stack*)malloc (sizeof(struct stack*));
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
						q=push(q,ten);
                    } */
					q=docdanhba(f1,q);
					n=size(q);					               						
					break;
				case 2:
					top(q);
					break;
				case 3:					
					if(q=pop(q)){
						n=n-1;
						printf("da xoa\n");
						print(q);
					}					
					break;
				case 4:
					printf("%d\n",n);
					printf("nhap so luong them: ");scanf("%d",&l);fflush(stdin);
					for(i=n+1;i<=n+l;i++){
						fflush(stdin);
						printf("NAME.%d: ",i);fflush(stdin); gets(ten);
						fflush(stdin);					
						q=push(q,ten);							
					}
					n=size(q);
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
