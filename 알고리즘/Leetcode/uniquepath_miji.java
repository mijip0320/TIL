//Time Limit Exceeded ->input: 51, 9에서 에러 발생
/*class Solution {
    public int uniquePaths(int m, int n) {
        
        return recursive(n,m,0,0);
                 
    }
    
    public static int recursive(int nRow,int mCol,int row,int col ){
        if(row >= nRow || col >= mCol) return 0;
        if(row == nRow-1 && col == mCol-1) return 1;
        int sum = recursive(nRow,mCol,row,col+1) + recursive(nRow,mCol,row+1, col);
        return sum;
    }
}*/

//Success
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] temp=new int[n][m];
        return recursive(n,m,0,0, temp);
                 
    }
    
    public static int recursive(int nRow,int mCol,int row,int col, int[][] temp ){
        if(row >= nRow || col >= mCol) return 0;
        if(row == nRow-1 && col == mCol-1) return 1;
        if(temp[row][col]!=0){
            return temp[row][col];
        }
        temp[row][col]=recursive(nRow,mCol,row,col+1,temp)+recursive(nRow,mCol,row+1,col,temp);
        return temp[row][col];
    }
}