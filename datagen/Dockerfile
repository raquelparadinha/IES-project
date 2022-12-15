FROM python:3.8
WORKDIR /usr/src/app
COPY . .
RUN pip install -r requirements.txt

ADD ./wait-for-it.sh ./
RUN chmod u+x ./wait-for-it.sh

CMD ["python3","main.py"]