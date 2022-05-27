import { Component, EventEmitter, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CrearCompuestoComponent } from '../crear-compuesto/crear-compuesto.component';


@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  sideBarOpen = true;

  constructor() { }

  ngOnInit(): void {
    
  }

  

  sideBarToggler() {
    this.sideBarOpen = !this.sideBarOpen;
  }

}
