# Wmall电商网站 （Wmall 后台接口实现）

#### 此项目利用SSM框架，使用了一下技术
1. nginx 负载均衡，图片上传图片服务器
2. mybatis分页插件使用
3. 支付宝真实对接
4. ftp服务器连接
5. 使用mybatis插件进行自动生成dao pojo层


### 商品接口（前台）(product)
#### 根据关键字获取商品（关键字，分类id，第几页，页面大小，排序方式）(list.do)
1. 判断关键字和分类id为不为空
2. 数据库有没有此分类信息
3. 如果此分类存不在并且关键字为空，返回空的结果集
4. 分类存在，递归得到此分类和它所有子分类的集合
5. 初始化分页
6. 判断是增序还是降序
7. 服用sql语句，根据分类id或者关键字模糊查询，返回结果集
8. 把结果整合成ProductListVo对象
9. 返回分页后的集合

#### 获取商品详情 (detail.do)
1. 传入的产品id合法不合法
2. 根据id查询相应的产品
3. 把产品整合成详情vo对象，带ImageHost,格式化后时间
4. 返回此对象

### 商品接口（后台）(manage/product/)
#### 添加或者更新产品 (save.do)

1. 判断是不是管理员
2. 如果是调用增加或者更新商品接口
3. 增加或者更新商品接口
4. 设置产品的主图
5. 更新产品传入的有商品的id
6. 有id更新产品
7. 无id新增产品
#### 更新销售状态 (set_sale_status.do)

1. 参数（商品id，需要更改的状态）
2. 判断参数合法性
3. 修改产品销售状态
#### 获取商品详细信息 (detail.do)

1. 判断权限
2. 判断有没有此产品
3. 组装productDetailVo对象
4. 返回
#### 后台查询分页商品list (list.do)

1. 判断权限
2. startPage-start
3. 查询所有的商品
4. 分页收尾
#### 根据关键字还是id进行查询 (search.do)

1. 判断权限
2. startPage-start
3. 复用sql语句，查询商品id，或者名字查询所有的商品
4. 分页收尾

#### 上传到upload然后放到ftp服务器 (upload.do)

1. 判断权限
2. 获取upload文件夹路径
3. 传入文件和路径开始上传
4. 获取上传文件的原始名，扩展名，设置一个唯一的文件上传名
5. 判断upload是否存在，不存在创建
6. 文件上传
7. 把目标文件上传到FTP服务器
8. 删除upload文件下的文件
9. 把上传后的文件返回

#### 富文本上传 (richtext_img_upload.do)

1. 管理员登陆
2. 富文本中对于返回值有自己的要求，我们使用的是simditor所以按照simditor的要求进行返回
3. 传入文件和路径开始上传
4. 获取上传文件的原始名，扩展名，设置一个唯一的文件上传名
5. 判断upload是否存在，不存在创建
6. 文件上传
7. 把目标文件上传到FTP服务器
8. 删除upload文件下的文件
9. 把上传后的文件名返回

### 购物车接口（前台）(cart)
【注】:每次操作都会重新发挥一个 cartVo对象.即list.do
#### 查询购物车(list.do)

1. 判断登陆情况
2. 根据userId查询此用户的购物车
3. 组合成cartVo，此vo包含 商品的vo对象，购物车的总价，是不是全部勾选了，图片发武器地址
4. 组合商品的vo对象，这时候应该对每个商品进行判断库存情况
5. 物品真实库存大于于购物车中的购买量充足商品的vo对象的limit属性success
6. 物品真实库存大于于购物车中的购买量充足商品的vo对象的limit属性fail
7. 库存中没那么多，必须连接数据库更新购物车中的库存
8. 判断够没够选，来计算最后的总价
9. 返回cartVo对象

#### 添加到购物车(add.do)

1. 判断登陆情况
2. 传进来的productId和count合法性
3. 购物车有没有这个物品
4. 有物品count相加，没物品插入数据
5. 返回此用户的cartvo对象

#### 更新购物车(update.do)

1. 判断登陆情况
2. 传进来的productId和count合法性
3. 根据userId，productId查询此购物记录
4. 此cart对象count重新赋值
5. 更新数据库信息
6. 返回更新后的cartVo对象

#### 删除购物车物品 (delete_product.do)
【注】:允许多物品删除，用逗号分隔开
1. 判断登陆情况
2. 把字符串用逗号分开，添加到集合中
3. 把需要删除物品的集合用sql集中删除
4. sql怎么写:利用foreach遍历物品id的列表进行删除

#### 购物车全选，全不选
#### 单个商品选择，取消选择复用的一个方法

1. 判断登陆情况
2. 根据productId传没传进来，进行指定物品选中，还是全选
3. 重新获取cartVO对象返回

