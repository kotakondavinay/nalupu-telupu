#include<stdio.h>
#include<stdlib.h>
int main()
{
    //max outputsize is 5,000,000. 5 millions. maxOutCounter * maxOutputIndex.
    //change these values if you are getting out of memory exception.
    int *z,i,j,k,n,t,currentPointer=-1,outputModuloCounter=0,maxOutCounter=50000,maxOutputIndex=100;
    char **output;
    printf("enter n value\n");
    scanf("%d",&n);
    if(n==0) {
	printf("\n\nfactorial of 0 is:\n\n1\n");
	return 0;
    }
    z=(int *)malloc(n*sizeof(int));
    output = (char**)malloc(sizeof(char*) * maxOutputIndex);
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
	    if(currentPointer == -1 || outputModuloCounter == maxOutCounter) {
	        outputModuloCounter = 0;
		currentPointer += 1;
		output[currentPointer] = (char *)malloc(maxOutCounter*sizeof(char));
	    }
	    output[currentPointer][outputModuloCounter++]=t%10+48;
	    z[j]=z[j]/10;
	}
    }
    printf("\n\nfactorial of (%d) is:\n\n",n);
    j = currentPointer;
    k = outputModuloCounter;
    while(j >= 0) {
	for(i = k;i >= 0;i--) {
	    printf("%c",output[j][i]);
	}
	k = maxOutCounter;
	j--;
    }
    printf("\n");
    printf("no of digits are %d \n", (currentPointer * maxOutCounter) + outputModuloCounter);  
    return 0;
}
