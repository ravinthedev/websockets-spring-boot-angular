import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Observable } from 'rxjs';
import { Message } from './message.model';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Stomp.Client;
  private messages: Observable<Message[]>;

  constructor() {
    const sockJs = new SockJS('http://localhost:8080/sba-websocket');
    this.stompClient = Stomp.over(sockJs);
    this.messages = new Observable<Message[]>(observer => {
      this.stompClient.connect({}, () => {
        this.stompClient.subscribe('/topic/messages', (message) => {
          observer.next(JSON.parse(message.body));
        });
      });
    });
  }

  sendMessage(message: string) {
    this.stompClient.send('/app/chat', {}, JSON.stringify({ content: message }));
  }

  getMessages(): Observable<Message[]> {
    return this.messages;
  }
}
