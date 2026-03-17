import socket

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print("socket successfully created")

serverPort = 12345

serverSocket.bind(('', serverPort))
print("socket binded to", serverPort)

serverSocket.listen(5)
print("socket is listening")

while True:

    connectionSocket, addr = serverSocket.accept()
    print("got connection from", addr)

    sentence = "thank you for connecting"
    connectionSocket.send(sentence.encode())

    connectionSocket.close()
    break