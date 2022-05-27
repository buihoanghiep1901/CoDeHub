#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<btree.h>
#include<ctype.h>

#define WORD_MAX_LEN  200
#define MEANING_MAX_LEN  40000
//lấy dữ liệu từ file anhviet.txt
// OK -> return 1, error -> return 0

void soundex(char* name, char* s)
{   
    int si = 1;
    char c;

    //                 ABCDEFGHIJKLMNOPQRSTUVWXYZ
    char mappings[] = "01230120022455012623010202";

    s[0] = toupper(name[0]);

    for(int i = 1, l = strlen(name); i < l; i++)
    {
        c = toupper(name[i]) - 65;

        if(c >= 0 && c <= 25)
        {
            if(mappings[c] != '0')
            {
                if(mappings[c] != s[si-1])
                {
                    s[si] = mappings[c];
                    si++;
                }

                if(si > 3)
                {
                    break;
                }
            }
        }
    }

    if(si <= 3)
    {
        while(si <= 3)
        {
            s[si] = '0';
            si++;
        }
    }
    for(int i=0; i<strlen(s); i++){
        s[i] = tolower(s[i]);
    }
}

int readDictText(BTA* btfile){
    FILE* fin;
    if((fin=fopen("anhviet.txt", "r"))==NULL){
        printf("Error: ko the mo file tu dien\n");
        return 0;
    }

    //biến tmp
    char line[500];
    char word[WORD_MAX_LEN];
    char meaning[MEANING_MAX_LEN];

    int meaning_len=0;
    while(!feof(fin)){
        memset(line, '\0', strlen(line)); //khởi tạo lại biến line
        fgets(line, 500, fin);

        for(int i = 0; i < strlen(line); i++){
            if(line[0]=='@'){
                //khởi tạo lại các biến và lưu word + meaning vao btree
                if(strlen(word)!=0)
                    btins(btfile, word, meaning, strlen(meaning)*sizeof(char));

                memset(word,'\0', strlen(word)); //khởi tạo lại biến word
                memset(meaning, '\0', strlen(meaning)); //khởi tạo lại biến mening
                meaning_len=0;

                i++; //bỏ qua '@'
                //lưu word từ line
                for(int j=0; line[i+1]!='/' && line[i]!='\n'; i++, j++)
                    word[j]=line[i];
                word[strlen(word)]='\0';
            }
            while (line[i]=='\n' || line[i]==' ') i++;
            //lưu meaning từ line
            for(; i<strlen(line); i++, meaning_len++){
                meaning[meaning_len]=line[i];
            }
        }
    }
    //lưu từ cuối cùng
    btins(btfile, word, meaning, strlen(meaning)*sizeof(char));
    return 1;
}

int read2text(BTA* btfile2){
    FILE* fin;
    if((fin=fopen("anhviet.txt", "r"))==NULL){
        printf("Error: ko the mo file tu dien\n");
        return 0;
    }
    char line[300];
    char *word=malloc(100);
    char *sound=malloc(20);// sound=key trong btree
    char *data=malloc(40000);// data cua sound bao gom nhieu word gop lai
    int i=0,j=0;
    int *m=malloc(40000);
    while(!feof(fin)){
        memset(line, '\0', strlen(line)); //khởi tạo lại biến line
        fgets(line, 300, fin);
        if(line[0]=='@'){              
            if(strlen(word)!=0){
                soundex(word,sound);
                if(btsel(btfile2, sound,data,MEANING_MAX_LEN,m)!=0){ // check xem da ton tai sound hay chua
                    strcpy(data,word);
                    btins(btfile2,sound,data,40000);
                }
                else{// neu da ton tai sound 
                    strcat(data,word);
                    btupd(btfile2,sound,data,40000);// update data
                }
            }                   
            memset(word,'\0', 100);
            memset(sound, '\0', 40000);
            //lưu word từ line
            for(j=0,i=1;line[i+1]!='/' && line[i]!='\n'; i++, j++)//i=1 de loai bo @
                word[j]=line[i];
            strcat(word,"\n");//"\n" de khi them word vao data thi word se xuong dong
        }
    }
    //lưu từ cuối cùng
    soundex(word,sound);
    if(btsel(btfile2, sound,data,MEANING_MAX_LEN,m)!=0){ // check xem da ton tai sound hay chua
        strcpy(data,word);
        btins(btfile2,sound,data,strlen(data)*sizeof(char));
    }
    else{// neu da ton tai sound 
        strcat(data,word);
        btupd(btfile2,sound,data,strlen(data)*sizeof(char));
    }                     
    memset(word,'\0', strlen(word));
    memset(sound, '\0', strlen(sound));
    return 1;
}

