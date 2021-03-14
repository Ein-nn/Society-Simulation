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
		// ��������������ʽ �������
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart(
		"���ݶԱ�ͼ",//ͼ����
		"����",//������z
		"��ֵ",//������
		mDataset,//���ݼ�
		PlotOrientation.VERTICAL,//ͼ��ˮƽ����ֱ
		true, // ��ʾͼ��
		true, // ���ñ�׼������ 
		false);// �Ƿ����ɳ�����
		//.LIGHT_GRAY
		CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
		mPlot.setBackgroundPaint(Color.white);
		mPlot.setRangeGridlinePaint(Color.BLUE);//�����ײ�������
		mPlot.setOutlinePaint(Color.RED);//�߽���
		
		/*
		* Y������
		*/
		NumberAxis vn = (NumberAxis) mPlot.getRangeAxis();
	
		vn.setUpperMargin(0.1);
		vn.setLowerMargin(0.1);
		vn.setAutoRangeMinimumSize(1);//��С��� 
		vn.setLowerBound(0);//��Сֵ��ʾ
		vn.setUpperBound(100);
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();// ��ȡ��ʾ�����Ķ���
		lasp.setBaseShapesVisible(true);// ���ùյ��Ƿ�ɼ�/�Ƿ���ʾ�յ�
		lasp.setDrawOutlines(true);// ���ùյ㲻ͬ�ò�ͬ����״
		lasp.setUseFillPaint(true);// ���������Ƿ���ʾ�����ɫ
		lasp.setBaseFillPaint(Color.BLACK);//// ���ùյ���ɫ
		
		/*
		* X��
		*/
		CategoryAxis domainAxis = mPlot.getDomainAxis();
		setX();
		setDomainAxis(domainAxis,categories);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setCategoryMargin(0.5);
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 15)); // ���ú�������
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 10));// ������������ֵ����
		domainAxis.setLowerMargin(0.01);// ��߾� �߿����
		domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ
		domainAxis.setMaximumCategoryLabelLines(10);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// ���� lable ��λ�� �����ϵ� Lable 45����б DOWN_45
		domainAxis.setCategoryMargin(0.2);
		
		try {
			File file = new File("E:/table.png");
			ChartUtilities.saveChartAsPNG(file,mChart,800,600);//�ѱ�����Ϊ�ļ�
			}catch (Exception e) {
			/*String s = e.getLocalizedMessage();
			s = e.getMessage();
			s = e.toString();*/
			}
			ChartFrame mChartFrame = new ChartFrame("����ͼ", mChart);
			mChartFrame.pack();
			mChartFrame.setVisible(true);
	}

	private CategoryDataset GetDataset() {
		
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		
		for(int i=0;i<peopleNum.length;i++) {
			Number pn=peopleNum[i];
			int a=i+1;
			mDataset.addValue(pn, "Ĭ��ֵ����ֵ", a);
		}
		for(int i=0;i<enviorment.length;i++) {
			Number pn=enviorment[i];
			int a=i+1;
			mDataset.addValue(pn, "Ĭ�ϻ���ֵ", a);
		}
		for(int i=0;i<peopleNum2.length;i++) {
			Number pn=peopleNum2[i];
			int a=i+1;
			mDataset.addValue(pn, "�Ƚ�ֵ", a);
		}
		for(int i=0;i<enviorment2.length;i++) {
			Number pn=enviorment2[i];
			int a=i+1;
			mDataset.addValue(pn, "�Ƚϻ���ֵ", a);
		}
		
		return mDataset;
	}
	
	public void setDomainAxis(CategoryAxis domainAxis,String[] categories){
		// ���ñ�ǩ�ɼ�
		domainAxis.setTickLabelsVisible(true);
		for(int i = 0; i<categories.length; i++){
			String s = categories[i];
			if(i%5 ==0){
				domainAxis.setTickLabelPaint(s, Color.black);
			}else{
				// ���ñ���ɫΪ��ɫ
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
