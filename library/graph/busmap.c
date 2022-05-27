// #include<conio.h>
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
    return 0;
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

void addEdge(Graph graph, int v1, int v2, char *mark)// thêm cạnh vào cây g.edge
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
        jrb_insert_int(tree, v2, new_jval_s(strdup(mark)));

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
    char from[30],to[30], mark[20];// là value lưu điểm đến, diểm đi và số xe(mark) 
    int i=0;// i là key(stt các điểm dừng xe bus) 
    //printf("START:\n");
    while(get_line(is)>=0){
        //printf("A\n");
        strcpy(from,is->fields[0]); //printf("\n %s", is->fields[0]);
        strcpy(to,is->fields[1]);   //printf("\n %s", is->fields[1]);
        strcpy(mark,is->fields[2]); //printf("\n %s", is->fields[2]);
        if(addVertex(g,i,from)==1)
            i++;
        //printf("\n i= %d",i);
        if(addVertex(g,i,to)==1){// thêm đỉnh thành công thì i++(stt đỉnh)
            i++;
        }
        //printf("\n i= %d",i);
        int a=vertex(g,from); // tìm stt a(key) tương ứng với tên đỉnh (from-value) 
        //printf("\n a= %d", a);
        int b=vertex(g,to);
        //printf("\n b= %d", b);
        addEdge(g, a, b, mark);// thêm cạnh 
    }
    return 1;
}

