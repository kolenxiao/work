<?php

class JsonRpcFault extends Exception {}
class JsonRpcClient {
    private $uri;
    private $resp;
    private $method;
    private $jsonRequest;
    static $interfaceSafe;

    public function __construct($uri) {
        $this->uri = $uri;
    }

    private function generateId() {
        $chars = array_merge(range('A', 'Z'), range('a', 'z'), range(0, 9));
        $id = '';
        for($c = 0; $c < 16; ++$c)
            $id .= $chars[mt_rand(0, count($chars) - 1)];
        return $id;
    }

    public function __call($name, $arguments) {
    	$this->method = $name;
        $id = $this->generateId();
        
        $ignore_error = false;
        
        $ind = array_search("ignore_rpc_error",$arguments);
        
        //忽略rpc错误
        if(!empty($ind)){
        	$ignore_error = true;
        	unset($arguments[$ind]);
        }

        $request = array(
            'jsonrpc' => '2.0',
            'method'  => $name,
            'params'  => $arguments,
            'id'      => $id,
        );


        $jsonRequest = json_encode($request);

    	$this->jsonRequest = $jsonRequest;
        
        
        //$bef_time = time();
        
//      $jsonResponse = @file_get_contents($this->uri, false, $ctx);
        $jsonResponse=$this->curlrequest();
        
        $this->resp = $jsonResponse;
        
        //$n_time = time();
        //超过5秒写日志
       // if($n_time-$bef_time > 15){
       if($jsonResponse === null){
//        	error_log("[".date('Y-m-d H:i:s').']['.($n_time-$bef_time).']['.$this->uri.']['.$this->method.']'."\r\n",3,WWW_ROOT.'/static/sys_log/timeout_log_'.date('Ymd').'.log');
        	return $this->show_system_error(10010 ,'RPC request timeout',$ignore_error);
        }
        
        if(IS_DEBUG == 2){
        	error_log("[".date('Y-m-d H:i:s').']['.($n_time-$bef_time).']['.$this->uri.']['.$this->method.']['.$this->jsonRequest.']'.'--------'.$this->resp."\r\n============================\r\n",3,WWW_ROOT.'/static/sys_log/rpc_log_'.date('Ymd').'.log');
//   	 	error_log("[".date('Y-m-d H:i:s').']['.($n_time-$bef_time).']['.$_SERVER['REQUEST_URI'].']['.$this->uri.']['.$this->method.']['.$this->jsonRequest.']'."\r\n".$this->resp."\r\n============================\r\n",3,WWW_ROOT.'/static/sys_log/rpc_log_'.date('Ymd').'.log');
   	 	}
   	 	
        
        if ($jsonResponse === false){
        	return $this->show_system_error(10011 ,'file_get_contents failed',$ignore_error);
//            throw new JsonRpcFault('file_get_contents failed', -32603);
        }
        $response = json_decode($jsonResponse);

        if ($response === null){
        	return $this->show_system_error(10012 ,'JSON cannot be decoded',$ignore_error);
//            throw new JsonRpcFault('JSON cannot be decoded', -32603);
    	}	
        if ($response->id != $id){
        	return $this->show_system_error(10013 ,'Mismatched JSON-RPC IDs',$ignore_error);
//            throw new JsonRpcFault('Mismatched JSON-RPC IDs', -32603);
        }
        if (property_exists($response, 'error')){
        	return $this->show_system_error(10014 , 'error',$ignore_error);
//            throw new JsonRpcFault($response->error->message, $response->error->code);
            
        }else if (property_exists($response, 'result')){
        	
        	
            return $response->result;
        }else{
        	return $this->show_system_error(10015 , 'Invalid JSON-RPC response',$ignore_error);
//            throw new JsonRpcFault('Invalid JSON-RPC response', -32603);
        }
    }

    
    /**
     * 处理RPC错误信息
     */
    private function show_system_error($code , $desc, $ignore_error = false){
   	 	global $login_user;
   	 	if(IS_DEBUG > 0){
   	 		error_log("============================\r\n[".date('Y-m-d H:i:s').']['.$this->uri.']['.$this->method.']['.$this->jsonRequest.']'.$desc."\r\n".$this->resp."\r\n".$this->request."\r\n",3,WWW_ROOT.'/static/sys_log/err_log_'.date('Ymd').'.log');
   	 	}
   	 	
   	 	//忽略错误
   	 	if($ignore_error){
   	 		if(IS_DEBUG > 0){
   	 			error_log("[XT_ignore_error_desc][".$code."]\r\n",3,WWW_ROOT.'/static/sys_log/err_log_'.date('Ymd').'.log');
   	 		}
   	 		return array('XT_ignore_error_code'=>$code,'XT_ignore_error_desc'=>$desc);
   	 	}else{
	   	 	$this->show_error_page($code,$desc);
   	 	}
    }
    
