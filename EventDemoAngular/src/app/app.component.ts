import {Component, OnInit} from '@angular/core';
var playlist = [
    {artist:"Herbie Hancock", title:"Thrust"},
    {artist:"Lalo Schifrin", title:"Shifting Gears"},
    {artist:"Faze-O", title:"Riding High"}
];
function swapElement(array, indexA, indexB) {
    var tmp = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = tmp;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
    title = 'EventDemoAngular';

    ngOnInit() {

        // swapElement(playlist, 1, 2);
        console.log(playlist.reverse());

    }
}