char* validWord(char* key){
    if(key[strlen(key)-1]=='\n')
        key[strlen(key)-1]='\0'; //lấy key từ hàm fgets -> ptu cuối = '\n'
    for(int i=0; i<strlen(key); i++){
        key[i] = tolower(key[i]);
    }
    return key;
}

int searchWord(BTA* btfile2, char* key, char* meaning){
    validWord(key);
    
    int meaningLength=0;
    return btsel(btfile2, key,meaning,MEANING_MAX_LEN,&meaningLength);
}

int deleteWord(BTA* btfile, char* key){
    validWord(key);
    return bdelky(btfile, key);
}

void addword(BTA* btfile){
    char key[WORD_MAX_LEN];
    char value[MEANING_MAX_LEN];
    int meaningLength=0;

	//them key, value moi vao btfile
      memset(key, 0, 50);
      memset(value, 0, 50);                 
      printf("Key: ");                      
      __fpurge(stdin);
      fgets(key, 50, stdin);
      //lay "\n" ra khoi xau key, value
      validWord(key);
      //kiem tra xem key da ton tai trong btfile chua
      
      if (btsel(btfile,key,value,MEANING_MAX_LEN, &meaningLength)!=0)
      {
        printf("Value: ");
        __fpurge(stdin);
        fgets(value, 50, stdin);
        if (value[strlen(value) - 1] == '\n')
        {
          value[strlen(value) - 1] = '\0';
        }

        btins(btfile, key, value, 50);
        printf("Them tu thanh cong\n");
      }
      else {
        printf("Key da ton tai\n");
      }
}

void main(){
    //khoi tao btree
    btinit();
    BTA *btfile; 
    btfile = btcrt("test_db", 0, FALSE);
    btcrtr(btfile, "btree");
    btdups(btfile, 0);

    BTA *btfile2;
    btfile2= btcrt("test", 0, FALSE);
    btcrtr(btfile2, "soundex");
    btdups(btfile2, 0);

    //lấy data từ file
    if(readDictText(btfile)==1)
        printf("Tai du lieu thanh cong\n");
    else
        printf("Error: tai du lieu khong thanh cong\n");

    if(read2text(btfile2)==1)
        printf("Tai du lieu  dict 2 thanh cong\n");
    else
        printf("Error: tai du lieu dict 2 khong thanh cong\n");
  
    char *key=malloc(100);
    char *value=malloc(40000);
    int  size=0;

    int choose=0;
    do{
        printf("\n***************************MENU***************************\n");
        printf("1. Tim kiem.\n");
        printf("2. Them tu.\n");
        printf("3. Xoa tu.\n");
        printf("4. Suggestion search\n");
        printf("5. Auto conplete search\n");
        printf("6. Exit\n");
        printf("**********************************************************\n\n");
        printf("Lua chon = ");
        scanf("%d%*c", &choose);
        switch (choose){
        case 1: //search word
            printf("Nhap tu can tim: ");
            fgets(key, WORD_MAX_LEN*sizeof(char), stdin);
            if(searchWord(btfile, key, value)==0){
                printf("%s\n", value);
            }else
                printf("\nKo tim thay\n\n");
            memset(key, '\0', strlen(key)); //khoi tao lai cac bien sau khi su dung
            memset(value, '\0', strlen(value));
            break;
        case 2:
            addword(btfile);
            break;
        case 3: 
            printf("Nhap tu can xoa: ");
            fgets(key, WORD_MAX_LEN, stdin);
            switch (deleteWord(btfile, key)){
            case 0:
                printf("Xoa thanh cong\n");
                break;
            case QNOKEY:
                printf("Tu can xoa ko ton tai\n");
                break;
            default:
                printf("Xay ra loi\n");
                break;
            }
            memset(key, '\0', strlen(key));
            break;
        case 4:
            printf("Nhap tu nao ban ei: ");
            fgets(key, WORD_MAX_LEN*sizeof(char), stdin);
            char s[20];
            soundex(key,s);
            printf("%s\n",s);
            if(btsel(btfile2,s,value,MEANING_MAX_LEN,&size)==0){
                printf("Hey bruh co ve ban da nhap khong dung, de toi suggest 1 so tu cho nhe:\n");
                printf("%s\n",value);
            }else{
                printf("Oh no!!! Tu ban nhap sai qua ae toi khong tim dc tu de suggest luon!!");
            }
            memset(key, '\0', strlen(key)); 
            memset(value, '\0', strlen(value));
            memset(s, '\0', strlen(s));
            break;
        case 5:
            break;
        case 6: 
            printf("Exit\n");
            return;
        default:
            printf("Nhap cac lua chon tu 1 -> 4\n");
        }
    } while (1);
}