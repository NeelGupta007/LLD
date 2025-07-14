

abstract class Handler {
    public Handler nextHandler;

    public void setHandler(Handler h){
        this.nextHandler = h;
    }
    abstract public void dispense(int amount);

}


class ThousandRupeesHandler extends Handler{
    private int notes;
    ThousandRupeesHandler(int notes){
        this.nextHandler = null;
        this.notes = notes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount/1000;

        if(notesNeeded > notes){
            notesNeeded = notes;
            notes = 0;
        } else {
            notes -= notesNeeded;
        }
        
        System.out.println("Dispensing 1000 notes: " + notesNeeded);
        if(notesNeeded * 1000 == amount){   
            return;
        }

        int remAmount = amount - notesNeeded*1000;

        if(nextHandler != null){
            nextHandler.dispense(remAmount);
        }else{
            System.out.println("Request for this amount cannot be fullfilled,  " + remAmount);
        }
    }
}


class FiveHundredHandler extends Handler{
    int notes;

    FiveHundredHandler(int notes){
        this.nextHandler = null;
        this.notes = notes;
    }


    @Override
    public void dispense(int amount) {
        int notesNeeded = amount/500;

        if(notesNeeded > notes){
            notesNeeded = notes;
            notes = 0;
        } else {
            notes -= notesNeeded;
        }

        
        System.out.println("Dispensing 500 notes: " + notesNeeded);

        if(notesNeeded * 500 == amount){
            return;
        }

        int remAmount = amount - notesNeeded*500;

        if(nextHandler != null){
            nextHandler.dispense(remAmount);
        }else{
            System.out.println("Request for this amount cannot be fullfilled,  " + remAmount);
        }
    }

}

// client
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler h1 = new ThousandRupeesHandler(1);
        Handler h2 = new FiveHundredHandler(2);
        h1.setHandler(h2);

        h1.dispense(3000);
    }
}
