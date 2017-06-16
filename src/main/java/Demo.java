import com.qcloud.*;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Demo {
    // appid, access id, access key请去http://app.qcloud.com申请使用
    // 下面的的demo代码请使用自己的appid�
    public static final int APP_ID_V1 = 111;
    public static final String SECRET_ID_V1 = "SECRET_ID_V1";
    public static final String SECRET_KEY_V1 = "SECRET_KEY_V1";

    public static final int APP_ID_V2 = 1251961789;
    public static final String SECRET_ID_V2 = "AKIDoW6You0cDdEl8NyxLkBXp05zhIl4gLI4";
    public static final String SECRET_KEY_V2 = "kJkFhy3kYGnMrCMMTPArNjx1whKtIXdb";
    public static final String BUCKET = "dianping";        //空间名

    public static void main(String[] args) throws Exception {
        //身份证信息
//        System.out.println("=== 身份证信息 ===");
//        String url= "http://imgs.focus.cn/upload/sz/5876/a_58758051.jpg";
//        idCardDemo(url, "0");

        //黄图识别(File)
//        String[] pornFile = {"/Users/yang/Downloads/test.jpeg",
//        };
//        pornFileDemo(pornFile);
//
//        //身份证(File)
//        String[] idFile = {"/Users/yang/Downloads/shenfenzheng.jpg",
//        };
//        idcardFileDemo(idFile);
//
//        String url = "http://zuogeceshi-1253838826.picsh.myqcloud.com/IMG_20170505_190347.jpg";
//        generalDemo(url);

        //通用信息
        System.out.println("\n === 通用信息 ===");
        String[] file = {"/Users/yang/Downloads/0606_40_OCR/0606_OCR_40/14182873/IMG_20170505_152416.jpg",};
        generalFileDemo(file);



//        String[] file = {"/Users/yang/Downloads/0606_40_OCR/0606_OCR_40/14182873/IMG_20170505_152416.jpg",};
//        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496825830459&di=5957cf2638acc4061d4fceb0c1f5676d&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F02%2F61%2F16pic_261572_b.jpg";

        //sign_test();
        //v1版本api的demo
        //apiV1Demo("D:/sss.jpg");
        //v2版本api的demo
        //apiV2Demo("D:/test.jpg");
        //分片上传
        //sliceUpload("D:/sss.jpg");
        //黄图识别
        //黄图识别(Url)
//        String[] pornUrl = {"http://b.hiphotos.baidu.com/image/pic/item/8ad4b31c8701a18b1efd50a89a2f07082938fec7.jpg",
//                "http://c.hiphotos.baidu.com/image/h%3D200/sign=7b991b465eee3d6d3dc680cb73176d41/96dda144ad3459829813ed730bf431adcaef84b1.jpg"
//        };
//        pornUrlDemo(pornUrl);
//        //黄图识别(File)
//        String[] pornFile = {"D:\\porn\\test1.jpg",
//                "D:\\porn\\test2.jpg",
//                "..\\..\\..\\..\\..\\porn\\测试.png"
//        };
//        pornFileDemo(pornFile);
    }

    public static void signDemo() {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        long expired = System.currentTimeMillis() / 1000 + 3600;
        String sign = pc.getSign(expired);
        System.out.println("sign=" + sign);

    }

    public static void apiV1Demo(String pic) throws Exception {
        PicCloud pc = new PicCloud(APP_ID_V1, SECRET_ID_V1, SECRET_KEY_V1);
        picBase(pc, pic);
    }

    public static void apiV2Demo(String pic) throws Exception {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        picBase(pc, pic);
    }

    public static void picBase(PicCloud pc, String pic) throws Exception {
        // 上传一张图片�
        //1. 直接指定图片文件名的方式
        UploadResult result = pc.upload(pic);
        if (result != null) {
            result.print();
        }
        //2. 文件流的方式
        FileInputStream fileStream = new FileInputStream(pic);
        result = pc.upload(fileStream);
        fileStream.close();
        if (result != null) {
            result.print();
        }
        //3. 字节流的方式
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(你自己的参数);
        //ret = pc.upload(inputStream, result);
        // 查询图片的状态��
        PicInfo info = pc.stat(result.fileId);
        if (info != null) {
            info.print();
        }
        // 复制一张图片
        result = pc.copy(result.fileId);
        // 删除一张图片
        int ret = pc.delete(result.fileId);
    }

    public static void sliceUpload(String url) {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        SliceUploadInfo info = pc.simpleUploadSlice(url, 128 * 1024);
        if (info != null) {
            System.out.println("slice upload pic success");
            info.print();
        } else {
            System.out.println("slice upload pic error, error=" + pc.getError());
        }
    }

    public static void pornDemo(String url) {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        PornDetectInfoData info = pc.pornDetect(url);
        info.print();
    }

    /*
    * 身份证信息
    * */
    public static void idCardDemo(String url, String card_type){
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        IdCardInfoData info = pc.idCardDetect(url, card_type);
        info.print();
    }
    /*
    * 通用信息
    * */
    public static void generalDemo(String url){
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        GeneralInfoData info = pc.generalDetect(url);
        System.out.println(pc.getError());
        System.out.println(info);
        info.print();
    }



    public static void pornUrlDemo(String[] pornUrl) {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        ArrayList<PornDetectInfo> info = pc.pornDetectUrl(pornUrl);
        if (info != null) {
            System.out.println("detect porn pic success");
            System.out.println("result_list = {");
            for (int i = 0; i < info.size(); i++) {
                System.out.println("url-" + Integer.toString(i) + " = {");
                info.get(i).print();
                System.out.println("}");
            }
            System.out.println("}");
        } else {
            System.out.println("detect porn pic error, " + pc.getError());
        }
    }

    public static void pornFileDemo(String[] pornFile) {
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        ArrayList<PornDetectInfo> info = pc.pornDetectFile(pornFile);
        if (info != null) {
            System.out.println("detect porn pic success");
            System.out.println("result_list = {");
            for (int i = 0; i < info.size(); i++) {
                System.out.println("file-" + Integer.toString(i) + " = {");
                info.get(i).print();
                System.out.println("}");
            }
            System.out.println("}");
        } else {
            System.out.println("detect porn pic error, " + pc.getError());
        }
    }

    public static void generalFileDemo(String[] file){
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        GeneralInfoData info = pc.generalDetectFile(file);
        System.out.print(info);
//        info.print();
    }

    public static void idcardFileDemo(String[] file){
        PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
        ArrayList<IdCardInfoData> info = pc.idcardDetectFile(file);
//        System.out.print();
    }


}
