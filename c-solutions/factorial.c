#include<stdio.h>
#include<stdlib.h>
int main()
{
    int *z,i,n,j,w1=0,t,x1=0,y1=0,a1=0,b1=0;
    char w[500000],x[500000],y[500000],a[500000],b[500000];
    printf("enter n value\n");
    scanf("%d",&n);
    z=(int *)malloc(n*sizeof(int));
    if(n==0||n==1)
        printf("1\n");
    else{
    for(i=1;i<n;i++)
        z[i]=0;
    z[0]=1;
    for(j=0;j<n;j++)
    {
        while(z[j]>0)
        {
            t=z[j]%10;
	    for(i=j+1;i<n;i++)
            	{
            	    z[i]=(i+1)*t+z[i];
            	    t=z[i]%10;
            	}
            if(w1<500000){w[w1++]=z[n-1]%10+48;}
	    else if(x1<500000){x[x1++]=z[n-1]%10+48;}
	    else if(y1<500000){y[y1++]=z[n-1]%10+48;}
	    else if(a1<500000){a[a1++]=z[n-1]%10+48;}
	    else if(b1<500000){b[b1++]=z[n-1]%10+48;}
            for(i=j;i<n;i++)
                z[i]=z[i]/10;
        }
    }
    printf("\n\nfactorial(%d) is:\n\n",n);
    if(a1>=500000)for(i=b1-1;i>=0;i--)printf("%c",b[i]);
    if(y1>=500000)for(i=a1-1;i>=0;i--)printf("%c",a[i]);
    if(x1>=500000)for(i=y1-1;i>=0;i--)printf("%c",y[i]);
    if(w1>=500000)for(i=x1-1;i>=0;i--)printf("%c",x[i]);
    for(i=w1-1;i>=0;i--)
        printf("%c",w[i]);
    printf("\n");}
    printf("no of digits are %d + %d + %d + %d + %d \n",w1,x1,y1,a1,b1);  
    return 0;
}
