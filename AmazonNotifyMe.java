/**
 *  Problem Statement: "Notify Me" Feature for E-commerce Platform
    Design and implement a "Notify Me" feature for an e-commerce platform (like Amazon). 
    When a product goes out of stock, users should be able to subscribe for back-in-stock 
    notifications. Once the product is restocked, the system should automatically notify all 
    subscribed users, and then clear the subscriptions to avoid redundant notifications.
 */

import java.util.*;

interface IObservable {
    public void notifyAllUsers();
    public void unnotifyme(User user);
    public void notifyme(User user);
    String getUpdate();
}


class Product implements IObservable{
    HashSet<User> subscribedUsers;
    int quantity;
    String name;

    Product(String name,int quantity) {
        subscribedUsers = new HashSet<>();
        this.quantity = quantity;
        this.name = name;
    }
    

    @Override
    public void notifyme(User user){
        this.subscribedUsers.add(user);
    }

    public void unnotifyme(User user){
        this.subscribedUsers.remove(user);
    }

    public void notifyAllUsers(){
        for(User user: subscribedUsers) {
            user.sendNotification(this);
        }
        subscribedUsers.clear(); // delete all the user once notified;
    }

    public void addStock(int count){
         int oldQuantity = quantity;
        quantity += count;

        if(oldQuantity == 0 && quantity > 0){
            notifyAllUsers();
        }


        if(quantity <= 0) {
            System.out.println("Product is out of stock!");
        } else {
            System.out.println("Remaining stock is: "+ quantity);
        }
    }

    public String getUpdate(){
        return "Yayy! Item is in stock...";
    }

}

class User{
    String name;
    String email;
    
    INotification notification;

    User(String name, String email){
        this.name = name;
        this.email = email;
        if(email.length() != 0){
            notification = new emailNotification();
        } else {
            notification = new defaultNotification();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void sendNotification(IObservable observable){
        notification.sendNotification(this, observable.getUpdate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}


interface INotification{
    public void sendNotification(User user, String message);
}

class emailNotification implements INotification {

    @Override
    public void sendNotification(User user, String message) {
        System.out.println("Notification sent to " + user.getEmail() + " with :" + message);
    }
}

class defaultNotification implements INotification{
    @Override
    public void sendNotification(User user, String message){
        System.out.println("push notification :"+  message);
    }
}


class ProductManager{
    List<Product> products;

    ProductManager(){
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void setProductSubscribers(Product product, User user){
        product.notifyme(user);
    }

    public void updateProductStock(Product product,int amount){
        product.addStock(amount);
    }
}


public class AmazonNotifyMe {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Product p1 =  new Product("Iphone ", 1);
        Product p2 = new Product("Samsung", 2);

        productManager.addProduct(p1);
        productManager.addProduct(p2);

        User user1 = new User("Neel", "abc@gmail.com");
        User user2 = new User("Prerna", "def@gmail.com");

        productManager.updateProductStock(p2, -2);
        productManager.updateProductStock(p1, -1);

        productManager.setProductSubscribers(p1,user1);

        productManager.setProductSubscribers(p2, user2);
        productManager.setProductSubscribers(p1, user2);

        productManager.updateProductStock(p2, 1);
        productManager.updateProductStock(p1, 1);

    }
}
