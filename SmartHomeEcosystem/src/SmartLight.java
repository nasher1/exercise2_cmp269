public class SmartLight extends SmartDevice implements Adjustable {

    private int brightness;  // 0-100

    public SmartLight(String deviceName) {
        super(deviceName);
        this.brightness = 0;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            activeDevicesCount++;
            System.out.println(deviceName + " Light is now ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            activeDevicesCount--;
            System.out.println(deviceName + " Light is now OFF.");
        }
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println("Cannot adjust: Device is OFF.");
            return;
        }

        if (level >= 0 && level <= 100) {
            brightness = level;
            System.out.println(deviceName + " brightness set to " + brightness);
        } else {
            System.out.println("Invalid brightness level.");
        }
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println(deviceName + ": Checking LED health...");
    }
}