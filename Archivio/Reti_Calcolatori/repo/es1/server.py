import socket
import threading

# Lunghezza massima msg
HEADER = 64
# Porta libera qualsiasi sulla rete
PORT = 5050
# Assegna a SERVER l'indirizzo IPv4 dinamico del dispositivo
SERVER = socket.gethostbyname(socket.gethostname())
# Collego l'ip del server e la porta da utilizzare
ADDR = (SERVER, PORT)
# Metodo di decoding
FORMAT = "utf-8"
# Messaggio per disconnettersi
DISCONNECT_MSG = "!close"


# Crea un socket che accetta IPv4
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDR)


def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    connected = True
    while connected:
        msg_length = conn.recv(HEADER).decode(FORMAT)
        print(f"primo msg_length {msg_length}")
        if msg_length:
            msg_length = int(msg_length)
            print(f"secondo msg_length {msg_length}")
            msg = conn.recv(msg_length).decode(FORMAT)
            print(f"[{addr}] {msg}")
            if msg == DISCONNECT_MSG:
                connected = False

    conn.close()


def start():
    print("[STARTING] server is starting")
    server.listen()
    print(f"[LISTENING] server is listening on {ADDR}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.active_count()-1}")


start()
