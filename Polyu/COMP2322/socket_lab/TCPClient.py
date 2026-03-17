import socket

clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serverName = '127.0.0.1'
serverPort = 12345

clientSocket.connect((serverName, serverPort))

sentence = clientSocket.recv(1024).decode()
print("from server:", sentence)

clientSocket.close()