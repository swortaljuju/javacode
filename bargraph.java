import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: WANG
 * Date: 2/16/14
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class bargraph {
    private int[] origin;
    private int[] indexarray;
    bargraph(int[] origin1)
    {
        origin=origin1;
        indexarray=new int[origin1.length];
        for (int i=0;i<origin1.length;i++)
            indexarray[i]=i;
    }
    private void exchange(int a, int b)
    {
        int temp = indexarray[a];
        indexarray[a]=indexarray[b];
        indexarray[b]=temp;
    }
    private  void quicksort(int start,int end)
    {
        if (start>=end)
            return;
        exchange(end, (start+end)/2 );
        int temp=start,tempend=origin[indexarray[end]];
        for (int i=start;i<end;i++)
           if (origin[indexarray[i]]>=tempend)
          {exchange(temp,i);temp++;}
        exchange(end, temp );
        quicksort(start,temp-1);
        quicksort(temp+1,end);
       }

    private int get()
    {
       int max=indexarray[0],left,right;
        left=right=max;
        int sum=0;
        for (int i=1;i<origin.length;i++)
        {
            int temp=indexarray[i] ;
          if (temp<left)
          {
              if (temp!=left-1)
                  for (int j=temp;j<left;j++)
                      sum+=origin[temp]-origin[j];
               left=temp;
          }
            else if (temp>right)
            {
                if (temp!=right+1)
                     for (int j=temp;j>right;j--)
                        sum+=origin[temp]-origin[j];

                right=temp;
            }
        }

        return sum;
    }
    public static int get (int[] origin)
    {
       bargraph graph = new bargraph(origin);
        graph.quicksort(0,origin.length-1);
        return graph.get();
    }
   public static void quicksorttest(int[] origin1)
   {
       bargraph graph = new bargraph(origin1);
       graph.quicksort(0,origin1.length-1);
       for (int i=0;i<origin1.length;i++)
       System.out.print(graph.origin[graph.indexarray[i]]+"  ");
       System.out.print("\n");
       System.out.println(Arrays.toString(origin1));
   }
    public static void main(String[] args)
    {
   //     quicksorttest(new int[]{3,2,6,5,1,5,4,3,8});
//       System.out.println(bargraph.get(new int[]{3,2,3}));
     //   System.out.println(bargraph.get(new int[]{3,2,6,5,1,5,4,3,8}));

        System.out.println(bargraph.get(new int[]{1, 5, 1, 3, 1, 10, 1, 9, 1}));
    }
}
