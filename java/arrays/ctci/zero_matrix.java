/*
Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to O.
*/

//Space efficient way
void zeroMatrix(int[][] matrix){
	boolean firstRowHasZero = false;
	boolean firstColHasZero = false;

	for(int i=0;i<matrix.length;i++){
		if(matrix[i][0]==0) firstColHasZero = true;
	}
	for(int j=0;j<matrix[0].length;j++){
		if(matrix[0][j]==0) firstRowHasZero = true;
	}
	for(int i=1;i<matrix.length;i++){
		for(int j=1;j<matrix[0].length;j++){
			if(matrix[i][j]==0){
				matrix[0][j] = 0;
				matrix[i][0] = 0;
			}
		}
	}
	for(int i=0;i<matrix.length;i++){
		if(matrix[i][0]==0) nullifyRow(i, matrix);
	}
	for(int j=0;j<matrix[0].length;j++){
		if(matrix[0][j]==0) nullifyCol(j, matrix);
	}
	if(firstRowHasZero) nullifyRow(0, matrix);
	if(firstColHasZero) nullifyCol(0, matrix);
}

void nullifyRow(int row, int[][] matrix){
	for(int j=0;j<matrix[0].length;j++){
		matrix[row][j] = 0;
	}
}

void nullifyCol(int col, int[][] matrix){
	for(int i=0;i<matrix.length;i++){
		matrix[i][col] = 0;
	}
}

//Simpler though more space taking way
void zeroMatrix(int[][] matrix){
	boolean[] rows = new boolean[matrix.length];
	boolean[] columns = new boolean[matrix[0].length];
	for(int i=0;i<matrix.length;i++){
		for(int j=0;j<matrix[0].length;j++){
			if(matrix[i][j]==0) {
				rows[i] = true;
				columns[j] = true;
			}
		}
	}
	for(int i=0;i<matrix.length;i++){
		if(rows[i]) {
			for(int j=0;j<matrix[0].length;j++){
				matrix[I][j] = 0;
			}
		}
	}
	for(int j=0;j<matrix[0].length;j++){
		if(columns[j]){
			for(int i=0;i<matrix.length;i++){
				matrix[i][j]=0;
			}
		}
	}
}
