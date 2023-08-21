import { Component, OnInit } from '@angular/core';
import { WebSocketService } from './websocket.service';
import { Message } from './message.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  message: string = '';
  messages: Message[] = [];

  constructor(private websocketService: WebSocketService) {}

  ngOnInit() {
    this.websocketService.getMessages().subscribe(messages => {
      this.messages = messages;
    });
  }

  sendMessage() {
    this.websocketService.sendMessage(this.message);
    this.message = '';
  }

  get messageArray(): Message[] {
    // Convert the messages object to an array using Object.values()
    return Object.values(this.messages);
  }
}
