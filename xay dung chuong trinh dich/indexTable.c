#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int removemeanningless(char *a){
    char temp[200], temp2[200];
    strcpy(temp,a);
    lowercase(temp);
    FILE *f = fopen("stopw.txt", "r");
    if(!f) exit(1);
    while(fscanf(f, "%s", temp2) == 1){
        removeSpecialCharacters(temp);
        if(strcmp(temp, temp2)==0){
        fclose(f);
        return 1;
    }
        if(temp[0] >= '0' && temp[0] <= '9'){
            fclose(f);
            return 1;
        }
    }
    fclose(f);
    return 0;
}

int checkCapital(char *a){
    char temp[200];
    strcpy(temp, a);
    removeSpecialCharacters(temp);
    if(temp[0] >= 'A' && temp[0] <= 'Z') return 1;
    else return 0;
}

void removeSpecialCharacters(char *a){
    int i,j;
    for(i = 0;a[i] != '\0';++i){
        while(!(a[i] >= 'a' && a[i] <= 'z') && !(a[i] >= 'A' && a[i] <= 'Z') && !(a[i] == '\0') && !(a[i] >= '0' && a[i] <= '9')){
            for(j = i;a[j] != '\0';++j){
                a[j] = a[j+1];
            }
            a[j] = '\0';
        }
    }
    return;
}

int checkSpecialCharacter(char a){
    if(( a>='a'&& a<='z')||(a >='A'&& a<='Z')) return 0;
    return 1;
}

void lowercase(char *a){
    int i;
    for(i = 0;a[i] != '\0';++i){
        if(a[i] >= 'A' && a[i] <= 'Z') a[i] += 32;
    }
    return;
}

void indextable(){
    char words[200][200], words2[200][200], temp[200], line[500];
    int i = 0,next = 0,a,b,c,d,n,linenumber = 1,count = 0;
    FILE *f = fopen("vanban.txt", "r");
    if(!f) exit(1);
    while(fscanf(f, "%s", temp) == 1){
        if(removemeanningless(temp) == 0){
            if(next == 1 && checkCapital(temp) == 1){
                if(temp[strlen(temp) - 1] != '.' || strcmp(temp, "U.S.") == 0) continue;
                else{
                    next = 0;
                    continue;
                }
            }
            if(temp[strlen(temp)-1] != '.'){
            next = 1;
            strcpy(words[i], temp);
            removeSpecialCharacters(words[i]);
            lowercase(words[i]);
            i++;
            }else if(strcmp(temp, "U.S.") != 0){
                next = 0;
                strcpy(words[i], temp);
                removeSpecialCharacters(words[i]);
                lowercase(words[i]);
                i++;
            }
        
        }
    }
    for(a = 0;a < i;++a){
        for(b = a+1;b < i;++b){
            if(strcmp(words[a], words[b]) > 0){
                strcpy(temp, words[a]);
                strcpy(words[a], words[b]);
                strcpy(words[b], temp);
            }
        }
    }
    b = 0;
    for(a = 0;a < i;++a) if(strcmp(words[a], words[a+1]) != 0) strcpy(words2[b++], words[a]);
    strcpy(words2[b], words[i]);
    fseek(f, 0, SEEK_SET);
    for(c = 0;c < b;++c){
        fseek(f, 0, SEEK_SET);
        printf("%s ",words2[c]);
            while(fscanf(f,"%s",temp) == 1){
                removeSpecialCharacters(temp);
                lowercase(temp);
                if(strcmp(words2[c], temp) == 0) count++;
            }
        fseek(f, 0, SEEK_SET);
        printf(" %d",count);
        count = 0;
            while(fgets(line, 256, f) != NULL){
                a = 0;
                n = strlen(line);
                    while(a < n){
                    d = 0;
                    while( a < n && d < strlen( words2[c] ) && ( line[a] == words2[c][d] || line[a] + 32 == words2[c][d] ) ){
                            ++a;
                            ++d;
                        }
                    if( (a == n||checkSpecialCharacter(line[a])==1) && d == strlen( words2[c] ) ) 
                    {
                        
                        printf(",%d", linenumber);
                    }
                    while(a < n && line[a] != ' ') a++;
                    a++;
                    }
        linenumber++;
    }
    printf("\n");
    linenumber = 1;
    }
    fclose(f);
    return;
}
    
void main(){
    indextable();
    return;
}