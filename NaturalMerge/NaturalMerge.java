import java.util.*;


class NaturalMerge {
	int noe;  // the number of elements
	private int[] inputArray; // input array 
	int[] outputArray; // output array 


	NaturalMerge() { 
		noe = 0;
	}

	void Init(int [] arr, int n) { 
		noe = n;
		inputArray = new int[noe];
		System.arraycopy(arr, 0, inputArray, 0, n);

		outputArray = new int[noe];
	}

	void Merge() { 
		int m = 0;
		int pos1 = 0;
		int pos2 = 0;
		int pos3 = 0;
		for(int i = 0; i < noe - 1; i++) {
			if(inputArray[i] > inputArray[i+1]) {
				m = i + 1;
				pos2 = m;
			}
		}
		System.out.println("m = " + m + ", n = " + noe);
		while(pos1 < m && pos2 < noe) {
			if(inputArray[pos1] < inputArray[pos2]) {
				outputArray[pos3] = inputArray[pos1];
				pos1++;
			}
			else {
				outputArray[pos3] = inputArray[pos2];
				pos2++;
			}
			pos3++;
		}
		if(pos1 == m) {
			for(int i = pos3; i < noe; i++) {
				outputArray[i] = inputArray[pos2];
				pos2++;
			}
		}
		else {
			for(int i = pos3; i < noe; i++) {
				outputArray[i] = inputArray[pos1];
				pos1++;
			}
		}
		// NEED TO IMPLEMENT


	}
}
