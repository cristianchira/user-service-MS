package com.codedecode.userinfo.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
 private String message;
 private int status;
 private LocalDateTime timestamp;

 public ErrorResponse() {
  this.timestamp = LocalDateTime.now();
 }

 public LocalDateTime getTimestamp() {
  return timestamp;
 }

 public void setTimestamp(LocalDateTime timestamp) {
  this.timestamp = timestamp;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 public int getStatus() {
  return status;
 }

 public void setStatus(int status) {
  this.status = status;
 }
}
