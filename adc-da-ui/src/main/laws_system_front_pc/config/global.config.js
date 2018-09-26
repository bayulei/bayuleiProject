/**
 * @description: 通用配置
 * @author: chenxiaoxi
 * @date: 2018-09-04 17:47:46
 */
// 开发环境配置
const devIp = '127.0.0.1'
const devInterfacePORT = '8888'
const simpleFilePath = 'att/attFile/upload'
const multipleFilePath = 'att/attFile/uploadFiles'
const staticFilePath ='192.168.1.149:9988'
// 生产环境配置
const prodIp = 'localhost'
const prodInterfacePORT = '3000'

// 判断环境
const serverIP = process.env.NODE_ENV === 'production'?prodIp:devIp;
const interfacePORT =  process.env.NODE_ENV === 'production'?prodInterfacePORT:devInterfacePORT;

module.exports = {
  serverUrl: 'http://'+serverIP, // 服务器IP地址
  interfaceUrl: 'http://'+serverIP+':'+interfacePORT+'/api/', // 服务器端接口访问地址,
  simpleUploadPath: 'http://'+serverIP+':'+interfacePORT+'/api/' + simpleFilePath, // 单文件上传地址
  multipleUploadPath: 'http://'+serverIP+':'+interfacePORT+'/api/' + multipleFilePath, // 多文件上传地址
  staticFilePath: 'http://'+staticFilePath
}
