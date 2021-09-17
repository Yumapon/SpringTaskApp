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
