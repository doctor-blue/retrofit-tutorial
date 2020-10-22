class NoteModel:
    def __init__(self, note_id=0, title="", description="", img_path="/static/avatar.png"):
        self.title = title
        self.description = description
        self.id = note_id
        self.img_path = img_path

    def set_data(self, data):
        self.title = data['title']
        self.description = data['description']
        if data['imgPath'] is not None:
            self.img_path = data['imgPath']
        return self

    def to_json(self, ip_address):
        data = {
            'id': self.id,
            'title': self.title,
            'description': self.description,
            'imgPath': "http://" + ip_address + self.img_path
        }
        return data


def createNoteModel(note_id, data, img_path):
    return NoteModel(note_id, data['title'], data['description'], data['imgPath'])
