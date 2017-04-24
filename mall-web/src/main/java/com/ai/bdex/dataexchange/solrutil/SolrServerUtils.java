//package com.ai.bdex.dataexchange.solrutil;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.client.HttpClient;
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.impl.CloudSolrServer;
//import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
//import org.apache.solr.client.solrj.impl.HttpClientUtil;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
//import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
//import org.apache.solr.common.params.ModifiableSolrParams;
//import org.apache.solr.common.util.DateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.bdex.dataexchange.exception.BusinessException;
//
//public class SolrServerUtils {
//
//    public final static String MODULE = "【搜索引擎】SearchUtils";
//    private static final Logger Log = LoggerFactory.getLogger(SolrServerUtils.class);
//    private static final int ZK_CLIENT_TIMEOUT = Integer.MAX_VALUE;
//    private static final int ZK_CONNECT_TIMEOUT = Integer.MAX_VALUE;
//    private static Map<String, SolrServer> readServerMap = new HashMap<String, SolrServer>();
//    private static Map<String, SolrServer> writeServerMap = new HashMap<String, SolrServer>();
//
//    public static void cleanSolrSeverCache(){
//        readServerMap = new HashMap<String, SolrServer>();
//        writeServerMap = new HashMap<String, SolrServer>();
//    }
//    
//    /**
//     * 多用于查询操作，只用于单节点查询
//     * @param collectionName
//     * @param serverInfo
//     * @return
//     * @throws BusinessException
//     */
//    public static SolrServer createHttpSolrServer(final String collectionName,final String serverInfo) throws BusinessException {
//        ModifiableSolrParams params = new ModifiableSolrParams();
//        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 128);
//        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 32);
//        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
//        params.set(HttpClientUtil.PROP_CONNECTION_TIMEOUT, Integer.MAX_VALUE);
//        params.set(HttpClientUtil.PROP_SO_TIMEOUT, Integer.MAX_VALUE);
//        params.set(HttpClientUtil.PROP_USE_RETRY, true);
//        HttpClient httpClient =  HttpClientUtil.createClient(params);
//        SolrServer solrServer = new HttpSolrServer("http://"+serverInfo+"/solr/"+collectionName,httpClient);
//        return solrServer;
//        
//    }
//    
//    /**
//     * 多用于更新删除索引操作，只用于单节点更新
//     * @param collectionName
//     * @param masterServer
//     * @return
//     * @throws BusinessException
//     */
//    public static SolrServer createConcurrentUpdateSolrServer(final String collectionName,final String masterServer) throws BusinessException {
//        
//        SolrServer solrServer = new ConcurrentUpdateSolrServer("http://"+masterServer+"/solr/"+collectionName, 100, 10);
//        return solrServer;
//        
//    }
//    
//    /**
//     * 用于有多个Solr服务器，负载均衡
//     * @return
//     * @throws BusinessException
//     */
//    public static SolrServer createLBHttpSolrServer(final String[] solrServerUrls) throws BusinessException {
//        
//        SolrServer solrServer = null; 
//        try {
//            solrServer = new LBHttpSolrServer(solrServerUrls);
//        } catch (MalformedURLException e) {
//            Log.error(MODULE,"LBHttpSolrServer创建异常", e);
////            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{solrServerUrls.toString(),SearchUtils.getExceptionMessage(e)});
//        }
//        return solrServer;
//    }
//
//    /**
//     * 用于cloud模式
//     * @param collectionName
//     * @return
//     * @throws BusinessException
//     */
//    public static SolrServer createCloudSolrServer(final String collectionName) throws BusinessException {
//
//        CloudSolrServer cloudSolrServer = null;
//        try {
//
//            /*DefaultMaxPerRoute就是CloudSolrServer中默认的maxConnectionsPerHost，MaxTotal就是maxTotalConnection。
//            maxConnectionsPerHost是指针对一个目标服务器，httpclient客户端可以和它建立的并发连接数。之前的版本是2，现在是5。
//            maxTotalConnections是指针对服务器集群，client可以保持的所有连接数，即同一客户端同时可连接多个目标主机。现在是
//            maxConnectionsPerHost的2倍。*/
//            ModifiableSolrParams params = new ModifiableSolrParams();
//            params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);//10
//            params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 500);//5
//            params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
//            params.set(HttpClientUtil.PROP_CONNECTION_TIMEOUT, Integer.MAX_VALUE);
//            params.set(HttpClientUtil.PROP_SO_TIMEOUT, Integer.MAX_VALUE);
//            params.set(HttpClientUtil.PROP_USE_RETRY, true);
//            HttpClient client = HttpClientUtil.createClient(params);
//            LBHttpSolrServer lbServer = new LBHttpSolrServer(client);
//
//            cloudSolrServer = new CloudSolrServer("",lbServer);
//            cloudSolrServer.setDefaultCollection(collectionName);
//            cloudSolrServer.setZkClientTimeout(ZK_CLIENT_TIMEOUT);
//            cloudSolrServer.setZkConnectTimeout(ZK_CONNECT_TIMEOUT);
//            cloudSolrServer.connect();
//        } catch (Exception e) {
//            Log.error(MODULE, "CloudSolrServer创建异常", e);
////            throw new BusinessException(
////                    SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,SearchUtils.getExceptionMessage(e)});
//        }
//
//        return cloudSolrServer;
//    }
//    
//    public static SolrServer getSolrServer(final String collectionName,boolean readMode) throws BusinessException {
//        
////        if(StringUtils.isBlank(collectionName)){
////            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,"collectionName empty"});
////        }
//        
//        Map<String,String> args=SearchCacheUtils.getSecArgsMap();
//        
//        SolrServer solrServer=null;
//        
//        //判断部署模式
//        if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.STANDALONE)){//单点模式
//            
//            if(readMode){
//                if(readServerMap.containsKey(collectionName)){
//                    return readServerMap.get(collectionName);
//                }
//                List<String> serverList;
//                try {
//                    serverList = SearchCacheUtils.getSecSolrServerList();
//                } catch (SearchException e) {
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
//                }
//                
//                if(serverList.size()>1){
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE+collectionName+"部署模式是单点模式，但是有多个节点配置记录");
//                }
//                solrServer=createHttpSolrServer(collectionName, serverList.get(0));
//                readServerMap.put(collectionName, solrServer);
//                return solrServer;
//            }else{
//                if(writeServerMap.containsKey(collectionName)){
//                    return writeServerMap.get(collectionName);
//                }
//                List<String> serverList;
//                try {
//                    serverList = SearchCacheUtils.getSecSolrServerList();
//                } catch (SearchException e) {
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
//                }
//                
//                if(serverList.size()>1){
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE+collectionName+"部署模式是单点模式，但是有多个节点配置记录");
//                }
//                solrServer=createConcurrentUpdateSolrServer(collectionName, serverList.get(0));
//                writeServerMap.put(collectionName, solrServer);
//                return solrServer;
//            }
//            
//        }else if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.REPLICATION)){//主从模式（副本模式）
//            if(readMode){
//                if(readServerMap.containsKey(collectionName)){
//                    return readServerMap.get(collectionName);
//                }
//                List<String> serverList;
//                try {
//                    serverList = SearchCacheUtils.getSecSolrServerList();
//                } catch (SearchException e) {
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
//                }
//                
//                String[] solrServerUrls=new String[serverList.size()];
//                for(int i=0;i<serverList.size();i++){
//                    solrServerUrls[i]="http://"+serverList.get(i)+"/solr/"+collectionName;
//                }
//                solrServer = createLBHttpSolrServer(solrServerUrls);
//                readServerMap.put(collectionName, solrServer);
//                return solrServer;
//            }else{
//                if(writeServerMap.containsKey(collectionName)){
//                    return writeServerMap.get(collectionName);
//                }
//                String masterServer;
//                try {
//                    masterServer = SearchCacheUtils.getSecMasterSolrServer();
//                } catch (SearchException e) {
//                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE+collectionName+"部署模式是主从模式，但是没有主节点配置记录");
//                }
//                solrServer=createConcurrentUpdateSolrServer(collectionName,masterServer);
//                writeServerMap.put(collectionName, solrServer);
//                return solrServer;
//            }
//        }else if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.CLOUD)){//CLOUD模式
//            if(writeServerMap.containsKey(collectionName)){
//                return writeServerMap.get(collectionName);
//            }
//            solrServer=createCloudSolrServer(collectionName);
//            writeServerMap.put(collectionName, solrServer);
//            return solrServer;
//        }
//        
//        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_DEPLOYTYPE);
//        
//    }
//
//    private static String getHttpRequest(String url) throws Exception{
//        String result = "";
//        BufferedReader in = null;
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                Log.error(MODULE, "HTTP请求后关闭BufferedReader异常", e2);
//            }
//        }
//        return result;
//    }
//    
//    public static String getExceptionMessage(Exception e){
//        if(e instanceof BusinessException){
//            if(StringUtils.isNotBlank(((BusinessException) e).getMessage())){
//                return ((BusinessException) e).getMessage();
//            }
//        }
//        if(e.getCause()!=null){
//            if(StringUtils.isNotBlank(e.getCause().getMessage())){
//                return e.getCause().getMessage();
//            }
//            if(StringUtils.isNotBlank(e.getCause().getLocalizedMessage())){
//                return e.getCause().getLocalizedMessage();
//            }
//        }
//        if(StringUtils.isNotBlank(e.getMessage())){
//            return e.getMessage();
//        }
//        return e.getLocalizedMessage();
//    }
//    
//    public static String getFuzzyQueryKeyword(String keyword){
//    	return SearchConstants.STAR+keyword+SearchConstants.STAR;
//    }
//    
//    public static String format(Date date) {
//        return DateUtil.getThreadLocalDateFormat().format(date);
//    }
//}
