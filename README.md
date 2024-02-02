## Buildin endpoints
- **GET** ``/hello`` 查看内建的接口是否正常工作
- **POST** ``/endpoints`` 用于创建动态endpoint
- **DELETE** ``/endpoints/{id}`` 用于删除动态创建的endpoint，id有后端分配随机uuid

## 原理
- 通过``WebFilter``识别是否为``isDynamicRoute``，
  - 如果是的话触发自定义流程
  - 如果不是的话继续原先的``WebFilterChain``，目的是不影响内建的endpoints
- 动态创建的Endpoint保存在``DynamicRouteStore``中

## 测试
1. 创建动态接口``/test``
```shell
curl --request POST \
  --url http://localhost:8080/endpoints2 \
  --header 'content-type: application/json' \
  --data '{
    "path":"/test",
    "response": "test-response"
}'
```
2. 调用动态接口``/test``
```shell
curl --request GET --url http://localhost:8080/test
```
3. 删除动态接口``/test``(id在测试1中获得)
```shell
curl --request DELETE --url http://localhost:8080/endpoints/<replaced-with-your-uuid>
```
4. 确认动态接口删除成功，再运行测试2，会**404**
