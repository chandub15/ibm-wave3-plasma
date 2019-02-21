import { SpeechRecognizerService } from './../../services/speech-recognizer.service';
// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { WebSpeechComponent } from './web-speech.component';

// @NgModule({
//   declarations: [WebSpeechComponent],
//   imports: [
//     CommonModule
//   ]
// })
// export class WebSpeechModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { WebSpeechComponent } from './web-speech.component';
import { PlasmaMaterialModule } from '../../plasma.material.modules';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  declarations: [
  ],
  providers: [
     SpeechRecognizerService,
  ]
})
export class WebSpeechModule { }




