# README

[![Build and Push](https://github.com/Yumapon/SpringTaskApp/actions/workflows/maven.yml/badge.svg)](https://github.com/Yumapon/SpringTaskApp/actions/workflows/maven.yml)

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

## Dockerfileの軽量化

