package proxy;

public class StaticProxyTest {

    public static void main(String[] args) {
        ClothFactory clothFactory = new NikeClothFactory();
        ClothFactory proxy = new ClothFactoryProxy(clothFactory);
        proxy.productCloth();
    }
}

// 接口
interface ClothFactory {
    void productCloth();
}

// 代理对象
class ClothFactoryProxy implements ClothFactory {

    private ClothFactory factory;

    public ClothFactoryProxy(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void productCloth() {
        System.out.println("代理对象做一些准备工作");
        factory.productCloth();
        System.out.println("代理对象做一些收尾工作");
    }
}

class NikeClothFactory implements ClothFactory {

    @Override
    public void productCloth() {
        System.out.println("Nike工厂做了一批运动服");
    }
}