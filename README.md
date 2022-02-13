# README

[![Build and Push](https://github.com/Yumapon/SpringTaskApp/actions/workflows/maven.yml/badge.svg)](https://github.com/Yumapon/SpringTaskApp/actions/workflows/maven.yml)

ReadOnlyのApplication

本当はこいつでDBにアクセスする際はReadOnlyのuserでアクセスさせたい
[x-ray](https://docs.aws.amazon.com/ja_jp/xray/latest/devguide/xray-console-sampling.html)

## App展開コマンド

```sh
#SpringTaskAppとxrayについては事前にimageのビルドが必要

#Appを動かすNW空間を作成
docker network create taskapp

#dbはあらかじめ動いているものとする。
#もしコンテナ環境でDBを実行している場合は以下を実行
docker network connect <dbコンテナ>

#taskappを展開
#環境変数は事前にexportコマンドで定義しておいてください
docker container run -p 8080:8080 \         
-e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} \
-e SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME} \
-e SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
-e SPRING_DATASOURCE_DRIVERCLASSNAME=${SPRING_DATASOURCE_DRIVERCLASSNAME} \
-e AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} \
-e AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} \
--net=taskapp \
--name taskapp1 \
taskapp:latest

#opa起動
docker run -it --rm -p 8181:8181 openpolicyagent/opa run --server --addr :8181 --net=taskapp

#x-ray起動
docker container run \
--attach STDOUT \
--name xray \
--net=taskappreadnetwork \
-p 2000:2000/udp \
-e AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} \
-e AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} \
xray:latest
```

## secretsに設定必要な項目

1. DOCKERHUB_TOKEN
2. DOCKERHUB_USERNAME
3. GIT_ACCESS_TOKEN （repo権限付与）

## ci回す流れ

```sh
git add .

git commit -m "example"

git tag v1.0.0

git push origin v1.0.0

```

## Prometheusにメトリクスを投げる設定を追加

* 参考情報

    [Spring公式ドキュメント](https://spring.pleiades.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics.export.prometheus)

* 必要なライブラリ

    1. spring-metrics
    2. simpleclient_common
    3. spring-boot-starter-actuator
    4. micrometer-registry-prometheus

* application.ymlの設定

    以下内容を追記

    ```yaml
        management:
        endpoints:
            web:
            exposure:
                include: "prometheus, health"
    ```

* prometheus側の設定

```yaml
```

<<<<<<< HEAD
## Dockerfileの軽量化
=======
# OracleDB 12c Enterprise Edition 構築手順

## ~~新しくDockerMachineを作成する~~(Diskが足りないため、docker-machineは分けない)

```sh
#docker-machineの作成
docker-machine create oracledb

#docker-machineの切り替え
eval $(docker-machine env oracledb)

#docker-machineにssh
docker-machine ssh oracledb
```

## oracedbコンテナの実行とコンテナへのログイン

```sh
#volumeの作成
docker volume create oracledbvolume
#コンテナの生成、実行
docker run --name oracledb -d -p 1521:1521 -p 5500:5500 -v oracledbvolume:/ORCL store/oracle/database-enterprise:12.2.0.1
#作成したコンテナにログイン
docker exec -it oracledb /bin/bash
```

## OracleDBへのログインとPDB作成

```sh
#システムとしてoracleDBのCDBにログイン
sqlplus / AS SYSDBA
```

```sql
#PDBのシードの場所を確認
alter session set container=PDB$SEED;
select file_name from dba_data_files;

#CDBに再接続
conn / as sysdba;

#PDBのデータを格納するフォルダを作成
!mkdir /u02/app/oracle/oradata/ORCL/taskapp

#PDBの作成
CREATE PLUGGABLE DATABASE TASKAPPDATABASE ADMIN USER taskapp_admin IDENTIFIED BY password FILE_NAME_CONVERT = ('/u02/app/oracle/oradata/ORCL/pdbseed/','/u02/app/oracle/oradata/ORCL/taskapp/');

#DBFileの確認(何かしらファイルが作成されていることを確認)
! ls -l /u02/app/oracle/oradata/ORCL/taskapp/

＃PDBのリストも確認
show pdbs

＃PDBをMOUNT状態から起動する
ALTER PLUGGABLE DATABASE TASKAPPDATABASE OPEN;

＃PDBのリストからREAD WRITEにMODEが変わっていることを確認
show pdbs

#PLUGGABLE DATABASE の自動起動設定を行う
#※現在起動している状態のPDBを記憶し、CDB起動時にその状態にする設定です
alter pluggable database all save state;
```

## PDBへログインし、ユーザが作成されていることを確認

```sh
#まずはCDBにログイン
sqlplus / AS SYSDBA
```

```sql
#CDBにログインできたことを確認(接続先がCDB$ROOTならOK)
show con_name

#接続先DBを変更する
alter session set container=TASKAPPDATABASE;

#接続先が切り替わったかを確認(接続先がTASKAPPDATABASEならOK)
show con_name

#ユーザ一覧の取得(CREATE DATABASE文で作成したTASKAPP_ADMINがあればOK)
SELECT * FROM all_users;

#ユーザに権限を付与
grant create session to TASKAPP_ADMIN;
grant resource to TASKAPP_ADMIN;
grant unlimited tablespace to TASKAPP_ADMIN;

#権限の確認
SELECT grantee, privilege from dba_sys_privs where grantee = 'TASKAPP_ADMIN';
```

## 先ほど作成したユーザでPDBにログインする

```sh
#接続するインスタンス名を特定する
lsnrctl services
```

＃こんな感じで作成される
Service "taskappdatabase.localdomain" has 1 instance(s).
  Instance "ORCLCDB", status READY, has 1 handler(s) for this service...
    Handler(s):
      "DEDICATED" established:6 refused:0 state:ready
         LOCAL SERVER

```sh
#ログイン
sqlplus TASKAPP_ADMIN/password@localhost:1521/taskappdatabase.localdomain
```

## PDBにテーブルを作成する(上記手順でログインしたまま)

* TASK_LIST

```DDL
CREATE TABLE TASK_LIST
(
    NUM VARCHAR2(500) NOT NULL,
    DEADLINE DATE,
    NAME VARCHAR2(200) NOT NULL,
    CONTENT VARCHAR2(2000),
    CLIENT VARCHAR2(100),
    PRIMARY KEY (NUM)
);


INSERT INTO TASK_LIST (num, deadline, name, content, client) VALUES (100, DATE '2005-06-07', '丘本悠真', 'testetstテスト', '田中慎吾');
select
　　convert('丘本','AL32UTF8')
　from dual;
```

* USER_ID

```DDL
CREATE TABLE USER_ID
(
    ID NUMBER(11) NOT NULL,
    PASSWORD VARCHAR2(20),
    PRIMARY KEY (ID)
);
```

## ログイン用データも作成

```sql
INSERT INTO USER_ID (ID, PASSWORD) VALUES(100, 'password');
```

## 接続設定 ※jdbcはojdbc7を使用

|||
| ------- | --------------------------- |
| JDBC Driver | oracle.jdbc.OracleDriver |
| JDBC　タイプ | Type4 |
| 接続文字列(s) | jdbc:oracle:thin:@localhost:1521/taskappdatabase.localdomain |
| 接続ユーザ | TASKAPP_ADMIN |
| 接続パスワード | password |
&#13;&#10;

## 検証用データ

```sql
CREATE PLUGGABLE DATABASE TESTPDB ADMIN USER testuser IDENTIFIED BY password FILE_NAME_CONVERT = ('/u02/app/oracle/oradata/ORCL/pdbseed/','/u02/app/oracle/oradata/ORCL/testpdb/');

ALTER PLUGGABLE DATABASE TESTPDB OPEN;

alter session set container=TESTPDB;

grant create session to testuser;
grant resource to testuser;
grant unlimited tablespace to testuser;
```
