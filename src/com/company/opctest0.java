package com.company;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.*;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.*;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.FlatBrowser;
import org.openscada.opc.lib.da.browser.Leaf;
import org.openscada.opc.lib.list.Categories;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;


public class opctest0 {
    /*IP地址和主机名称密码*/
    private static String host ="127.0.0.1";//172.16.4.11//IP 127.0.0.1
    private static String domain="127.0.0.1";//127.0.0.1
    private static String user="Yk";//computername
    private static String password="";//password

    private static final int PERIOD = 100;
    private static final int SLEEP = 2000;

    public static void main(String[] args)
            throws JIException, UnknownHostException, AlreadyConnectedException, NotConnectedException, DuplicateGroupException, AddFailedException, InterruptedException, DuplicateGroupException {

        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();



        ServerList serverList = new ServerList(host,user,password,domain);//获取服务器列表

//        System.out.println("Hello World!");//检测点
//        Collection<ClassDetails> classDetails = serverList.listServersWithDetails(new Category[] {
//                Categories.OPCDAServer10, Categories.OPCDAServer20,
//                Categories.OPCDAServer30 }, new Category[] {});
//        //打印IP地址中的server列表
//        System.out.println("---LIST SERVER---");
//        for (ClassDetails cds : classDetails) {
//            System.out.println(cds.getProgId() + "=" + cds.getDescription());
//        }

        ////////////////////////////////////////////////////////////////////////////////////////
        //创建server对象属性
        final ConnectionInformation ci = new ConnectionInformation();
        ci.setHost(host);
        ci.setUser(user);
        ci.setPassword(password);

        /*server端名称，需要更改*/
        ci.setClsid(serverList.getClsIdFromProgId ("Knight.OPC.Server.Demo"));  //ICONICS.SimulatorOPCDA.2   Knight.OPC.Server.Demo   Matrikon.OPC.Simulation.1

        //连接sever端
        System.out.println("---connect start---");//检测点2

        System.out.println(serverList.getClsIdFromProgId ("Knight.OPC.Server.Demo"));
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        Server server = new Server(ci, exec);//创建server
        System.out.println("---connect---");
        server.connect();//连接服务端
        System.out.println("---connect finish---");
//
//        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
//        Server server = new Server(ci, exec);//创建server
//        server.connect();//连接服务端

        //列举所有的Group和Item
        System.out.println("---LIST group&item---");
        dumpTree(server.getTreeBrowser().browse(),0);//组名
        dumpFlat(server.getFlatBrowser());//点名
        System.out.println("---LIST OVER---");
        server.disconnect();
        server.connect();//连接服务端
        System.out.println("---READ START---");
//        //同步访问Async20Access
//        final String itemID = " ";//
//        AccessBase access = new SyncAccess(server,1000);
//        access.addItem(itemID , new DataCallback() {
//            @Override
//            public void changed(Item item, ItemState itemState) {
//                System.out.println(itemState.getValue());
//                System.out.println("---wu\nla\nla\nla\nla\nla---");
//            }
//        });

        //异步访问Async20Access
//        final String itemID = "a.a.f";//
//        AccessBase access = new Async20Access(server,100,false);
//        access.addItem(itemID , new DataCallback() {
//            @Override
//            public void changed(Item item, ItemState itemState) {
//                System.out.println(">>>Asynchronized reag:value====" + itemState.toString());
//            }
//        });

        AccessBase access = new SyncAccess(server, 1*100);
        access.addItem("a.a.f", new DataCallback() {
            private int i;

            public void changed(Item item, ItemState itemstate) {
                System.out.println("[" + (++i) + "],ItemName:[" + item.getId()
                        + "],value:" + itemstate.getValue());
            }
        });

        access.bind();//start read
        Thread.sleep(10*100);//wait a minate
        access.unbind();//stop read

//        //报警订阅
//        AccessBase access = new Async20Access(server, PERIOD, false);
//        access.addItem("a.a.f", new DataCallback() {
//
//            private int count;
//
//            public void changed(Item item, ItemState itemstate) {
//                System.out.println("[" + (++count) + "],ItemName:["
//                        + item.getId() + "],value:" + itemstate.getValue());
//            }
//        });

//        access.bind();
//        Thread.sleep(SLEEP);
//        access.unbind();
//        server.dispose();

        System.out.println("---READ OVER---");

        //new Thread(new listserver());
        //按点名读点
//        Group group = server.addGroup("Group1");//创建group
//        group.setActive ( true );//激活点组
//        //创建点组及相关名称
//        String[] test2 = new String[]{"a.a.a","a.a.b","a.a.c"};//
//        for(String s : test2) {
//            //System.out.println(s);
//            Item item1 = group.addItem(s);
//            //获取点的数据
//            ItemState itemState = item1.read(true);
//            System.out.println(s + "-------" + itemState.toString());//itemState.toString()完整信息
//        }
        //单点读数
//        final String itemId="a.a.f";
//        Group group = server.addGroup("Group1");
//        Item item = group.addItem(itemId); //get item for writing
//
//        ItemState itemState = item.read(true);
//        System.out.println("<<< first read: " + itemState.toString());//getclass
//
//        System.out.println("Hello World!3");//检测点3
//        server.dispose();//关闭server对象
    }


    private static void dumpFlat(final FlatBrowser browser)
            throws IllegalArgumentException,UnknownHostException,JIException{
        for (String name : browser.browse()){
            System.out.println(name);
        }
    }

    private  static  void dumpTree(final Branch barnch, final int level){
        for (final Leaf leaf : barnch.getLeaves()){
            dumpLeaf(leaf,level);
        }
        for (final Branch subBranch : barnch.getBranches()){
            dumpBranch(subBranch, level + 1);
        }
    }

    private static String printTab(int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<level;i++){
            sb.append("\t");
        }
        return sb.toString();
    }

    private  static void dumpLeaf(final Leaf leaf,final int level){
        System.out.println(printTab(level) + "Leaf:" + leaf.getName() + ":" + leaf.getItemId());
    }

    private static void dumpBranch(final Branch branch,final int level){
        System.out.println(printTab(level) + "Branch:" + branch.getName());
    }


}

