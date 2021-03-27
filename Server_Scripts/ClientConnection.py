#!/usr/bin/env python3

import socket

BUFFER_SIZE = 4096 # amount of data to send/receive at a time

def clientConnection(client_socket, address, queuedMessages, clientConnections, username, loop):
    while (loop[0]):
        msg = client_socket.recv(BUFFER_SIZE)

        if (len(msg) == 0):
            break
        # end

        msg = msg.decode()
        queuedMessages.append((msg, username, address))
    # end while

    clientConnections.remove(client_socket)
    client_socket.close()

    return
# end clientConnection
