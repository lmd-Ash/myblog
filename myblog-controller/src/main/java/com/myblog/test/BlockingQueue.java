package com.myblog.test;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 *
 * @author lmd
 * @version 1.0.0
 * @date 2021/7/12
 */
public class BlockingQueue {
    /**
     * 队列容器
     */
    private Object[] tab;

    /**
     * 出队下标
     */
    private int takeIndex;

    /**
     * 入队下标
     */
    private int pubIndex;

    /**
     * 元素数量
     */
    private int size;

    /**
     * 可重入锁
     */
    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 读条件
     */
    private Condition notEmpty;

    /**
     * 写条件
     */
    private Condition notFull;

    public BlockingQueue(int tabCount) {
        if (tabCount <= 0) {
            throw new NullPointerException();
        }
        tab = new Object[tabCount];
        notEmpty = reentrantLock.newCondition();
        notFull = reentrantLock.newCondition();
    }

    public boolean offer(Object obj) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException();
        }
        // 获取锁
        reentrantLock.lock();
        try {
            // 队列已满
            while (size == tab.length) {
                System.out.println("队列已满");
                // 堵塞
                notFull.await();
            }
            tab[pubIndex] = obj;
            if (++pubIndex == tab.length) {
                pubIndex = 0;
            }
            size++;
            // 唤醒读线程
            notEmpty.signal();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // 唤醒读线程
            notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
        return false;
    }

    public Object take() {
        reentrantLock.lock();
        try {
            while (size == 0) {
                System.out.println("队列空了");
                // 堵塞
                notFull.await();
            }
            Object obj = tab[takeIndex];
            // 如果到了最后一个，则从头开始
            if (++takeIndex == tab.length) {
                takeIndex = 0;
            }
            size--;
            // 唤醒读线程
            notEmpty.signal();
            return obj;
        } catch (Exception e) {
            // 唤醒读线程
            notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
        return null;
    }


    public static void main(String[] args) {
        Random random = new Random(100);
        BlockingQueue blockingQueue = new BlockingQueue(5);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            int index = i;

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index);
                    blockingQueue.offer(index);
                    System.out.println("生产者生产了：" + index);
                }
            });

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index);
                    Object take = blockingQueue.take();
                    System.out.println("消费者消费了：" + take);
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}

