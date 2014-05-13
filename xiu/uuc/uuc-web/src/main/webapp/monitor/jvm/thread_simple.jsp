<%@ page contentType="text/html; encoding=gb2312"%><%@ page import="java.lang.management.*"%><%@ page import="java.util.*"%><%
ThreadMXBean tm = ManagementFactory.getThreadMXBean();
tm.setThreadContentionMonitoringEnabled(true);
%>
<b>Thread Count: </b><%=tm.getThreadCount()%><br>
<b>Started Thread Count: </b><%=tm.getTotalStartedThreadCount()%><br>
<b>thread contention monitoring is enabled? </b><%=tm.isThreadContentionMonitoringEnabled()%><br>
<b>if the Java virtual machine supports thread contention monitoring? </b><%=tm.isThreadContentionMonitoringSupported()%><br>
<b>thread CPU time measurement is enabled? </b><%=tm.isThreadCpuTimeEnabled()%><br>
<b>if the Java virtual machine implementation supports CPU time measurement for any thread? </b><%=tm.isThreadCpuTimeSupported()%><br>
<hr>
<%
long [] tid = tm.getAllThreadIds();
ThreadInfo [] tia = tm.getThreadInfo(tid, Integer.MAX_VALUE);

Map tMap = new HashMap(); //key thread的各种信息合集; value: ThreadInfo List
for (int t = 0; t < tia.length; t++) {
    String key = tia[t].getThreadState() + "\t" + tia[5].getLockOwnerName() + "\t";
    for (int i = 0; i < tia[t].getStackTrace().length; i ++ ) {
        key += "\t" + tia[t].getStackTrace()[i].toString();
    }
    List threads = (List) tMap.get(key);
    if (threads == null) {
        threads = new ArrayList();
        tMap.put(key, threads);
    }
    threads.add(tia[t]);
}

%>
<b>Thread Group Count: </b><%=tMap.size()%><br /> <br />
<%
Object [] threadsArray = tMap.values().toArray();
for (int i = 0; i < threadsArray.length - 1; i++) {
    for (int j = i + 1; j < threadsArray.length; j++) {
        if (((List)threadsArray[i]).size() < ((List)threadsArray[j]).size()) {
            Object tmp = threadsArray[i]; threadsArray[i] = threadsArray[j]; threadsArray[j] = tmp;
        }
    }
}

for (int i = 0; i < threadsArray.length; i++) {
    List threads = (List)threadsArray[i];
    if (threads == null) {
        continue;
    }
    ThreadInfo ti = (ThreadInfo)threads.get(0);
    String ids = (ti).getThreadId() + "";
    for (int j = 1; j < threads.size(); j++) {
        ids += " ; " + ((ThreadInfo) threads.get(j)).getThreadId();
    }
%>    

<b>Thread ID: </b><%=ids%><br>
<b>Thread count in group: </b> <%=threads.size()%> <br />
<b>Thread Name: </b><%=ti.getThreadName()%><br>
<b>Thread State: </b><%=ti.getThreadState()%><br>
<b>Thread Lock Name: </b><%=ti.getLockName()%><br>
<b>Thread Lock Owner Name: </b><%=ti.getLockOwnerName()%><br>
<b>Stack Info: (depth:<%=ti.getStackTrace().length%>)</b><br>
<%
StackTraceElement[] stes = ti.getStackTrace();
for(int j=0; j<stes.length; j++)
{
  StackTraceElement ste = stes[j];
%>
&nbsp;&nbsp;&nbsp;&nbsp;+<%=ste%><br>
<%
}
%>
<hr>

<%
}
%>
