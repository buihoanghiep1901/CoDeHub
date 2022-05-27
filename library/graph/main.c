//#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "jrb.h"
#include "dllist.h"
#include "fields.h"

#define INFINITIVE_VALUE  10000000

typedef struct {
   JRB edges; //cạnh
   JRB vertices; //đỉnh
} Graph;

Graph createGraph() 
{
   Graph g; //đồ thị
   g.edges = make_jrb();  //cạnh
   g.vertices = make_jrb();  //đỉnh
   return g;
}

char* lowercase(char *s) { //đưa xâu về dạng viết thường
            int i;
            for(i=0; i<strlen(s); i++) {
                        if(s[i] >='A' && s[i] <='Z')
                                    s[i] = s[i] + 32;
            }

}

int check_vetex_existed(Graph g,char* name){ // ktra xem đỉnh đã tồn tại chưa 
    JRB node;
    Jval tree;
    char *a[30],*b[30];
    jrb_traverse(node,g.vertices){//duyệt tùng node trong tree
        tree.s=jval_s(node->val);// lấy value 
        strcpy(a,tree.s);
        strcpy(b,name);
        lowercase(a);
        lowercase(b);
        if(strcmp(a,b)==0)
            return 0; //có tồn tại đỉnh
    }
    return 1;// không tồn tại đỉnh

}

int addVertex(Graph g, int id, char* name) // thêm đỉnh vào cây
{   
    if(check_vetex_existed(g,name)==1){ // kiểm tra xem đỉnh đã tồn tại chưa
        jrb_insert_int(g.vertices, id, new_jval_s(strdup(name))); // chưa thì insert
        return 1; 
    }else{
        return 0; 
    }          
}

char *get_vetex_name(Graph g, int id){ // xác định tên đỉnh dựa vào stt
     JRB node = jrb_find_int(g.vertices, id);
     if (node==NULL) 
        return NULL;
     else                
        return jval_s(node->val);
}     

int vertex(Graph g,char *name){ // xác định stt đỉnh dựa vào tên đỉnh
    JRB node;
    Jval tree;
    char * a[30],*b[30];
    jrb_traverse(node,g.vertices){
        tree.s= jval_s(node->val);
        strcpy(a,tree.s);
        strcpy(b,name);
        lowercase(a);
        lowercase(b);
        if(strcmp(a,b)==0)
            return jval_i(node->key);
    }
    return 200;
}

double check_edge_existed(Graph graph, int v1, int v2)// ktra cạnh đã tồn tại chưa
{
    JRB node, tree;
    node = jrb_find_int(graph.edges, v1);// tìm v1 là điểm xuất phát
    if (node==NULL)
       return INFINITIVE_VALUE;
    tree = (JRB) jval_v(node->val);// nếu có v1 thì truy cập vào valua cửa nó
    node = jrb_find_int(tree, v2);// tìm v2 la điểm đến trong valua cửa v1
    if (node==NULL)
       return INFINITIVE_VALUE;
    else
       return 1;       
}

void addEdge(Graph graph, int v1, int v2)// thêm cạnh vào cây g.edge
{
     JRB node, tree;
     if (check_edge_existed(graph, v1, v2)==INFINITIVE_VALUE)
     {
        node = jrb_find_int(graph.edges, v1);
        if (node==NULL) {
           tree = make_jrb();
           jrb_insert_int(graph.edges, v1, new_jval_v(tree));
        } else
        {
           tree = (JRB) jval_v(node->val);   
        }
        jrb_insert_int(tree, v2, new_jval_i(0));

     }  
}

char* get_edge_mark(Graph g,int v1,int v2){ // lấy mark (số hiệu cửa bus) trong valua cửa v2
    JRB node, tree;
    node = jrb_find_int(g.edges, v1);
    if (node==NULL)
       return INFINITIVE_VALUE;
    tree = (JRB) jval_v(node->val);
    node = jrb_find_int(tree, v2);
    if (node==NULL)
       return INFINITIVE_VALUE;
    else
       return jval_s(node->val);
}

int file_input(Graph g, IS is){
    char from[30],to[30];
    int i=0;
    printf("\n input; ");

    while(get_line(is)>=0){
        printf("\nline= %d",is->line);
        printf("\nNF= %d",is->NF);
        if(is->line!=1){
            strcpy(from,is->fields[0]); printf("\nField[0]= %s", is->fields[0]);
            if(addVertex(g,i,from)==1)
                i++;
            for(int n=1; n<is->NF;n++){
                strcpy(to,is->fields[n]);
                printf("\n is->field[%d]= %s",n, to);
                //printf("\n i= %d",i);
                if(addVertex(g,i,to)==1){// thêm đỉnh thành công thì i++(stt đỉnh)
                    i++;
                    printf("\nnhap thanhf cong");
                }
                //printf("\n i= %d",i);
                int a=vertex(g,from);  
                printf("\n a= %d", a);
                int b=vertex(g,to);
                printf("\n b= %d", b);
                
            
                addEdge(g, a, b);// thêm cạnh 
            }

        } 
        
    }
    return 1;
}

