import json

import pika


class Sender():
    def __init__(self):
        self.queue = 'datagen'

    def conninit(self):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672))
        self.channel = self.connection.channel()

        self.channel.queue_declare(queue=self.queue)

    def connclose(self):
        self.connection.close()

    def publish(self, msg):
        msg = json.dumps(msg)
        self.channel.basic_publish(exchange='', routing_key=self.queue, body=msg)
        print('sent {}'.format(msg))