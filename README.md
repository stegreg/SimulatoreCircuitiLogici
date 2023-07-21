# SimulatoreCircuitiLogici

Simple logic circuit simulator, written in Java, as a final project for a Software Engineering course.

## Usage
The software uses a command line interface. In order to define and simulate a circuit, you have to write
a sequence of commands, each of which is fully documented. The documentation can be viewed
by using the 'help' command.

### Example with command line
For example, a simple AND gate with its input and output signals can be defined with the following commands:<br/>
```
>> def gate MyAnd and
>> def signal MyInputSignal0
>> def signal MyInputSignal1
>> def signal MyOutputSignal
>> link MyInputSignal0 MyAnd I0
>> link MyInputSignal1 MyAnd I1
>> link MyOutputSignal MyAnd O0
```
The first line declares an and gate, named "MyAnd". The following three lines declare the three signals (two inputs and one ouput) that will
be attached to the gate. The last three lines actually make the linking between the and gate and the signals.

In order to simulate and print the results, the following commands can be used:
```
>> simulate 5
>> print track MyOutputSignal
```
The first command simulates the defined circuit for the first 5 time units. The second one plots the specified signal state against time.
The console will output the following:
```
                0       1       2       3       4

MyOutputSignal  L       L       L       L       L
```
Note that, if not explicitly set by the user, all the defined signals will start with Low (L) state.

Events that modify signals state can also be specified:
```
>> def event raise MyInputSignal0 2
>> def event raise MyInputSignal1 4
```
In this example, we are defining two events of type 'raise' for MyInputSignal0 and MyInputSignal1 at the time 2 and 4 respectively. <br/>
If we simulate again, and plot all the signals we get the following results:
```
>> print track MyInputSignal0
                0       1       2       3       4

MyInputSignal0  L       L       H       H       H
>> print track MyInputSignal1
                0       1       2       3       4

MyInputSignal1  L       L       L       L       H
>> print track MyOutputSignal
                0       1       2       3       4

MyOutputSignal  L       L       L       L       H
```

### Example with file loading

As the circuit gets bigger and complicated, using only the command line can become uncomfortable. To sove this issue, circuit definition
commands can also be written in an external .txt file and loaded at runtime with the 'load' command. <br/>
The [examples](examples) folder contains some circuit definition files that demonstrate this type of usage.
```
>> load examples/example_01/circuit.txt
```
or
```
>> load examples/example_02/circuit.txt
```
NB: The path must be relative to the application .jar file. Also, the circuit diagram is not mandatory. It's there only for demonstration purposes.

