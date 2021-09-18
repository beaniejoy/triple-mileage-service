
<img src="https://img.shields.io/badge/author-hanbin-yellowgreen00"/>

# Triple Mileage Service

트리플여행자 클럽 마일리지 서비스

## Specification
- Java 11
- Gradle 7.1
- MySQL 5.7.34
- docker
- kafka
- redis

## Run Application

```shell
$ docker-compose up --build
```

## Test Case

장소에 대한 테스트 데이터 2개로 설정하였습니다.  
장소 ID는 flyway에 등록이 된 내용이기에 아래 제시해드린 해당 테스트 장소 ID로 테스트진행하셔야 합니다.
```json
[
  {"place":  "bca43723-17cd-11ec-9445-0242ac120002"},
  {"place":  "7ef07ee1-1834-11ec-a95a-0242ac150002"}
]
```
사용자에 대한 테스트 데이터는 3개로 진행하였습니다.
```json
[
  {"user": "2c2a45ad-1705-11ec-b4d9-0242ac1a0002"},
  {"user": "f8c7a574-17ce-11ec-9445-0242ac120002"},
  {"user": "2be6eae3-17cf-11ec-9445-0242ac120002"}
]
```

테스트를 위한 케이스는 action 별로 `/testcase` 디렉토리에 저장해 두었습니다. 

## Description

프로젝트 설명에 대한 부분은 WIKI에 기록했습니다.  
[GO WIKI](https://github.com/beaniejoy/triple-mileage-service/wiki)