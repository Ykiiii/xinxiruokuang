package com.company;

import java.net.UnknownHostException;
import java.util.Collection;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.list.Categories;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;

public class getServerList {
	private static String host ="127.0.0.1";//172.16.4.11
	private static String domain="127.0.0.1";
	private static String user="Yk";
	private static String password="";
	public static void main(String[] args)
			throws IllegalArgumentException, UnknownHostException, JIException {

		ServerList serverList = new ServerList(host,user,password,domain);
		System.out.println("Hello World--2--");

		Collection<ClassDetails> classDetails = serverList.listServersWithDetails(new Category[]{
				Categories.OPCDAServer10, Categories.OPCDAServer20,
				Categories.OPCDAServer30}, new Category[]{});;
		System.out.println("Hello World--3--");

		for (ClassDetails cds : classDetails) {
			System.out.println(cds.getProgId() );
		}

		//创建server对象属性
		final ConnectionInformation ci = new ConnectionInformation();

		/*server端名称，需要更改*/
		ci.setClsid(serverList.getClsIdFromProgId ("Knight.OPC.Server.Demo"));  //ICONICS.SimulatorOPCDA.2   Knight.OPC.Server.Demo

		//连接sever端

		System.out.println(serverList.getClsIdFromProgId ("Knight.OPC.Server.Demo"));




		//showAllOPCServer(serverList);

	}

}
