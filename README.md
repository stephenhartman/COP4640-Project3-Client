# COP4640 - Project 3

This is the 3rd project for Operating Systems Environments.

## Group Members:

- Troy Bogle
- Greg Cunningham
- Bishaui Dong
- Stephen Hartman
- Seth Johnson

## Description:

### Purpose

This project allows students to implement a single- and multi-threaded client-server application to examine, analyze, and study the effects single and multiple threads have on the average turn-around time of a set of processes.

### Instructions

#### Note: Refer to the Client-Server Application Example section for help with creating a client-server application.

1. Create single- and multi-thread server programs that listen for client requests on a specified network port
1. The single-threaded server program should handle one client request at a time (serially)
1. The multi-threaded server program should handle multiple client requests at a time (concurrently)
1. Create a multi-threaded client program that transmits requests to a server on a specified network port
1. The client program should handle multiple clients at a time
1. Regardless if transmitting to the single- or multi-threaded server
1. The client network port MUST be the same as the server network port
1. The client should collect the following data:
    - Turn-around Time (elapsed time) of each client request to travel to the server, be processed, and return
    - Total Turn-around Time required for all client requests to travel to a server, be processed, and return
    - Average Turn-around Time (divide Total Turn-around Time by number of clients)

### Data Collection

#### Part 1

1. Run the client program against the single- and multi-threaded servers using an increasing number of clients

    `1, 2, 3, 4, 5, 10, 25, 50, 100 and 500`

1. Record the collected data in an Excel spreadsheet
    1. Total Turn-around Time
    1. Average Turn-around Time
    1. Create two (2) charts to visually represent the data collected in step 2

#### Part 2

1. Modify the source code of the single- and multi-threaded servers
    1. Change the statement Thread.sleep(250); to Thread.sleep(500);
    1. Perform Part 1 again using a maximum of 50 clients
    ##### Note: Create two (2) additional charts.
1. Modify the source code of the single- and multi-threaded servers
    1. Change the statement Thread.sleep(500); to Thread.sleep(250);

#### Part 3

1. Modify the source code of the client
    1. Change the statement Thread.sleep(25); to Thread.sleep(1);
    1. Perform steps 1 and 2 of Part 1 using exactly 20 clients
        - Note: Do NOT perform step 3 of Part 1.
1. Modify the source code of the client
    1. Change the statement Thread.sleep(1); to Thread.sleep(25);

#### Data Analysis

##### Write a report (2000 words max) that answers the following questions (include the charts created in Part 1).

1. Which server configuration (single- or multi-threaded) performed the best in Part 1?
1. Explain why this server configuration performed better than the other server configuration
1. What affect, if any, did the modifications in Part 2 have on the client-server performance?
1. Explain the affect, if any, reducing the Thread.sleep value would have on the client-server performance
1. What affect, if any, did the modifications in Part 3 have on the order client requests were processed?
1. Explain what may have caused the affect, if any, to occur
