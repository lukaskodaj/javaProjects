package lukaskodaj.planner;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface NotesRepository extends CrudRepository<Notes, Long> {

    @Query("SELECT u FROM Notes u WHERE u.userId = ?1 ORDER by u.edited DESC")
    List<Notes> findNotesByUserId(int userId);

    @Query("SELECT u FROM Notes u WHERE u.userId = ?1 AND (u.type IN ?2) ORDER BY u.edited ASC")
    List<Notes> findNotesByFilterOrderAsc(int userId, String[] type1);

    @Query("SELECT u FROM Notes u WHERE u.userId = ?1 AND (u.type IN ?2) ORDER BY u.edited DESC")
    List<Notes> findNotesByFilterOrderDesc(int userId, String[] type1);

    @Query("SELECT u FROM Notes u WHERE u.id = ?1")
    Notes findNotesById(Long id);

//    @Modifying
//    @Query("update Notes u set u.title = :title, u.note = :note, u.type = :type, u.userId = :userId, u.edited = :edited where u.id = :id")
//    int updateAllNote(@Param("title") String title,
//                      @Param("note") String note,
//                      @Param("type") String type,
//                      @Param("userId") int userId,
//                      @Param("edited") Date edited,
//                      @Param("id") long id
//
//    );

//    @Modifying
//    @Query(value = "UPDATE Notes u set u.note = ? where u.id = ?", nativeQuery = true)
//    int updateAllNote(String note, long id);

//    @Modifying
//    @Query("update Notes u set u.note = :note where u.id = :id")
//    int updateAllNote(@Param("note") String note, @Param("id") long id);

}
