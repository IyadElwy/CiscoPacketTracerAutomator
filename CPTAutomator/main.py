import time

import pyautogui
import csv
import subprocess
from time import sleep

endDevices = []
networkDevices = []
network = []
config = []

locationOfNetworkDevice = None

allDevs = [["endDevices", endDevices], ["networkDevices", networkDevices],
           ["network", network], ["config", config]]

for device in allDevs:
    with open(rf"C:\Users\iyade\IdeaProjects\Java\PersonalProjects\CPCAutomator"
              fr"\src\inputData\{device[0]}.csv") as file:
        for r in csv.reader(file):
            device[1].append(r)

subprocess.Popen([r"C:\Program Files\Cisco Packet Tracer "
                  r"7.3.0\bin\PacketTracer7.exe"])

sleep(9)

x_Network_Devices_Box, y_Network_Devices_Box = (18, 1004)

x_EndDevices_Box, y_End_Devices_Box = (x_Network_Devices_Box + 30,
                                       y_Network_Devices_Box + 1)

for networkDevice in networkDevices:
    pyautogui.moveTo(x_Network_Devices_Box, y_Network_Devices_Box)
    pyautogui.click()
    pyautogui.moveTo(55, 1053)
    pyautogui.click()
    x_Switch_Box, y_Switch_Box = (228, 1004)
    pyautogui.moveTo(x_Switch_Box, y_Switch_Box)
    pyautogui.dragTo(777, 510)
    locationOfNetworkDevice = (777, 510)

time.sleep(0.1)

shifter_x = 0
shifter_Y = -100
pc_locations = []
for endDevice in endDevices:

    if endDevice[0][1:4] == "pc":
        x_Pc_Box, y_Pc_Box = (231, 1006)
        pyautogui.moveTo(x_EndDevices_Box, y_End_Devices_Box)
        pyautogui.click()
        pyautogui.moveTo(x_Pc_Box, y_Pc_Box)
        pyautogui.dragTo(locationOfNetworkDevice[0] + shifter_x,
                         locationOfNetworkDevice[1] + shifter_Y)
        pc_locations.append((locationOfNetworkDevice[0] + shifter_x,
                             locationOfNetworkDevice[1] + shifter_Y))
        shifter_x += 50

for i, endDevice in enumerate(endDevices):
    time.sleep(0.1)
    pyautogui.moveTo(126, 998)
    pyautogui.click()
    pyautogui.moveTo(240, 1007)
    pyautogui.click()
    pyautogui.moveTo(pc_locations[i][0], pc_locations[i][1])
    pyautogui.click()
    pyautogui.dragTo(locationOfNetworkDevice[0], locationOfNetworkDevice[1])
    pyautogui.click()

    pyautogui.moveTo(pc_locations[i][0], pc_locations[i][1])
    pyautogui.click()
    pyautogui.moveTo(818, 284)
    pyautogui.click()
    pyautogui.moveTo(776, 356)
    pyautogui.click()
    pyautogui.moveTo(888, 420)
    pyautogui.click()
    pyautogui.typewrite(endDevice[2][1:])
    pyautogui.press("enter")
    pyautogui.moveTo(1215, 248)
    pyautogui.click()

    print(endDevice[2][1:])

for device in allDevs:
    file = open(
        rf"C:\Users\iyade\IdeaProjects\Java\PersonalProjects\CPCAutomator" \
        fr"\src\inputData\{device[0]}.csv", "r+")
    file.truncate(0)
    file.close()