int indegree (Graph graph, int v, int* output)
{
    JRB tree, node;
    int total = 0;   
    jrb_traverse(node, graph.edges)
    {
       tree = (JRB) jval_v(node->val);
       if (jrb_find_int(tree, v))
       {
          output[total] = jval_i(node->key);
          total++;
       }                
    }
    return total;   
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

void BFS(Graph graph, int start, int stop)
{
   int visited[100] = {};
   int n, output[100], i, u, v;
   Dllist node, queue;
   
   queue = new_dllist();
   dll_append(queue, new_jval_i(start));

   while ( !dll_empty(queue) )
   {
      node = dll_first(queue);   
      u = jval_i(node->val);
      dll_delete_node(node);
      if (!visited[u]) 
      {
          printf("\n%d", u);
          visited[u] = 1;
          if ( u == stop ) return;           
          n = outdegree(graph, u, output);
          for (i=0; i<n; i++)
          {
              v = output[i];
              if (!visited[v])
                 dll_append(queue, new_jval_i(v));
          }
      }
   }                            
}

void DFS(Graph graph, int start, int stop)
{
   int visited[100] = {};
   int n, output[100], i, u,v;
   Dllist node, stack;
   
   stack = new_dllist();
   dll_append(stack, new_jval_i(start));

   while ( !dll_empty(stack) )
   {
      node = dll_last(stack);
      u = jval_i(node->val);
      dll_delete_node(node);
      if (!visited[u]) 
      {
        printf("\n%d", u);
          visited[u] = 1;
          if ( u == stop ) return;           
          n = outdegree(graph, u, output);
          for (i=0; i<n; i++)
          {
              v = output[i];
              if (!visited[v])
                 dll_append(stack, new_jval_i(v));
          }
      }
   }                            
}

int DAG(Graph graph)
{
   
   int n, output[100], i, u, v, start;
   Dllist node, stack;
   JRB vertex;
   
   jrb_traverse(vertex, graph.vertices)
   {
       int visited[1000] = {};
       start = jval_i(vertex->key);              
       stack = new_dllist();
       dll_append(stack, new_jval_i(start));
    
       while ( !dll_empty(stack) )
       {
          node = dll_last(stack);
          u = jval_i(node->val);
          dll_delete_node(node);
          if (!visited[u])
          {
              visited[u] = 1;
              n = outdegree(graph, u, output);
              for (i=0; i<n; i++)
              {
                  v = output[i];
                  if ( v == start ){
                      printf("\n cycle detected: %d \n",v);
                      return 0;

                  } // cycle detected 
                     
                  if (!visited[v])    
                     dll_append(stack, new_jval_i(v));
              }
          }           
       }
   }
   printf("\nAcycle detected \n");
   return 1; // no cycle    
}

double shortestPath(Graph g, char* from, char* to, int* path, int*length)
{   printf("\n STARTING");
    int s,t;
    s=vertex(g,from);
    t=vertex(g,to);
    //printf("\n s and t: %d %d",s,t);
    
   double distance[1000], min, w, total;
    int previous[1000], tmp[1000];
    int n, output[100], i, u, v, start;
    Dllist ptr, queue, node;
 
    for (i = 0; i < 1000; i++)
        distance[i] = INFINITIVE_VALUE;
    distance[s] = 0;
    previous[s] = s;
 
    queue = new_dllist();
    dll_append(queue, new_jval_i(s));
 
    while (!dll_empty(queue))
    {
        // get u from the priority queue
        min = INFINITIVE_VALUE;
        dll_rtraverse(ptr, queue)
        {
            u = jval_i(ptr->val);
            if (min >= distance[u])
            {
                min = distance[u];
                node = ptr;
                printf("\nPTR= %d",jval_i(node->val));
            }
        }
        u = jval_i(dll_first(queue)->val);
        printf("\n U= %d",u);
 
        //printf("dll_delete_node: %d\n", jval_i(node->val));
 
        if (jval_i(node->val) == t)
            break;             // stop at t
        printf("\nNODE= %d",jval_i(node->val));
        dll_delete_node(node); //xóa node đầu tiên (các node đã sắp xếp theo thứ tự tăng dần trong cây)
 
        n = outdegree(g, u, output);
        //printf("u = %d\n", u);
        for (i = 0; i < n; i++)
        {
            v = output[i];
            w = 1;
            if (distance[v] > distance[u] + w)
            {
                distance[v] = distance[u] + w;
                previous[v] = u;
                //printf("add_node: %d\n", v);
                dll_append(queue, new_jval_i(v));
            }
        }
    }
 
    //Tính tổng quãng đường phải đi
    total = distance[t];
    if (total != INFINITIVE_VALUE)
    {
        tmp[0] = t;
        n = 1;
        while (t != s)
        {
            t = previous[t];
            tmp[n++] = t;
            printf("\nN= %d",n);
        }
        
        for (i = n - 1; i >= 0; i--){
            path[n - i - 1] = tmp[i];
            
        }
            
        *length = n;
        
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

int main()
{
    
    Graph g = createGraph();
    IS is;
    is=new_inputstruct("Tim_Bus.txt");
    char s[30],t[30];
    int i,length,path[100];
    double w;
    
    file_input(g,is);
    if(file_input(g,is)==1)
        printf("tải dữ liệu thành công\n");
        printf("\nBFS:");
        BFS(g,0,-1);
        printf("\nDFS:");
        DFS(g,0,-1);
        DAG(g);
/*
    while(1){
        printf("\n*********************Menu*********************\n");
        printf("1, Tim Kiem\n");
        printf("2, Exit\n");
        printf("**********************************************\n");
        printf("Lua chon cua ban: ");
        char choose[20];
        gets(choose);
        switch(choose[0]){
            case '1':
                printf("\n Điểm bắt đầu: ");
                scanf("%s[^\n]",&s);// lấy luôn cả dấu cách
                while (1==1)
                {
                    if(check_vetex_existed(g,s)!=0){
                        printf("\n Điểm bắt đầu ko tồn tại, hãy nhập lại!");
                        printf("\n Điểm bắt đầu: ");
                        scanf("%s[^\n]",&s);
                    }else{
                        break;
                    }
                    
                }

                printf("\n Điểm đến : ");
                scanf("%s[^\n]",&t);
                while (1==1)
                {
                    if(check_vetex_existed(g,t)!=0){
                        printf("\n Điểm đến không tồn tại, hãy nhập lại!");
                        printf("\n Điểm đến: ");
                        scanf("%s[^\n]",&t);
                    }else{
                        break;
                    }
                    
                }
                
                w=shortestPath(g, s, t, path, &length);
                if (w == INFINITIVE_VALUE)
                {
                    printf("\n No path from %s to %s", s, t);
                }else
                {
                    printf("\n Path from  %s to %s (with total distance %f)\n", s, t, w);
                    for (i=0; i<length; i++)
                        if(i>=1)printf("\n  => %s ( %s)", get_vetex_name(g, path[i]),get_edge_mark(g,path[i-1],path[i]));
                        else printf("\n  => %s", get_vetex_name(g, path[i]));
                memset(choose, '\0', strlen(choose));
                break;
            case '2':
                printf("Exit!\n\n");
                dropGraph(g);
                memset(choose, '\0', strlen(choose));
                return 0;
            default: 
                printf("Chon 1 hoac 2!\n");
                memset(choose, '\0', strlen(choose));
                break;
        }

        

        }
    }*/
    
    // getch();   
}
/* BUG:
-Từ nhập từ bàn phím có thể viết hoa hoặc không, nhưng nếu là các chứ cái tiếng việt đ,ê,... thì phải nhập viết hoa nếu ở đầu dòng
vd:
    lý_thường_kiệt=Lý_Thường_Kiệt
    xã_đàn != Xã_Đàn
    xã_Đàn=Xã_Đàn
-Đôi khi nhập tiếng việt viết hoa từ àn phím có thể xuất hiện thêm những kí tự lạ????
*/
