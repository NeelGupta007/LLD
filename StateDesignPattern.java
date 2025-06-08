import java.nio.channels.Pipe.SourceChannel;
import java.util.*;


// implementing traffic light states

// interface of state
interface State{
    public void nextState(TrafficLightManager manager);
    public String getColor();
}

// concrete states

class RedLight implements State{
    @Override
    public void nextState(TrafficLightManager manager){
        System.out.println("Traffic Signal Changes:  Red to Green..... You can move!");
        manager.setState(new GreenLight());
    }

    @Override
    public String getColor(){
        return "Red Light..";
    }
}

class GreenLight implements State{
    @Override
    public void nextState(TrafficLightManager manager){
        System.out.println("Traffic Signal Changes:  Green to Yellow..... Slow down!");
        manager.setState(new YellowLight());
    }

    @Override
    public String getColor(){
        return "Green Light..";
    }
}

class YellowLight implements State{
    @Override
    public void nextState(TrafficLightManager manager){
        System.out.println("Traffic Signal Changes:  Yellow to Red..... Stop!");
        manager.setState(new RedLight());
    }

    @Override
    public String getColor(){
        return "Yellow Light..";
    }
}


// concrete class for TrafficLightManager
class TrafficLightManager{
    protected State currentState;

    public TrafficLightManager(){
        currentState = new RedLight();
    }

    public void setState(State state){
        this.currentState = state;
    }

    public void changeToNextState(){
        currentState.nextState(this);
    }

    public String getColor(){
        return currentState.getColor();
    }
}





// client code
class StateDesignPattern{
    public static void main(String[] args) {
        System.out.println("State Design Pattern!");

        TrafficLightManager manager = new TrafficLightManager();
        manager.changeToNextState();
        System.out.println(manager.getColor());
        manager.changeToNextState();
        System.out.println(manager.getColor());
        manager.changeToNextState();
        System.out.println(manager.getColor());
        manager.changeToNextState();
        System.out.println(manager.getColor());
    }
}