class Solution {
    
    public boolean isValidSudoku(char[][] board) {
       for(int i=0; i<9; i++){
           for(int j=0; j<9; j++){
               if(board[i][j] == '.'){ //.을 만나게 되면 무시
                   continue;
               }
               
               char ch = board[i][j];
               board[i][j] = '.';
               
               if(isExist(board, ch, i, j)) return false;
               
               board[i][j]=ch;
           }
       }
        return true;
	}
    
    public boolean isExist(char[][] board, char ch, int i, int j){
         for(int m = 0; m < 9; m++)
            if(board[m][j] == ch ||board[i][m] == ch)
                return true;
        
        i = (i / 3) * 3;
        j = (j / 3) * 3;
        
        for(int m = i; m < i + 3; m++)
            for(int n = j; n < j + 3; n++)
                if(board[m][n] == ch)
                    return true;
        
        return false;
    }
}