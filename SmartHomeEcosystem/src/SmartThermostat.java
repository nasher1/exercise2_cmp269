public class SmartThermostat extends SmartDevice implements Adjustable {

    private int temperature;

    public SmartThermostat(String deviceName) {
        super(deviceName);
        this.temperature = 70;  // default temp
    }

    @Override
    public void turnOn() {
        System.out.println("HVAC System Starting...");
        if (!isOn) {
            isOn = true;
            activeDevicesCount++;
            System.out.println(deviceName + " Thermostat is now ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            activeDevicesCount--;
            System.out.println(deviceName + " Thermostat is now OFF.");
        }
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: Device is OFF.");
            return;
        }

        if (level >= 60 && level <= 80) {
            temperature = level;
            System.out.println(deviceName + " temperature set to " + temperature);
        } else {
            System.out.println("Temperature must be between 60 and 80.");
        }
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println(deviceName + ": Checking temperature sensors...");
    }
}