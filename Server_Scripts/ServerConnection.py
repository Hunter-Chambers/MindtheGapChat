#!/usr/bin/env python3

import time
import socket

BUFFER_SIZE = 4096 # amount of data to send/receive at a time

def serverConnection(queuedMessages, clientConnections, loop):
    while (loop[0]):
        time.sleep(1)
        copyOfMessages = queuedMessages.copy()
        for msg in copyOfMessages:
            copyOfConnections = clientConnections.copy()
            for connection in copyOfConnections:
                if (connection[1][0] != msg[2][0]):
                    try:
                        connection[0].send((msg[1] + ":" + msg[0] + "\n").encode())
                    except socket.error:
                        clientConnections.remove(connection)
                    # end try/except
                # end if
            # end for

            queuedMessages.remove(msg)
        # end for
    # end while

    copyOfConnections = clientConnections.copy()
    for connection in copyOfConnections:
        clientConnections.remove(connection)
        connection.close()
    # end for
# end serverConnection
