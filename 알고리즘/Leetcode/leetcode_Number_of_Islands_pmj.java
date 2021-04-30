package Leetcode;
//https://leetcode.com/problems/number-of-islands/
/**
 * 1. 문제 풀이 시간: 초과
 * 2. 풀이 방법
 *   1) 행열 탐색할 때 1을 만나게 되면 count++(섬 개수 증가), dfs 실시
 *   2) dfs 에서
 *      2-1) 해당 원소가 0이면 return
 *      2-2) 해당 원소가 1이면 0으로 바꾸고 그 원소 중심으로 상/하/좌/우로 이동 후 2번 반복
 *      2-3) 반복해서 모두 0으로 바꾸었으면 다시 행열 탐색, 또 다시 1을 만나게 되면 dfs 실시
 */

public class numberOfIslands {
    static int m, n, count;

    public static int numIslands(char[][] grid) {
        //아무것도 존재하지 않을 때 0 리턴
        if(grid == null || grid.length == 0){
            return 0;
        }

        m = grid.length; //열의 크기
        n = grid[0].length; //행의 크기
        count=0;


        for(int i =0; i< m; i++){
            for(int j =0; j<n; j++){
                if(grid[i][j] == '1'){ //1을 만날때 섬 갯수 증가
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int r, int c){
        //0인 곳은 리턴
        if(r<0 || r== m || c<0 || c==n || grid[r][c]=='0'){
            return;
        }

        //1인 곳을 0으로 바꿈
        grid[r][c] = '0';
        //방향 탐색(상하좌우)
        int[][] direction = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
        for(int[] dir: direction){
            int row = r + dir[0];
            int col = c + dir[1];
            dfs(grid, row, col);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        System.out.println(numIslands(grid));
    }
}
