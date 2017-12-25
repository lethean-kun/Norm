package cn.com.zhiding.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * @author zzk
 */
public class ExcelUtil {


	/**
	 * 将用户上传的测评数据保存到虚拟机内容,以用户id+产品版本为key,导入的数据为value
	 * 尽量保证key不重复，所以用户id和产品版本为key
	 */
    public static Map<String,List<Long>> IdsMap = new Hashtable<>();
    /**
     * 通过传入文件路径获取excel第一列List
     * @param file
     * @return
     * @throws Exception
     */

    public static int readXls(MultipartFile file, Long userId,String version) throws Exception {

        if(file.isEmpty()){
            if(ExcelUtil.IdsMap.get(userId + "&&" + version)!=null) {
                removeKey(userId + "&&" + version);
            }
            return 0;
        }
        String fileName = file.getOriginalFilename();
        //获取文件类型
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        InputStream is = file.getInputStream();
        Workbook wb;
        if ("xls".equals(fileType)) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }

        Set<Long> list = new HashSet<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = wb.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    //获取每一行第一列
                    try {
                        Cell number = hssfRow.getCell(0);
                        String val = null;
                        if(number.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            val = String.valueOf(number.getNumericCellValue());
                        }else if(number.getCellType() == Cell.CELL_TYPE_STRING) {
                            val = number.getStringCellValue();
                        }
                        if(!"".equals(val) && val != null) {
                            if (val.endsWith(".0")) {
                                val = val.substring(0, val.indexOf("."));
                            }
                            list.add(Long.valueOf(val));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        if(ExcelUtil.IdsMap.get(userId + "&&" + version)!=null) {
                            removeKey(userId + "&&" + version);
                        }
                        return rowNum+1;
                    }
                }
            }
        }
        is.close();
        IdsMap.put(userId+"&&"+version,new ArrayList<>(list));
        return 1;
    }

    public static void removeKey(String k){
        Iterator<String> iterator= ExcelUtil.IdsMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            if(k.equals(key)){
                iterator.remove();
            }
        }
    }

//    /**
//     * 通过传入文件路径获取List对象
//     * @param filePath
//     * @return
//     * @throws Exception
//     */
//    public static List<OutNorm> returnList(String filePath) throws Exception {
//
//        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1,
//                filePath.length());
//        InputStream is = new FileInputStream(filePath);
//        Workbook wb = null;
//        OutNorm outNorm = null;
//
//        if (fileType.equals("xls")) {
//            wb = new HSSFWorkbook(is);
//        } else{
//            wb = new XSSFWorkbook(is);
//        }
//
//        List<OutNorm> list = new ArrayList<OutNorm>();
//        // 循环工作表Sheet
//        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
//            Sheet hssfSheet = wb.getSheetAt(numSheet);
//            if (hssfSheet == null) {
//                continue;
//            }
//            // 循环行Row
//            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//                Row hssfRow = hssfSheet.getRow(rowNum);
//                if (hssfRow != null) {
//                    //获取每一行
//                    outNorm = new OutNorm();
//                    Cell name = hssfRow.getCell(0);
//                    Cell average = hssfRow.getCell(1);
//                    Cell standardscore = hssfRow.getCell(2);
//                    Cell level = hssfRow.getCell(3);
//                    outNorm.setName(String.valueOf(name.getStringCellValue()));
//                    outNorm.setAverage(Double.valueOf(String.valueOf(average.getNumericCellValue())));
//                    outNorm.setStandardscore(Double.valueOf(String.valueOf(standardscore.getNumericCellValue())));
//                    outNorm.setLevel(String.valueOf(level.getStringCellValue()));
//                    list.add(outNorm);
//                }
//            }
//        }
//        is.close();
//        return list;
//    }
//
//
//
//    public static void exportExcel(String outFilePath, Map map,List<?> list, String classPath) throws Exception {
//        Class<?> demo = null;
//        demo = Class.forName(classPath);
//        Object obj = demo.newInstance();
//        // 创建HSSFWorkbook对象(excel的文档对象)
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // 建立新的sheet对象（excel的表单）
//        HSSFSheet sheet = wb.createSheet("sheet1");
//        //声明样式
//        HSSFCellStyle style = wb.createCellStyle();
//        //居中显示
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 在sheet里创建第一行为表头，参数为行索引(excel的行)，可以是0～65535之间的任何一个
//        HSSFRow rowHeader = sheet.createRow(0);
//        //创建单元格并设置单元格内容
//        Set keySet = map.keySet();// 返回键的集合
//        Iterator it = keySet.iterator();
//        //存储属性信息
//        Map<String,String> attMap = new HashMap();
//        int index = 0;
//
//        while(it.hasNext()){
//            String key = it.next().toString();
//            rowHeader.createCell(index).setCellValue(key);
//            attMap.put(Integer.toString(index), map.get(key).toString());
//            index++;
//        }
//        // 在sheet里创建表头下的数据
//        for(int i=1;i<list.size();i++){
//            HSSFRow row = sheet.createRow(i);
//            for(int j=0;j<map.size();j++){
//                Class<?> attrType = BeanUtils.findPropertyType(attMap.get(Integer.toString(j)), new Class[] { obj.getClass() });
//                Object value = getAttrVal(list.get(i), attMap.get(Integer.toString(j)), attrType);
//                row.createCell(j).setCellValue(value.toString());
//                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//            }
//        }
//
//        // 输出Excel文件
//        try {
//            FileOutputStream out = new FileOutputStream(outFilePath);
//            wb.write(out);
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    /**
//     * getAttrVal:(反射的get方法得到属性值)
//     *
//     * @author likaixuan
//     * @param obj
//     *            具体的类
//     * @param att
//     *            类的属性
//     * @param type
//     *            属性是种类型 比如:String double boolean等类型
//     * @throws Exception
//     */
//    public static Object getAttrVal(Object obj, String att,Class<?> type)
//            throws Exception {
//        try {
//            Method method = obj.getClass().getMethod("get" + StringUtil.toUpperCaseFirstOne(att));
//            Object value = new Object();
//            value = method.invoke(obj);
//            return value;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    /**
//     * getMap:(将传进来的表头和表头对应的属性存进Map集合，表头字段为key,属性为value)
//     * @author zzk
//     * @param keyValue
//     *            形如: String keyValue = "名称:name,平均值:average,标准差:standardscore,级别：level";
//     * @return
//     */
//    public static Map<String, String> getMap(String keyValue) {
//        //用LinkedHashMap，可以保证迭代输出的顺序和输入的一样
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        if (keyValue != null) {
//            String[] str = keyValue.split(",");
//            for (String element : str) {
//                String[] str2 = element.split(":");
//                System.out.println(str2[0]+str2[1]);
//                map.put(str2[0], str2[1]);
//            }
//        }
//        return map;
//    }

    public static void main(String[] args) throws Exception {
//        String filePath = "E:\\Book1.xlsx";
        //      List<OutNorm> lid = returnList(filePath);
        //      for (OutNorm a:lid) {
        //          System.out.println(a);
        //      }
//        String keyValue = "名字:name,平均值:average,标准差:standardscore,级别:level";
        //xls命名规则：产品名称V版本名称计算时间（样本量）
        //exportExcel("E:\\Booktest.xls",getMap(keyValue),lid,"cn.com.zhiding.user.entity.OutNorm");
        //System.out.println(lid.size());
    }



}
