import socket

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serverPort = 41315
serverSocket.bind(('', serverPort))

serverSocket.listen(5)

print("Server is listening on port", serverPort)

while True:

    connectionSocket, addr = serverSocket.accept()
    print("Connection from:", addr)

    sentence = "thank you for connecting"
    connectionSocket.send(sentence.encode())

    connectionSocket.close()
    break