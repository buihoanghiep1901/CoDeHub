#include<stdio.h>
#include<stdlib.h>

typedef struct node{
    int data;
    struct node *left;
    struct node *right;
}NODE;
void insert(NODE **T, int x){
    if (*T==NULL){
        NODE* N=(NODE*) malloc (sizeof(NODE));
        N->left=NULL;
        N->right=NULL;
        N->data=x;
        *T=N;
    }
    else{
        if((*T)->data>x){
            insert(&(*T)->left, x);
        }
        else if ((*T)->data <x){
            insert(&(*T)->right, x);
        }
    }
}

void xuatLNR(NODE *T){
	NODE *p;
	while( T ){
		if( (T->right) == NULL ){
			printf("%d  ", T->data);
			T = T->left;
		}
		else{
			p = T->right;
			while(p->left && p->left != T ){
				p = p->left;
			}
			if( p->left == NULL ){
				p->left = T;
				T = T->right;
			}
			else{ 
				p->left = NULL;
				printf("%d  ", T->data);
				T = T->left;
			}
		}
	}

}
int main(){
    NODE *T;
    T=NULL;
    int a;
    printf("Nhap so gia tri:");
    scanf("%d", &a);
    int i;
    for (i=0;i<a;i++){
        int x;
        printf("Nhap x:");
        scanf("%d", &x);
        insert(&T, x);
    }
    xuatLNR(T);
    return 0;
}
