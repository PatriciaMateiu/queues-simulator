# queues-simulator

This project uses a GUI to interact with the user and displays another one to display information about the queues and their settlement.
The order in which the clients go to each queue is using a certain strategy, of which line has the smallest waiting time. When the first customer has finished (the corresponding processing time is over), it is remove from the queue and the next one’s processing time starts (the next task begins).
The simulation stops when all the clients have left or when the maximum interval (or simulation time) is over.
