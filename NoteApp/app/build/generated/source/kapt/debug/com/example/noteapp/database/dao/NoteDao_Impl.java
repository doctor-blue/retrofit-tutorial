package com.example.noteapp.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.noteapp.data.Note;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote_1;

  private final EntityDeletionOrUpdateAdapter<Note> __deletionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __updateAdapterOfNote;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `note_table` (`title_col`,`description_col`,`note_id_col`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__insertionAdapterOfNote_1 = new EntityInsertionAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `note_table` (`title_col`,`description_col`,`note_id_col`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `note_table` WHERE `note_id_col` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `note_table` SET `title_col` = ?,`description_col` = ?,`note_id_col` = ? WHERE `note_id_col` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        stmt.bindLong(3, value.getId());
        stmt.bindLong(4, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from note_table";
        return _query;
      }
    };
  }

  @Override
  public Object insertNote(final Note note, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNote.insert(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object addAllNote(final List<Note> notes, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNote_1.insert(notes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteNote(final Note note, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNote.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateNote(final Note note, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNote.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<Note>> getAllNote() {
    final String _sql = "select * from note_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"note_table"}, false, new Callable<List<Note>>() {
      @Override
      public List<Note> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title_col");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description_col");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "note_id_col");
          final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Note _item;
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new Note(_tmpTitle,_tmpDescription);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
