import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.model.ThumbnailModel

fun getCharacterModelStub() : List<CharacterModel> {
    return listOf(
        CharacterModel(
            id = "1",
            name="Teste",
            description = "description",
            thumbnail = ThumbnailModel(
                path = "path",
                extension = "jpg"
            )
    ))
}