#### 当前用户的购物车里面的产品数量（get_cart_product_count.do）

1. select IFNULL(sum(quantity),0) as count from mmall_cart where user_id = #{userId}

### 地址接口（前台）(shipping)

####  添加地址(add.do)

1. 判断用户
2. insert

#### 删除地址(del.do)

1. 判断登陆
2. 传入shippingId
3. 删除语句

#### 更新地址(update.do)

1. 判断登陆
2. 更新 where id and userid

#### 根据地址id,查询地址的详细信息(select.do)

1. 判断登陆
2. 查询
 
#### 获取用户的所有地址(list.do)

1. 判断登陆
2. 查询
3. 分页


### 订单接口(order)

#### 创建订单 (create.do) 
```
{
    "status": 0,
    "data": {
        "orderNo": 1485158223095,
        "payment": 2999.11,
        "paymentType": 1,
        "postage": 0,
        "status": 10,
        "paymentTime": null,
        "sendTime": null,
        "endTime": null,
        "closeTime": null,
        "createTime": 1485158223095,
        "orderItemVoList": [
            {
                "orderNo": 1485158223095,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": null
            }
        ],
        "shippingId": 5,
        "shippingVo": null
    }
}
```

1. 判断用户登陆状态
2. 传入shippingId,userId
3. 获取此用户的购物车所有商品信息
4. 把购物车里面的商品组装成一个orderItemVoList
    - 遍历所有购物车中信息
    - 根据购物车中商品的id进行查询此产品的product表
    - 校验此商品是不是销售状态
    - 校验此商品的库存是不是满足下单的数量
    - 符合条件生成orderItemVoList
5. 遍历orderItemVoList购物车每条购物记录算出总价
6. 生成订单(userId,shippingId,payment)
    - 生成随机的订单号
    - 订单的状态(初始未支付)
    - 支付方式
    - shippingId
    - 数据库插入此订单信息
7. 遍历刚刚获取的orderItemVoList，为里面每一个购物记录增加订单号
8. 批量插入，orderItemVoList购物中的每一个物品都得写入orderItem数据表之中
9. 购物中的每一个物品减少库存
10. 清空购物车
11. 把生成的订单和orderItemVoList，整合后发给前端

#### 取消订单(cancel.do)

1. 判断登陆状态
2. 传入userId，订单号
3. 如果是已付款无法取消订单
4. 把订单的status 设置成取消
5. 更新订单

#### 获取已经选中的购物车订单信息(get_order_cart_product.do)
```
{
    "status": 0,
    "data": {
        "orderItemVoList": [
            {
                "orderNo": null,
                "productId": 1,
                "productName": "iphone7",
                "productImage": "mmall/aa.jpg",
                "currentUnitPrice": 7999,
                "quantity": 10,
                "totalPrice": 79990,
                "createTime": ""
            }
        ],
        "imageHost": "http://image.qian.com/",
        "productTotalPrice": 79990
    }
}
```

1. 获取购物车list
2. 组合orderItemVoList对象
3. 添加总价钱，图片服务器地址等属性
4. 返回

#### 查看订单详情(detail.do)

1. 根据订单号或者此订单在order，orderItem表中的信息
2. order，orderItem表中的信息进行整合成orderVo
    - order里面取订单号，总价，支付类型，支付状态
    - 根据order里面的ShippingId取收件人地址和信息
    - orderItem获取订单物品的详细信息
3. 合成orderVo的展示对象返回给前端

#### 查看订单列表(list.do)
```
{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 3,
    "size": 3,
    "orderBy": null,
    "startRow": 1,
    "endRow": 3,
    "total": 16,
    "pages": 6,
    "list": [
      {
        "orderNo": 1485158676346,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "2017-02-11 12:27:18",
        "sendTime": "2017-02-11 12:27:18",
        "endTime": "2017-02-11 12:27:18",
        "closeTime": "2017-02-11 12:27:18",
        "createTime": "2017-01-23 16:04:36",
        "orderItemVoList": [
          {
            "orderNo": 1485158676346,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2017-01-23 16:04:36"
          }
        ],
        "imageHost": "http://img.happymmall.com/",
        "shippingId": 5,
        "receiverName": "geely",
        "shippingVo": null
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 2,
    "lastPage": 6,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6
    ]
  }
}
```
1. order表查数据
2. 整合成orderVo对象
    - 遍历每一个order
    - order中的订单号，查询符合此订单号中每个物品
    - 利用此订单号中每个物品，和order中部分信息整合成orderVo
    - 遍历的每一个order整合后的orderVo加入到orderVoList
3. 把orderVoList返回

#### 订单号，生成二维码(pay.do)

1. 获取二维码上传的路径
2. 传入订单号，路径，userId
3. 下单
   - 支付宝demo
   - 下单，上传二维码到服务器
