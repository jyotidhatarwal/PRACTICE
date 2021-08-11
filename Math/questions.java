//  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//  ANY BASE TO DECIMAL NUMBER CONVERSION


 public static int anyBaseToDecimal(int n, int b){
    int val = 0;

   int p = 1;
   while(n > 0){
       int d = n % 10;
       n = n / 10;
       val += p * d;
       p = p * b;
   }

    return val;
}


//  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

// DECIMAL TO ANY BASE CONVERSION

 public static int decimalToAnyBase(int n, int b){
    int val = 0;

   int p = 1;
   while(n > 0){
       int d = n % b;
       n = n / b;
       val += p * d;
       p = p * 10;
   }

    return val;
}



//  Column name from a given column number  (GFG)


class Solution
{
    String colName (long n)
    {
        // your code here
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            long rem = n % 26;
            if(rem == 0){
                sb.append("Z");
                n = (long)Math.floor(n/26.0)-1;
            }else{
               // str = String.fromcharcode(rem-1+65) + str;
               sb.append((char)((rem-1) + 'A'));
                n = (long)Math.floor(n/26.0);
            }
        }
        String str = sb.toString();
        return reverse(str);
    }
    private String reverse(String s){
        String str = "";
        for(int i=s.length()-1;i>=0;i--){
            str += s.charAt(i);
        }
        return str;
    }
}




