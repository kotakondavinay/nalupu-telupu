#include<stdio.h>
#include<stdlib.h>

long reverse(char *fileName1, char *fileName2);

// Give a file as a argument. 
int main(int argc, char * argv[])
{
    int *z,i,j,n,t;
    FILE *fp;
    long digits;
    char *temp = "temp.txt";
    printf("enter n value\n");
    scanf("%d",&n);
    if(n==0) 
    {
	printf("\n\nfactorial of 0 is:\n\n1\n");
	return 0;
    }
    z=(int *)malloc(n*sizeof(int));
    fp = fopen(temp, "w");
    for(i=1;i<n;i++)
	z[i]=0;
    z[0]=1;
    for(j=0;j<n;j++)
    {
	while(z[j] > 0)
	{
	    t=z[j]%10;
	    for(i=j+1;i<n;i++)
	    {
		z[i]=(i+1)*t+z[i];
		t=z[i]%10;
		z[i]=z[i]/10;
		if(t == 0 && z[i] == 0 && z[n-1] == 0)
		    break;
	    }
	    fputc(t+48, fp);
  	    z[j]=z[j]/10;
	}
    }
    fclose(fp);
    printf("no of digits are %d \n", reverse(temp, argv[1]));
    remove(temp);
    return 0;
}

//Reverse Content of fileName1 to fileName2.
long reverse(char *fileName1, char *fileName2)
{
	FILE *f1, *f2;
	char ch;
	long count, lastPosition;
	if (f1 = fopen(fileName1, "r"))    
        {
            if(f2 = fopen(fileName2, "w")) 
	    {
		fseek(f1, -1L, 2);
		lastPosition = ftell(f1);
		count = lastPosition+1; 
		while (count)
		{
		    ch = fgetc(f1);
		    fputc(ch, f2);
		    fseek(f1, -2L, 1);
		    count--;
		}
	    }
	    else
	    {
		printf("Error writing to file %s\n", fileName2);
	    }
        }
        else
        {
            printf("Error opening file %s\n", fileName1);
        }
        fclose(f1);
        fclose(f2);
        return lastPosition+1;
}
