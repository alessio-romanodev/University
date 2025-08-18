import socket

HEADER = 64
PORT = 5050
ENCODING = "utf-8"
SERVER = "192.168.1.53"
DISCONNECT_MSG = "!CLOSE"
ADDR = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDR)


def send(msg):
    message = msg.encode(ENCODING)
    msg_length = len(message)
    send_length = str(msg_length).encode(ENCODING)
    send_length += b" " * (HEADER - len(send_length))
    client.send(send_length)
    client.send(message)


while True:
    send(input("Inserisci il messaggio da inviare al server: "))
