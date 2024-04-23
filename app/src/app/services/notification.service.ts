import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Injectable()
export class NotificationService {
  private readonly DEFAULT_ACTION = 'Close';
  private readonly DEFAULT_TIMEOUT = 2000;
  private readonly DEFAULT_NOTIFICATION_CONFIGURATION: MatSnackBarConfig = {
    verticalPosition: 'top',
    horizontalPosition: 'center',
  };
  private readonly SUCCESS_NOTIFICATION_CONFIGURATION: MatSnackBarConfig = {
    ...this.DEFAULT_NOTIFICATION_CONFIGURATION,
    duration: this.DEFAULT_TIMEOUT,
  };

  constructor(private snackBar: MatSnackBar) {}

  success(label: string, action: string | null = null) {
    this.snackBar.open(
      label,
      action || this.DEFAULT_ACTION,
      this.SUCCESS_NOTIFICATION_CONFIGURATION,
    );
  }

  error(label: string, action: string | null = null, timeout: boolean = false) {
    this.snackBar.open(label, action || this.DEFAULT_ACTION, {
      ...this.DEFAULT_NOTIFICATION_CONFIGURATION,
      duration: timeout ? this.DEFAULT_TIMEOUT : undefined,
    });
  }
}
