import com.qcloud.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by yang on 2017/6/7.
 */
public class GeneralTest {

    public static final int APP_ID_V2 = 1251961789;
    public static final String SECRET_ID_V2 = "AKIDoW6You0cDdEl8NyxLkBXp05zhIl4gLI4";
    public static final String SECRET_KEY_V2 = "kJkFhy3kYGnMrCMMTPArNjx1whKtIXdb";
    public static final String BUCKET = "dianping";        //空间名

    public static void main(String[] args) throws Exception {
        Map<String, String> fileInfo = getFileName("/Users/yang/Desktop/jietu/");
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/tmp/res.txt")));
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        GeneralInfoData info;

        for(Map.Entry<String , String > entry : fileInfo.entrySet()){
            String filePath = entry.getKey();
            String shopID = entry.getValue();
            String [] file = {filePath};
            info = pc.generalDetectFile(file);
            if(info == null){
                writer.write(String.format("%s\t%s\n",shopID, filePath));
                continue;
            }
            Set<String> item = info.item;
            Iterator<String> iter = item.iterator();
            while (iter.hasNext()){
                writer.write(String.format("%s\t%s\t%s\n",shopID, filePath, iter.next()));
                writer.flush();
            }
        }
        writer.close();
    }

    public static Map getFileName(String basepath){
        File bpath = new File(basepath);
        Map<String , String> res = new HashMap<String, String>();
        File [] fileArr = bpath.listFiles();
        for (int i =0 ;i<fileArr.length; i++){
            String shopID =  fileArr[i].getName();
            if(fileArr[i].isDirectory()){
                File[] files = fileArr[i].listFiles();
                for(int j =0; j<files.length;j++){
                    res.put(files[j].getAbsolutePath(), shopID);
                }
            }
        }
        return res;
    }

}