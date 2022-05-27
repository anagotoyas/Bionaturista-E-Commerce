import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Compuesto } from '../shared/compuesto.model';
import { CompuestoService } from '../shared/compuesto.service';


@Component({
  selector: 'app-crear-compuesto',
  templateUrl: './crear-compuesto.component.html',
  styleUrls: ['./crear-compuesto.component.css']
})
export class CrearCompuestoComponent implements OnInit {

  compuestoForm: FormGroup;

  @Input() compuesto: Compuesto = new Compuesto();
  @Output() onSubmit: EventEmitter<any> = new EventEmitter();

  constructor(public compuestoService: CompuestoService, private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<CrearCompuestoComponent>,
    ) { }

  ngOnInit(): void {
    this.compuestoForm = this.formBuilder.group({
      nombreCompuesto: [
        this.compuesto.nombreCompuesto, 
        [
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.required
        ]]
    })
  }

  crearCompuesto() {
    this.compuestoService.crearCompuesto(this.compuestoForm.value).subscribe({
      next: () => {
        this.compuestoForm.reset();
        this.dialogRef.close('save');
      },
      error: ()=> {
       
        
      }
    })
  }

}
