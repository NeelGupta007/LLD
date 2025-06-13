import java.util.*;

// Strategy interface
interface Flyable{
    public void fly();
}

interface  Talkable {
    public void talk();
}

interface  Walkable{
    public void walk();
}

// concrete strategy class

class NormalTalk implements Talkable{
    @Override
    public void talk() {
        System.out.println("Entity can talk normally!");
    }
}

class NoTalk  implements Talkable {
    @Override
    public void talk(){
        System.out.println("Entity can't talk!");
    }
}



class NormalWalk implements Walkable{
    @Override
    public void walk() {
        System.out.println("Entity can walk normally!");
    }
}

class NoWalk  implements Walkable {
    @Override
    public void walk(){
        System.out.println("Entity can't walk!");
    }
}




class NormalFly implements Flyable{
    @Override
    public void fly() {
        System.out.println("Entity can fly normally!");
    }
}

class NoFly  implements Flyable {
    @Override
    public void fly(){
        System.out.println("Entity can't fly!");
    }
}


// entity

interface RobotInterface {

    public void projection();
}

class FlyableRobot implements RobotInterface{
    public Flyable f;
    public Talkable t;
    public Walkable w;

    FlyableRobot(Flyable f,Talkable t,Walkable w){
        this.f = f;
        this.t = t;
        this.w = w;
    }

    public void projection(){
        System.out.println("this is flyable robot projection");
    }

    public void walk(){
        this.w.walk();
    }

    public void talk() {
        this.t.talk();
    }

    public void fly(){
        this.f.fly();
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        System.out.println("Strategy Design Pattern....");

        Flyable fly = new NormalFly();
        Talkable talk = new NoTalk();
        Walkable walk = new NormalWalk();
        
        FlyableRobot flyableRobot = new FlyableRobot(fly, talk, walk);
        flyableRobot.walk();
        flyableRobot.talk();
        flyableRobot.fly();
    }
}
