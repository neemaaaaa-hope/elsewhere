package com.example.elsewhere.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClientInstance {

    val client = createSupabaseClient(
        supabaseUrl = "https://bswtvcudlkbudaoneebd.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJzd3R2Y3VkbGtidWRhb25lZWJkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc5NjY5MjYsImV4cCI6MjA5MzU0MjkyNn0.D7oqhYikd4KTTtPiCWbgpNeHEEUjWIqLfR0DHfxgIfE"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}