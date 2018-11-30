/**
 * 配置编译环境和线上环境之间的切换
 *
 * baseUrl: 域名地址
 *
 */
let baseUrl = '';

//上传文件保存路径http://47.96.91.129:18083/file
let imageUrl="";
//上传文件请求路径
let uploadUrl="";

let authUrl="";
if (process.env.NODE_ENV == 'development') {
    imageUrl='/file';
    uploadUrl='/upload';
    authUrl='/auth';
}else{
    imageUrl='/file';
    uploadUrl='/upload';
    authUrl='/auth';
}

export {
    baseUrl,

    imageUrl,
    uploadUrl,
    authUrl
}
