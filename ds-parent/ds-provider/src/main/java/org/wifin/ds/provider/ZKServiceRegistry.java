package org.wifin.ds.provider;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKServiceRegistry implements ServiceRegistry {
	private final Logger logger = LoggerFactory.getLogger(ZKServiceRegistry.class);

	public static final String ZK_REGISTER_ROOT_PATH = "/wifin/registry";

	private final CountDownLatch counter = new CountDownLatch(1);

	private String zkAddress;

	@Override
	public void register(String resource) {

	}

	public static String createZKNode(ZooKeeper zk, String path, String data)
			throws KeeperException, InterruptedException {
		return createZKNode(zk, path, data.getBytes());
	}

	public static String createZKNode(ZooKeeper zk, String path, String data, String charset)
			throws KeeperException, InterruptedException, UnsupportedEncodingException {
		byte[] bytes = data.getBytes(charset);
		return createZKNode(zk, path, bytes);
	}

	public static String createZKNode(ZooKeeper zk, String path, byte[] data)
			throws KeeperException, InterruptedException {
		return zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
	}

}
