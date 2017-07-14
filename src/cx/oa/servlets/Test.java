package cx.oa.servlets;

public class Test
{
	String str = "{'id':41,'bxdate':'2017-07-11','hrId':'1','name':'张三','position':'程序员','department':'IT','fno':2225,'fdbk':'','telext':5587,'alp':'','reason':'客户需要','cstbl':'','ytjt':[{'id':21,'instrct':'','money':455,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-06-25 00:00:00','spoint':'汉口','edate':'2017-06-26 00:00:00','epoint':'北京'},{'id':22,'instrct':'','money':458,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-06-27 00:00:00','spoint':'北京','edate':'2017-06-28 00:00:00','epoint':'上海'},{'id':23,'instrct':'','money':399,'id0':41,'trfct':'交通工具2','tcktno':1,'sdate':'2017-06-29 00:00:00','spoint':'上海','edate':'2017-06-30 00:00:00','epoint':'深圳'},{'id':24,'instrct':'','money':264,'id0':41,'trfct':'交通工具2','tcktno':1,'sdate':'2017-07-01 00:00:00','spoint':'深圳','edate':'2017-07-02 00:00:00','epoint':'香港'},{'id':25,'instrct':'','money':1655,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-03 00:00:00','spoint':'香港','edate':'2017-07-04 00:00:00','epoint':'新加坡'},{'id':26,'instrct':'','money':2896,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-05 00:00:00','spoint':'新加坡','edate':'2017-07-06 00:00:00','epoint':'纽约'},{'id':27,'instrct':'','money':3547,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-07 00:00:00','spoint':'纽约','edate':'2017-07-08 00:00:00','epoint':'北京'},{'id':28,'instrct':'','money':558,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-08 00:00:00','spoint':'北京','edate':'2017-07-09 00:00:00','epoint':'武昌'}],'zs':[{'id':21,'edate':'2017-06-27 00:00:00','sdate':'2017-06-26 00:00:00','id0':41,'point':'北京国际大酒店','reimbw':1,'tcktno':1,'money':326,'instrct':''},{'id':22,'edate':'2017-06-29 00:00:00','sdate':'2017-06-28 00:00:00','id0':41,'point':'上海商务大酒店','reimbw':2,'tcktno':1,'money':496,'instrct':''},{'id':23,'edate':'2017-05-31 00:00:00','sdate':'2017-06-30 00:00:00','id0':41,'point':'深圳A区酒店','reimbw':1,'tcktno':1,'money':476,'instrct':''},{'id':24,'edate':'2017-07-03 00:00:00','sdate':'2017-07-02 00:00:00','id0':41,'point':'香港','reimbw':1,'tcktno':1,'money':469,'instrct':''},{'id':25,'edate':'2017-07-05 00:00:00','sdate':'2017-07-04 00:00:00','id0':41,'point':'新加坡','reimbw':2,'tcktno':1,'money':346,'instrct':''},{'id':26,'edate':'2017-07-08 00:00:00','sdate':'2017-07-06 00:00:00','id0':41,'point':'纽约','reimbw':2,'tcktno':1,'money':627,'instrct':''},{'id':27,'edate':'2017-07-10 00:00:00','sdate':'2017-07-08 00:00:00','id0':41,'point':'北京','reimbw':1,'tcktno':1,'money':376,'instrct':''}],'snjt':[{'id':21,'id0':41,'sdate':'2017-06-26 00:00:00','spoint':'1','epoint':'2','money':47.5,'tcktno':3,'trfct':'的士'},{'id':22,'id0':41,'sdate':'2017-06-29 00:00:00','spoint':'1','epoint':'2','money':55,'tcktno':25,'trfct':'公交车'}],'qt':[{'id':21,'money':126,'bz':'','tcktno':6,'popse':'过路费','id0':41},{'id':22,'money':34,'bz':'','tcktno':1,'popse':'保险','id0':41}],'fee':[{'id':1,'money':30,'pro':'出差'},{'id':2,'money':20,'pro':'夜补'}]}";

	public static void fun1(int a)
	{
		a++;
	}

	public static void main(String[] args)
	{
		int x = 1;
		Test.fun1(x);
		System.out.println(x);
	}
}