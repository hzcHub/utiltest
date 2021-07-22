package com.hh.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ZkTest {


     ZooKeeper zkclient;
    // String connectString = "192.168.1.104:2181,192.168.1.105:2181,192.168.1.106:2181";
     String connectString = "192.168.1.108:2181";
     int sessionTimeout = 5000;

    @Test
    public void cli() throws IOException {

        zkclient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    @Test
    public  void get() throws InterruptedException, KeeperException, IOException {
        zkclient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children = null;
                try {
                    children = zkclient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("children = " + children);
                System.out.println("size = " + children.size());
            }
        });

        Thread.sleep(Long.MAX_VALUE);
    }


    @Test
    public  void delete() throws InterruptedException, KeeperException, IOException {
        zkclient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children = null;
                try {

                    children = zkclient.getChildren("/", true);

                    System.out.println("children = " + children);
                    System.out.println("size = " + children.size());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        zkclient.delete("/", 0, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println(rc);//0表示删除成功
                System.out.println(path);//节点路径
                System.out.println(ctx);//上下文信息
            }
        },"ctx");

    }

    @Test
    public void create() throws IOException, InterruptedException, KeeperException {
        zkclient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        String s = zkclient.create("/node2", "改故障工单列表".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("s = " + s);

    }
    @Test
    public void exist() throws IOException, InterruptedException, KeeperException {
        zkclient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        Stat exists = zkclient.exists("/node1", false);
        System.out.println("exists = " + exists);
    }


}
