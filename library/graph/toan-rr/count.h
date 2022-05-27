
#include<stdio.h>
#include<stdlib.h>


#define MAX  100

#define TRUE 1

#define FALSE 0


int G[MAX][MAX], n, chuaxet[MAX], QUEUE[MAX], solt,i;


void Init(char filename[]){
 
 FILE* f;
 
 f=fopen(filename,"r");
 
 if(f==NULL) printf(" ko mo dc file");
 fscanf(f,"%d",&n);
 
 

 printf("\n so dinh cua do thi n = %d",n);

 //nhập ma trận kề của đồ thị.
 printf("\n Ma trận là");
 for(int i=1; i<=n;i++){
   printf("\n");
  for(int j=1; j<=n;j++){

   fscanf(f,"%d",&G[i][j]);
   printf("%d ",G[i][j]);

  }
  printf("\n");

 }

 //Khởi tạo giá trị ban đầu cho mảng chuaxet.

 for(int i=1; i<=n;i++)

  chuaxet[i]=0;

 solt=0;
 fclose(f);

}

void Result(int *chuaxet, int n, int solt){

 if(solt==1){

  printf("\n Do thi la lien thong");

  return;

 }

 for(int i=1; i<=solt;i++){

  printf("\n Thanh phan lien thong thu %d:",i);

  for(int j=1; j<=n;j++){

   if( chuaxet[j]==i)

    printf("%3d", j);

  }

 }

}

/* Breadth First Search */

void BFS(int G[][MAX], int n, int i, int *solt, int chuaxet[], int QUEUE[MAX]){

 int u, dauQ, cuoiQ, j;

 dauQ=1; cuoiQ=1;

 QUEUE[cuoiQ]=i;chuaxet[i]=*solt;

 while(dauQ<=cuoiQ){

  u=QUEUE[dauQ];

  dauQ=dauQ+1;

  for(j=1; j<=n;j++){

   if(G[u][j]==1 && chuaxet[j]==0){

    cuoiQ=cuoiQ+1;

    QUEUE[cuoiQ]=j;

    chuaxet[j]=*solt;

   }

  }

 }

}

void lienth(char filename[]){

 Init(filename);

 for(i=1; i<=n; i++)

  if(chuaxet[i]==0){

   solt=solt+1;

   BFS(G, n, i, &solt, chuaxet, QUEUE);

  }

  Result(chuaxet, n, solt);

  

}
