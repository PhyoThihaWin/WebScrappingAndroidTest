package com.novalabs.webscrappingtest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.executeAsyncTask(onPreExecute = {
            // ... runs in Main Thread
        }, doInBackground = {
            val doc: Document = Jsoup.connect("https://www.livecoinwatch.com/").get()
            Log.i("test_jsoup", doc.title())
            val tableRow: Elements = doc.select(".table-row")
            for (headline in tableRow) {
//                    log(
//                        "%s\n\t%s",
//                        headline.attr("title"), headline.absUrl("href")
//                    )

            }
            return@executeAsyncTask tableRow[1].text()
        }, onPostExecute = {
            // runs in Main Thread
            findViewById<TextView>(R.id.tvTest).text = it
        })

    }
}

fun <R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit
) = launch {
    onPreExecute() // runs in Main Thread
    val result = withContext(Dispatchers.IO) {
        doInBackground() // runs in background thread without blocking the Main Thread
    }
    onPostExecute(result) // runs in Main Thread
}