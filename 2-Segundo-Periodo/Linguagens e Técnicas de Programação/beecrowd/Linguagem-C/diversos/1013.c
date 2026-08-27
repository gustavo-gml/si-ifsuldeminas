#include <stdio.h>

int main()
{
    int a,b,c,maior;

    scanf("%d %d %d",&a,&b,&c);
    int vet[3] = {a, b, c};

    for(int i = 0; i<3; i++){
        if(i == 0){
            maior = vet[i];
        }
        else if(vet[i]>maior){
            maior = vet[i];
        }
        
    }

    printf("%d eh o maior\n",maior);

}