import axios from 'axios';

let url = 'http://localhost:8080/books/'

const addNewBook = async (name, author, year, photo, genreId, genreName) => {
    try{
        const res = await axios.post(url, {
            name: name,
            author: author,
            publicationYear: year,
            photo: photo,
            fkGenre:{
                id: genreId,
                description: genreName
            }
        })
        return res.data
    }catch(error){
        throw error
    }
}

export default addNewBook