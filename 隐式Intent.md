Intent隐式启动：
- 打电话：
```java
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:10086"));
    startActivity(intent);
```
- 拨号：
```java
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:10086"));
    startActivity(intent);
```
