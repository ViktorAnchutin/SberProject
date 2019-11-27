### ATM events processing study project
![Image project](https://github.com/ViktorAnchutin/SberProject/blob/master/img/prj.png)
Agent : https://github.com/ViktorAnchutin/Agent

Producer : https://github.com/ViktorAnchutin/Producer

### The task
There is a set of devices. Each device has id , name , a set of Component components and status (normal, warning, error).

Component has id , name, status (Boolean). Status true means that the component is working properly, and false means that the component is broken.

The system receives a set of Event events. Events can be of different types ErrorEvent, RestoreEvent.

The program processes each message from this queue sequentially. The ErrorEvent indicates that the componentId component of deviceId is broken. The RestoreEvent event indicates that the componentId component of the deviceId device has restored its work. 

If all components of the device are working, the device has status "Normal".

If at least one component of the device does not work, the device should be in state "warning".

If more than half of the device components do not work, the device is in Error mode.

 
When changing the state of a device (Device), write the following in the log
“Device id =?, Name =? changed his condition to? ”


### Event processing with Strategy design pattern
Event processing algorithm is selected depending on the type of events.
The business logic for selecting a specific event processing implementation can be found in the "ProcessEventStrategyFactory" class.

![Image alt](https://github.com/ViktorAnchutin/SberProject/blob/SpringJdbcTemplate/img/uml.jpg)  
