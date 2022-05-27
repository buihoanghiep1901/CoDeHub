#include "jrbgraph.hpp"
#include <stdio.h>
#include <iostream>
#include <map>
#include <string>
#include <bits/stdc++.h>
using namespace std;

Graph g;
JRB list_cntTX;
int n;


void CN1(){
	FILE*input = fopen("tiepXucGan.txt", "r");
	int a1, a2, cnt = 0;
	
	g = createGraph();
	list_cntTX = make_jrb();

	fscanf(input, "%d", &n);
	char c, s2[100], x; 
	c = fgetc(input);
	for(int i=1; i<= n; i++){
			fscanf(input, "%c", &x);
			a1 = x - 'A';
			c = fgetc(input);
			cnt = 0;
			while(c!='\n' && c!=EOF){
				fscanf(input, "%c", &x);
				a2 = x - 'A';
				c = fgetc(input);
				//printf("%d %d\n", a1, a2);
				addEdge_int(g, a1, a2, 1);
				cnt++;
			}
			jrb_insert_int(list_cntTX, a1, new_jval_i(cnt));
		
		//printf("%d %d %f\n", a1, a2, w);
	}
	fclose(input);
}

void CN2(){
	JRB node, node2;
	printf("%d\n", n);
	jrb_traverse(node, g)
	{
		printf("%c ", jval_i(node->key)+'A');
		JRB tree = (JRB) jval_v(node->val) ;
		jrb_traverse(node2, tree){
			printf("%c ", jval_i(node2->key)+'A');
		}
	printf("\n");	
	}
	return;
}

void CN3(){
	int id1, id2;
	char c1,c2;
	fflush(stdin);
	printf("Nhap ten nguoi thu 1: ");
	fflush(stdin);
	scanf("%c", &c1);
	id1 = c1-'A';
	printf("Nhap ten nguoi thu 2: ");
	fflush(stdin);
	scanf("%c", &c2);
	id2 = c2-'A';
	JRB bn = jrb_find_int(g, id1);
	JRB tree = (JRB) jval_v(bn->val);
	jrb_traverse(bn, tree){
		 if(jval_i(bn->key) == id2){
		 	printf("Hai nguoi tren co tiep xuc gan!\n");
		 	return;
		 }
	}
	printf("Hai nguoi tren khong tiep xuc gan!\n");
	return;
}

void CN4(){
	int id1;
	char c;
	fflush(stdin);
	printf("Nhap ten nguoi can tra cuu: ");
	fflush(stdin);
	scanf("%c", &c);
	id1 = c-'A';
	JRB bn = jrb_find_int(g, id1);
	JRB tree = (JRB) jval_v(bn->val);
	printf("Danh sach tiep xuc gan cua nguoi %c la:\n", c);
	jrb_traverse(bn, tree){
		printf("%c\n", jval_i(bn->key) + 'A');
	}
}

void CN5(){
	JRB node;
	int max = -1;
	jrb_traverse(node, list_cntTX){
		if(max < jval_i(node->val))
			max = jval_i(node->val);
	}
	printf("Nhung nguoi tiep xuc voi nhieu nguoi nhat:\n");
	jrb_traverse(node, list_cntTX){
		if(max == jval_i(node->val))
			printf("%c\n", jval_i(node->key)+'A');
	}
}


void CN6(){
	int id1;
	char c;
	int bl[50];
	fflush(stdin);
	printf("Nhap ten nguoi tro thanh F0: ");
	fflush(stdin);
	scanf("%c", &c);
	id1 = c-'A';
	
	for(int i = 0; i <= 'Z'-'A'; i++)
		bl[i] = 0;
	
	bl[id1] = 1;
	JRB bn = jrb_find_int(g, id1);
	JRB tree = (JRB) jval_v(bn->val);
	printf("Danh sach F1 la:\n");
	jrb_traverse(bn, tree){
		printf("%c\n", jval_i(bn->key) + 'A');
		bl[jval_i(bn->key)] = 1;
	}
	
	
	printf("\nDanh sach F2 la:\n");
	int output[100];
	int m = getAdjacentVertices_int (g, id1, output);
	for(int i=0; i<m;i++)
	{
		bn = jrb_find_int(g, output[i]);
		tree = (JRB) jval_v(bn->val);
		jrb_traverse(bn, tree){
			if (bl[jval_i(bn->key)] == 0) printf("%c\n", jval_i(bn->key) + 'A');
		}
	}
	
}

