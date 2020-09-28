#include<stdio.h>

int main()
{
	int variable;
	int numberOfMinterms;
	
	int temp;
	
	int scanVal; //주어진 식의 Valiable 갯수를 저장 
	int scanNOM; //Minterms의 갯수를 저장 
	int scanDont; //Don't Care 항을 저장 
	int scanDo; // Minterms의 갯수 - Don't Care항 갯수 
	int getMin; //Minterms 값을 입력 받기위한 변수 
	int getDo; // Don't care를 제외한 나머지 Minterms 값을 받기 위한 변수 
	int counter = 0;
	int counterMin; // Prime Implicant의 갯수를 저장하는 변수 
	
	int i,j,k,l,o,p; // for 문을 돌리기 위한 변수들 
	int m = 0;
	int n = 0;
	
	int digit = 0; // 2진수 끼리 비교할때 서로 값이 같으면 증가하는 변수 (또는 x와 0 or x와 1)
	
	printf("민텀의 갯수는?\n");
	scanf("%d",&scanNOM);
	
	printf("변수의 갯수는?\n");
	scanf("%d",&scanVal);
	
	printf("Don't care 갯수는?\n");
	scanf("%d",&scanDont);
	
	scanDo = scanNOM - scanDont;
	
	
	int minterms[scanVal][scanNOM*scanNOM][scanVal]; //minterms 값을 저장하는 3차원 배열.
	int cpMinterms[1][scanNOM*scanNOM][scanVal];  //2진수로 변환한 결과를 저장하기 위한 변수 
	int careM[scanDo][scanVal]; // don't care 를 제외한 나머지 minterms를 저장 
	int tempStore[scanNOM];
	int tempStoreDo[scanDo];
	
	//전부 minterms배열을 9로 초기화
	for(i=0;i<scanVal;i++)
	{
		for(j=0;j<scanNOM  * scanNOM;j++)
		{
			for(k=0;k<scanVal;k++)
			{
				minterms[i][j][k] = 9;
			}
		}
	}
	
	printf("Minterms를 입력하세요 (Don't care 도 포함해서 입력하세요)\n");
	for(i=0; i<scanNOM;i++)
	{
		scanf("%d",&getMin);
		tempStore[i] = getMin;
	}
	
	printf("\nDon't care를 제외한 나머지 Minterms를 입력하세요\n");
	for(i=0; i<scanDo; i++)
	{
		scanf("%d",&getDo);
		tempStoreDo[i] = getDo;
	}
	
	
	//입력 받은 수를 2진수로 변환 
	for(i=0;i<scanNOM;i++)
	{
		for(j=0;j<scanVal;j++)
		{
			minterms[0][i][j] = tempStore[i] % 2;
			tempStore[i] = tempStore[i] / 2;
		}
	}
	
	for(i=0;i<scanDo;i++)
	{
		for(j=0;j<scanVal;j++)
		{
			careM[i][j] = tempStoreDo[i] % 2;
			tempStoreDo[i] = tempStoreDo[i] / 2;
		}
	}
	
	//2진수로 변환한 결과를 저장 
	for(i=0;i<scanNOM;i++)
	{
		for(j=0;j<scanVal;j++)
		{
			cpMinterms[0][i][j] = minterms[0][i][j];
		}
	}
	
	//입력 받은 minterms들을 비교 
	for(i=0;i<scanVal - 1;i++)
	{
		m = 0;
		for(j=0;j < scanNOM * scanNOM - 1;j++)//민텀의 갯수 
		{				
			for(k=j+1;k < scanNOM * scanNOM ;k++)//2진수 각각 비교 
			{
				digit = 0;
				for(l=0;l<scanVal;l++)//k는 비교받는 배열 
				{
					if(minterms[i][j][l] == minterms[i][k][l])
					{
						digit++;
					}
				}
				
				if(digit == scanVal - 1) //1개 차이나면 다음 차원의 배열에 값을 넘겨줌 이때 차이나는 부분은 값 5를 대입해서 보냄 
				{
					for(l=0; l < scanVal; l++)
					{
						if(minterms[i][j][l] != minterms[i][k][l])
						{
							n = l;
						}
					} 
					for(o=0;o<scanVal;o++)
					{
						minterms[i+1][m][o] = minterms[i][j][o]; // 1차원 아래의 배열값과 동일하게 채운뒤 
					}
					minterms[i+1][m][n] = 5; //다른 부분 즉 x가 채워질 부분에 값 5를 대입; 
					m++; //입력이 끝나면 m의 값을 1 올려준다 
				}
				
			}
		}
	} //pi 후보 찾기 끝
	 
	
	//겹치는 항 제거 하기
	for(i=1;i<scanVal;i++) //i=0에서 겹치는 항은 없음 
	{
		for(j=0;j<scanNOM*scanNOM - 1;j++)
		{
			if(minterms[i][j][0] != 9)
			{
				for(k=j+1;k<scanNOM*scanNOM;k++)
				{
					digit = 0;
					
					if(minterms[i][k][0] != 9)
					{
						for(l=0;l<scanVal;l++)
						{
							if(minterms[i][j][l] == minterms[i][k][l])
							{
								digit++;
							}
						}
						
						if(digit == scanVal) //필요 없는 항들은 전부 9로 초기화 
						{
							for(l=0;l<scanVal;l++)
							{
								minterms[i][k][l] = 9;
							}
						}
					}
				}
			}
		}
	}
	
	//pi 값 찾기 (1개 차이나는 부분만 찾음 5들어간 부분만 다르게 나올것이기 때문)
	for(i=0;i<scanVal - 1;i++) 
	{
		for(j=0;j<scanNOM*scanNOM;j++)
		{
			for(k=0;k<scanNOM*scanNOM;k++)
			{
				digit = 0;
				
				if(minterms[i+1][j][0] != 9 && minterms[i][k][0] != 9) // 9가 들어간 부분은 검사할 필요가 없음 
				{
					for(l=0;l<scanVal;l++)
					{
						if(minterms[i+1][j][l] == minterms[i][k][l])
							digit++;
					}
					if(digit == scanVal - 1)
					{
						for(l=0;l<scanVal;l++)
						{
							minterms[i][k][l] = 9;
						}
					}
				}
			}
		}
	}
	printf("\n\n\n2진수로 변환 결과\n---------------------------------\n");
	for(i=0;i<scanNOM;i++)
	{
		for(j=0;j<scanVal;j++)
		{
			printf("%d",cpMinterms[0][i][scanVal-j-1]);
		}
		printf("\n");
	}
	
	printf("\n\n\nPrime Implicant\n---------------------------------\n");
	for(p=1;p<scanVal;p++)
	{
		{
			for(j=0;j<scanNOM * scanNOM;j++)
			{
				if(minterms[p][j][1] != 9)
				{
					for(k=0;k<scanVal;k++)
					{
						if(minterms[p][j][scanVal-k-1] == 5) // 5가 들어간 부분은 5대신 x를 출력 
						{
							printf("x");
						}
						else
						{
							printf("%d",minterms[p][j][scanVal-k-1]);
						}
						counter++;
					}
					printf("\n");
				}
			}
		}
	}
	counterMin = counter / 4; //Prime Implicant의 갯수가 counterMin에 저장됨 
	counter = 0;
	
	int finalMinterms[counterMin][scanVal]; //최종 답을 저장하는 배열 
	int solution[scanDo][counterMin]; //Essenstial Prime Implicant를 구하기위한 2차원 배열 
	int answerArray[scanDo]; // EPi가 있는지 검사하는 배열 
	
	for(i=0;i<scanDo;i++)
		answerArray[i] = 0;
	
	for(i=0;i<scanDo;i++)
	{
		for(j=0;j<counterMin;j++)
		{
			solution[i][j] = 0;
		}
	}
	
	for(i=0;i<counterMin;i++)
	{
		for(j=0;j<scanVal;j++)
		{
			finalMinterms[i][j] =9;
		}
	}
	
	for(i=0;i<scanVal;i++)
	{
		for(j=0;j<scanNOM*scanNOM;j++)
		{
			if(minterms[i][j][0] != 9)
			{
				for(k=0;k<scanVal;k++)
				{
					finalMinterms[counter][k] = minterms[i][j][k];
				}
				
				counter++;
			}
		}
	}
	m=0;
	//careM과 finalMinterms 비교 
	for(i=0; i < scanDo; i++)
	{
		m=0;
		
		for(k=0; k < counterMin; k++)
		{
			digit = 0;
			for(l=0; l < scanVal; l++)
			{
				if((careM[i][l] == finalMinterms[k][l]) || (finalMinterms[k][l] == 5))
					digit++;
			}
			
			if(digit == scanVal)
			{
				solution[i][m] = k + 1;
				m++;
			}
		}
		if(m == 1)
		{
			answerArray[i] = solution[i][0];
		}
	}
	
	
	
	//answerArray에서 겹치는 부분 제외 하기 
	for(i=0;i<scanDo - 1;i++)
	{
		if(answerArray[i] != 0)
		{
			for(j=i+1;j<scanDo;j++)
			{ 
				if(answerArray[i] == answerArray[j])
				{
					answerArray[j] = 0;
				}
			}
		}
	}
	
	//EPI 출력
	printf("\nEssential Prime Implicant\n---------------------------------\n");
	for(i=0;i<scanDo;i++)
	{
		if(answerArray[i] != 0)
		{
			
			for(j=0;j<scanVal;j++)
			{	
				if(finalMinterms[answerArray[i]-1][scanVal-j-1] == 5)
					printf("x");
				else
					printf("%d",finalMinterms[answerArray[i]-1][scanVal-j-1]);
			}
			printf("\n");
		}
	} 
	
}
