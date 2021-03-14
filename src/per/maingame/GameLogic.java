package per.maingame;

import java.util.Random;

public class GameLogic {
	
	private int col=15;
	private int row=15;
	private int m,n;
	private int num=0;
	private int time;
	private int times;
	private int[] peopleNum;	//记录每一代人数
	private int[] enviormentNum;//记录每一代环境参数
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
	 * 格式化参数，清除数据
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
	 * 统计周围生命数量
	 */
	public void getNeighbors() {
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				neighbors[i][j]=0;
				for(int x=i-1;x<i+2;x++) {
					for(int y=j-1;y<j+2;y++) {
						if(x<0||x>=row||y<0||y>=col) {//碰壁，
							continue;
						}
						if(a[x][y]>0 && a[x][y]<7) {//周围有生命
							neighbors[i][j]++;
						}
						if(a[x][y]>10) {//周围有环境资源，资源被消耗
							m=x;
							n=y;
							a[x][y]=a[x][y]-1;
						}
					}
				}
				//如果自身活着，减1
				if(a[i][j]>0 && a[i][j]<10) {
					neighbors[i][j]--;
				}
			}
		}
	}
	/**
	 * 统计生命个数，及环境条件
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
				if(a[i][j]>6 && a[i][j]<11) {//环境资源被消耗，清零
					a[i][j]=0;
				}
			}
		}
	}
	/**
	 * 统计生命个数，环境条件
	 */
	public void getEn() {
		peopleNum[time]=num;
		enviormentNum[time]=enviorment;
		time++;
	}
	/**
	 * 开始演化，并指向不同生存法则
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
	 * 如何定义行动？？？？？？？？？
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
			if(neighbors[i][j]==0) {//随机前进一格
				a[i+r1-2][i+r2-2]=a[i][j];
			}
		}
		private void t2(int i,int j) {
			if(neighbors[i][j]<5||neighbors[i][j]>1) {//生命力更顽强一点
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
			if((Math.abs(d1)+Math.abs(d2))<7) {//模拟运动
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
	 * 数据存储至数据库
	 * @return
	 */
	public void setdb() {
		
		//生成表名
		TimeString ts=new TimeString();
		String tableName="t"+ts.getTimeString();
		//传入参数
		connectdb.setId(Integer.parseInt(ts.getTimeString()));
		connectdb.setNum(peopleNum);
		connectdb.setEnviorment(enviormentNum);
		connectdb.setTableName(tableName);
		//执行操作
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
