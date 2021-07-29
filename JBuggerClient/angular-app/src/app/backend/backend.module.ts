import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackendService } from './backend.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [BackendService]
})
export class BackendModule { }
