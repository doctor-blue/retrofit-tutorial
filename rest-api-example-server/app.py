import concurrent.futures
import json
import socket

import web

from error_handler import ErrorHandler
from note_model import NoteModel
from response_handler import ResponseHandler

routes = (
    '/note/?', 'Note',
    '/note/(\d+)/?', 'NoteSearch',
    '/upload', 'Upload'

)
err_handler = ErrorHandler()
res_handler = ResponseHandler()

notes = []

for i in range(97):
    notes.append(NoteModel(i, f"Note {i}", f'Description {i}'))

notes_json = []

# get Ip Address
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

s.connect(('8.8.8.8', 80))

ip_address = s.getsockname()[0] + ':8080'

s.close()
print("ip address = ", ip_address)


class Note:
    def __init__(self):
        pass

    def GET(self, page=5, per_page=20):

        end_position = page*per_page
        start_position = end_position - per_page

        if(end_position > len(notes)):
            end_position -= (end_position - len(notes))

        try:
            notes_json = []
            for note in notes[start_position:end_position]:
                notes_json.append(note.to_json(ip_address))
            return res_handler.get_with_results(notes_json)
        except Exception as err:
            return err_handler.handle_server_error(err)

    def POST(self):
        note_obj = NoteModel(len(notes) + 1)
        note_obj.set_data(json.loads(web.webapi.data()))
        notes.append(note_obj)
        notes_json.append(note_obj.to_json(ip_address))
        return res_handler.created_with_results(note_obj.to_json(ip_address))


class NoteSearch:
    def __init__(self):
        pass

    def find_by_id(self, note_id):
        found = None
        for therm in notes:
            if therm.id == int(note_id):
                found = therm
                break
        return found

    def GET(self, note_id):
        try:
            note = self.find_by_id(note_id)
            if note is None:
                return err_handler.handle_not_found_error('Note not found with the provided ID')
            return res_handler.get_with_results(note.to_json(ip_address))
        except ValueError as err:
            return err_handler.handle_input_error(err)
        except Exception as err:
            return err_handler.handle_server_error(err)

    def PUT(self, note_id):
        try:
            note = self.find_by_id(note_id)
            if note is None:
                return err_handler.handle_not_found_error('Note not found with the provided ID')
            note.set_data(json.loads(web.webapi.data()))
            return res_handler.updated_with_results(note.to_json(ip_address))
        except ValueError as err:
            return err_handler.handle_input_error(err)
        except Exception as err:
            return err_handler.handle_server_error(err)

    def DELETE(self, note_id):
        try:
            note = self.find_by_id(note_id)
            if note is None:
                return err_handler.handle_not_found_error('Note not found with the provided ID')
            idx1 = notes.index(note)
            del notes[idx1], note
            return res_handler.deleted_with_results(note_id)
        except ValueError as err:
            return err_handler.handle_input_error(err)
        except Exception as err:
            return err_handler.handle_server_error(err)


class Upload:
    def __init__(self):
        pass

    def GET(self):
        web.header("Content-Type", "text/html; charset=utf-8")
        return """<html><head></head><body>
           <form method="POST" enctype="multipart/form-data" action="">
           <input type="file" name="picture" />
           <input type="text" name="title" />
           <input type="text" name="description" />
           <br/>
           <input type="submit" />
           </form>
           </body></html>"""

    def save_file(self, x):
        filedir = './static'
        if 'picture' in x:
            filepath = x.picture.filename.replace('\\', '/')
            filename = filepath.split('/')[-1]
            fout = open(filedir + '/' + filename, 'wb')
            fout.write(x.picture.file.read())
            fout.close()
            path = '/static/' + filename
            return path

    def POST(self):
        x = web.input(picture={})
        with concurrent.futures.ThreadPoolExecutor() as executor:
            future = executor.submit(self.save_file, x)
            return res_handler.created_with_results({
                "imagePath": future.result()
            })


if __name__ == "__main__":
    app = web.application(routes, globals())
    app.run()
