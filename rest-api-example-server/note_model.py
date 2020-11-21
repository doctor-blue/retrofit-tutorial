class NoteModel:
    def __init__(
        self, note_id=0, title="", description=""
    ):
        self.title = title
        self.description = description
        self.id = note_id

    def set_data(self, data):
        self.title = data["title"]
        self.description = data["description"]
        return self

    def to_json(self, ip_address):
        data = {
            "id": self.id,
            "title": self.title,
            "description": self.description,
        }
        return data


def createNoteModel(note_id, data):
    return NoteModel(note_id, data["title"], data["description"])
