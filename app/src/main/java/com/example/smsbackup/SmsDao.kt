package com.example.smsbackup
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sms_table")
data class SmsEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0, val sender: String, val receiver: String, val messageBody: String, val timestamp: Long)
