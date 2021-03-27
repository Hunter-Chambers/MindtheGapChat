#!/usr/bin/env python3

from threading import Thread
import socket
import os

from ClientConnection import *
from ServerConnection import *

HOST = "0.0.0.0"   # listen on public, private, and local ips
PORT = 8081        # port to listen on
BUFFER_SIZE = 4096 # amount of data to send/receive at a time

LOOP = [True]

def main():
    # create socket and allow it to reuse the same port
    # this allows us to reconnect to the same port after
    #   immediately restarting this script, otherwise an
    #   error would occur
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    queuedMessages = []
    clientRecvConnections = []
    clientSendConnections = []

    try:
        s.bind((HOST, PORT))
        s.listen(1)             # listen for one connection
    except socket.error:
        print(f"[-] Failed to bind to port {PORT}")
        sys.exit()
    # end try/except

    outputThread = Thread(target=serverConnection, args=(queuedMessages, clientRecvConnections, LOOP))
    outputThread.start()

    try:
        while (True):
            try:
                print("-"*60)
                print(f"[+] Listening as {HOST}:{PORT}")

                client_socket, address = s.accept() # receive client info

                print(f"[+] {address} is connected.")
                print()

                clientType = client_socket.recv(BUFFER_SIZE).decode()

                if (clientType[1] == "S" and not(client_socket in clientSendConnections)):
                    clientSendConnections.append(client_socket)
                    newThread = Thread(target=clientConnection, args=(client_socket, address, queuedMessages, clientSendConnections, clientType[9:], LOOP))
                    newThread.start()
                elif (clientType[1] == "R" and not(client_socket in clientRecvConnections)):
                    clientRecvConnections.append((client_socket, address))
                # end if

            except socket.error:
                #print(f"[-] Connection to {address} was lost.")
                #client_socket.close() # close client connection
                pass
            # end try/except

        '''
                client_action = client_socket.recv(BUFFER_SIZE).decode() # check if client is '<SENDING>' or '<RECEIVING>'
                filename = client_socket.recv(BUFFER_SIZE).decode()      # the file the client is sending/requesting
                filename = os.path.basename(filename)                    # parse only the filename and ignore
                                                                         #   OS specific syntax

                if (client_action == "<SENDING>"):
                    print(f"Client is sending {filename}")
                    print()

                    ####################################################
                    # TODO: check if file already exists; prompt client
                    #       for overwrite
                    ####################################################

                    with open(os.path.join("..", "copyfiles", filename), "wb") as f:
                        while (True):
                            bytes_read = client_socket.recv(BUFFER_SIZE) # read info from client

                            if (not bytes_read): # stop writing to file
                                break
                            # end if

                            f.write(bytes_read) # write information to receiving file
                        # end while
                    # end with

                    print(f"[+] {filename} was successfully received.")
                else:
                    print(f"Client is requesting {filename}")
                    print()

                    try:
                        client_socket.sendall("TRUE".encode()) # inform client that file exists

                        with open(os.path.join("..", "copyfiles", filename), "rb") as f:

                            client_socket.sendall("TRUE".encode()) # inform client that file exists

                            while (True):
                                bytes_read = f.read(BUFFER_SIZE) # read info from requested file

                                if (not bytes_read): #stop reading from file
                                    break
                                # end if

                                client_socket.sendall(bytes_read) # send info to client
                            # end while
                        # end with

                        print(f"[+] {filename} was successfully sent.")
                        print()
                        print(f"Check {address} to see if it was 100% sent.")
                    except FileNotFoundError:
                        client_socket.sendall("FALSE".encode()) # inform client that file does not exist

                        print(f"Client requested {filename} which does not exist.")
                        print()

                        ####################################################
                        # TODO: keep connection to client
                        ####################################################

                        print(f"[-] Connection to {address} was dropped.")
                        client_socket.close()
                    # end try/except
                # end if

                client_socket.close() # close client connection

        # end while
    '''
    except KeyboardInterrupt:
        LOOP[0] = False
    # end try/except

    outputThread.join()

    s.close() # close server socket

    print()
    print("Server was stopped.")
    print("-"*60)
# end main

if (__name__ == "__main__"):
    main()
# end if