    /**
     * 显示RPC错误信息
     */
    public function show_error_page($code , $desc){
    	//转换当前请求的URL，让开发人员能够理解，同时又避免暴露给用户
   	 	$encode_uri = strtoupper(str_replace('.','_',str_replace('/','::',substr($this->uri,strpos($this->uri,'/',8)+1))));
    	$err_msg= array('code'=>$code,'desc'=>strtoupper($desc),'uri'=>$encode_uri);
    	include template('system_error');
    	exit();
    }
    
    
    private function curlrequest(){
	    $proxy=trim($proxy);
	    //$user_agent ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";
	    $ch = curl_init();    // 初始化CURL句柄
	    if(!empty($proxy)){
	        curl_setopt ($ch, CURLOPT_PROXY, $proxy);//设置代理服务器
	    }
	    curl_setopt($ch, CURLOPT_URL, $this->uri); //设置请求的URL
	     //curl_setopt($ch, CURLOPT_FAILONERROR, 1); // 启用时显示HTTP状态码，默认行为是忽略编号小于等于400的HTTP信息
	     //curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);//启用时会将服务器服务器返回的“Location:”放在header中递归的返回给服务器
	    curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);// 设为TRUE把curl_exec()结果转化为字串，而不是直接输出
	    curl_setopt($ch, CURLOPT_POST, 1);//启用POST提交
	    curl_setopt($ch, CURLOPT_POSTFIELDS, $this->jsonRequest); //设置POST提交的字符串
	     //curl_setopt($ch, CURLOPT_PORT, 80); //设置端口
	    curl_setopt($ch, CURLOPT_TIMEOUT, 15); // 超时时间
	    //curl_setopt($ch, CURLOPT_USERAGENT, $user_agent);//HTTP请求User-Agent:头
	     //curl_setopt($ch,CURLOPT_HEADER,1);//设为TRUE在输出中包含头信息
	     //$fp = fopen("example_homepage.txt", "w");//输出文件
	     //curl_setopt($ch, CURLOPT_FILE, $fp);//设置输出文件的位置，值是一个资源类型，默认为STDOUT (浏览器)。
	    //设置HTTP头信息
	    if(empty(self::$interfaceSafe)){
	    	curl_setopt($ch,CURLOPT_HTTPHEADER,array(
		        'Content-Type: application/json\r\n',
		    ));
	    }else{
		    curl_setopt($ch,CURLOPT_HTTPHEADER,array(
		        'Content-Type: application/json\r\n',
		    	'PARAM_1: '.self::$interfaceSafe['PARAM_1'],
		    	'PARAM_2: '.self::$interfaceSafe['PARAM_2'],
		    	'PARAM_3: '.self::$interfaceSafe['PARAM_3'],
		    	'PARAM_4: '.self::$interfaceSafe['PARAM_4'],
		    ));
	    }
	    $document = curl_exec($ch); //执行预定义的CURL
	    $info=curl_getinfo($ch); //得到返回信息的特性
	     //print_r($info);
	    curl_close($ch);
	    return $document;
	}
}

?>