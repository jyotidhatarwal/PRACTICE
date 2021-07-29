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



