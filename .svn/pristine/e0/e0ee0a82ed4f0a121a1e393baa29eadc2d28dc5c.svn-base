package cn.com.zhiding.util;

/**
 * 此类用于处理计算项级别和维度类型之间的关系
 * 当维度类型为6时,不参与常模值的计算
 * @author gaoqj
 * @version 2017年10月10日
 */
public enum FigureTerm {
	
	ONE("1","一级"),
	TWO("2","二级"),
	THREE("3","三级"),
	FOUR("4","四级"),
	FIVE("5","五级"),
	SIX("6","六级"),
	SEVEN("7","七级"),
	EIGHT("8","八级"),
	NINE("9","九级");
	
	private String moduleType;//维度类型
	private String FTLevel;//计算项级别
	
	private FigureTerm(String moduleType,String FTLevel){
		this.moduleType = moduleType;
		this.FTLevel = FTLevel;
	}
	
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getFTLevel() {
		return FTLevel;
	}
	public void setFTLevel(String fTLevel) {
		FTLevel = fTLevel;
	}
	
	/**
	 * 根据维度类型返回对应的维度类型和计算项级别的关系
	 * @param moduleType
	 * @return
	 */
	public static FigureTerm getFTLevel(String moduleType){

		switch (moduleType){
			case "1":
				return ONE;
			
			case "2":
				return TWO;
			
			case "3":
				return THREE;
			
			case "4":
				return FOUR;
			
			case "5":
				return FIVE;
			
			case "6":
				return SIX;
			
			case "7":
				return SEVEN;
				
			case "8":
				return EIGHT;
				
			case "9":
				return NINE;
			default:
				return null;
		}
	}
	
	/**
	 * 根据维度类型返回对应的维度类型和计算项级别的关系
	 * @param moduleType
	 * @return
	 */
	public static FigureTerm getModuleType(String FTLevel){

		switch (FTLevel){
			case "一级":
				return ONE;
			
			case "二级":
				return TWO;
			
			case "三级":
				return THREE;
			
			case "四级":
				return FOUR;
			
			case "五级":
				return FIVE;
			
			case "六级":
				return SIX;
			
			case "七级":
				return SEVEN;
				
			case "八级":
				return EIGHT;
				
			case "九级":
				return NINE;
			default:
				return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getFTLevel("1").getFTLevel());
	}
}
