/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.  You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �ڷֲ�ʽϵͳ�У���Ҫ����ȫ��UID�ĳ��ϻ��ǱȽ϶�ģ�twitter��snowflake�������������ʵ��Ҳ���Ǻܼ򵥵ģ���ȥ������Ϣ�����Ĵ�����Ǻ��뼶ʱ��41λ+����ID
 * 10λ+����������12λ�� ���Ĵ���Ϊ��IdWorker�����ʵ�֣���ԭ��ṹ���£��ҷֱ���һ��0��ʾһλ���á��ָ���ֵ����ã� 0---0000000000 0000000000
 * 0000000000 0000000000 0 --- 00000 ---00000 ---0000000000 00
 * ��������ַ����У���һλΪδʹ�ã�ʵ����Ҳ����Ϊlong�ķ���λ������������41λΪ���뼶ʱ�䣬Ȼ��5λdatacenter��ʶλ��5λ����ID���������ʶ����ʵ����Ϊ�̱߳�ʶ����Ȼ��12
 * λ�ú����ڵĵ�ǰ�����ڵļ������������պ�64λ��Ϊһ��Long�͡�
 * �����ĺô��ǣ������ϰ���ʱ���������򣬲��������ֲ�ʽϵͳ�ڲ������ID��ײ����datacenter�ͻ���ID�����֣�������Ч�ʽϸߣ������ԣ�snowflakeÿ���ܹ�����26��ID
 * ���ң���ȫ������Ҫ��
 */
public class IdBitWorker  {
    private static final long startTimeStamp = 1464710400000L; //����һ����ʼʱ�� 2016-6-1 00:00:00

    private static final long workerIdBits = 5L;
    private static final long dataCenterIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private static final Random r = new Random();

    private final long workerId;
    private final long dataCenterId;
    private final long idEpoch;
    private long lastTimestamp = -1L;
    private long sequence = 0;

    public IdBitWorker() {
        this(startTimeStamp);
    }

    public IdBitWorker(long idEpoch) {
        this(r.nextInt((int) maxWorkerId), r.nextInt((int) maxDataCenterId), 0, idEpoch);
    }

    public IdBitWorker(long workerId, long dataCenterId, long sequence) {
        this(workerId, dataCenterId, sequence, startTimeStamp);
    }

    public IdBitWorker(long workerId, long dataCenterId, long sequence, long idEpoch) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
        this.idEpoch = idEpoch;

        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
        if (dataCenterId < 0 || dataCenterId > maxDataCenterId) {
            throw new IllegalArgumentException("dataCenterId is illegal: " + dataCenterId);
        }

        if (idEpoch >= timeGen()) {
            throw new IllegalArgumentException("idEpoch is illegal: " + idEpoch);
        }
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getTime() {
        return timeGen();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalArgumentException("Clock moved backwards.");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        long id = ((timestamp - idEpoch) << timestampLeftShift) | (dataCenterId <<
                dataCenterIdShift) | (workerId << workerIdShift) | sequence;
        return id;
    }

    public long getIdTimestamp(long id) {
        return idEpoch + (id >> timestampLeftShift);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdBitWorker{");
        sb.append("workerId=").append(workerId);
        sb.append(", dataCenterId=").append(dataCenterId);
        sb.append(", idEpoch=").append(idEpoch);
        sb.append(", lastTimestamp=").append(lastTimestamp);
        sb.append(", sequence=").append(sequence);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        IdBitWorker worker = new IdBitWorker();
        ExecutorService executor = Executors.newFixedThreadPool(8);

        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        Runnable run = () -> {
            worker.nextId();
            countDownLatch.countDown();
        };

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            executor.execute(run);
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - startTime);
        executor.shutdown();
    }
}