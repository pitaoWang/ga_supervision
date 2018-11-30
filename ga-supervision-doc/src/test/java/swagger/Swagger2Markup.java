///**  
// * 
// * @Title: Swagger2Markup.java 
// * @Package swagger  
// * @author wpt1225 wpt1225_gmail_com 
// * @date 2018年8月1日 上午11:56:42 
// * @version V1.0
// *
// */
//package swagger;
//
//import io.github.swagger2markup.Swagger2MarkupConfig;
//import io.github.swagger2markup.Swagger2MarkupConverter;
//import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
//
//import java.net.URL;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.apache.commons.configuration2.Configuration;
//import org.apache.commons.configuration2.builder.fluent.Configurations;
//
//
//
///** 
// * TODO(这里用一句话描述这个类的作用)
// * 
// * @ClassName: Swagger2Markup 
// * @author wpt1225 wpt1225_gmail_com
// * @date 2018年8月1日 上午11:56:42 
// *  
// */
//public class Swagger2Markup {
//
//	//指定adoc文件生成路径
//    public Path outputDirectory = Paths.get("target/asciidoc");
//
//    //通过配置文件生成swagger2markup的参数
//    public Swagger2MarkupConfig config;
//
//    public Swagger2Markup(String Json) throws Exception {
//        //读取配置文件
//        Configuration configuration = new Configurations().properties("config.properties");
//        config = new Swagger2MarkupConfigBuilder(configuration).build();
//        if(Json.startsWith("http")){
//            //获取远程json数据
//            createAdocFile(new URL(Json));
//        }else{
//            //获取本地json数据
//            createAdocFile(Paths.get(Json));
//        }
//    }
//    /**
//     * 通过url生成adoc文件
//     */
//    public void createAdocFile(URL remoteSwaggerFile){
//        Swagger2MarkupConverter.from(remoteSwaggerFile)
//                                .withConfig(config)
//                                .build()
//                                .toFolder(outputDirectory);
//    }
//    /**
//     * 通过json文件生成adoc文件
//     */
//    public void createAdocFile(Path localSwaggerFile){
//        Swagger2MarkupConverter.from(localSwaggerFile)
//                                .withConfig(config)
//                                .build()
//                                .toFolder(outputDirectory);
//    }
//    
//    public static void main(String[] args) throws Exception{
//        //指定本地json文件路径
//        new Swagger2Markup("target/swagger/api-docs.json");
//        //指定远程json文件路径
//    //  new Swagger2Markup("http://petstore.swagger.io/v2/swagger.json");
//
//    }
//}
