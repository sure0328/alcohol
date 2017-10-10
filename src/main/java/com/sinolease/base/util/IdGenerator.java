package com.sinolease.base.util;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2位的版本，32位的日期（当前日期-20141124的秒数）+m位的机器号+n位的和业务+x位的业务扩展+y位的自增
 * 2 + 32 + m + n + x + y = 64,  因此 m + n + x + y = 30
 *
 * @author AlbertLy
 */
public class IdGenerator {
    private static Integer long_len = 50;
    //固定的34位：2位的版本，32位的日期（当前日期-20141124的秒数）
    private static Integer date_len = 32;
    //默认的机器位数
    private static Integer host_len = 8;

    //通过上述定义，可得到如下数据
    /*各个字段的最低位数*/
    private static Integer date_lowpos = long_len - date_len;
    private static Integer host_lowpos = date_lowpos - host_len;
    //计算得到的每次id自增范围
    private static Long id_incr_range = (long) Math.pow(2, long_len - date_len - host_len) - 1;

    private static Long version = 1L;
    private static Long date = getRelationTime();
    //主机
    private static Long host = null;
    //业务
    private static Long bussiness = 0L;

    private static int CICLEMINGROWTH = 1;
    private static AtomicInteger i = new AtomicInteger(CICLEMINGROWTH);

    public static Long next() {
        return next(getHost());
    }
    public static Integer nextInt() {
        return nextInt(getHost());
    }
    public static Integer nextInt(Long host) {
        int x = i.getAndIncrement();
        if (x >= id_incr_range) {
            long date_tmp = date;
            synchronized (IdGenerator.class) {
                if (date_tmp == date) {
                    refreshTime();
                    i.set(CICLEMINGROWTH);
                }
            }
            return nextInt(host);
        }
        long time_tmp_movbit = date << date_lowpos;
        long host_tmp_movbit = host << host_lowpos;
        return (int)time_tmp_movbit + (int)host_tmp_movbit + x;
    }
    public static Long next(Long host) {
        int x = i.getAndIncrement();
        if (x >= id_incr_range) {
            long date_tmp = date;
            synchronized (IdGenerator.class) {
                if (date_tmp == date) {
                    refreshTime();
                    i.set(CICLEMINGROWTH);
                }
            }
            return next(host);
        }
        long time_tmp_movbit = date << date_lowpos;
        long host_tmp_movbit = host << host_lowpos;
        return time_tmp_movbit + host_tmp_movbit + x;
    }

    public static Long getVersion() {
        return version;
    }

    public static Long getBussiness() {
        return bussiness;
    }

    public static void setBussiness(Long bussiness) {
        IdGenerator.bussiness = bussiness;
    }

    private static Long getHost() {
        if (host == null) {
            synchronized (IdGenerator.class) {
                if (host == null) {
                    //首先尝试读取配置文件，是否设置了ip
                    try {
                        InputStream inputStream = IdGenerator.class.getClassLoader()
                                .getResourceAsStream("ipConfig.properties");
                        Properties p = new Properties();
                        p.load(inputStream);
                        host = Long.parseLong(p.getProperty("host"));
                    } catch (Exception e) {
                        System.out.println("IdCreator not get config");
                    }
                    //如果获取不到，则读取本机内网ip地址后缀
                    try {
                        if (host == null) {
                            String ipSuffix = getIpSuffix();
                            //如果获取到的不是172.0.0.1、172.0.0.0或255.255.255.255这些ip地址，则使用
                            if (ipSuffix != null && !ipSuffix.equals("0") && !ipSuffix.equals("1")) {
                                host = Long.parseLong(ipSuffix);
                                System.out.println("IdCreator get host config by localhost. host=" + host);
                            } else {
                                System.out.println("IdCreator ipSuffix is not fixable. ipSuffix=" + ipSuffix);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("IdCreator not get from localhost");
                    }
                    //如果获取不到，则取一个随机数
                    if (host == null) {
                        host = Long.valueOf(new Random().nextInt((int) Math.pow(2, host_len) - 1));
                        System.out.println("IdCreator get host config by random. host=" + host);
                    }
                }
            }
        }

        return host;
    }

    private static String getIpSuffix() {
        try {
            String localIp = getLinuxLocalIp();
            String ipSuffix;
            if (localIp != null) {
                ipSuffix = localIp.substring(localIp.lastIndexOf(".") + 1);
                return ipSuffix;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                //System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            //System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        //System.out.println("IP:" + ip);
        return ip;
    }

    /**
     * 获取相对于20141124的秒数
     *
     * @return
     */
    private static Long getRelationTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis() / 1000 - 1419350400L;
    }

    private static Long refreshTime() {
        Long data_tmp = getRelationTime();
        if (data_tmp <= date) {
            date++;
        } else {
            date = data_tmp;
        }
        return date;
    }


    /*public static void main(String[] args) {
        *//*Date date=new Date();
        Calendar cal=Calendar.getInstance(); 
        cal.set(2014, 11, 24, 0, 0, 0);
        System.out.println(cal.getTimeInMillis()/1000);
        
        System.out.println(id_incr_range);
        long version_tmp = getVersion();
        long time_tmp = getRelationTime();
        long host_tmp = getHost();
        long bussiness_tmp = getBussiness();
        System.out.println(date_lowpos + "_" + host_lowpos);
        System.out.println(version_tmp + "_" + time_tmp + "_" + host_tmp + "_" + bussiness_tmp);
        System.out.println(time_tmp + "  " + Long.toBinaryString(time_tmp));
        long time_tmp_movbit = time_tmp << date_lowpos;
        long host_tmp_movbit = host_tmp << host_lowpos;
        System.out.println(Long.toBinaryString(time_tmp_movbit));
        System.out.println(Long.toBinaryString(host_tmp_movbit));
        System.out.println(Long.MAX_VALUE);
        System.out.println(next());
        System.out.println(next(11L));
        System.out.println(Long.toBinaryString(next()));
        System.out.println(Long.toBinaryString(next(0L)));
        System.out.println("----------------------------------------");
        
        for(int i = 0; i < 10; i++){
            Runnable r = new Runnable(){
				public void run() {
					for(int j = 0; j < 10; j++){
                        long id = next();
                        System.out.println(Long.toBinaryString(Long.valueOf("999" + id)));
                    }
				}
            };
            Thread t = new Thread(r);
            t.run();
        }
        System.out.println("101100010111101000111101010111100000011111000000000010010".length());*//*
        Long id1 = next();
        System.out.println(id1);
        System.out.println(Long.toBinaryString(id1));
        System.out.println(String.valueOf(Long.toBinaryString(id1)).length());
    }*/

}
