import java.util.*;

interface IChannel {
    void subscribe(ISubscriber ISubscriber);
    void unsubscribe(ISubscriber ISubscriber);
    void notifySubscribers();
}

interface ISubscriber {
    void update();
}

// concrete class of channel
class Channel implements IChannel{
    List<ISubscriber> subs;
    String latestVideo;

    public Channel(){
        subs = new ArrayList<>();
        latestVideo = "";
    }

    @Override
    public void subscribe(ISubscriber s) {
        if(subs.contains(s)) return;
        subs.add(s);
    }

    @Override
    public void unsubscribe(ISubscriber s){
        subs.remove(s);
    }

    @Override
    public void notifySubscribers(){
        for(ISubscriber sub: subs){
            sub.update();
        }
    }

    public void uploadVideo(String title){
        System.out.println("new video uploaded :" + title);
        latestVideo = title;
        notifySubscribers();
    }

    public String getLatestVideo(){
        return latestVideo;
    }
}


class Subscriber implements ISubscriber{
    Channel channel;
    String name;

    public Subscriber(String name,Channel ch) {
        this.name = name;
        this.channel = ch;
    }

    public void update(){
        System.out.println("Notification received by " + name + " : " + channel.getLatestVideo());
    }
}


class ObserverDesignPattern {
    public static void main(String[] args) {
        System.out.println("Observer Design Pattern Example");
        Channel channel = new Channel();
        ISubscriber sub1 = new Subscriber("Neel", channel);
        ISubscriber sub2 = new Subscriber("Nitin", channel);
        ISubscriber sub3 = new Subscriber("Prerna", channel);

        channel.subscribe(sub1);
        channel.subscribe(sub2);
        channel.subscribe(sub3);

        channel.uploadVideo("first video.");

        channel.unsubscribe(sub2);

        channel.uploadVideo("second video");

    }
}