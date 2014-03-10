import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: WANG
 * Date: 2/15/14
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class findnthprime {
    private int[] primes;
   private  int[] primesindex;
    private  int initiallen;
    findnthprime(int n,int initial)
    {   primes = new int[n];
        primesindex=new int[n];
        initiallen = initial;
    }

    public int get ()
    {
        double start = Math.sqrt(initiallen);
        int nthprime = 1;
        boolean[] tempstore = new boolean [initiallen];
        for (int i=2; i<=initiallen;i++)
        {
            if (!tempstore[i-1])
            {
                primes[nthprime-1]=i;
                int j;
                for ( j=i;(j*i)<=initiallen;j++)
                tempstore[j*i-1]=true;
                                primesindex[nthprime-1]=(int) Math.floor((double)initiallen/(double) i)+1;
                                 nthprime++;
                if (nthprime>primes.length)
                    return i;
            }
        }
        for (int k=2*initiallen;;k+=initiallen)
        {     tempstore = new boolean [initiallen];
          for (int i=0;primes[i]!=0;i++)
          {        int j;
              for ( j = primesindex[i];primes[i]*j<=k;j++)
                             tempstore[primes[i]*j-1-k+initiallen]=true;
              primesindex[i]=(int) Math.floor((double)k/(double) primes[i])+1;
          }
             for (int i=0;i<initiallen;i++)
            {
                if (!tempstore[i])
                {
                    primes[nthprime-1]=i+1+k-initiallen;
                    primesindex[nthprime-1]=2;
                    nthprime++;
                    if (nthprime>primes.length)
                      return  i+1+k-initiallen;
                }
            }

        }

    }

    public static void main (String[] args)
    {

        long startTime = System.nanoTime();

        findnthprime test=new findnthprime(60,10);
       int result =    test.get();

        long endTime =  System.nanoTime();


        System.out.println(result);
        System.out.println(Arrays.toString(test.primes));
        System.out.println(endTime-startTime);
    }

}
