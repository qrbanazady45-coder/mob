package com.example.smsbackup
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(context)
                for (sms in messages) {
                    db.smsDao().insert(SmsEntity(sender = sms.originatingAddress ?: "Unknown", receiver = "MyDevice", messageBody = EncryptionHelper.encrypt(sms.messageBody), timestamp = System.currentTimeMillis()))
                }
            }
        }
    }
}
