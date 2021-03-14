package per.maingame;

import java.util.Random;

public class GameLogic {
	
	private int col=15;
	private int row=15;
	private int m,n;
	private int num=0;
	private int time;
	private int times;
	private int[] peopleNum;	//��¼ÿһ������
	private int[] enviormentNum;//��¼ÿһ����������
	private int[][] a=new int[row][col];
	private int[][] neighbors=new int[row][col];
	private int enviorment;
	
	Random rand=new Random();
	
	ConnectDb connectdb = new ConnectDb();
	
	public void preSet() {
		a= new int[row][col];
		neighbors=new int[row][col];
	}
	public void preCount() {
		peopleNum=new int[times];
		enviormentNum=new int[times];
		for(int i=0;i<times;i++) {
			peopleNum[i]=0;
			enviormentNum[i]=0;
		}
		time=0;
	}
	
	/**
	 * ��ʽ���������������
	 */
	public void clearWorld() {
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				a[i][j]=0;
			}
		}
		num=0;
	}
	/**
	 * ͳ����Χ��������
	 */
	public void getNeighbors() {
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				neighbors[i][j]=0;
				for(int x=i-1;x<i+2;x++) {
					for(int y=j-1;y<j+2;y++) {
						if(x<0||x>=row||y<0||y>=col) {//���ڣ�
							continue;
						}
						if(a[x][y]>0 && a[x][y]<7) {//��Χ������
							neighbors[i][j]++;
						}
						if(a[x][y]>10) {//��Χ�л�����Դ����Դ������
							m=x;
							n=y;
							a[x][y]=a[x][y]-1;
						}
					}
				}
				//���������ţ���1
				if(a[i][j]>0 && a[i][j]<10) {
					neighbors[i][j]--;
				}
			}
		}
	}
	/**
	 * ͳ����������������������
	 */
	public void getNumber() {
		num=0;
		enviorment=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(a[i][j]>0 && a[i][j]<7){
					num++;
				}
				if(a[i][j]>10) {
					enviorment++;
				}
				if(a[i][j]>6 && a[i][j]<11) {//������Դ�����ģ�����
					a[i][j]=0;
				}
			}
		}
	}
	/**
	 * ͳ��������������������
	 */
	public void getEn() {
		peopleNum[time]=num;
		enviormentNum[time]=enviorment;
		time++;
	}
	/**
	 * ��ʼ�ݻ�����ָ��ͬ���淨��
	 */
	public void transform() {
		
		TransformClass tf = new TransformClass();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				switch(a[i][j]) {
				case 0:
					tf.t0(i, j);
					break;
				case 1:
					tf.t1(i,j);
					break;
				case 2:
					tf.t2(i,j);
					break;
				case 3:
					tf.t3(i,j);
					break;
				case 4:
					tf.t4(i,j);
					break;
				case 5:
					tf.t5(i,j);
					break;
				case 6:
					tf.t6(i,j);
				case 11:
					tf.t10(i,j);
					break;
				case 12:
					tf.t10(i,j);
					break;
				case 13:
					tf.t10(i,j);
					break;
				case 14:
					tf.t10(i,j);
					break;
				case 15:
					tf.t10(i,j);
					break;
				case 16:
					tf.t10(i,j);
					break;
				case 17:
					tf.t10(i,j);
					break;
				case 18:
					tf.t10(i,j);
					break;
				case 19:
					tf.t10(i,j);
					break;
				case 20:
					tf.t10(i,j);
					break;
				default:
					
				}
			}
		}
	}

	/**
	 * ��ζ����ж�������������������
	 */
	public class TransformClass {
		
		private void t0(int i,int j) {
			if(neighbors[i][j]==3) {
				a[i][j]=3;
			}else {
				a[i][j]=0;
			}
		}
		public void t10(int i, int j) {
			if(neighbors[i][j]>0) {
				a[i][j]=a[i][j]-1;
			}
			
		}
		private void t1(int i,int j) {
			int r1=rand.nextInt(5);
			int r2=rand.nextInt(5);
			if(neighbors[i][j]==0) {//���ǰ��һ��
				a[i+r1-2][i+r2-2]=a[i][j];
			}
		}
		private void t2(int i,int j) {
			if(neighbors[i][j]<5||neighbors[i][j]>1) {//����������ǿһ��
				a[i][j]=3;
			}else {
				a[i][j]=0;
			}
		}
		private void t3(int i,int j) {
			if(neighbors[i][j]==3||neighbors[i][j]==2) {
				a[i][j]=3;
			
			int d1=i-m;
			int d2=j-n;
			if((Math.abs(d1)+Math.abs(d2))<7) {//ģ���˶�
				if(d1<=0 && d2<=0) {
					if(i<row && j<col) {
					a[i+1][j+1]=a[i][j];}
				}else if(d1<=0 && d2>=0) {
					a[i+1][j-1]=a[i][j];
				}else if(d1>=0 && d2<=0) {
					a[i-1][j+1]=a[i][j];
				}else {
					if(i>0 && j>0){
						a[i-1][j-1]=a[i][j];
					}
				}
			}
			}else {
				a[i][j]=0;
			}
		}
		private void t4(int i,int j) {
			if(neighbors[i][j]>=4) {
				a[i][j]=0;
			}else {
						
			}
		}
		private void t5(int i,int j) {
			if(neighbors[i][j]>=6||neighbors[i][j]<=2) {
				a[i][j]=0;
			}
			else {
				
			}	
		}
		private void t6(int i,int j) {
			if(neighbors[i][j]>=2) {
				a[i][j]=0;
			}else if(neighbors[i][j]==0) {
				a[i-1][j]=6;
			}else {
				
			}
		}
	}
	
	/**
	 * ���ݴ洢�����ݿ�
	 * @return
	 */
	public void setdb() {
		
		//���ɱ���
		TimeString ts=new TimeString();
		String tableName="t"+ts.getTimeString();
		//�������
		connectdb.setId(Integer.parseInt(ts.getTimeString()));
		connectdb.setNum(peopleNum);
		connectdb.setEnviorment(enviormentNum);
		connectdb.setTableName(tableName);
		//ִ�в���
		connectdb.creatTable();
		connectdb.upData();
		
	}

	
	public int[][] getA(){
		return a;
	}
	public void setA(int[][] a){
		this.a=a;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num=num;
	}
	public void setRow(int row) {
		this.row=row;
	}
	public void setCol(int col) {
		this.col=col;
	}

	public int getEnviorment() {
		return enviorment;
	}

	public void setEnviorment(int enviorment) {
		this.enviorment = enviorment;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	public int[] getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(int[] peopleNum) {
		this.peopleNum=peopleNum;
	}
	public int[] getEnviormentNum() {
		return enviormentNum;
	}
	public void setEnviormentNum(int[] enviormentNum) {
		this.enviormentNum=enviormentNum;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}
