/**
 * Created with IntelliJ IDEA.
 * User: WANG
 * Date: 1/20/14
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class kmp {
    static void makenext(char[] string, int[] next)
    {
        int len=string.length,i=2,j=1;
        next[0]=0;
        next[1]=0;
        while(i<len)
        {
            if (j==0||string[i-1]==string[j-1])
            {
                next[i]=j;
                i++;
                j++;
            }
            else
                j=next[j];
        }
    }
    static boolean kmpmatch(char[] t, char[] p,int[] next)
    {
         int lent=t.length,lenp=p.length,i=0,j=0;
         while(i<lent)
         {
             if(t[i]==p[j])
             {
                 if (j==lenp-1)
                     return true;
                 i++;j++;
             }
             else
             {
                 if(j==0)
                     i++;
                 else
                  j=next[j];

             }
         }
        return false;
    }
    static void test(String t,String p)
    {
         char[] pattern= p.toCharArray();
        int [] next= new int[pattern.length];
        makenext(pattern,next);
        System.out.print(t+"\n"+p+"\n"+kmpmatch(t.toCharArray(),pattern,next)+"\n");
    }
    public static void main(String[] args)
    {
        test("ABABCABAABABCBABCGHISABAABAABABCABAABABCABABCEFABABCGHISABABCBCEFABABBCEFABABBCEFABAB","ABABCABABCEFABABCGHISABABG") ;
        test("ababcdeabcde","abcde");
    }
}
