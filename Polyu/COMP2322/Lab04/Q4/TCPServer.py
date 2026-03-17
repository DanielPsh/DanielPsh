import socket

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serverPort = 41315
serverSocket.bind(('', serverPort))

serverSocket.listen(5)

correct_password = "1315"

print("Server is listening")

while True:

    connectionSocket, addr = serverSocket.accept()
    print("Connection from:", addr)

    password = connectionSocket.recv(1024).decode()

    if password == correct_password:
        message = "Your password is correct!"
    else:
        message = "Your password is incorrect!"

    connectionSocket.send(message.encode())

    connectionSocket.close()
    break