void CN7(){
	char c;
	int m, id1, id2;
	fflush(stdin);
	printf("Nhap ten nguoi can bo sung: ");
	fflush(stdin);
	scanf("%c", &c);
	id1 = c - 'A';
	printf("Nhap so nguoi tiep xuc gan: ");
	fflush(stdin);
	scanf("%d", &m);
	printf("Nhap danh sach tiep xuc gan: ");
	fflush(stdin);
	
	//tang n len 1 don vi
	n++;
	for(int i=1;i<=m;i++){
		scanf("%c", &c);
		getchar();
		id2 = c - 'A';
		addEdge_int(g, id1, id2, 1);
		addEdge_int(g, id2, id1, 1);
		
		/*JRB bn = jrb_find_int(list_cntTX, id1);
		if(bn == NULL){
			jrb_insert_int(list_cntTX, id1, new_jval_i(1));
		}
		else{
			int k = jval_i(bn->val) + 1;
			bn->val = new_jval_i(k);
		}*/
		
		JRB bn = jrb_find_int(list_cntTX, id2);
		if(bn == NULL){
			jrb_insert_int(list_cntTX, id2, new_jval_i(1));
		}
		else{
			int k = jval_i(bn->val) + 1;
			bn->val = new_jval_i(k);
		}
	}
	jrb_insert_int(list_cntTX, id1, new_jval_i(m));
}


void CN8(){
	char c;
	int m, id1, id2;
	fflush(stdin);
	printf("Nhap ten nguoi 1: ");
	fflush(stdin);
	scanf("%c", &c);
	id1 = c - 'A';
	printf("Nhap ten nguoi 2: ");
	fflush(stdin);
	scanf("%c", &c);
	id2 = c - 'A';
	
	addEdge_int(g, id1, id2, 1);
	addEdge_int(g, id2, id1, 1);
		
	JRB bn = jrb_find_int(list_cntTX, id1);
	if(bn == NULL){
		jrb_insert_int(list_cntTX, id1, new_jval_i(1));
	}
	else{
		int k = jval_i(bn->val) + 1;
		bn->val = new_jval_i(k);
	}
	
	bn = jrb_find_int(list_cntTX, id2);
	if(bn == NULL){
		jrb_insert_int(list_cntTX, id2, new_jval_i(1));
	}
	else{
		int k = jval_i(bn->val) + 1;
		bn->val = new_jval_i(k);
	}
}


void CN9(){
	FILE*out = fopen("tiepXucGan_out.txt", "w");
	fprintf(out, "%d\n", n);
	JRB node, bn;
	jrb_traverse(bn, g){
		fprintf(out, "%c ", jval_i(bn->key)+'A');
		JRB tree = (JRB)jval_v(bn->val);
		jrb_traverse(node, tree){
			fprintf(out, "%c ", jval_i(node->key)+'A');
		}
		fprintf(out,"\n");
	}
	fclose(out);
}


int main()
{
	while(0==0){
		printf("\n========================\n");
		printf("1. Doc file du lieu\n");
		printf("2. In danh sach tiep xuc gan\n");
		printf("3. Kiem tra tiep xuc gan\n");
		printf("4. Hien thi danh sach tiep xuc gan cua 1 nguoi\n");
		printf("5. Nguoi tiep xuc gan voi nhieu nguoi nhat\n");
		printf("6. Truy vet F1, F2\n");
		printf("7. Bo sung nguoi moi\n");
		printf("8. Bo sung thong tin tiep xuc gan moi\n");
		printf("9. Ghi danh sach ra file\n");
		printf("10. Thoat\n");
		printf("========================\n");
		
		int menu;
		printf("Moi nhap menu: ");
		scanf("%d", &menu);
		while(menu<=0 || menu >= 11){
			printf("Moi nhap lai menu: ");
			scanf("%d", &menu);
		}
		switch(menu){
			case 1:
				CN1();
				break;
			case 2:
				CN2();
				break;
			case 3:
				CN3();
				break;
			case 4:
				CN4();
				break;
			case 5:
				CN5();
				break;
			case 6:
				CN6();
				break;
			case 7:
				CN7();
				break;
			case 8:
				CN8();
				break;
			case 9:
				CN9();
				break;
			case 10:
				dropGraph(g);
				exit(0);
		}
		
	}
	
}
