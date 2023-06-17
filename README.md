# SimulatoreCircuitiLogici

Simple logic circuit simulator, written in Java, as a final project for a Software Engineering course.

## Usage
The software uses a command line interface. In order to define and simulate a circuit, you have to write
a sequence of commands, each of which is fully documented. The documentation can be viewed
by using the 'help' command.

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

As an alternative, a .txt file containing the sequence of commands can also be specified, 
by using the 'load' command. <br/>
The [examples](examples) folder contains some circuit definition files
that can be loaded as quick start:
```
>> load examples/example_01/circuit.txt
```
or
```
>> load examples/example_02/circuit.txt
```
NB: The path must be relative to the application .jar file. Also, the circuit diagram is not mandatory. It's there only for demonstration purposes.

