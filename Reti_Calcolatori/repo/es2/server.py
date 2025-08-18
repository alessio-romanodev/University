import socket
import threading

IP = socket.gethostbyname(socket.gethostname())
print(IP)
PORT = 5050
HEADER = 64
ADDR = (IP, PORT)
ENCODING = "utf-8"
DISCONNECT_MSG = "!CLOSE"

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDR)


def handle_conn(conn, addr):
    print(f"[NEW CONNECTIONS] {addr} connected")
    connected = True
    while connected:
        msg_length = conn.recv(HEADER).decode(ENCODING)
        if msg_length:
            print(connected)
            msg_length = int(msg_length)
            msg = conn.recv(msg_length).decode(ENCODING)
            print(f"[{addr}] {msg}")
            if msg == DISCONNECT_MSG:
                connected = False
                print(connected)
                conn.close()


def start():
    print("[STARTING] Server is starting")
    server.listen()
    print(f"[LISTENING] server is listening on port: {PORT}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_conn, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.active_count()-1}")


start()
