#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include"MST_Minspaningtree.h"
#include"dijktras1.h"
#include"tarjan_scc.h"
#include"count.h"

int main(){
    printf("\n Cây bao trùm nhỏ nhất:\n");
    primMSTrun();

    printf("\n Dijktras:\n");
    dijktras();

    printf("\n Thành phần liên thông mạnh:\n");
    tplt_manh();

    printf(" \n Đém số thành phần liên thông\n");
    lienth("lienth.txt");
    
    return 0;
}