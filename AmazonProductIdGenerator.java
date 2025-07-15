import java.util.ArrayList;
import java.util.List;

/**
 * Amazon has various products and we need to generate an id
 *  for each item. And each item may have some formats for their id.
 *  Design a LLD classes and methods for this..
 * 
 * 
 * it is like, whenever we create any new product, the new Unique id will be generated for that
 * from our IdGenerator based on the provided format
 * 
 * we can dynamically change the format for the generator
 * 
 * usecase flow -
 * 1. client registers new Product.
 * 2. while creating new Product, for the id it will send a request to our service or call function generateId
 * 3. new id will be generated and stored in the product.
 * 4. client can set the format for the idGenerator
 */


class Productt{
    String id;
    String type;
    String name;
    int price;

    Productt(String type,String productName,int price){
        this.type = type;
        this.name = productName;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        System.out.println(id);
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

}

class ProductService{
    List<Productt> products;
    IdGenerator idGenerator;

    ProductService(){
        products = new ArrayList<>();
        idGenerator = new HashIdGenerator();
    }

    public void setIdGenerator(GeneratorType type){
        idGenerator = IdGeneratorFactory.getIdGenerator(type);
    }

    public Productt createProduct(String name,String type,int price){
        Productt newProduct = new Productt(name, type, price);
        // generate id
        String id = idGenerator.generateId(newProduct);
        newProduct.setId(id);
        // set id

        return newProduct;
    }
    
}

enum GeneratorType{
    HASH_BASED, TIMESTAMP_BASED, PRODUCT_TYPE_COUNTER_BASED;
}

interface  IdGenerator {

    public String generateId(Productt product);
    public GeneratorType getType();
    
}

class HashIdGenerator implements IdGenerator{


    @Override
    public String generateId(Productt product) {
        return Integer.toString(product.hashCode()); 
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.HASH_BASED;
    }
}

class ProductCounterIdGenerator implements IdGenerator{
    private static int COUNTER = 1;

    @Override
    public String generateId(Productt product) {
        
        return product.getType() + "_" + (COUNTER++); 
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.PRODUCT_TYPE_COUNTER_BASED;
    }
}

class IdGeneratorFactory{
    static public IdGenerator getIdGenerator(GeneratorType type){
        if(type == GeneratorType.HASH_BASED){
            return new HashIdGenerator();
        } else if (type == GeneratorType.PRODUCT_TYPE_COUNTER_BASED){
            return new ProductCounterIdGenerator();
        }
        return null;
    }
}

public class AmazonProductIdGenerator {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.setIdGenerator(GeneratorType.HASH_BASED);
        Productt p1 = productService.createProduct("phone", "Electronics", 1000);
        productService.setIdGenerator(GeneratorType.PRODUCT_TYPE_COUNTER_BASED);
        Productt p2 = productService.createProduct("samsoong", "Electronics", 20000);
        Productt p3 = productService.createProduct("appul", "Electronics", 20000);
    }
}
