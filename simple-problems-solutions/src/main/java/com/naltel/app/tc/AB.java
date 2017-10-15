package com.naltel.app.tc;

public class AB {
    public static int count(String s) {
        int counter = 0;
        for(int i = 0; i < s.length()-1; i++) {
            for(int k = i+1; k < s.length();k++) {
                if(s.charAt(i) == 'A' && s.charAt(k) == 'B') {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int countABC(String s) {
        int counter = 0;
        for(int i = 0; i < s.length()-1; i++) {
            for(int k = i+1; k < s.length();k++) {
                if( (s.charAt(i) == 'A' && s.charAt(k) == 'B') ||
                        (s.charAt(i) == 'A' && s.charAt(k) == 'C' ) ||
                        (s.charAt(i) == 'B' && s.charAt(k) == 'C') ) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static String createString(int n, int k) {
        int left = n/2;
        int right = n - n/2;
        if( k > left * right) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for( int rightPointer = 0; rightPointer <= right; rightPointer++) {
            if (k == rightPointer * (n - rightPointer)) {
                for (int i = 0; i < n - rightPointer; i++) {
                    sb.append('A');
                }
                for (int i = 0; i < rightPointer; i++) {
                    sb.append('B');
                }
                return sb.toString();
            } else if (k < rightPointer * (n - rightPointer)) {
                int correctRightPointer = rightPointer - 1;
                int offSet = k - ((n - correctRightPointer) * correctRightPointer);
                for (int i = 0; i < n - correctRightPointer; i++) {
                    if (i == correctRightPointer + offSet)
                        sb.append('B');
                    else sb.append('A');
                }
                for (int i = 0; i < correctRightPointer; i++)
                    sb.append('B');
                return sb.toString();
            }
        }
        return "";
    }

    public static String createStringABC(int n, int k) {
        int left = n/3;
        int mid = n/3;
        int right = n/3;
        if(n%3 == 1) right += 1;
        if(n%3 == 2) { mid +=1; right +=1; }

        if( k > left * mid + mid * right + left * right) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for( int rightPointer = 0; rightPointer <= right; rightPointer++) {
            int remainingLeft = n - rightPointer;
            int minValue = remainingLeft * rightPointer;
            int x = remainingLeft/2;
            int y = remainingLeft - remainingLeft/2;
            int z = rightPointer;
            int maxvalue =   x * y + y * z + x * z;
            if(minValue <= k && k <= maxvalue) {
                // C is fixed.
                if (k == x * y + y * z + z * x) {
                    for (int i = 0; i < x; i++) {
                        sb.append('A');
                    }
                    for (int i = 0; i < y; i++) {
                        sb.append('B');
                    }
                    for (int i = 0; i < z; i++) {
                        sb.append('C');
                    }
                    return sb.toString();
                } else {

                    for( int midPointer = 0; midPointer <= y; midPointer++) {
                        int xx = remainingLeft - midPointer;
                        int yy = midPointer;
                        int zz = z;
                        int totalPairs = xx * yy + yy * zz + xx * zz;

                        if (k == totalPairs) {
                            for (int i = 0; i < xx; i++) {
                                sb.append('A');
                            }
                            for (int i = 0; i < yy; i++) {
                                sb.append('B');
                            }
                            for (int i = 0; i < zz; i++) {
                                sb.append('C');
                            }
                            return sb.toString();
                        } else if (k < totalPairs) {
                            yy = yy - 1;
                            xx = xx + 1;
                            totalPairs = xx * yy + yy * zz + xx * zz;
                            int cOffSet = yy + zz + (k - totalPairs);
                            int bOffSet = -1;
                            if(cOffSet >= xx) bOffSet = yy + k - totalPairs;
                            for (int i = 0; i < xx; i++) {
                                if (i ==  cOffSet && bOffSet == -1)
                                    sb.append('C');
                                else if (i == bOffSet)
                                    sb.append('B');
                                else sb.append('A');
                            }
                            for (int i = 0; i < yy; i++)
                                sb.append('B');
                            for (int i = 0; i < zz; i++) {
                                sb.append('C');
                            }
                            return sb.toString();
                        }
                    }
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        int m = 20, l = 30;
        String s = createString(m, l);
        System.out.println(m +" " +l);
        System.out.println(s + " "+ count(s));
        int n = 50, k = 700;
        System.out.println(n+ " "+ k);
        String t = createStringABC(n, k);
        System.out.println(t + countABC(t));
    }
}
