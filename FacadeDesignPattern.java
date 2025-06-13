import java.util.*;

class PowerSupply{
    public void providePower(){
        System.out.println("Power Supply: providing power...");
    }
}

class Memory{
    public void selfTest(){
        System.out.println("Memory: Self testing passed...");
    }
}

class CoolingSystem{
    public void startFans(){
        System.out.println("Cooling System: Staring fans....");
    }
}


class CPU{
    public void initialize() {
        System.out.println("CPU: Initializing CPU....");
    }
}

class HardDrive{
    public void spinup(){
        System.out.println("HardDrive: spining up hard drive....");
    }
}

class BIOS{
    public void boot(CPU cpu,Memory mem){
        System.out.println("BIOS: self tesing memory and starting CPU...");
        cpu.initialize();
        mem.selfTest();
    }
}

class OperatingSystem{
    public void load(){
        System.out.println("Operating System: loading into memory ...");
    }
}

// Facade
class ComputerFacade{
    private OperatingSystem os;
    private Memory memory;
    private CPU cpu;
    private BIOS bios;
    private CoolingSystem coolingSystem;
    private HardDrive hardDrive;
    private PowerSupply powerSupply;

    ComputerFacade(){
        cpu = new CPU();
        os = new OperatingSystem();
        memory = new Memory();
        bios = new BIOS();
        hardDrive = new HardDrive();
        powerSupply = new PowerSupply();
        coolingSystem = new CoolingSystem(); 
    }

    public void startComputer(){
        System.out.println("Computer Starting...");

        powerSupply.providePower();
        coolingSystem.startFans();
        bios.boot(cpu, memory);
        hardDrive.spinup();
        os.load();

        System.out.println("Computer Started successfully......");
    }
}

public class FacadeDesignPattern {
    public static void main(String[] args) {
        System.out.println("Behind the scenes of turning on of your computer: Facade Design Pattern");

        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startComputer();
    }
}
