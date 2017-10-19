package com.naltel.app.tc;

public class ABCPath {
    public static int length(String[] input) {
        int rows = input.length;
        int cols = input[0].length();
        int[][] counters = new int[input.length][input[0].length()];
        int[][] next = {{-1,-1,-1,1,1,1,0,0},{-1,0,1,-1,0,1,-1,1}};
        int max = 0;
        for(int i = 0; i < rows; i++) {
            for( int j= 0; j < cols; j++) {
                if( input[i].charAt(j) == 'A'){
                    int count = count(counters, input, i, j, -1, 'A', next);
                    if(max < count) max = count;
                }
            }
        }
        return max;
    }

    public static int count(int[][] counter, String[] input, int row, int col, int alreadyCount, char prev, int[][] next) {
        if( row < 0 || col < 0 || row > input.length -1 || col > input[row].length() - 1
                || counter[row][col] != 0 || alreadyCount == 0 || alreadyCount == 26)
            return alreadyCount;
        int max = 0;
        if(alreadyCount == -1){
            counter[row][col] = 1;
            for(int i = 0; i < next[0].length; i++){
                int count = count(counter, input, row+next[0][i], col+next[1][i], 1, prev, next);
                if(max < count) max = count;
            }
        } else {
            if(prev+1 == input[row].charAt(col)) {
                alreadyCount = alreadyCount+1;
                counter[row][col] = alreadyCount;
                prev = input[row].charAt(col);
                for(int i = 0; i < next[0].length; i++){
                    int count = count(counter, input, row+next[0][i], col+next[1][i], alreadyCount, prev, next);
                    if(max < count) max = count;
                }
            }
        }
        return max;
    }

    public static int length2(String[] input) {
        int rows = input.length;
        int cols = input[0].length();
        int[][] counters = new int[input.length][input[0].length()];
        boolean aExists = false;
        for(int i = 0; i < rows; i++) {
            for( int j= 0; j < cols; j++) {
                if( input[i].charAt(j) == 'A')
                    count(counters, input, i, j, -1, 'A');
            }
        }
        int max = counters[0][0];
        for(int i = 0; i < rows; i++) {
            for( int j= 0; j < cols; j++) {
                if( max < counters[i][j])
                    max = counters[i][j];
            }
        }
        return max;
    }
    public static void count(int[][] counter, String[] input, int row, int col, int alreadyCount, char prev) {

        if( row < 0 || col < 0 || row > input.length -1 || col > input[row].length() - 1
                || counter[row][col] != 0 || alreadyCount == 0 || alreadyCount == 26)
            return;
        if(alreadyCount == -1){
            counter[row][col] = 1;
            count(counter, input, row-1, col-1, 1, prev);
            count(counter, input, row-1, col, 1, prev);
            count(counter, input, row-1, col+1, 1, prev);
            count(counter, input, row+1, col-1, 1, prev);
            count(counter, input, row+1, col, 1, prev);
            count(counter, input, row+1, col+1, 1, prev);
            count(counter, input, row, col-1, 1, prev);
            count(counter, input, row, col+1, 1, prev);
        } else {
            if(prev+1 == input[row].charAt(col)) {
                alreadyCount = alreadyCount+1;
                counter[row][col] = alreadyCount;
                prev = input[row].charAt(col);
                count(counter, input, row-1, col-1, alreadyCount, prev);
                count(counter, input, row-1, col, alreadyCount, prev);
                count(counter, input, row-1, col+1, alreadyCount, prev);
                count(counter, input, row+1, col-1, alreadyCount, prev);
                count(counter, input, row+1, col, alreadyCount, prev);
                count(counter, input, row+1, col+1, alreadyCount, prev);
                count(counter, input, row, col-1, alreadyCount, prev);
                count(counter, input, row, col+1, alreadyCount, prev);
            }
        }
    }

    public static void main(String[] args) {


        String[] input1 = { "ABE", "CFG", "BDH", "ABC" };
        System.out.println("Length1 is " + length(input1));
        System.out.println("Length1 is " + length2(input1));
        String[] input2 = { "A" };
        System.out.println("Length2 is " + length(input2));
        System.out.println("Length2 is " + length2(input2));
        String[] input3 = { "BCDEFGHIJKLMNOPQRSTUVWXYZ" };
        System.out.println("Length3 is " + length(input3));
        System.out.println("Length3 is " + length2(input3));
        String[] input4 = { "C", "D", "B", "A" };
        System.out.println("Length4 is " + length(input4));
        System.out.println("Length4 is " + length2(input4));
        String[] input5 = { "KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO", "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX", "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX", "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ", "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV", "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP", "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ", "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU", "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH", "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP", "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE", "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ", "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS", "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK", "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ", "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC", "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS", "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS", "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF", "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" };
        System.out.println("Length5 is " + length(input5));
        System.out.println("Length5 is " + length2(input5));
        String[] input6 = { "EDCCBA", "EDCCBA" };
        System.out.println("Length6 is " + length(input6));
        System.out.println("Length6 is " + length2(input6));
        String[] input7 = { "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" };
        System.out.println("Length7 is " + length(input7));
        System.out.println("Length7 is " + length2(input7));
    }
}
