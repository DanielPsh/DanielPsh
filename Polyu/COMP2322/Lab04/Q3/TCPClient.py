import socket

clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serverName = '127.0.0.1'
serverPort = 41315

clientSocket.connect((serverName, serverPort))

sentence = clientSocket.recv(1024).decode()
print("From server:", sentence)

print("Client socket:", clientSocket.getsockname())
print("Server socket:", clientSocket.getpeername())

clientSocket.close()