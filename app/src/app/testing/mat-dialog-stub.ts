import { Injectable } from '@angular/core';

@Injectable()
export class MatDialogStub {
  open() {
    return {
      close: () => {},
      afterClosed: () => {},
    };
  }
}
