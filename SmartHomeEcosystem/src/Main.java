import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        SmartLight livingRoom = new SmartLight("Living Room");
        SmartLight kitchen = new SmartLight("Kitchen");
        SmartThermostat hallway = new SmartThermostat("Hallway");

        homeHub.add(livingRoom);
        homeHub.add(kitchen);
        homeHub.add(hallway);

        // Logic Test
        livingRoom.turnOn();
        hallway.turnOn();

        kitchen.setLevel(75);  // should fail because it's OFF

        System.out.println("Active Devices: " + SmartDevice.activeDevicesCount);

        // Polymorphism
        for (SmartDevice device : homeHub) {
            device.performSelfDiagnostic();
        }
    }
}