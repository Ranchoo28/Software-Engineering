import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appAutoResize]'
})
export class AutoResizeDirective {

  @Input() maxLength: number = 2000; // Imposta la lunghezza massima desiderata

  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event.target'])
  onInput(textArea: HTMLTextAreaElement): void {
    this.adjustTextArea(textArea);
  }

  ngAfterViewInit(): void {
    this.adjustTextArea(this.el.nativeElement);
  }

  adjustTextArea(textArea: HTMLTextAreaElement): void {
    textArea.style.overflow = 'true';
    textArea.style.height = 'auto';
    textArea.style.width = '330px'

    // Limita la lunghezza massima del testo
    if (textArea.value.length > this.maxLength) {
      textArea.value = textArea.value.substring(0, this.maxLength);
    }

    textArea.style.height = (textArea.scrollHeight) + 'px';
  }
}
