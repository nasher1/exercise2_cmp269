public abstract class SmartDevice implements Powerable {

    protected String deviceName;
    protected boolean isOn;

    public static int activeDevicesCount = 0;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.isOn = false;  // default OFF
    }

    public abstract void performSelfDiagnostic();
}