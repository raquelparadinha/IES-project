import pika
import json

class Sender():
    def __init__(self):
        self.exchange = ''
        self.routingkey = 'datagen'
        self.queue = 'datagen'

    def conninit(self):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672))
        self.channel = self.connection.channel()

        self.channel.queue_declare(queue=self.queue)

    def connclose(self):
        self.connection.close()

    def send(self, msg):
        self.channel.basic_publish(exchange=self.exchange, routing_key=self.routingkey, body=msg)
        print('sent {}'.format(msg))

if __name__ == '__main__':
    sender = Sender()
    sender.conninit()
    sender.send('hello')
    sender.connclose()