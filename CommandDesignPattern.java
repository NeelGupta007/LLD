import java.util.*;


class Light {
    public void on() {
        System.out.println("Light turned On...");
    }

    public void off() {
        System.out.println("Light turned off...");
    }
}


class Fan {
    public void on() {
        System.out.println("Fan turned On...");
    }

    public void off() {
        System.out.println("Fan turned off...");
    }
}

interface Command{
    public void execute();
    public void undo();
}




class LightCommand implements Command{
    Light light;

    public LightCommand(Light l){
        this.light = l;
    }

    @Override
    public void execute(){
        System.out.println("Executing light command ...");
        light.on();
    }

    @Override
    public void undo(){
        System.out.println("Undo light command ...");
        light.off();
    }
}




class FanCommand implements Command{
    Fan fan;

    public FanCommand(Fan f){
        this.fan = f;
    }

    @Override
    public void execute(){
        System.out.println("Executing Fan command ...");
        fan.on();
    }

    @Override
    public void undo(){
        System.out.println("Undo Fan command ...");
        fan.off();
    }
}


class RemoteControl {
    List<Boolean> state;
    List<Command> buttons;

    RemoteControl(){
        buttons = new  ArrayList<>();
        state = new ArrayList<>();
    }

    public void addButtons(){
        buttons.add(new LightCommand(new Light()));
        state.add(false);
        buttons.add(new FanCommand(new Fan()));
        state.add(false);
    }


    public void setButtons(int index,Command command){
        if(index >= buttons.size()) return;
        buttons.set(index,command);
    }

    public void pressButton(int index){
        if(state.get(index) == false){
            buttons.get(index).execute();
        } else {
            buttons.get(index).undo();
        }
        state.set(index,!(state.get(index)));
    }
}

class CommandDesignPattern{
    public static void main(String[] args){
        System.out.println("Command Design Pattern!!!");

        RemoteControl rc = new RemoteControl();
        rc.addButtons();

        rc.pressButton(0);
        rc.pressButton(1);

        
        rc.pressButton(0);
        rc.pressButton(1);

    }
}