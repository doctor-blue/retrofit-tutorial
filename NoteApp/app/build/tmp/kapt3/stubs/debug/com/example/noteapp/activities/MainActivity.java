package com.example.noteapp.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\u0012\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u000f*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/noteapp/activities/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/noteapp/adapter/NoteAdapter;", "getAdapter", "()Lcom/example/noteapp/adapter/NoteAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "allNote", "Lretrofit2/Call;", "", "Lcom/example/noteapp/model/Note;", "builder", "Lretrofit2/Retrofit$Builder;", "kotlin.jvm.PlatformType", "client", "Lcom/example/noteapp/sever/NoteClient;", "noteViewModel", "Lcom/example/noteapp/viewmodel/NoteViewModel;", "getNoteViewModel", "()Lcom/example/noteapp/viewmodel/NoteViewModel;", "noteViewModel$delegate", "onItemClick", "Lkotlin/Function1;", "", "onItemDelete", "retrofit", "Lretrofit2/Retrofit;", "fetchData", "initControls", "initEvents", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy noteViewModel$delegate = null;
    private final retrofit2.Retrofit.Builder builder = null;
    private final retrofit2.Retrofit retrofit = null;
    private final com.example.noteapp.sever.NoteClient client = null;
    private retrofit2.Call<java.util.List<com.example.noteapp.model.Note>> allNote;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.jvm.functions.Function1<com.example.noteapp.model.Note, kotlin.Unit> onItemClick = null;
    private final kotlin.jvm.functions.Function1<com.example.noteapp.model.Note, kotlin.Unit> onItemDelete = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.example.noteapp.viewmodel.NoteViewModel getNoteViewModel() {
        return null;
    }
    
    private final com.example.noteapp.adapter.NoteAdapter getAdapter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initEvents() {
    }
    
    private final void initControls() {
    }
    
    private final void fetchData() {
    }
    
    public MainActivity() {
        super();
    }
}