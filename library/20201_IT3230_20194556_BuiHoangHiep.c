#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct node{
    char dc[20];
    int gio;
    int phut;
    struct node*next;
}node;

node*cre(char dc[],int gio,int phut){
    node*a=(node*)malloc(sizeof(node));
    strcpy(a->dc,dc);
    a->gio=gio;
    a->phut=phut;
    a->next=NULL;
    return a;
}

node*addtail(node*head,char dc[],int gio,int phut){
    if(head==NULL) {
        head=cre(dc,gio,phut);
        
    }else{
        node*a=cre(dc,gio,phut);
        node*p=head;
        while(p->next!=NULL){
            p=p->next;
        }
        p->next=a;
        
    }
    return head;
}

void print(node*head){
    node*p=head;
    while (p!=NULL){
        printf("%50s %10d %10d\n",p->dc,p->gio,p->phut);
        p=p->next;
    }   
}

void addrsearch(node*head,char dc[]){
	int k=0;
    node*p=head;
    while(p!=NULL){
        if(strcmp(p->dc,dc)==0){
            printf("%d:%d, ",p->gio,p->phut);
            k++;           
        }
        p=p->next;
    }
    if(k==0){
    	printf("khong tim thay\n");
	}else{
		return;
	}  
}

void timesearch(node*head,int gio,int phut){
if(0<=gio && gio<=24 && 0<=phut && phut<=60){
	
    node*p=head;
    while(p!=NULL){
        if(p->gio==gio && p->phut==phut){
        	printf("da tim thay: \n");
            printf("%s %d %d\n",p->dc,p->gio,p->phut);
            return;
        }
        p=p->next;
    }
    printf("khong tim thay lich su di chuyen\n");
}else{
	printf("nhap sai ");
}
}

void issafe(node*head,char dc[],int gio, int phut){
	node*p=head;
	while(p!=NULL){
		if(strcmp(p->dc,dc)==0&& p->gio>=gio ){
			if(p->gio==gio&&p->phut>=phut){
				printf("Ban co kha nang bi lay covid, can phai khia bao y te ngay lap tuc\n");
				return;	
			}else if(p->gio>gio){
				printf("Ban co kha nang bi lay covid, can phai khia bao y te ngay lap tuc\n");
				return;	
			}			
		}
		p=p->next;
	}
	printf("Lich su di chuyen cua ban OK");
}


node*docdanhba( FILE*f1,node*head){	
	char dc[40];
	int gio,phut;
	while(fscanf(f1, "%s %d %d\n", &dc,&gio,&phut) != EOF ){
		
		head=addtail(head,dc,gio,phut);
    }
    return head;
}

int main(){
    char addr[40];
    int hour,minute;
    node*head=(node*)malloc(sizeof(node));
    head=NULL;
    int n,m,i=0,menu,l;
    FILE* f1,*f2;
    char a[]="log.txt";
    char b[]="indanhba.txt";
   	f1=fopen(a,"r");
   	f2=fopen(b,"w");
   	if(f1==NULL){
   		printf("ko mo dc file");
   		return 1;
	}
do{
		printf("\n");
		printf("\n------------CHUC NANG-------------\n");
		printf("1.Nap du lieu log lich su di chuyen\n");
		printf("2.in ra lich su di chuyen\n");
		printf("3.Tim kiem lich su di chuyen theo dia diem\n");
		printf("4.Tìm kiem thong tin di chuyen theo tg\n");
		printf("5.Kiem tra truy vet moi nhat\n");
		printf("6.Thoat\n");
		printf("----------------------------------\n");
		printf("chon chuc nang: "); scanf("%d",&menu);
		
			switch(menu){
				case 1:
                    head=docdanhba(f1,head);                  	
					break;
				case 2:
					printf("Lich su di chuyen la: \n");
					print(head);					            
					break;
				
				case 3:	
					printf("nhap ten can tim : ");fflush(stdin);gets(addr);
					addrsearch(head,addr);
					break;
					
				case 4:
					printf("nhap gio : ");
					scanf("%d",&hour);
					printf("nhap phut: ");scanf("%d",&minute);
					while(hour<0||hour>24){
						printf("nhap sai gio nhap lai di ban ei: ");
						scanf("%d",&hour);
					}
					while(minute<0||minute>60){
						printf("nhap sai phut nhap lai di ban ei: ");
						scanf("%d",&minute);
					}
					timesearch(head,hour,minute);
					break;
				case 5:
					printf(" nhap dia diem va thoi gian:");
					scanf("%s %d %d",&addr,&hour,&minute);
					while(hour<0||hour>24){
						printf("nhap sai gio nhap lai di ban ei: ");
						scanf("%d",&hour);
					}
					while(minute<0||minute>60){
						printf("nhap sai phut nhap lai di ban ei: ");
						scanf("%d",&minute);
					}
					issafe(head,addr,hour,minute);
					break;
				case 6:
					exit(0);
					break;			
		}
	}while(0==0);
}

