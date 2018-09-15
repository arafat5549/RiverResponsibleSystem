package other;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.junit.Test;

/**
 * Created by wangyaoyao on 2018/7/31.
 */
public class JasperReportTest
{

    @Test
    public  void jrxmlToJasper(){
//        String path = this.getClass().getResource("/jasper/report1.jrxml").getPath();
//
//        File file = new File(path);
//        String parentPath = file.getParent();
//        String jrxmlDestSourcePath = parentPath+"/report1.jasper";
//        JasperCompileManager.compileReportToFile(path,jrxmlDestSourcePath);
//        InputStream isRef = new FileInputStream(new File(jrxmlDestSourcePath));
//        OutputStream sosRef = new //response.getOutputStream();
//        response.setContentType("application/pdf");
//        JasperRunManager.runReportToPdfStream(isRef,sosRef,new HashMap(),
//                new JREmptyDataSource());
//        sosRef.flush();
//        sosRef.close();
//        JasperCompileManager.compileReport()
        //the path to the jrxml file to compile
        try {

            String jpath  = System.getProperty("user.dir")+"/src/main/webapp/resource/jaspers/";
            System.out.println(jpath);
            JasperCompileManager.compileReportToFile(
                    jpath+"user.jrxml",
                    jpath+"user2.jasper");

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
