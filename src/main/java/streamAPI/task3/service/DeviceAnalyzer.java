package streamAPI.task3.service;

import streamAPI.task3.model.Device;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceAnalyzer {
    private final List<Device> devices;

    public DeviceAnalyzer(List<Device> devices) {
        this.devices = List.copyOf(devices);
    }

    public List<Device> getAllDevices() {
        return devices;
    }

    public List<Device> getDevicesByColor(String color) {
        return devices.stream()
                .filter(d -> d.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Device> getDevicesByYear(int year) {
        return devices.stream()
                .filter(d -> d.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Device> getDevicesMoreExpensiveThan(double price) {
        return devices.stream()
                .filter(d -> d.getPrice() > price)
                .collect(Collectors.toList());
    }

    public List<Device> getDevicesByType(String type) {
        return devices.stream()
                .filter(d -> d.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Device> getDevicesInYearRange(int startYear, int endYear) {
        return devices.stream()
                .filter(d -> d.getYear() >= startYear && d.getYear() <= endYear)
                .collect(Collectors.toList());
    }
}