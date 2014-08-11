JS = {

	version : '1.0'
};

JS.apply = function(o, c, defaults){

    if(defaults){
        JS.apply(o, defaults);
    }
    if(o && c && typeof c == 'object'){
        for(var p in c){
            o[p] = c[p];
        }
    }
    return o;
};


JS.apply(JS,{

	isObject : function(v){
		return !!v && Object.prototype.toString.call(v) === '[object Object]';
	},

	isFunction : function(v){
		return toString.apply(v) === '[object Function]';
	},

	isNumber : function(v){
		return typeof v === 'number' && isFinite(v);
	},

	isString : function(v){
		return typeof v === 'string';
	},

	isBoolean : function(v){
		return typeof v === 'boolean';
	},

	isArray : function(v){
		return toString.apply(v) === '[object Array]';
	},

	isDefined : function(v){
		return typeof v !== 'undefined';
	},

	applyIf : function(o, c){
		if(o){
			for(var p in c){
				if(!JS.isDefined(o[p])){
					o[p] = c[p];
				}
			}
		}
		return o;
	},

	extend : function(){
		// inline overrides
		var io = function(o){
			for(var m in o){
				this[m] = o[m];
			}
		};
		var oc = Object.prototype.constructor;

		return function(sb, sp, overrides){
			if(JS.isObject(sp)){
				overrides = sp;
				sp = sb;
				sb = overrides.constructor != oc ? overrides.constructor : function(){sp.apply(this, arguments);};
			}
			var F = function(){},
				sbp,
				spp = sp.prototype;

			F.prototype = spp;
			sbp = sb.prototype = new F();
			sbp.constructor=sb;
			sb.superclass=spp;
			if(spp.constructor == oc){
				spp.constructor=sp;
			}
			sb.override = function(o){
				JS.override(sb, o);
			};
			sbp.superclass = sbp.supr = (function(){
				return spp;
			});
			sbp.override = io;
			JS.override(sb, overrides);
			sb.extend = function(o){return JS.extend(sb, o);};
			return sb;
		};
	}(),


	override : function(origclass, overrides){
		if(overrides){
			var p = origclass.prototype;
			JS.apply(p, overrides);
			if(overrides.hasOwnProperty('toString')){
				p.toString = overrides.toString;
			}
		}
	}
})



/**
 * MAP对象，模拟java.util.MAP功能
 *
 * 接口： size() 获取MAP元素个数 isEmpty() 判断MAP是否为空 clear() 删除MAP所有元素 put(key, value)
 * 向MAP中增加元素（key, value) remove(key) 删除指定KEY的元素，成功返回True，失败返回False get(key)
 * 获取指定KEY的元素值VALUE，失败返回NULL containsKey(key) 判断MAP中是否含有指定KEY的元素 values()
 * 获取MAP中所有VALUE的数组（ARRAY） keys() 获取MAP中所有KEY的数组（ARRAY） putAll() 追加另一个map
 * entries() 返回MapEntry[]
 *
 * 例子： var map = new Map();
 *
 * map.put("key", "value"); var val = map.get("key")
 *
 */

MapEntry=function(k,v){
    this.key=k;
    this.value=v;
    this.keyEquals=function(key2){
        if(this.key==key2){
            return true;
        }else{
            return false;
        }
    }
}
Map=function(){
 	this.elements = [];
}
Map.prototype={
    size:function(){
        return this.elements.length;
    },
    clear:function(){
    	delete this.elements;
        this.elements=new Array();
    },
	isEmpty:function(){
        return (this.elements==null||this.elements.length<=0);
    },
    put:function(k,v){
        var newEntry=new MapEntry(k,v);
        for(var i=0;i<this.elements.length;i++){
            var entry=this.elements[i];
            if(entry.keyEquals(k)){
                return;
            }
        }
        this.elements.push(newEntry);
    },
    get:function(k){
        for(var i=0;i<this.elements.length;i++){
            var entry=this.elements[i];
            if(entry.keyEquals(k)){
                return entry.value;
            }
        }
        return null;
    },
	remove:function(k){
        var entryPoped;
        for(var i=0;i<this.elements.length;i++){
            entryPoped=this.elements.pop();
            if(entryPoped.keyEquals(k)){
                break;
            }else{
                this.elements.unshift(entryPoped);
            }
        }
    },
    keys:function(){
        var keys=[];
        for(var i=0;i<this.elements.length;i++){
            keys.push(this.elements[i].key);
        }
        return keys;
    },
    values:function(){
        var values=[];
        for(var i=0;i<this.elements.length;i++){
            values.push(this.elements[i].value);
        }
        return values;
    },
    containsKey:function(k){
        for(var i=0;i<this.elements.length;i++){
            if(this.elements[i].keyEquals(k))
                return true;
        }
        return false;
    },
    putAll:function(map){
        if(map==null||typeof map!="object"|| !(map instanceof Map)){
            throw new Error("the object to be put should be a valid map");
        }
        for(var i=0;i<map.size();i++){
            this.put(map.elements[i].key,map.elements[i].value);
        }
    },
    entries:function(){
    	return this.elements;
    }
};
/**
 * Javascript中字符串拼接(+)也有性能问题，利用Array.join可提高性能
 * StringBuffer对象,模拟java中的StringBuffer.
 *
 * 示例： var sb=new StringBuffer(); sb.append("aaa").append(100);
 * alert(sb.toString());
 */
StringBuffer=function(){
	this.elements =[];
}
StringBuffer.prototype={
	append:function(o){
		this.elements[this.elements.length]=o;
		return this;
	},
	toString:function(){
		return this.elements.join("");
	}
}


/**
 * 屏蔽浏览器差异，清除空白节点
 */
function cleanWhiteSpace(element) {
	for(var i = 0; i < element.childNodes.length; i++) {
		var node = element.childNodes[i];
		if (node.nodeType == 3 && !/\S/.test(node.nodeValue)) {
			node.parentNode.removeChild(node);
		}
	}
}



/**
 * 屏蔽浏览器差异，给firefox增加innerText方法
 */
function isIE(){
	if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1)
	    return true;
	else
	    return false;
}

if(!isIE()){
    HTMLElement.prototype.__defineGetter__("innerText",
	    function(){
	        var anyString = "";
	        var childS = this.childNodes;
	        for(var i=0; i<childS.length; i++) {
	            if(childS[i].nodeType==1)
	                // anyString += childS[i].tagName=="BR" ? "\n" :
					// childS[i].innerText;
	                anyString += childS[i].innerText;
	            else if(childS[i].nodeType==3)
	                anyString += childS[i].nodeValue;
	        }
	        return anyString;
	    }
    );
    HTMLElement.prototype.__defineSetter__("innerText",
	    function(sText){
	        this.textContent=sText;
	    }
    );
}
// 比较两个日期 日期的格式为:2011-11-03 这种方式，如果是2011/02/12方式，则切割方法应改为“/”
function CompareDate(startDate, endDate) {
	 if(startDate.length>0&&endDate.length>0){
		 var arrStartDate = startDate.split("-");
	     var arrEndDate = endDate.split("-");
	     var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2]);
	     var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2]);
		return (allStartDate.getTime()>allEndDate.getTime());
	 }

}