int outdegree (Graph graph, int v, int* output)// v là điểm bắt đầu tìm các đỉnh nối với nó
{
    JRB tree, node,a;
    int total;
    node = jrb_find_int(graph.edges, v);//tìm v
    if (node==NULL)
       return 0;
    tree = (JRB) jval_v(node->val);//nếu có v truy cập vào value cửa no
    total = 0;   
    jrb_traverse(a, tree)//duyệt value 
    {
       output[total] = jval_i(a->key);// lưu các đỉnh nối với v vào output
       //printf("\n%d ",a->key);
       total++;                
    }
    return total;   
}

void dropGraph(Graph graph)
{
    JRB node, tree;
    jrb_traverse(node, graph.edges)
    {
        tree = (JRB) jval_v(node->val);
        jrb_free_tree(tree);
    }
    jrb_free_tree(graph.edges);
    jrb_free_tree(graph.vertices);
}

void cn2(Graph g){
    JRB node;
    Jval tree;
    int output[100];
    jrb_traverse(node,g.edges){
        tree.s=get_vetex_name(g,node->key.i);
        printf("\n%s",tree.s);
        int a=outdegree(g,node->key.i,output);
        for(int i=0;i<a;i++){
            tree.s=get_vetex_name(g,output[i]);
            printf("%4s",tree.s);
        }
    }
}

void cn3(Graph g){
    char a[25],b[25];
    printf("\n Nhap lan luot 2 ng: \n");
    scanf("%s",&a);
    scanf("%s",&b);
    int x=vertex(g,a);
    int y=vertex(g,b);
    int c=check_edge_existed(g,x,y);
    if(c==1){
        printf("\n2 ng co tieep xucs gan");
    }else{
        printf(" \n2 nguoi ko tiep xuc gan");
    }

}

void cn4(Graph g){
    Jval tree;
    char name[25];
    int output[100];
    printf("\n Nhap nguoi:"); scanf("%s",&name);
    int a=vertex(g,name);
    int b=outdegree(g,a,output);
    if(b==0){
        printf(" \nkhong co ai tiep xuc gan vowi %s",name);
    }else{
        printf("\ndanh sach nhung nguoi tiep xuc gan voi %s: ",name);
        for(int i=0;i<b;i++){
            tree.s=get_vetex_name(g,output[i]);
            printf("%4s",tree.s);
        }
    }
}

void cn5(Graph g){
    printf("\n nhap nguoi: ");
    char name[30];
    int output[100], n,tx[100],i=0;
    JRB node;
    Jval tree;
    jrb_traverse(node, g.edges){
        n=outdegree(g,node->key.i,output);
        tx[i]=n;
        i++;

    }
    
}





int main()
{
    
    Graph g = createGraph();
    IS is;
    is=new_inputstruct("tiepxucgan.txt");
    char s[30],t[30];
    int i,length,path[100];
    double w;
    printf("\nstart");
    

    while(1){
        printf("\n*********************Menu*********************\n");
        printf("1, Tim Kiem\n");
        printf("2, Exit\n");
        printf("3, Exit\n");
        printf("4, Exit\n");
        printf("5, Exit\n");
        printf("6, Exit\n");
        printf("7, Exit\n");
        printf("**********************************************\n");
        printf("Lua chon cua ban: ");
        int choose;
        scanf("%d",&choose);
        
        switch(choose){
            case 1:
                file_input(g,is);
                if(file_input(g,is)==1)
                    printf("\ntải dữ liệu thành công\n");
                break;
            case 2:
                cn2(g);
                break;
            case 3:
                cn3(g);
                break;
            case 4:
                cn4(g);
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 10:
                dropGraph(g);
                exit(0);
            
        }

        

        
    }
    
    getch();   
}
/* BUG:
-Từ nhập từ bàn phím có thể viết hoa hoặc không, nhưng nếu là các chứ cái tiếng việt đ,ê,... thì phải nhập viết hoa nếu ở đầu dòng
vd:
    lý_thường_kiệt=Lý_Thường_Kiệt
    xã_đàn != Xã_Đàn
    xã_Đàn=Xã_Đàn
-Đôi khi nhập tiếng việt viết hoa từ àn phím có thể xuất hiện thêm những kí tự lạ????
*/
