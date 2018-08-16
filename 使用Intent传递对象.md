## 使用Intent传递对象
1. Serializable方式 ： 简单、效率低
- Serializable是序列化的意思，表示将一个对象转换成可存储或可传输的状态。
- 序列化后的对象可以在网络上进行传输，也可以存储到本地。
- 序列化的方法：让一个类去实现Serializable这个接口即可。
```
将Person类序列化：

public class Person implements Serializable {

    private String name;

    private int age;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}

在FirstActivity中的写法:

Person person = new Person();
person.setName("Tom");
person.setAge(20);
Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
intent.putExtra("person_data",person);
startActivity(intent);

在SecondActivity中获取这个对象：

Perosn person = (Person) getIntent.getSerializableExtra("perosn_data");
```
2. Parcelable方式 ：推荐使用
- Parcelable方式实现原理是将一个完整的对象进行分解，而分解后的每一部分都是Intent所支持的数据类型，这样也就实现传递对象的功能了。
```
将Person类实现Parcelable接口：

public class Person implements Parcelable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);//写出name
        parcel.writeInt(age);//写出age
    }
    
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel parcel) {
            Person person = new Person();
            //注意这里读出的顺序一定要和刚才写出的顺序完全相同
            person.name = parcel.readString();//读出name
            person.age = parcel.readInt();//读出age
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    
}

在FirstActivity中的写法:同上

在SecondActivity中获取这个对象：

Perosn person = (Person) getIntent.getParcelableExtra("perosn_data");
```