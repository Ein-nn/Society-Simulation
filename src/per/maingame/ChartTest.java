package per.maingame;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartTest {

	private int[] peopleNum;
	private int[] peopleNum2;
	private int[] enviorment;
	private int[] enviorment2;
	private String[] categories;

	public void paint() {
		// 设置中文主题样式 解决乱码
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart(
		"数据对比图",//图名字
		"代数",//横坐标z
		"数值",//纵坐标
		mDataset,//数据集
		PlotOrientation.VERTICAL,//图表水平或竖直
		true, // 显示图例
		true, // 采用标准生成器 
		false);// 是否生成超链接
		//.LIGHT_GRAY
		CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
		mPlot.setBackgroundPaint(Color.white);
		mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
		mPlot.setOutlinePaint(Color.RED);//边界线
		
		/*
		* Y轴设置
		*/
		NumberAxis vn = (NumberAxis) mPlot.getRangeAxis();
	
		vn.setUpperMargin(0.1);
		vn.setLowerMargin(0.1);
		vn.setAutoRangeMinimumSize(1);//最小跨度 
		vn.setLowerBound(0);//最小值显示
		vn.setUpperBound(100);
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();// 获取显示线条的对象
		lasp.setBaseShapesVisible(true);// 设置拐点是否可见/是否显示拐点
		lasp.setDrawOutlines(true);// 设置拐点不同用不同的形状
		lasp.setUseFillPaint(true);// 设置线条是否被显示填充颜色
		lasp.setBaseFillPaint(Color.BLACK);//// 设置拐点颜色
		
		/*
		* X轴
		*/
		CategoryAxis domainAxis = mPlot.getDomainAxis();
		setX();
		setDomainAxis(domainAxis,categories);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setCategoryMargin(0.5);
		domainAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15)); // 设置横轴字体
		domainAxis.setTickLabelFont(new Font("宋书", Font.PLAIN, 10));// 设置坐标轴标尺值字体
		domainAxis.setLowerMargin(0.01);// 左边距 边框距离
		domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
		domainAxis.setMaximumCategoryLabelLines(10);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
		domainAxis.setCategoryMargin(0.2);
		
		try {
			File file = new File("E:/table.png");
			ChartUtilities.saveChartAsPNG(file,mChart,800,600);//把报表保存为文件
			}catch (Exception e) {
			/*String s = e.getLocalizedMessage();
			s = e.getMessage();
			s = e.toString();*/
			}
			ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
			mChartFrame.pack();
			mChartFrame.setVisible(true);
	}

	private CategoryDataset GetDataset() {
		
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		
		for(int i=0;i<peopleNum.length;i++) {
			Number pn=peopleNum[i];
			int a=i+1;
			mDataset.addValue(pn, "默认值生命值", a);
		}
		for(int i=0;i<enviorment.length;i++) {
			Number pn=enviorment[i];
			int a=i+1;
			mDataset.addValue(pn, "默认环境值", a);
		}
		for(int i=0;i<peopleNum2.length;i++) {
			Number pn=peopleNum2[i];
			int a=i+1;
			mDataset.addValue(pn, "比较值", a);
		}
		for(int i=0;i<enviorment2.length;i++) {
			Number pn=enviorment2[i];
			int a=i+1;
			mDataset.addValue(pn, "比较环境值", a);
		}
		
		return mDataset;
	}
	
	public void setDomainAxis(CategoryAxis domainAxis,String[] categories){
		// 设置标签可见
		domainAxis.setTickLabelsVisible(true);
		for(int i = 0; i<categories.length; i++){
			String s = categories[i];
			if(i%5 ==0){
				domainAxis.setTickLabelPaint(s, Color.black);
			}else{
				// 设置背景色为白色
				domainAxis.setTickLabelPaint(s, Color.white);
			}
		}
	}
	public void setX() {
		if(peopleNum.length>peopleNum2.length) {
			categories=new String[peopleNum.length];
		}else {
			categories=new String[peopleNum2.length];
		}
		for(int i=0;i<categories.length;i++) {
			int a=i+1;
			categories[i]=String.valueOf(a);
		}
	}

	public int[] getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int[] peopleNum) {
		this.peopleNum = peopleNum;
	}

	public int[] getPeopleNum2() {
		return peopleNum2;
	}

	public void setPeopleNum2(int[] peopleNum2) {
		this.peopleNum2 = peopleNum2;
	}

	public int[] getEnviorment() {
		return enviorment;
	}

	public void setEnviorment(int[] enviorment) {
		this.enviorment = enviorment;
	}

	public int[] getEnviorment2() {
		return enviorment2;
	}

	public void setEnviorment2(int[] enviorment2) {
		this.enviorment2 = enviorment2;
	}
}
