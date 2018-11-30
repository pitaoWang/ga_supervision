package com.atosenet.ga.company.mgt.config;

public final class Constants 
{
    /**成功状态码*/
    public static final int OK = 200;
    
    /**用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的**/
    public static final int INVALID_REQUEST = 400;
    
    /** 403 Forbidden - 访问被禁止 **/
    public static final int FORBIDDEN = 403;
    
    /** 404 Not Found - 用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的 **/
    public static final int NOT_FOUND = 404;
    
    /** 429 Too Many Request - 请求过多 **/
    public static final int TOO_MANY_REQUEST = 429;
    
    /** 500 Internal Server Error - 服务器发生错误，用户将无法判断发出的请求是否成功 **/
    public static final int INTERNAL_SERVER_ERROR = 500;
    
     //执照类型是飞行员的定义
  	public static final String LICENSR_TYPE = "1";  
  	
	 //个人证件照
  	public static final String PHOTO_PATH ="photo";
  	
  	 //个人签名扫描件文件目录
  	public static final String SCAN_SIGANTURE_PATH ="scanSiganture";
  	
  	//身份证目录
  	public static final String SCAN_CARD_PATH ="scanCard";
  	
    //人员执照目录
  	public static final String FILE_PATH ="filePath";
  	
  	//体检合格证目录
  	public static final String CERTIFICATE_PATH ="certificatePath";
  	
}
