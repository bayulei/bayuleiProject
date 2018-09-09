/**
 * @description: 通用配置
 * @author: chenxiaoxi
 * @date: 2018-09-04 17:47:46
 */
// 开发环境配置
const devIp = '192.168.7.185'
const devInterfacePORT = '8888'

// 生产环境配置
const prodIp = 'localhost'
const prodInterfacePORT = '3000'

// 判断环境
const serverIP = process.env.NODE_ENV === 'production'?prodIp:devIp;
const interfacePORT =  process.env.NODE_ENV === 'production'?prodInterfacePORT:devInterfacePORT;

module.exports = {
  serverUrl: 'http://'+serverIP, // 服务器IP地址
  interfaceUrl: 'http://'+serverIP+':'+interfacePORT+'/api/', // 服务器端接口访问地址
}
