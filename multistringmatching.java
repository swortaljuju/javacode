import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WANG
 * Date: 1/25/14
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
class treenode{
    HashMap<Character,treenode> childs=new HashMap<Character, treenode>();
    treenode fail;
    ArrayList<treenode> failprev=new ArrayList<treenode>(); //for ease of adding up all cnt whose owner contain this substring
    int cnt=0;
 }
public class multistringmatching {
   private treenode root;
   private HashMap<String,treenode> ref;        //for ease of extract cnt of each string from the tree
    multistringmatching()  {
        root = new treenode();
        root.fail=root;
        ref=new HashMap<String, treenode>();
    }
    /*first build tree second find fail value*/
  public void buildtree(String... args)         {
      //first
      for(String s:args)
      {
          treenode cur = root;
           treenode temp=null;
           char[] c=s.toCharArray();
           for (char c1:c)
           {     if ((temp=cur.childs.get(c1))==null)
                 {
                    temp=new treenode();
                     cur.childs.put(c1,temp);
                     cur=temp;
                  }
                  else
                         cur=temp;
           }
           ref.put(s,temp);
             }
      //second dfs
        Stack<treenode> travelednodes = new Stack<treenode>();
      // root's children all point to root
        if (!root.childs.isEmpty())
            for(Map.Entry<Character,treenode> entry:root.childs.entrySet())
            {
                treenode value=entry.getValue();
                   value.fail=root;
                   if (!value.childs.isEmpty())
                    travelednodes.push(value);
            }
      //dfs   fail is either its parent's fails' next node or child of root or root
        while(!travelednodes.empty())
        {
           treenode temp=travelednodes.pop();
           for(Map.Entry<Character,treenode> entry:temp.childs.entrySet())
           {
               treenode temp1=null,value=entry.getValue();
               Character key = entry.getKey();
               if ((temp1=temp.fail.childs.get(key))!=null)
               {   value.fail=temp1; temp1.failprev.add(value);}
               else if ((temp1=root.childs.get(key))!=null)
               {  value.fail=temp1;        temp1.failprev.add(value);}
               else
                   value.fail=root;
               if (!value.childs.isEmpty())
                   travelednodes.push(value);
           }
        }
    }
    /* adding up all cnt whose owner contain this substring (dfs)
    * if one string's end node is called, all its "superstring"'s cnt will be updated and their failprev will be cleared so that next time if
    * any of them is called it can return its cnt directly without doing dfs all the way down again */
    private int calcnt(treenode node)
    {
        if (!node.failprev.isEmpty())
              for (treenode i:node.failprev)
                   node.cnt+=calcnt(i);
         node.failprev.clear();
        return node.cnt;
    }
 // first do matching through the tree, second collect and sum all pattern strings' count.
 public HashMap<String,Integer> check(String s)
 {
     char[] string=s.toCharArray();
     treenode cur=root;
     // first for each character go through the tree stop if match or never match
     for (char ch:string)
     {   treenode temp=null;
         while(true)
         {
             if ((temp=cur.childs.get(ch))!=null)    //match
             {cur=temp;cur.cnt++;break;}
             else if (cur==root) break;     //never match
             else
                 cur =cur.fail;
         } }
     if (ref.isEmpty())
         return null;
     HashMap<String,Integer> map = new HashMap<String, Integer>();
     //seconds
      for (Map.Entry<String,treenode> entry:ref.entrySet())
     {
         treenode node=entry.getValue();
        map.put(entry.getKey(),calcnt(node));
     }
     return map;
 }
    public HashMap<String,Integer> buildandcheck(String s,String... args)
    {
       buildtree(args);
       return check(s);
    }
     public static void test(String s,String... args)
     {
         multistringmatching ml =new multistringmatching();
         HashMap<String,Integer> nap=ml.buildandcheck(s, args);
         if (nap==null)
             System.out.println("null");
         for(Map.Entry<String,Integer> entry:nap.entrySet())
             System.out.print(entry.getKey()+"="+entry.getValue()+"    ");
         System.out.print("\n");
     }
    public static void main(String[] args){

      test("abcdef abco abcdhq abh abhpa abhpaq abhpas abcdefg bco","abcdef","abhpa","bcd","bhpaq","efg","dhq","co");
      test("abcd cd","abcd","bcd","cd","d");
        test("abcdabcdabe","abcdabe");
    }
}
