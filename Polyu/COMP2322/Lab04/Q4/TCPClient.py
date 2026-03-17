import socket

clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serverName = '127.0.0.1'
serverPort = 41315

clientSocket.connect((serverName, serverPort))

password = input("Enter password: ")

clientSocket.send(password.encode())

message = clientSocket.recv(1024).decode()

print("From server:", message)

clientSocket.close()