import json

import pika


class Receiver():
    def __init__(self):
        self.queue = 'datagen'

    def conninit(self):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672))
        self.channel = self.connection.channel()

        self.channel.queue_declare(queue=self.queue)

    def connclose(self):
        self.connection.close()

    def recv(self):
        def callback(ch, method, properties, body):
            print('recv {}'.format(body))
        
        self.channel.basic_consume(queue=self.queue, on_message_callback=callback, auto_ack=True)

        print('waiting for messages...')
        self.channel.start_consuming()
